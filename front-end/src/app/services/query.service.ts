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

  public findMostProbable(e: Examination): Observable<any>{
    return this.http.put('http://localhost:8080/api/query/most-probable', JSON.stringify(e), this.headers);
  }

  public findAllPossible(s: Symptom[]): Observable<any>{
    return this.http.put('http://localhost:8080/api/query/possible', JSON.stringify(s), this.headers);
  }

  public findAllSymptoms(d: Disease): Observable<any>{
    return this.http.put('http://localhost:8080/api/query/symptoms', JSON.stringify(d), this.headers);
  }

}
