import { UserEnterprise } from './user-enterprise';

export class User {
  constructor(
    public id:number,
    public email: string,
    public name: string,
    public status: any,
    public profile: number,
    public empresas:UserEnterprise[],
    public profilesByProfile:{id:number, status:number, name:string}
  ) {}
}
