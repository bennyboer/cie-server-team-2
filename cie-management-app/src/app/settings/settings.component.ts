import { Component, OnInit } from '@angular/core';
import {SettingsService} from '../util/settings/settings.service';
import {MatSlideToggleChange} from '@angular/material';
import {UserService} from '../user/user.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  isBackgroundMusicEnabled: boolean;

  constructor(private settingsService: SettingsService, private userService: UserService) { }

  ngOnInit() {
    this.isBackgroundMusicEnabled = this.settingsService.backgroundMusicEnabled.value;
    this.settingsService.backgroundMusicEnabled.observe().subscribe(isEnabled => {
      this.isBackgroundMusicEnabled = isEnabled;
    });
  }

  backgroundMusicEnabled(change: MatSlideToggleChange) {
    this.settingsService.backgroundMusicEnabled.value = change.checked;
  }

}
