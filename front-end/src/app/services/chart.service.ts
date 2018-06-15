import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Chart } from '../model/chart';

@Injectable()
export class ChartService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  get(id: any): Observable<any> {
    return this.http.get('http://localhost:8080/api/charts/'+id, this.headers);
  }

  post(c: Chart): Observable<any>{
    return this.http.post('http://localhost:8080/api/charts', 
      JSON.stringify({
        'patient': c.patient,
        'examinations': c.examinations
      })
    , this.headers);
  }

  put(c: Chart, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/charts/'+id, JSON.stringify(c), this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/charts/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/charts', this.headers);
  }

}
