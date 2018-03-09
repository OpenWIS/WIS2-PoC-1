import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
import "rxjs/add/operator/startWith";
import "rxjs/add/operator/map";
import { ENTER, COMMA } from "@angular/cdk/keycodes";
import { MatChipInputEvent } from '@angular/material'
import { SearchQuery } from "../_dto/SearchQuery.dto";
import { SearchResults } from "../_dto/SearchResults.dto";
import { SettingsService } from "../_services/rest/settings.service";
import { SearchService } from "../_services/rest/search.service";
import { SearchResultsComponent } from "../search-results/search-results.component";
import { WmoCodesService } from "../_services/rest/wmo-codes.service";



@Component({
  selector: 'app-awisc-search',
  templateUrl: './awisc-search.component.html',
  styleUrls: ['./awisc-search.component.css']
})


export class AwiscSearchComponent implements OnInit {
  panelOpenState: boolean = false;
  searchResults: SearchResults;
  filteredStates: Observable<any[]>;
  removable: boolean = true;
  selectable: boolean = true;
  codeSelected: any;
  addOnBlur: any;
  displayFn: any;
  separatorKeysCodes: any;
  @ViewChild("introHeader") private introHeader: ElementRef;
  wmoCodes: any = [];
  selectedCodes: any = [];

  stateCtrl: FormControl;
  searchForm: FormGroup;

  constructor(private router: Router, private settingsService: SettingsService, private searchService: SearchService, private wmoCodesService: WmoCodesService) { }

  getSettingValue(data, settingKey) {
    for (let setting of data) {
      if (setting.settingKey === settingKey) {
        return setting.settingVal;
      }
    }
  }

  listSettingsCallback = response => {
    var data = response.body;
    this.introHeader.nativeElement.innerHTML = this.getSettingValue(data, "header");
  };

  listWmoCodesCallback = response => {
    this.wmoCodes = response.body;
    this.refreshAutoCompleteList();
  };

  ngOnInit() {
    this.stateCtrl = new FormControl();
    this.searchForm = new FormGroup({
      searchText: new FormControl(""),
    });
    this.settingsService.list(this.listSettingsCallback, null);
    this.wmoCodesService.list(this.listWmoCodesCallback, null);
  }

  filterStates(name: string) {
    let out = this.wmoCodes.filter(
      wmoCode =>
        wmoCode.name.toLowerCase().indexOf(name.toLowerCase()) === 0
    );
    return out;
  }

  deSelect(code: any): void {
    this.removeFromArray(this.selectedCodes, code);
    //Add to unselected
    this.addToArray(this.wmoCodes, code);
    this.refreshAutoCompleteList();
  }

  private addToArray(array: any[], item: any) {
    array.push(item);
  }

  private removeFromArray(array: any[], item: any) {
    let index = array.indexOf(item);
    if (index >= 0) {
      array.splice(index, 1);
    }
  }

  private predicateBy(prop) {
    return function (a, b) {
      if (a[prop] > b[prop]) {
        return 1;
      } else if (a[prop] < b[prop]) {
        return -1;
      }
      return 0;
    }
  }


  private refreshAutoCompleteList() {
    this.wmoCodes.sort(this.predicateBy("name"));
    this.filteredStates = this.stateCtrl.valueChanges
      .startWith(null)
      .map(
        wmoCode =>
          wmoCode
            ? this.filterStates(wmoCode)
            : this.wmoCodes.slice()
      );
  }

  addSearchCriteria() {
    if (this.codeSelected != "") {
      let code = this.wmoCodes.find(i => i.code === this.codeSelected);
      var index = this.wmoCodes.indexOf(code, 0);
      if (index > -1) {
        this.wmoCodes.splice(index, 1);
        this.selectedCodes.push(code);
        this.codeSelected = "";
      }
    }
    this.stateCtrl.setValue("");
  }

  setSelectedCode($event, wmocode) {
    this.codeSelected = wmocode;
  }

  search() {
    var sq: SearchQuery = new SearchQuery();
    sq.searchText = this.searchForm.get("searchText").value;

    var wmoCodes = new Array();
    this.selectedCodes.forEach((wmoCode) => {
      wmoCodes.push(wmoCode.code);
    });
    sq.wmoCodes = wmoCodes;

    this.searchService.advanced(sq, this.showSearchResults, null);
  }

  showSearchResults = (response) => {
    this.searchResults = <SearchResults>JSON.parse(JSON.stringify(response.body));
  }

  navigateToHome() {
    this.router.navigateByUrl('/home');
  }


}
