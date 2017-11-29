import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-queue-edit',
  templateUrl: './queue-edit.component.html',
  styleUrls: ['./queue-edit.component.css']
})
export class QueueEditComponent implements OnInit {
  metadataForm: FormGroup;
  paramsObj: Object;

  queue = {
    name: "",
    uri: "",
  };


  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj["params"]["id"];
    let queue = queueList.find(i => i.id === id);


    if (queue == null) {

      this.metadataForm = new FormGroup({
        name: new FormControl("", [Validators.required]),
        uri: new FormControl("", [Validators.required]),
      });
    } else {

      this.metadataForm = new FormGroup({
        name: new FormControl(queue.name, [Validators.required]),
        uri: new FormControl(queue.uri, [Validators.required]),

      });

    }
  }

  onSubmit() {
    console.log("submit");
  }

}


export interface Element {
  name: string;
  uri: string;
  id: string;
}


const queueList: Element[] = [
  { name: 'AthQ', uri: "arn:aws:sns:us-ATH-1:11784:SEDataQueue ", id: "23" },
  { name: 'ThesQ', uri: "arn:aws:sns:us-THES-1:753424:SEDataQueue", id: "24" },
  { name: "IrakQ", uri: "arn:aws:sns:us-IRR-1:23284:SEDataQueue ", id: "25" },
];
