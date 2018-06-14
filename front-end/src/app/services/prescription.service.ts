import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';
import { Prescription } from '../model/prescription';

@Injectable()
export class PrescriptionService {

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
    return this.http.get('http://localhost:8080/api/prescriptions/'+id, this.headers);
  }

  getByName(name: string): Observable<any> {
    return this.http.get('http://localhost:8080/api/drugs?name='+name, this.headers);
  }

  post(p: Prescription): Observable<any>{
    return this.http.post('http://localhost:8080/api/prescriptions', p, this.headers);
  }

  put(p: Prescription, id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/prescriptions/'+id, p, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/prescriptions/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/prescriptions', this.headers);
  }

}
