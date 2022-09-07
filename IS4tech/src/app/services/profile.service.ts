import { Injectable } from '@angular/core';
import url from '../helper';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from '../models/profile';
import { Byte } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(public http: HttpClient) {}

  getProfiles(
    page: number,
    size: number,
    order: string,
    asc: boolean
  ): Observable<any> {
    return this.http.get(
      url +
        'profile/all?page=' +
        page +
        '&size=' +
        size +
        '&order=' +
        order +
        '&asc=' +
        asc
    );
  }

  getProfile(id: number): Observable<any> {
    return this.http.get(url + 'profile/profile/' + id);
  }

  postProfile(model: Profile): Observable<any> {
    return this.http.post(url + 'profile/new', model);
  }

  putProfile(model: Profile, id: number): Observable<any> {
    return this.http.put(url + 'profile/editProfile/' + id, model);
  }

  getStatusProfile(): Observable<any> {
    return this.http.get(url + 'profile/findByStatus/' + 1);
  }
}
