import { Component, OnInit } from "@angular/core";
import { MatTableDataSource, MatDialog } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";
import { AppComponent } from "../app.component";
import { DataService } from "../data.service";
import { OkCancelDialogComponent } from "../shared/ok-cancel-dialog.component";

@Component({
  selector: "app-datasets-admin",
  templateUrl: "./datasets-admin.component.html",
  styleUrls: ["./datasets-admin.component.css"],
  providers: [DataService]
})

export class DatasetsAdminComponent implements OnInit {
  displayedColumns = ["name", "description", "url"];
  dataSource = new MatTableDataSource<Element>(datasetList);

  constructor(private dataService: DataService, private router: Router, public dialog: MatDialog) {
    AppComponent.selectedMenuItem = 'datasets';
  }


  ngOnInit() {
    this.loadDatasets();
  }


  loadDatasets() {
    this.dataService.getCall("getAllDatasets").then(result => {
      this.loadDatasetsTable(result);
    })
  }


  private loadDatasetsTable(datasets: Element[]) {

    if (datasets instanceof Array) {
      datasetList = (datasets).slice();
    } else {
      datasetList = [];
      if (datasets != undefined) {
        datasetList.push(datasets);
      }
    }
    this.dataSource = new MatTableDataSource<Element>(datasetList);
  }



  navigateToEditDataset(id: string) {
    //this.router.navigateByUrl('/datasets');
    this.router.navigate(['/submit'], { queryParams: { "id": id } });
  }

  navigateToViewDataset(id: string) {
    this.router.navigate(['/view'], { queryParams: { "id": id } });
  }
  addNewDataset() {
    this.router.navigate(['/submit'], { queryParams: {} });

  }


  deleteDataset(id: string) {

    let dialogRef = this.dialog.open(OkCancelDialogComponent, {
      data: {
        title: 'Delete LDSH',
        question: 'Are you sure you want to delete this LDSH?'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
    this.dataService.getCall("deleteDataset/id=" + id).then(result => {
        this.ngOnInit();
    })
  }
});

  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById('sitenav').click();
    }
  }


}
export interface Element {
  name: string;
  description: string;
  id: string;
}


var datasetList: Element[] = [];
