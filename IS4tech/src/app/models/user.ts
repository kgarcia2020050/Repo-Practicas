import { Byte } from '@angular/compiler/src/util';
import { Enterprise } from './enterprise';
import { UserEnterprise } from './user-enterprise';

export class User {
  constructor(
    public id:number,
    public email: string,
    public name: string,
    public status: Byte,
    public profile: number,
    public empresas:UserEnterprise[]
  ) {}
}
