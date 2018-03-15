import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { FormControl, Validators, FormGroup, FormArray } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { MatInput, MatRadioGroup } from '@angular/material';
import { MatCheckbox } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { Title } from '@angular/platform-browser';
import { DataSet } from '../dto/DataSet';
import { WmoCode } from "../dto/WmoCode";


@Component({
  selector: 'app-dataset-view',
  templateUrl: './dataset-view.component.html',
  styleUrls: ['./dataset-view.component.css'],
  providers: [DataService]
})
export class DatasetViewComponent implements OnInit {

  metadataForm: FormGroup;
  paramsObj: Object;
  dataSet: DataSet;
  measurements: String[] = [];

  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute,
    private router: Router, private titleService: Title) {
  }

  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let dataset_id = this.paramsObj['params']['id'];

    this.fetchDataset(dataset_id);
  }

  private fetchDataset(id: number) {

    if (id) {
      this.dataService.getCall("getDataset/id=" + id).then(result => {
        this.dataSet = result;
        this.titleService.setTitle(this.dataSet.name);
        this.updateMeasurments(this.dataSet);
        if (!this.dataSet.imageUrl) {
          this.dataSet.imageUrl = "/assets/no_map.jpg"
        }
      })
    }
  }


  private updateMeasurments(dataSet: DataSet) {

    if (dataSet.wmoCodes) {

      //cases with 1 element handle as array!
      if (!Array.isArray(dataSet.wmoCodes)) {
        var arr: WmoCode[] = [dataSet.wmoCodes];
        dataSet.wmoCodes = arr;
      }

      for (let mesurement of dataSet.wmoCodes) {
        this.measurements.push(mesurement.name);

      }
    }
  }

}

