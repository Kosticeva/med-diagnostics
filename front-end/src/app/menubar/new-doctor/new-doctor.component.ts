import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../../services/doctor.service';
import { Doctor } from '../../model/doctor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-doctor',
  templateUrl: './new-doctor.component.html',
  styleUrls: ['./new-doctor.component.css']
})
export class NewDoctorComponent implements OnInit {

  doctor: Doctor;
  docId: number;
  success: string;

  errorMessage: {
    username: string,
    password: string
  };

  constructor(
    private doctorService: DoctorService,
    private router: Router
  ) { }

  ngOnInit() {
    this.doctor = {
      firstName: '',
      lastName: '',
      username: '',
      password: '',
      type: false,
      licenceId: undefined
    }

    this.success = "";
    this.errorMessage = {
      username: '',
      password: ''
    };

    this.docId = 0;
  }

  createDoctor() {
    this.success = "";
    if(this.doctor.username === ""){
      this.errorMessage.username = "Morate uneti korisnicko ime";
      return;
    }else{
      this.errorMessage.username = "";
    }

    if(this.doctor.password === ""){
      this.errorMessage.password = "Morate uneti sifru";
      return;
    }else{
      this.errorMessage.password = "";
    }

    if(this.doctor.type == true){
      this.doctor.type = "ADMINISTRATOR";
    }else{
      this.doctor.type = "REGULAR";
    }

    this.doctorService.post(this.doctor).subscribe(
      (data) => {
        this.errorMessage.username = "";
        this.errorMessage.password = "";
        this.success = "Doktor "+this.doctor.username+" uspesno kreiran!";
      },
      error => this.errorMessage.username = "Korisnicko ime je zauzeto. Odaberite novo."
    );
  }

  updateDoctor(){
    if(this.doctor.username === ""){
      this.errorMessage.username = "Morate uneti korisnicko ime";
      return;
    }else{
      this.errorMessage.username = "";
    }

    if(this.doctor.password === ""){
      this.errorMessage.password = "Morate uneti sifru";
      return;
    }else{
      this.errorMessage.password = "";
    }

    if(this.doctor.type == true){
      this.doctor.type = "ADMINISTRATOR";
    }else{
      this.doctor.type = "REGULAR";
    }

    this.doctor.licenceId = this.docId;

    this.doctorService.put(this.doctor, this.docId).subscribe(
      (data) => {
        this.errorMessage.username = "";
        this.errorMessage.password = "";
      },
      error => this.errorMessage.username = "Korisnicko ime je zauzeto ili nema doktora sa tim idjem. Odaberite novo."
    )
  }

  deleteDoctor(){
    this.doctorService.delete(this.docId).subscribe(
      (data) => {
        this.errorMessage.username = "";
      },
      error => this.errorMessage.username = "Nema doktora sa tim idjem. Odaberite novo."
    )
  }

  getDoctor(){
    this.doctorService.get(this.docId).subscribe(
      (data) => {
        alert(data);
        this.errorMessage.username = "";
      },
      error => this.errorMessage.username = "Nema doktora sa tim idjem. Odaberite novo."
    )
  }

  allDocs() {
    this.doctorService.getAll().subscribe(
      (data) => {
        alert(data);
        this.errorMessage.username = "";
      }
    )
  }

}
