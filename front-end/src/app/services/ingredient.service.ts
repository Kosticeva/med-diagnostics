import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Ingredient } from '../model/ingredient';

@Injectable()
export class IngredientService {

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
    return this.http.get('http://localhost:8080/api/ingredients/'+id, this.headers);
  }

  post(i: Ingredient): Observable<any>{
    return this.http.post('http://localhost:8080/api/ingredients', i, this.headers);
  }

  put(i: Ingredient, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/ingredients/'+id, i, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/ingredients/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/ingredients', this.headers);
  }

}
