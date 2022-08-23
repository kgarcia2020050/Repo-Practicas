import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import url from '../helper';

@Injectable({
  providedIn: 'root',
})
export class ServicioService {
  constructor(private _http: HttpClient) {}

  holaMundo(): Observable<any> {
    return this._http.get(url + 'saludo', { responseType: 'text' });
  }
}
