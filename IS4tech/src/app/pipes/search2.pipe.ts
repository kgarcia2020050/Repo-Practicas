import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'search2',
})
export class Search2Pipe implements PipeTransform {
  transform(names: any, search2: any) {
    if (!search2) {
      return names;
    } else {
      return names.filter((n) => {
        return n.name.toLowerCase().includes(search2.toLowerCase());
      });
    }
  }
}
