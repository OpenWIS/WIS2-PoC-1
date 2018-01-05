import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-datasets-main',
  templateUrl: './datasets-main.component.html',
  styleUrls: ['./datasets-main.component.css'],
  providers: [DataService]
  
})
export class DatasetsMainComponent implements OnInit {
  displayedColumns = ['name', 'description', 'url'];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor(private dataService: DataService, private router: Router) { }


  ngOnInit() {
    this.loadDatasets();
  }

  gotoPage(id: string) {

    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }

  loadDatasets(){
    this.dataService.getImportMessage("/cxf/api/getAllDatasets").then(result => {
      
      console.log(result);
edw eimai  todo na balw to resutl sthn lista kai na faietai swsta
      // this.buildForm(result);
    })
  }

}
export interface Element {
  name: string;
  description: string;
  id: string;
}


//get all datasets..
const datasetList: Element[] = [
  { name: 'Athens', description: "Temperature/Rainfall in Athens", id: "10" },
  { name: 'Thesaloniki', description: "Temperature/Rainfall in Thesaloniki", id: "30" },
  { name: "Iraklion", description: "Wind speed/direction in Iraklion" , id: "12" },
];


