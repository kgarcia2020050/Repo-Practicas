import { Injectable } from '@angular/core';
import url from '../helper';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Profile } from '../models/profile';

@Injectable({
  providedIn: 'root',
})
export class ProfileService {
  constructor(private http: HttpClient) {}

  getProfiles(
    page: Number,
    size: Number,
    order: String,
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

  postProfile(model: Profile): Observable<any> {
    return this.http.post(url + 'profile/new', model);
  }
}
