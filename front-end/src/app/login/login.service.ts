import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Doctor } from "../model/doctor";


const header = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};

@Injectable()
export class LoginService{

    constructor(
        private router: Router,
        private http: HttpClient) {

    }

    login(doctor: Doctor) {
        this.http.post<Doctor>('http://localhost:8080/api/login', doctor, header).subscribe(data => {
            this.router.navigateByUrl('/home');
        });
    }
}