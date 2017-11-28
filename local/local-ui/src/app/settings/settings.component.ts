import { Component, OnInit, ElementRef } from "@angular/core";
import { FormControl, Validators, FormGroup } from "@angular/forms";
import { ViewChild } from "@angular/core";

@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
  styleUrls: ["./settings.component.css"]
})
export class SettingsComponent implements OnInit {
  metadataForm: FormGroup;

  @ViewChild("readingTpSel") private readingTpSel: ElementRef;

  constructor() {}

  ngOnInit() {
    this.metadataForm = new FormGroup({
      title: new FormControl("", [Validators.required]),
      systemId: new FormControl("", [Validators.required]),
      email: new FormControl("", [Validators.required]),
      copyright: new FormControl("", [])
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
}
