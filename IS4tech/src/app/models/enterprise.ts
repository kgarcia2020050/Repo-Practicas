import { Byte } from "@angular/compiler/src/util";

export class Enterprise {
  constructor(public id: number, public name: string, public status: Byte) {}
}
