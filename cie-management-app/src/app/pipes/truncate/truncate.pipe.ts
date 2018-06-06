import {Pipe, PipeTransform} from '@angular/core';

/**
 * Pipe which truncates strings.
 *
 * Usage:
 * {{ str | truncate:[20] }}
 *
 * or
 *
 * {{ str | truncate:[20, '...'] }}
 */
@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {

  transform(value: any, args?: any): any {
    const limit = args.length > 0 ? parseInt(args[0], 10) : 20;
    const trail = args.length > 1 ? args[1] : '...';

    return value.length > limit ? value.substring(0, limit) + trail : value;
  }

}
