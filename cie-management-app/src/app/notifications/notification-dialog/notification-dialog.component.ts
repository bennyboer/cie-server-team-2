import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {NotificationService} from '../notification.service';

@Component({
  selector: 'app-notification-dialog',
  templateUrl: './notification-dialog.component.html',
  styleUrls: ['./notification-dialog.component.css']
})
export class NotificationDialogComponent implements OnInit {

  subject: string;
  content: string;

  constructor(public dialogRef: MatDialogRef<NotificationDialogComponent>,
              private notificationService: NotificationService) {
  }

  ngOnInit() {
  }

  send() {
    console.log('Send pressed');
    this.notificationService.sendNotification(this.subject, this.content).subscribe(result => {
      console.log('Notification service said: ' + result);

      this.dialogRef.close(true);
    }, error => {
      console.log('Notification said error: ' + error);

      this.dialogRef.close(false);
    });
  }

}
