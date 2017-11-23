import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';


@Component({
  selector: 'app-datasets-admin',
  templateUrl: './datasets-admin.component.html',
  styleUrls: ['./datasets-admin.component.css']
})
export class DatasetsAdminComponent implements OnInit {
  displayedColumns = ['name', 'description', 'url'];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor() { }


  ngOnInit() {
  }
  
  

}
export interface Element {
  name: string;
  description: string;
}


  const datasetList: Element[] = [
  {name: 'Athens', description:"Temperature/Rainfall in Athens"},
  {name: 'Thesaloniki', description:"Temperature/Rainfall in Thesaloniki"},
  {name: 'Iraklion', description:"Wind speed/direction in Iraklion"},

];

