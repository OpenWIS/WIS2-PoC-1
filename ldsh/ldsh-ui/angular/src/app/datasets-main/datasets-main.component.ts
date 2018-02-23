import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { DataSet } from '../dto/DataSet';

@Component({
  selector: 'app-datasets-main',
  templateUrl: './datasets-main.component.html',
  styleUrls: ['./datasets-main.component.css'],
  providers: [DataService]

})
export class DatasetsMainComponent implements OnInit {
  displayedColumns = ['name', 'description', 'country', 'region'];
  dataSource = new MatTableDataSource<DataSet>(datasetList);

  homePageText :String = "Not defined";
  urlPrefix :String = "/view?id=";


  constructor(private dataService: DataService, private router: Router) { }

  ngOnInit() {
    this.loadProperties();
    this.loadDatasets();
  }

  gotoPage(id: string) {

    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }

  loadDatasets() {
    this.dataService.getCall("getAllDatasets").then(result => {
      console.log(result);
      this.loadDatasetsTable(result);
    })
  }

  loadProperties(){
    this.dataService.getCall("getSettings").then(result => {
      this.homePageText = result.homeTxt;
    })
  }


  private loadDatasetsTable(datasets: DataSet[]) {

    if (datasets instanceof Array) {
      datasetList = (datasets).slice();
    } else {
      datasetList = [];
      if (datasets != undefined) {
        datasetList.push(datasets);
      }
    }
    this.dataSource = new MatTableDataSource<DataSet>(datasetList);
  }
}

var datasetList: DataSet[] = [];


