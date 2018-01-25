import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { FormControl, Validators, FormGroup, FormArray } from '@angular/forms';
import { ViewChild } from '@angular/core';
import { MatInput, MatRadioGroup } from '@angular/material';
import { MatCheckbox } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { DataSet } from '../submit-form/submit-form.component';



@Component({
  selector: 'app-dataset-view',
  templateUrl: './dataset-view.component.html',
  styleUrls: ['./dataset-view.component.css'],
  providers: [DataService]
})
export class DatasetViewComponent implements OnInit {

  // rdshDissEnabled = false;
  metadataForm: FormGroup;
  paramsObj: Object;
  dataSet:DataSet;


  // @ViewChild('datasetName') private text1: MatInput;
  // @ViewChild('rdshDis') private rdshCb: MatCheckbox;
  // @ViewChild('rdshOptions') private rdshOptions: MatRadioGroup;


  myscript:string = '<script type="application/ld+json"> dIMIIIIIIIIIIIIIIIIIIIIIIII</script>';
  

  viewMode: boolean = true;

  constructor(private dataService: DataService, private activatedRoute: ActivatedRoute,
    private router: Router) { 

      // <script type="application/ld+json">
      // // user defined jssonld
      //       {
      //       }{
      //       }
      //   
      // this.dataSet.jsonLd)
      const fragment = document.createRange().createContextualFragment(this.myscript) ;
      document.getElementsByTagName('head')[0] .appendChild(fragment);

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
      })
    }
  }

 rdshAdjust(srcElement: HTMLInputElement) {
    // console.log("CBval   " + this.rdshCb.checked);
  }

  setReadonly() {
  }

}

