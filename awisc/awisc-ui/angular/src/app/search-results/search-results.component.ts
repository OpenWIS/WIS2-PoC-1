import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AppComponent } from "../app.component";
import { FormGroup, FormControl } from "@angular/forms";

@Component({
  selector: "search-results",
  templateUrl: "./search-results.component.html",
  styleUrls: ["./search-results.component.scss"]
})
export class SearchResultsComponent {
  searchResults = [
    {
      name: "Athens Daily Rainfall",
      description:
        "Lorem ipsum dolor sit amet, vim et petentium hendrerit, per vitae numquam electram ut. Pri phaedrum reprehendunt an, est sumo utroque definitionem ad. Ut duo sale dictas, duo ei virtute expetendis. Legendos indoctum mediocrem et has, his quis minim facilisi at.",
      dataFormat: "CSV",
      license: " https://creativecommons.org/licenses/by/4.0/",
      url: "http://ldsh.gr/attica/athens",
      ldsh: "GR-ATH",
      latitude: "37.9838° N",
      longitude: "23.7275° E",
      elevation: "72",
      updateFrequency: "Daily",
      lastUpdate: "2017-12-05"
    },
    {
      name: "Thesaloniki Weekly Wind Digest",
      description:
        "Mazim invenire id duo, ullum nullam has ei, eam quot saepe conclusionemque cu. Mel ut voluptatum dissentias, aliquam molestie ea has, putent utroque dolores sed ad. Accusata accusamus eam te, at justo delicatissimi sit. Ex vim ignota option persius. No vel nonumes appetere signiferumque. Ea ludus aliquip omittam duo.",
      dataFormat: "XLSX",
      license: " https://creativecommons.org/licenses/by/4.0/",
      url: "http://ldsh.gr/thessaloniki/thessaloniki",
      ldsh: "GR-THE",
      latitude: "40.7368 ° N,",
      longitude: "22.9444° E",
      elevation: "5",
      updateFrequency: "Weekly",
      lastUpdate: "2017-12-05"
    },
    {
      name: "Iraklio Hourly Solar Intensity",
      description:
        "Partem salutatus constituam no vel, voluptatum dissentias ei vix. Te nam liber nusquam adversarium, eos in inermis intellegebat. Quidam consectetuer eam no, cu stet regione sed. Purto forensibus cotidieque ei his. Eos in delenit voluptatum. Te ipsum etiam vix, te eam nibh everti cetero, mel ut offendit appetere.",
      dataFormat: "JSON, CSV",
      license: " https://creativecommons.org/licenses/by/4.0/",
      url: "http://ldsh.gr/krete/irakleio",
      ldsh: "GR-IRA",
      latitude: "35.3387° N",
      longitude: "25.1442° E",
      elevation: "8",
      measurementUnit: "Hourly",
      lastUpdate: "2017-11-15"
    }
  ];

  constructor(private router: Router) {}
}
