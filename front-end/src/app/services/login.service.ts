import { Router } from "@angular/router";
import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Doctor } from "../model/doctor";
import { Observable } from "rxjs/Observable";

@Injectable()
export class LoginService{

    private doctor: any
    public loginMsg: string;

    constructor(
        private router: Router,
        private http: HttpClient) {
    }

    login(doctor: Doctor){
        let header = { 
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        this.http.post('http://localhost:8080/login', JSON.stringify(doctor), header).subscribe(
            (data) => {
                this.doctor = data;
                this.router.navigate(['/home']);
            },
            (error) => {
                this.loginMsg = "Kombinacija korisnickog imena i sifre nije dobra.";
            }
        );
    }

    logout(){
        let header = { 
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        this.http.post('http://localhost:8080/applogout', null, header).subscribe(data => {
            console.log(data);
            this.router.navigate(['/login']);
        });
    }

    public getDoctor(): any{
        if(this.doctor === undefined)
            return '';
        return this.doctor;
    }

    public setDoctor() {
        let header = { 
            headers: new HttpHeaders({
                'Content-Type': 'application/json'
            })
        };
        this.http.get('http://localhost:8080/authenticate/'+this.doctor, header).subscribe(
            (data) => {
                this.doctor = data;
            }
        );
    }

}