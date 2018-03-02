import { Component, OnInit, Input } from "@angular/core";
import { Router } from "@angular/router";
import { AppComponent } from "../app.component";
import { SearchResults } from "../_dto/SearchResults.dto";
import { FormGroup, FormControl } from "@angular/forms";

@Component({
  selector: "search-results",
  templateUrl: "./search-results.component.html",
  styleUrls: ["./search-results.component.scss"]
})
export class SearchResultsComponent {

  @Input()
  searchResults: SearchResults;

  constructor(private router: Router) {}
}
