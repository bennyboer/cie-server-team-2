import { Component, OnInit } from '@angular/core';
import {MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-notification-dialog',
  templateUrl: './notification-dialog.component.html',
  styleUrls: ['./notification-dialog.component.css']
})
export class NotificationDialogComponent implements OnInit {

  subject: string;
  content: string;

  constructor(public dialogRef: MatDialogRef<NotificationDialogComponent>) { }

  ngOnInit() {
  }

  send() {
    console.log('Send pressed');
    this.dialogRef.close(true);
  }

}
