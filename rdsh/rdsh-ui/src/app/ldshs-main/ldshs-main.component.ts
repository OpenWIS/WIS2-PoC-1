import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-ldshs-main',
  templateUrl: './ldshs-main.component.html',
  styleUrls: ['./ldshs-main.component.css']
})
export class LdshsMainComponent implements OnInit {
  displayedColumns = ['name', 'description', 'url'];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor(private router: Router) { }


  ngOnInit() {
  }

  navigateToViewLdsh(id: string){
    this.router.navigate(['/ldshView'], { queryParams: { "id": id } });
  }


}
export interface Element {
  name: string;
  description: string;
  id: string;
}


const datasetList: Element[] = [
  { name: 'Athens', description: "Athens ldsh", id: "31" },
  { name: 'Thesaloniki', description: "Thesaloniki ldsh", id: "32" },
  { name: "Iraklion", description: "Iraklion ldsh" , id: "33" },
];

