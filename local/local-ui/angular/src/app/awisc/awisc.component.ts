import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";
import { AppComponent } from "../app.component";

@Component({
  selector: "app-awisc",
  templateUrl: "./awisc.component.html",
  styleUrls: ["./awisc.component.css"]
})
export class AWISCComponent implements OnInit {
  metadataForm: FormGroup;

  @ViewChild("readingTpSel") private readingTpSel: ElementRef;

 

  constructor() {
    AppComponent.selectedMenuItem = 'awisc';
  }

  ngOnInit() {
    this.metadataForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      url: new FormControl('', [Validators.required]),
      token: new FormControl('', [Validators.required]),
    });
  }
  onSubmit() {
    console.log("submit");
  }

  getErrorMessage() {
    return this.metadataForm.hasError("required")
      ? "You must enter a value"
      : this.metadataForm.hasError("email")
        ? "Not a valid email"
        : "not valid Mail";
  }

  ngAfterViewInit(){
    if(!AppComponent.menuOpen){
      document.getElementById('sitenav').click();
    }    
  }
}
