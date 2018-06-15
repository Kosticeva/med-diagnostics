import { Injectable } from '@angular/core';
import { Symptom } from '../model/symptom';
import { Disease } from '../model/disease';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LinkService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  putLink(d: number, s: number): Observable<any>{
    return this.http.post('http://localhost:8080/api/diseases/'+d+'/symptoms/'+s, null, this.headers);
  }
}
