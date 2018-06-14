import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Doctor } from '../model/doctor';
import { HttpClient, HttpResponse, HttpHeaders, HttpEvent } from '@angular/common/http';

@Injectable()
export class DoctorService {

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
    return this.http.get<Doctor>('http://localhost:8080/api/doctors/'+id, this.headers);
  } 

  post(d: Doctor): Observable<any> {
    return this.http.post<Doctor>('http://localhost:8080/api/doctors', d, this.headers);
  }

  put(d: Doctor, id: number): Observable<any>{
    return this.http.put<Doctor>('http://localhost:8080/api/doctors/'+id, d, this.headers);
  }

  delete(id: number): Observable<any>{
    return this.http.delete<Doctor>('http://localhost:8080/api/doctors/'+id, this.headers);
  }

  getAll(): Observable<any>{
    return this.http.get('http://localhost:8080/api/doctors', this.headers);
  }
}
