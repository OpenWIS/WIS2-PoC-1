import {Component, ElementRef, OnInit, ViewChild} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AppComponent} from "../app.component";
import {SettingsService} from "../_services/rest/settings.service";
import {MatSnackBar} from "@angular/material";
import {SettingDTO} from "../_dto/Setting.dto";
import {Router} from "@angular/router";

@Component({
  selector: "app-settings",
  templateUrl: "./settings.component.html",
  styleUrls: ["./settings.component.css"]
})
export class SettingsComponent implements OnInit {
  settingsForm: FormGroup;

  @ViewChild("readingTpSel") private readingTpSel: ElementRef;

  constructor(private fb: FormBuilder, private settingsService: SettingsService,
              public snackBar: MatSnackBar, private router: Router) {
    AppComponent.selectedMenuItem = "settings";

    // Render an empty form until data is loaded.
    this.settingsForm = this.fb.group({
      title: ['', [Validators.required]],
      email: ['', [Validators.required]],
      copyright: ['', [Validators.required]],
      header: ['', [Validators.required]],

      jwt_secret: ['', [Validators.required]]
    });
  }

  listSettingsCallback = (response) => {
    var data = response.body;
    for (let setting of data) {
      this.settingsForm.controls[setting["settingKey"]].setValue(setting["settingVal"]);
    }
  }

  

  ngOnInit() {
    this.settingsService.list(this.listSettingsCallback, null);
  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById("sitenav").click();
    }
  }

  // We need to manually parse the value of the form here, since it can not be mapped to an object.
  onSubmit() {
    let jsonValue = JSON.parse(JSON.stringify(this.settingsForm.getRawValue()));
    var settings: SettingDTO[] = new Array<SettingDTO>();
    for (let key in jsonValue) {
      settings.push(new SettingDTO(key, jsonValue[key]));
    }
    //console.log(settings);
    this.settingsService.save(settings, null, null);
  }
}
