import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Drug } from '../model/drug';

@Injectable()
export class ReportService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { 

  }

  public chronics(): Observable<any>{
    return this.http.get('http://localhost:8080/api/reports/chronics', this.headers);
  }

  public addicts(): Observable<any>{
    return this.http.get('http://localhost:8080/api/reports/addicts', this.headers);
  }

  public weaks(): Observable<any>{
    return this.http.get('http://localhost:8080/api/reports/chronics', this.headers);
  }

  public allergies(drug: Drug, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/reports/allergies/'+id, JSON.stringify(drug), this.headers);
  }


}
