import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class IntensiveCareService {

  private headers = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'my-auth-token'
    })
  };

  constructor(
    private http: HttpClient
  ) { 

  }

  public checkIfInIc(id: number): Observable<any>{
    return this.http.get('http://localhost:8080/api/intensive-care/'+id, this.headers);
  }

  public addToIc(id: number): Observable<any>{
    return this.http.put('http://localhost:8080/api/intensive-care/'+id, null, this.headers);
  }

  public removeFromIc(id: number): Observable<any>{
    return this.http.delete('http://localhost:8080/api/intensive-care/'+id, this.headers);
  }

  public getAllInIc(): Observable<any>{
    return this.http.get('http://localhost:8080/api/intensive-care/', this.headers);
  }

}
