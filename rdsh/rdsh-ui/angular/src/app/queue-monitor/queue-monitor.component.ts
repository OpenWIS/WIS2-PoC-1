import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatSnackBar } from "@angular/material";
import { RouterModule, Routes, Router } from "@angular/router";
import { ChannelService } from '../services/channel.service';
import { MqttTopic } from '../dto/MqttTopic.dto';


@Component({
  selector: 'app-queue-monitor',
  templateUrl: './queue-monitor.component.html',
  styleUrls: ['./queue-monitor.component.css']
})
export class QueueMonitorComponent implements OnInit {
  displayedColumns = ["name", "messagesSent", "bytesSent", "failedConnections", "Actions"];

  dataSource = null;

  constructor(private channelService: ChannelService, private router: Router, public snackBar: MatSnackBar) { }

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

  gotoQueue(id: string) {
    this.router.navigate(['/queueView'], { queryParams: { "id": id } });
  }


  refresh() {
    this.getAllChannels();
  }

  purge(id: string) {
    this.channelService.purge(id).subscribe(onNext => {

      this.snackBar.open('Channel data cleared.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });

      this.refresh();

    }, onError => {
      console.error(onError);
      this.snackBar.open('There was a problem fetching the list of Channels.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    });
  }
}
