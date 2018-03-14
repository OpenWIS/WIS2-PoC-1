import {Component, OnInit} from '@angular/core';
import {MatSnackBar, MatTableDataSource} from '@angular/material';
import {Router} from '@angular/router';
import {LdshService} from "../services/ldsh-service.service";
import {LDSHDTO} from "../dto/LDSH.dto";
import {SettingsService} from "../services/settings-service.service";
import "rxjs/add/operator/map";
import "rxjs/add/operator/filter";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // LDSH table.
  displayedColumns = ['title', 'systemId', 'contactEmail'];
  dataSource = null;

  // Page-content variables.
  title = '';
  header = '';

  constructor(private router: Router, private ldshService: LdshService,
              private snackBar: MatSnackBar, private settingsService: SettingsService) {
  }

  ngOnInit() {
    // Get all LDSHs.
    this.ldshService.list().subscribe(
      onNext => {
        this.dataSource = new MatTableDataSource<LDSHDTO>(onNext);
      }, onError => {
        this.snackBar.open('There was a problem fetching the list of LDSHs.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      });

    // Get page-content variables.
    this.settingsService.getMany(['title', 'header']).subscribe(
      onNext => {
        this[onNext.settingKey] = onNext.settingVal;
      },
      onError => {
        this.snackBar.open('Could not get settings.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      }
    );
  }
}
