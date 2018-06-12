import {Preference} from './preference';

export class NumberPreference extends Preference<number> {

  clearValue() {
    localStorage.removeItem(this.key);
  }

  loadValue(): number | null {
    const numberString: string = localStorage.getItem(this.key);

    let num = -1;
    if (numberString !== null) {
      try {
        num = parseInt(numberString, 10);
      } catch (e) {
        // Do nothing.
      }
    }

    return num;
  }

  storeValue(newValue: number) {
    localStorage.setItem(this.key, newValue.toString());
  }

}
