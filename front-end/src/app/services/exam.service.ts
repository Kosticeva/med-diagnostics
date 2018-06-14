import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Examination } from '../model/examination';

@Injectable()
export class ExamService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  get(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/api/examinations/'+id, this.headers);
  }

  post(e: Examination): Observable<any>{
    return this.http.post('http://localhost:8080/api/examinations', e, this.headers);
  }

  put(e: Examination, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/examinations/'+id, e, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/examinations/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/examinations', this.headers);
  }

}
