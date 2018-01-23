import {Component, OnInit} from "@angular/core";
import {MatDialog, MatSnackBar, MatTableDataSource} from "@angular/material";
import {AppComponent} from "../../app.component";
import {LDSHDTO} from "../../dto/LDSH.dto";
import {LdshService} from "../../services/ldsh-service.service";
import {OkCancelDialogComponent} from "../../shared/ok-cancel-dialog.component";

@Component({
  selector: 'app-ldshs-admin',
  templateUrl: './ldshs-admin.component.html',
  styleUrls: ['./ldshs-admin.component.scss']
})
export class LdshsAdminComponent implements OnInit {
  // LDSH table.
  displayedColumns = ['title', 'systemId', 'contactEmail', 'actions'];
  dataSource = null;

  constructor(private ldshService: LdshService, public snackBar: MatSnackBar,
              public dialog: MatDialog) {
    AppComponent.selectedMenuItem = 'ldshs';
  }

  ngOnInit() {
    this.getLDSHs();
  }

  // Get all available LDSHs.
  private getLDSHs() {
    // Get all LDSHs.
    this.ldshService.list().subscribe(
      onNext => {
        this.dataSource = new MatTableDataSource<LDSHDTO>(onNext);
      }, onError => {
        console.error(onError);
        this.snackBar.open('There was a problem fetching the list of LDSHs.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      });
  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById('sitenav').click();
    }
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
        this.ldshService.delete(ldshId).subscribe(
          onNext => {
            this.snackBar.open('LDSH was deleted successfully.', null, {
              duration: 3000,
              verticalPosition: 'top'
            });
            this.getLDSHs();
          }, onError => {
            console.error(onError)
            this.snackBar.open('There was a problem deleting this LDSHs.', null, {
              duration: 5000,
              verticalPosition: 'top'
            });
          });
      }
    });
  }
}
