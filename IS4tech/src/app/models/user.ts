import { Byte } from '@angular/compiler/src/util';

export class User {
  constructor(
    public email: string,
    public name: string,
    public status: Byte,
    public profile: number
  ) {}
}
