import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Allergen } from '../model/allergen';

@Injectable()
export class AllergyService {

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
    return this.http.get('http://localhost:8080/api/allergys/'+id, this.headers);
  }

  post(a: any): Observable<any>{
    return this.http.post('http://localhost:8080/api/allergys', JSON.stringify(a), this.headers);
  }

  put(a: Allergen, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/allergys/'+id, a, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/allergys/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/allergys', this.headers);
  }

}
