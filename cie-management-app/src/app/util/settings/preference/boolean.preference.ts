import {Preference} from './preference';

export class BooleanPreference extends Preference<boolean> {

  loadValue(): boolean | null {
    const value: string = localStorage.getItem(this.key);

    if (value === undefined || value === null) {
      return null;
    }

    return value === 'true'; // Convert string to boolean.
  }

  storeValue(newValue: boolean) {
    localStorage.setItem(this.key, newValue.toString());
  }

  clearValue() {
    localStorage.removeItem(this.key);
  }

}
