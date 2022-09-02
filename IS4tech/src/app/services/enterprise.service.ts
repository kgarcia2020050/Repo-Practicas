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

  getEnterprises(
    page: number,
    size: number,
    order: string,
    asc: boolean
  ): Observable<any> {
    return this.http.get(
      url +
        'enterprise/all?page=' +
        page +
        '&size=' +
        size +
        '&order=' +
        order +
        '&asc=' +
        asc
    );
  }

  postEnterprise(model: Enterprise): Observable<any> {
    return this.http.post(url + 'enterprise/new', model);
  }

  editEnterprise(id, model: Enterprise): Observable<any> {
    return this.http.put(url + 'enterprise/edit/' + id, model);
  }
}
