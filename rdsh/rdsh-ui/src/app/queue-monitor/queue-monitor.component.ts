import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";


@Component({
  selector: 'app-queue-monitor',
  templateUrl: './queue-monitor.component.html',
  styleUrls: ['./queue-monitor.component.css']
})
export class QueueMonitorComponent implements OnInit {
    displayedColumns = [ "name" , "openConnections","closedConnections", "messagesSent", "bytesSent" , "failedConnections", "url", "purge"];

  dataSource = new MatTableDataSource<Element>(queueList);
  
  constructor(private router: Router) {}
    
  ngOnInit() {
    // console.log(this.dataSource);
  }


  gotoQueue(id: string){
    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }

  purge(id: string){}

}



export interface Element {
  name: string;
  // check mqtt statistics
  openConnections: string;
  closedConnections: string;
  messagesSent: string;
  bytesSent: string;
  failedConnections: string;
  id: string;
}



const queueList: Element[] = [
  { name: 'AthQ', openConnections: "21", closedConnections: "3", messagesSent: "32143", bytesSent: "54326", failedConnections: "3", id: "23" },
  { name: 'ThesQ', openConnections: "26", closedConnections: "5", messagesSent: "7411", bytesSent: "423121", failedConnections: "6", id: "24" },
  { name: "HerkQ", openConnections: "32", closedConnections: "2", messagesSent: "527", bytesSent: "4366", failedConnections: "7", id: "25" },
];