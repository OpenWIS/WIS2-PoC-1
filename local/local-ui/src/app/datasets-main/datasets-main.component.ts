import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-datasets-main',
  templateUrl: './datasets-main.component.html',
  styleUrls: ['./datasets-main.component.css']
})
export class DatasetsMainComponent implements OnInit {
  displayedColumns = ['name', 'description', 'url'];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor(private router:Router) { }

  
  ngOnInit() {
  }

  gotoPge(id:string){
    
    console.log("TO ID "+ id);
    this.router.navigate(['/submit'],
     { queryParams: {"id": id } });
     console.log("ok");
     
    }
    
 
}
export interface Element {
  name: string;
  description: string;
  url:string;
  id:string;
}


  const datasetList: Element[] = [
  {name: 'Athens rain', description:"Rain volume in Athens", url:"submit", id:"10"},
  {name: 'Thesaloniki wind', description:"Wind power in Thesaloniki", url:"/submit", id:"30"},
  {name: 'Iraklio sun', description:"Sun strength in Iraklio", url:"/submit", id:"12"},

];


