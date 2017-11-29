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

  navigateToEditQueue(id: string){
    //TODO FIX
    this.router.navigate(['/queueEdit'],{ queryParams: {"id": id } });
  }

  navigateToViewQueue(id: string){
    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }


  addQueue(){
    //mock new id 100;
  this.router.navigate(['/queueEdit'],{ queryParams: {"id":111 } });
 }

  ngAfterViewInit(){ }

}

export interface Element {
  name: string;
  uri: string;
  id: string;
}



const queueList: Element[] = [
  { name: 'AthQ', uri: "arn:aws:sns:us-ATH-1:11784:SEDataQueue ", id: "23" },
  { name: 'ThesQ', uri: "arn:aws:sns:us-THES-1:753424:SEDataQueue", id: "24" },
  { name: "HerkQ", uri: "arn:aws:sns:us-Her-1:23284:SEDataQueue " , id: "25" },
];