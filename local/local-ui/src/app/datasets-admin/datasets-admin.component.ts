import { Component, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";
import { AppComponent } from "../app.component";

@Component({
  selector: "app-datasets-admin",
  templateUrl: "./datasets-admin.component.html",
  styleUrls: ["./datasets-admin.component.css"]
})
export class DatasetsAdminComponent implements OnInit {
  displayedColumns = ["name", "description", "url"];
  dataSource = new MatTableDataSource<Element>(datasetList);
  static menuOpen : boolean = false;

  constructor(private router: Router) {
    AppComponent.selectedMenuItem = 'datasets';
  }

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

  ngAfterViewInit(){
    if(!DatasetsAdminComponent.menuOpen){
      document.getElementById('sitenav').click();
      DatasetsAdminComponent.menuOpen = true;
    }
    
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