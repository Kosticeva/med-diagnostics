import { Injectable } from '@angular/core';
import { Symptom } from '../model/symptom';
import { Disease } from '../model/disease';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class LinkService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  putLink(d: Disease, s: Symptom){
    this.http.post('http://localhost:8080/diseases/'+d.id+'/symptoms/'+s.id, null, this.headers);
  }
}
