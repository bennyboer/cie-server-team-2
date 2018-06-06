import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {NotificationRequest, Notification} from './notification';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  readonly apiUrl = '/api/notifications';

  constructor(private http: HttpClient) {
  }

  public sendNotification(subject: string, content: string) {
    const notificationRequest: NotificationRequest = new NotificationRequest();
    notificationRequest.priority = 'high';
    notificationRequest.notification = new Notification(subject, content);
    notificationRequest.data = null;

    return this.http.post<string>(this.apiUrl, notificationRequest);
  }
}
