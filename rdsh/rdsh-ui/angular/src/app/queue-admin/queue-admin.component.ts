import { Component, OnInit } from "@angular/core";
import { MatSnackBar, MatTableDataSource, MatDialog } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";
import { AppComponent } from "../app.component";
import { ChannelService } from "../services/channel.service";
import { MqttTopic } from "../dto/MqttTopic.dto";
import { OkCancelDialogComponent } from "../shared/ok-cancel-dialog.component";

@Component({
  selector: "app-queue-admin",
  templateUrl: "./queue-admin.component.html",
  styleUrls: ["./queue-admin.component.css"]
})
export class QueueAdminComponent implements OnInit {
  displayedColumns = ["name", "ldshname", "uri", "url"];
  dataSource = null;

  constructor(private channelService: ChannelService, private router: Router, public dialog: MatDialog,
    public snackBar: MatSnackBar) {
    AppComponent.selectedMenuItem = "channels";
  }

  ngOnInit() {
    this.getAllChannels();
  }

  private getAllChannels() {

    this.channelService.list().subscribe(
      onNext => {
        this.dataSource = new MatTableDataSource<MqttTopic>(onNext);
      }, onError => {
        console.error(onError);
        this.snackBar.open('There was a problem fetching the list of Channels.', null, {
          duration: 5000,
          verticalPosition: 'top'
        });
      });
  }


  deleteChannel(id: string) {

    let dialogRef = this.dialog.open(OkCancelDialogComponent, {
      data: {
        title: 'Delete Channel',
        question: 'Are you sure you want to delete this Channel?'
      }
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.channelService.delete(id).subscribe(
          onNext => {
            this.getAllChannels();
          }, onError => {
            console.error(onError);
            this.snackBar.open('There was a problem deleting the Channel.', null, {
              duration: 5000,
              verticalPosition: 'top'
            });
          });
      }
    });
  }

  // navigateToEditQueue(id: string) {
  //       this.router.navigate(["/queueEdit"], { queryParams: { id: id } });
  // }

  navigateToViewQueue(id: string) {
    this.router.navigate(['/channelView'], { queryParams: { "id": id } });
  }

  monitor() {
    this.router.navigate(["/monitor"]);
  }

  ngAfterViewInit() {
    if (!AppComponent.menuOpen) {
      document.getElementById("sitenav").click();
    }
  }
}

