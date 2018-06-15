import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Patient } from '../model/patient';

@Injectable()
export class PatientService {

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
    return this.http.get('http://localhost:8080/api/patients/'+id, this.headers);
  }

  post(p: Patient): Observable<any>{
    return this.http.post('http://localhost:8080/api/patients', 
      JSON.stringify({
        'firstName': p.firstName,
        'lastName': p.lastName,
        'allergens': p.allergens
      })
    , this.headers);
  }

  put(p: Patient, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/patients/'+id, p, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/patients/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/patients', this.headers);
  }

  getByName(query: string): Observable<any>{
    return this.http.get('http://localhost:8080/api/patients?name='+query, this.headers);
  }

}
