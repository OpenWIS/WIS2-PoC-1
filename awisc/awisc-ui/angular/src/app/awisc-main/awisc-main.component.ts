import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { AppComponent } from '../app.component';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchQuery } from "../_dto/SearchQuery.dto";
import { SearchResults } from "../_dto/SearchResults.dto";
import { SettingsService } from "../_services/rest/settings.service";
import { SearchService } from "../_services/rest/search.service";
import { SearchResultsComponent } from "../search-results/search-results.component";


@Component({
  selector: 'app-awisc-main',
  templateUrl: './awisc-main.component.html',
  styleUrls: ['./awisc-main.component.css']
})
export class AwiscMainComponent implements OnInit {

  @ViewChild("introHeader") private introHeader: ElementRef;
  searchResults: SearchResults;

  searchForm: FormGroup;

  constructor(private router: Router, private settingsService: SettingsService, private searchService: SearchService) { }

  onSubmit() {

  }

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


  ngOnInit() {
    this.searchForm = new FormGroup({
      searchText: new FormControl("")
    });

    this.settingsService.list(this.listSettingsCallback, null);

  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById('sitenav').click();
    }
  }


  navigateToMenu() {
    this.router.navigateByUrl('/awiscSearch');
  }

  search() {
    var sq: SearchQuery = new SearchQuery();
    sq.searchText = this.searchForm.get("searchText").value;
    this.searchService.simple(sq, this.showSearchResults, null);
  }

  showSearchResults = (response) => {
    this.searchResults = <SearchResults> JSON.parse(JSON.stringify(response.body));
  }

}
