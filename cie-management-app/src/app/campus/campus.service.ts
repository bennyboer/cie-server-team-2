import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Campus} from './campus';

@Injectable()
export class CampusService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = '/api/locations';

  public getCampuses() {
    return this.http.get<Campus[]>(this.apiUrl);
  }

  public getCampus(id: number) {
    return this.http.get<Campus>(this.apiUrl + '/' + id);
  }

  public deleteCampus(campus: Campus) {
    return this.http.delete(this.apiUrl + '/' + campus.id);
  }

  public createCampus(campus: Campus, base64Image?: string) {
    console.log(base64Image);
    return this.http.post(this.apiUrl, {
      campus: campus,
      base64Image: base64Image
    });
  }

  public updateCampus(campus: Campus, base64Image?: string) {
    return this.http.put(this.apiUrl, {
      campus: campus,
      base64Image: base64Image
    });
  }

}
