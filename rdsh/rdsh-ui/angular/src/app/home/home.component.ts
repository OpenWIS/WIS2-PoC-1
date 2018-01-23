import { Component, OnInit } from '@angular/core';
import {MatSnackBar, MatTableDataSource} from '@angular/material';
import { ActivatedRoute, Router } from '@angular/router';
import {LdshService} from "../services/ldsh-service.service";
import {LDSHDTO} from "../dto/LDSH.dto";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  // LDSH table.
  displayedColumns = ['title', 'systemId', 'contactEmail', 'actions'];
  dataSource = null;

  constructor(private router: Router, private ldshService: LdshService, public snackBar: MatSnackBar) { }

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
  }
}
