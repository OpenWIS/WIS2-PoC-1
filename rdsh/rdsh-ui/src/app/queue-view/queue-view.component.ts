import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-queue-view',
  templateUrl: './queue-view.component.html',
  styleUrls: ['./queue-view.component.css']
})
export class QueueViewComponent implements OnInit {

  paramsObj: Object;

  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }


  queue = {
    name: "",
    uri: "",
    id: "",
  };


  queueList = [
    { name: 'AthQ', uri: "arn:aws:sns:us-ATH-1:11784:SEDataQueue ", id: "23" },
    { name: 'ThesQ', uri: "arn:aws:sns:us-THES-1:753424:SEDataQueue", id: "24" },
    { name: "HerkQ", uri: "arn:aws:sns:us-Her-1:23284:SEDataQueue ", id: "25" },
  ];

  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj['params']['id'];
    let queue = this.queueList.find(i => i.id === id);
    this.queue = queue;

  }
}




