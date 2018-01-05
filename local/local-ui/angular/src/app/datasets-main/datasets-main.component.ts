import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-datasets-main',
  templateUrl: './datasets-main.component.html',
  styleUrls: ['./datasets-main.component.css']
})
export class DatasetsMainComponent implements OnInit {
  displayedColumns = ['name', 'description', 'url'];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor(private router: Router) { }


  ngOnInit() {
  }

  gotoPage(id: string) {

    // console.log("TO ID "+ id);
    this.router.navigate(['/view'], { queryParams: { "id": id } });
    //  this.router.navigate(['/submit'],{ queryParams: {"id": id } });
  }


}
export interface Element {
  name: string;
  description: string;
  id: string;
}


const datasetList: Element[] = [
  { name: 'Athens', description: "Temperature/Rainfall in Athens", id: "10" },
  { name: 'Thesaloniki', description: "Temperature/Rainfall in Thesaloniki", id: "30" },
  { name: "Iraklion", description: "Wind speed/direction in Iraklion" , id: "12" },
];


