import { Component, OnInit } from "@angular/core";
import { MatTableDataSource } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";
import { AppComponent } from "../app.component";

@Component({
  selector: 'app-queue-admin',
  templateUrl: './queue-admin.component.html',
  styleUrls: ['./queue-admin.component.css']
})
export class QueueAdminComponent implements OnInit {
  displayedColumns = ["name", "uri", "url"];
  dataSource = new MatTableDataSource<Element>(queueList);
  static menuOpen : boolean = false;

  constructor(private router: Router) {
    AppComponent.selectedMenuItem = 'queues';
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

  ngAfterViewInit(){ }

}

export interface Element {
  name: string;
  uri: string;
  id: string;
}



const queueList: Element[] = [
  { name: 'AthQ', uri: "/arn:aws:sns:us-ATH-1:11784:SEDataQueue ", id: "3" },
  { name: 'ThesQ', uri: "/arn:aws:sns:us-THES-1:753424:SEDataQueue", id: "4" },
  { name: "IrakQ", uri: "/arn:aws:sns:us-IRR-1:23284:SEDataQueue " , id: "5" },
];