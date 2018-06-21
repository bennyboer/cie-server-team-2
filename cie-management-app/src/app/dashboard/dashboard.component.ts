import {Component, OnInit} from '@angular/core';
import {StatisticsService} from './statistics/statistics.service';
import {ItemCountStatistics} from './statistics/item-count-statistics';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  countStatistics: ItemCountStatistics = null;
  errorMessage: string = null;

  constructor(private statistics: StatisticsService) {
  }

  ngOnInit() {
    this.statistics.getItemCount().subscribe(result => {
      this.countStatistics = result;
    }, e => {
      this.errorMessage = 'Statistics could not be loaded from the server.';
    });
  }

}
