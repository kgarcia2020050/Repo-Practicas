import { Injectable } from '@angular/core';
import { User } from '../models/user';
import url from '../helper';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(public http: HttpClient) {}

  getUsers(): Observable<any> {
    return this.http.get(url + 'user/all');
  }

  postUser(model: User): Observable<any> {
    return this.http.post(url + 'user/saveUser', model);
  }
}
