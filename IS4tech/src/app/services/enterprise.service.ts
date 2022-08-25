import { Injectable } from '@angular/core';
import url from '../helper';
import { Observable } from 'rxjs';
import { Enterprise } from '../models/enterprise';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EnterpriseService {
  constructor(public http: HttpClient) {}

  getEnterprises(): Observable<any> {
    return this.http.get(url + 'enterprise/all');
  }

  postEnterprise(model: Enterprise): Observable<any> {
    return this.http.post(url + 'enterprise/new', model);
  }
}
