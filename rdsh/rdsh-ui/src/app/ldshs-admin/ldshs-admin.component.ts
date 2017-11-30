import { Component, OnInit, ViewChild } from "@angular/core";
import { MatTableDataSource, MatSidenav } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";
import { AppComponent } from "../app.component";

@Component({
  selector: 'app-ldshs-admin',
  templateUrl: './ldshs-admin.component.html',
  styleUrls: ['./ldshs-admin.component.css']
})
export class LdshsAdminComponent implements OnInit {
  displayedColumns = ["name", "description", "url"];
  dataSource = new MatTableDataSource<Element>(ldshList);
  static menuOpen : boolean = false;
  

  constructor(private router: Router) {
    AppComponent.selectedMenuItem = 'ldshs';
  }

  ngOnInit() {}

  navigateToEditLdsh(id: string){
    //TODO FIX
    this.router.navigate(['/ldshEdit'],{ queryParams: {"id": id } });
  }

  navigateToViewLdsh(id: string){
    this.router.navigate(['/ldshView'], { queryParams: { "id": id } });
  }
  addNewLdsh(){
    //mock new id 100;
    this.router.navigate(['/ldshEdit'],{ queryParams: {"id":100 } });

  }


  ngAfterViewInit(){
    if(!LdshsAdminComponent.menuOpen){
      document.getElementById('sitenav').click();
      LdshsAdminComponent.menuOpen = true;
    }
  }

}


export interface Element {
  name: string;
  description: string;
  id: string;
}




const ldshList: Element[] = [
  { name: 'Athens', description: "Athens data shairing hub", id: "31" },
  { name: 'Thesaloniki', description: "Thesaloniki data shairing hub", id: "32" },
  { name: "Iraklion", description: "Iraklion data shairing hub" , id: "33" },
];
