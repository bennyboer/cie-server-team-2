import {Injectable} from '@angular/core';
import {Preference} from './preference/preference';
import {BooleanPreference} from './preference/boolean.preference';

@Injectable()
export class SettingsService {

  /*
  LIST OF PREFERENCES.
   */

  private backgroundMusicEnabledPreference: Preference<boolean> = new BooleanPreference('preferences.background-music-enabled', false);

  public get backgroundMusicEnabled(): Preference<boolean> {
    return this.backgroundMusicEnabledPreference;
  }

}
