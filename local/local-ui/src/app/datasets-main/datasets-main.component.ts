import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';


@Component({
  selector: 'app-datasets-main',
  templateUrl: './datasets-main.component.html',
  styleUrls: ['./datasets-main.component.css']
})
export class DatasetsMainComponent implements OnInit {
  displayedColumns = ['name', 'description', 'url'];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor() { }


  ngOnInit() {
  }
  
  
}
export interface Element {
  name: string;
  description: string;
  url:string;
}


  const datasetList: Element[] = [
  {name: 'Athens rain', description:"Rain volume in Athens", url:"/submit"},
  {name: 'Thesaloniki wind', description:"Wind power in Thesaloniki", url:"/submit"},
  {name: 'Iraklio sun', description:"Sun strength in Iraklio", url:"/submit"},

];

