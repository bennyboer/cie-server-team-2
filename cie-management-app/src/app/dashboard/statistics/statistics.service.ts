import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ItemCountStatistics} from './item-count-statistics';

@Injectable()
export class StatisticsService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = '/api/statistics';

  public getItemCount() {
    return this.http.get<ItemCountStatistics>(`${this.apiUrl}/count`);
  }

}
