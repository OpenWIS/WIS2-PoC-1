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
  public barChartType: string = 'bar';
  public barChartLegend: boolean = true;
  public dataSourceany: any[];


  constructor(private channelService: ChannelService, private activatedRoute: ActivatedRoute, private router: Router) {
  }


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
      this.buildChart(this.channel);
    }, onError => {
      console.error(onError);
      this.snackBar.open('There was a problem fetching the Channel data.', null, {
        duration: 5000,
        verticalPosition: 'top'
      });
    });
  }

  back() {
    this.router.navigate(["/channels"]);
  }

//Charts configuration:
  public bytesChartData: any[];
  public messagesChartData: any[];
  public bytesChartLabels: string[] = ['Channel Traffic'];
  public messagesChartLabels: string[] = ['Channel Utilization'];
  
  private buildChart(channel: MqttTopic): any {
    this.bytesChartData = [{ data: [this.channel.bytesSent], label: 'Bytes Sent' }];

    this.messagesChartData = [
      { data: [this.channel.msessagesSent], label: 'Messages Sent' },
      { data: [this.channel.failedConnections], label: 'Failed Connections' }];
  }

  public lineChartColors: Array<any> = [
    {
      backgroundColor: 'rgba(118,159,177,0.5)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#ffA',
      pointHoverBackgroundColor: '#fffA',
      pointHoverBorderColor: 'rgba(48,159,177,0.8)'
    },
    {
      backgroundColor: 'rgba(77,83,96,0.5)',
      pointBackgroundColor: 'rgba(77,83,96,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#Aff',
    },
  ];

  public bytesChartColors: Array<any> = [
    {
      backgroundColor: 'rgba(100,151,127,0.5)',
      pointBackgroundColor: 'rgba(148,159,177,1)',
      pointBorderColor: '#ffA',
      pointHoverBackgroundColor: '#fffA',
      pointHoverBorderColor: 'rgba(48,159,177,0.8)'
    }
  ];


  public bytesChartOptions: any = {

    scaleShowVerticalLines: false,
    responsive: true,

    scales: {
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Bytes'
        },
        ticks: {
          beginAtZero: true,
        }
      }]
    }
  };

  public msgsChartOptions: any = {

    scaleShowVerticalLines: false,
    responsive: true,

    scales: {
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Messages'
        },
        ticks: {
          beginAtZero: true,
        }
      }]
    }
  };


  // events
  public chartClicked(e: any): void {
    // console.log(e);
  }

  public chartHovered(e: any): void {
    // console.log(e);
  }


}


