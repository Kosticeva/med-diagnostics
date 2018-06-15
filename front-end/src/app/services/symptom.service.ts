import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Symptom } from '../model/symptom';

@Injectable()
export class SymptomService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    })
  };

  constructor(
    private http: HttpClient
  ) { }

  get(id: number): Observable<any> {
    return this.http.get('http://localhost:8080/api/symptoms/'+id, this.headers);
  }

  getByName(name: string): Observable<any> {
    return this.http.get('http://localhost:8080/api/symptoms?name='+name, this.headers);
  }

  post(s: any[]): Observable<any>{
    return this.http.post('http://localhost:8080/api/symptoms', JSON.stringify(s), this.headers);
  }

  put(s: Symptom, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/symptoms/'+id, s, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/symptoms/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/symptoms', this.headers);
  }

}
