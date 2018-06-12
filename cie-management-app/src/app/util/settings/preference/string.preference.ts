import {Preference} from './preference';

export class StringPreference extends Preference<string> {

  loadValue(): string | null {
    return localStorage.getItem(this.key);
  }

  storeValue(newValue: string) {
    localStorage.setItem(this.key, newValue);
  }

  clearValue() {
    localStorage.removeItem(this.key);
  }

}
