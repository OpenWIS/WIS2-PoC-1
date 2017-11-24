import { Component, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";

@Component({
  selector: "app-datasets-admin",
  templateUrl: "./datasets-admin.component.html",
  styleUrls: ["./datasets-admin.component.css"]
})
export class DatasetsAdminComponent implements OnInit {
  displayedColumns = ["name", "description", "url"];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor(private router: Router) {}

  ngOnInit() {}

  navigateToEditDataset(id: string){
    //this.router.navigateByUrl('/datasets');
    this.router.navigate(['/submit'],{ queryParams: {"id": id } });
  }

  navigateToViewDataset(id: string){
    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }
  addNewDataset(){
    //mock new id 100;
    this.router.navigate(['/submit'],{ queryParams: {"id":100 } });

  }


}
export interface Element {
  name: string;
  description: string;
  id: string;
}


const datasetList: Element[] = [
  { name: 'Athens rain', description: "Temperature/Rainfall in Athens", id: "10" },
  { name: 'Thesaloniki wind', description: "Temperature/Rainfall in Thesaloniki", id: "30" },
  { name: "Iraklion", description: "Wind speed/direction in Iraklion" , id: "12" },
];