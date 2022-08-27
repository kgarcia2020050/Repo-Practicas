import { Byte } from '@angular/compiler/src/util';

export class Profile {
  constructor(public id: number, public name: string, public status: Byte) {}
}
