import { Component, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material";
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
    AppComponent.selectedMenuItem = 'ldshList';
  }

  ngOnInit() {}

  navigateToEditLdsh(id: string){
    //TODO FIX
    this.router.navigate(['/submit'],{ queryParams: {"id": id } });
  }

  navigateToViewLdsh(id: string){
    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }
  addNewLdsh(){
    //mock new id 100;
    this.router.navigate(['/submit'],{ queryParams: {"id":100 } });

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
  { name: 'Athens', description: "Athens data shairing hub", id: "10" },
  { name: 'Thesaloniki', description: "Thesaloniki data shairing hub", id: "30" },
  { name: "Iraklion", description: "Iraklion data shairing hub" , id: "12" },
];