import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Drug } from '../model/drug';

@Injectable()
export class DrugService {

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
    return this.http.get('http://localhost:8080/api/drugs/'+id, this.headers);
  }

  getByName(name: string): Observable<any>{
    return this.http.get('http://localhost:8080/api/drugs?name='+name, this.headers);
  }

  post(d: Drug): Observable<any>{
    return this.http.post('http://localhost:8080/api/drugs', 
      JSON.stringify({
        'name': d.name,
        'drugType': d.drugType,
        'ingredients': d.ingredients
      }), this.headers);
  }

  put(d: Drug, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/drugs/'+id, d, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/drugs/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/drugs', this.headers);
  }

}
