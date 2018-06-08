import {Pipe, PipeTransform} from '@angular/core';

/**
 * Pipe which formats numbers to hex format.
 */
@Pipe({
  name: 'hex'
})
export class HexPipe implements PipeTransform {

  transform(value: number): any {
    return value.toString(16);
  }

}
