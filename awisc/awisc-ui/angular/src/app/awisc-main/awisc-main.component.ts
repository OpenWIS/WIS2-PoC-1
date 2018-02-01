import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SettingsService } from "../_services/rest/settings.service";

@Component({
  selector: 'app-awisc-main',
  templateUrl: './awisc-main.component.html',
  styleUrls: ['./awisc-main.component.css']
})
export class AwiscMainComponent implements OnInit {

  @ViewChild("introHeader") private introHeader: ElementRef;
  searchResults: boolean = false;
  
  searchForm: FormGroup;

  constructor(private router: Router, private settingsService: SettingsService) {  }

  onSubmit(){
    
  }

  getSettingValue(data, settingKey) {
    for (let setting of data) {
      if (setting.settingKey === settingKey) {
        return setting.settingVal;
      }
    }
  }

  listSettingsCallback = response => {
    var data = JSON.parse(response["_body"]);
    this.introHeader.nativeElement.innerHTML = this.getSettingValue(data, "header");
  };


  ngOnInit() {
    this.searchForm = new FormGroup({
      datasetSearch: new FormControl("")
    });

    this.settingsService.list(this.listSettingsCallback, null);
    
  }

  ngAfterViewInit(){
    if(!AppComponent.menuOpen){
      document.getElementById('sitenav').click();
    }    
  }


  navigateToMenu(){
    this.router.navigateByUrl('/awiscSearch');
  }

  search() {
    // call search service 
    //...
    this.showSearchResults();
  }


  showSearchResults() {
    this.searchResults = true;
  }

}
