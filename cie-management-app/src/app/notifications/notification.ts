export class NotificationRequest {
  priority: string;
  notification: Notification;
  data: NotificationData;
}

export class Notification {
  title: string;
  body: string;

  constructor(title: string, body: string) {
    this.title = title;
    this.body = body;
  }
}

export class NotificationData {

}
