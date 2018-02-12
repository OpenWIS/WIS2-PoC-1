import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChannelService } from '../services/channel.service';
import { MatTableDataSource } from '@angular/material';
import { MqttTopic } from '../dto/MqttTopic.dto';

@Component({
  selector: 'app-queue-view',
  templateUrl: './queue-view.component.html',
  styleUrls: ['./queue-view.component.css']
})
export class QueueViewComponent implements OnInit {
  snackBar: any;
  channel: MqttTopic = null;
  paramsObj: Object;

  constructor(private channelService: ChannelService, private activatedRoute: ActivatedRoute, private router: Router) {
    this.fullImagePath = '../../assets/aGrafph.png'
  }

  fullImagePath: string;


  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(params => {
      this.paramsObj = { ...params.keys, ...params };
      let id = this.paramsObj['params']['id'];
      this.getChannel(id);
    });

  }

  private getChannel(channelId) {

    this.channelService.get(channelId).subscribe(onNext => {
      this.channel = onNext;
    }, onError => {
      console.error(onError);
      this.snackBar.open('There was a problem fetching the Channel data.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    });
  }

}


