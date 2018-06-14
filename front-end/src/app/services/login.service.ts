import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Doctor } from "../model/doctor";
import { Observable } from "rxjs/Observable";

@Injectable()
export class LoginService{
    constructor(
        private router: Router,
        private http: HttpClient) {
    }

    login(doctor: any):  Observable<any>{
        let header = { 
            headers: new HttpHeaders({
                'Content-Type': 'application/x-www-form-urlencoded'//,
//                'j_username': doctor.username,
  //              'j_password': doctor.password
            })
        };
        return this.http.post('http://localhost:8080/login', 'j_username='+doctor.username+'&j_password='+doctor.password, header);
    }

    logout(){
        this.http.get('http://localhost:8080/logout').subscribe(data => {
            console.log(data);
            this.router.navigate(['/login']);
        });
    }
}