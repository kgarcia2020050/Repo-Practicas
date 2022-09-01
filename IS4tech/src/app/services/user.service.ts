import { Injectable } from '@angular/core';
import { User } from '../models/user';
import url from '../helper';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { UserDialog } from '../components/user-dialog/user-dialog';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(public http: HttpClient) {}

  getUsers(page: number,
    size: number,
    order: string,
    asc: boolean): Observable<any> {
    return this.http.get(url + 'user/all?page=' +
    page +
    '&size=' +
    size +
    '&order=' +
    order +
    '&asc=' +
    asc);
  }


  postUser(model: User): Observable<any> {
    return this.http.post(url + 'user/saveUser', model);
  }

  putUser(model:User, id: number): Observable<any> {
    return this.http.put(url + 'user/editUser/' + id, model);
  }
  getUser(id: number): Observable<any> {
    return this.http.get(url + 'user/user/' + id);
  }
}
