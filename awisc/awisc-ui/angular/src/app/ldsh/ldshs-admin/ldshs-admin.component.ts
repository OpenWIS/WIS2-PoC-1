import { Component, OnInit } from "@angular/core";
import { MatDialog, MatSnackBar, MatTableDataSource } from "@angular/material";
import { AppComponent } from "../../app.component";
import { LdshDTO } from "../../_dto/Ldsh.dto";
import { LdshService } from "../../_services/rest/ldsh.service";
import { OkCancelDialogComponent } from "../../_shared/ok-cancel-dialog.component";

@Component({
  selector: 'app-ldshs-admin',
  templateUrl: './ldshs-admin.component.html',
  styleUrls: ['./ldshs-admin.component.scss']
})
export class LdshsAdminComponent implements OnInit {
  // LDSH table.
  displayedColumns = ['name', 'systemId', 'contactEmail', 'actions'];
  dataSource = null;

  constructor(private ldshService: LdshService, public snackBar: MatSnackBar,
    public dialog: MatDialog) {
    AppComponent.selectedMenuItem = 'ldshs';
  }

  ngOnInit() {
    this.getLDSHs();
  }

  onLdshListSuccess = (response) => {
    var data = response.body;
    this.dataSource = new MatTableDataSource<LdshDTO>(data);
  };

  // Get all available LDSHs.
  private getLDSHs() {
    // Get all LDSHs.
    this.ldshService.list(this.onLdshListSuccess, null);
  }

  ngAfterViewInit() {
    // if (!AppComponent.menuOpen) {
    //   document.getElementById('sitenav').click();
    // }
  }

  // Delete an LDSH by Id.
  delete(ldshId: string) {
    let dialogRef = this.dialog.open(OkCancelDialogComponent, {
      data: {
        title: 'Delete LDSH',
        question: 'Are you sure you want to delete this LDSH?'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.ldshService.delete(ldshId, this.onLdshDeleteSuccess, null);
      }
    });
  }

  onLdshDeleteSuccess = (response) => {
    this.getLDSHs();
  };


  //index LDSH
  private indexLdsh(ldshId: string) {
    this.ldshService.index(ldshId, null, null);
  }
}
