import {Observable, Subject} from 'rxjs';

export abstract class Preference<T> {

  private _key: string;
  private _value: T = null;
  private _subject: Subject<T> = new Subject<T>();

  private _defaultValue: T = null;

  constructor(key: string, defaultValue?: T) {
    this._key = key;

    if (defaultValue !== undefined && defaultValue !== null) {
      this._defaultValue = defaultValue;
    }

    this.init();
  }

  /**
   * Initialize preference.
   */
  private init() {
    if (this._value === null) {
      const loadedValue: T = this.loadValue();

      if (loadedValue === null) {
        // Set default value.
        this.value = this._defaultValue;
      } else {
        this.value = loadedValue;
      }
    }
  }

  public observe(): Observable<T> {
    return this._subject.asObservable();
  }

  public set value(newValue: T) {
    this._value = newValue;
    this.storeValue(this._value);
    this._subject.next(this._value);
  }

  public get value(): T {
    if (this._value === null) {
      this.init();
    }

    return this._value;
  }

  public removeValue() {
    this._value = null;
    this.clearValue();
    this._subject.next(this._value);
  }

  public hasValue(): boolean {
    return this._value !== null;
  }

  protected get key(): string {
    return this._key;
  }

  public abstract clearValue();

  public abstract loadValue(): T | null;

  public abstract storeValue(newValue: T);

}
