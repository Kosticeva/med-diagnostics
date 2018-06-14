import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Disease } from '../model/disease';

@Injectable()
export class DiseaseService {

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
    return this.http.get('http://localhost:8080/api/diseases/'+id, this.headers);
  }

  getByName(name: string): Observable<any> {
    return this.http.get('http://localhost:8080/api/diseases?name='+name, this.headers);
  }

  post(d: Disease): Observable<any>{
    return this.http.post('http://localhost:8080/api/diseases', d, this.headers);
  }

  put(d: Disease, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/diseases/'+id, d, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/diseases/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/diseases', this.headers);
  }

}
