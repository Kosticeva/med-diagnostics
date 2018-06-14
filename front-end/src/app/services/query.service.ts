import { Injectable } from '@angular/core';
import { Examination } from '../model/examination';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Symptom } from '../model/symptom';
import { Disease } from '../model/disease';

@Injectable()
export class QueryService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  public findMostProbable(e: Examination, id:number): Observable<any>{
    return this.http.put('http://localhost:8080/api/query/most-probable/'+id, e, this.headers);
  }

  public findAllPossible(s: Symptom[]): Observable<any>{
    return this.http.put('http://localhost:8080/api/query/possible', s, this.headers);
  }

  public findAllSymptoms(d: Disease): Observable<any>{
    return this.http.put('http://localhost:8080/api/query/symptoms', d, this.headers);
  }

}
