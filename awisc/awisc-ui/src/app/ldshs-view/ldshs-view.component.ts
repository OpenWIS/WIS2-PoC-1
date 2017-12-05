import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-ldshs-view',
  templateUrl: './ldshs-view.component.html',
  styleUrls: ['./ldshs-view.component.css']
})
export class LdshsViewComponent implements OnInit {
  
  paramsObj: Object;

  constructor(private activatedRoute: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
    });
    let id = this.paramsObj['params']['id'];
    let dataset = this.ldshList.find(i => i.id === id);
    this.ldsh = dataset;

  }

  ldsh = {
    id: "",
    title: "",
    systemId: "",
    email: "",
    copyright: "",
    token: "",
  };

  ldshList = [
    {
      id: "31",
      title: "Ath_weath",
      systemId: "321",
      email: "ath@weath.com",
      copyright: "no",
      token: "3e617e30-0127-4366-0c43-51e3bcf0ca47",
    },
    {
      id: "32",
      title: "Thes_weath",
      systemId: "231",
      email: "thes@weath.com",
      copyright: "yes",
      token: "a233a609-5584-2bad-3479-cf5b0812d840",

    },
    {
      id: "33",
      title: "Her_weath",
      systemId: "211",
      email: "her@weath.com",
      copyright: "no",
      token: "0000b779-2b42-a1dd-ef81-3875750cafb3",
    }];


}
