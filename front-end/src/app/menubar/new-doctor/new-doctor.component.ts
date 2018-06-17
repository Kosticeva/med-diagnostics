import { Component, OnInit, Input } from '@angular/core';
import { DoctorService } from '../../services/doctor.service';
import { Doctor } from '../../model/doctor';
import { Router } from '@angular/router';

@Component({
  selector: 'app-new-doctor',
  templateUrl: './new-doctor.component.html',
  styleUrls: ['./new-doctor.component.css']
})
export class NewDoctorComponent implements OnInit {

  @Input() doctor: Doctor;
  success: string;
  mode: boolean;

  errorMessage: {
    username: string,
    password: string
  };

  constructor(
    private doctorService: DoctorService,
    private router: Router
  ) { }

  ngOnInit() {
    this.success = "";
    this.errorMessage = {
      username: '',
      password: ''
    };

    if(this.doctor.licenceId === -1){
      this.mode = true;
    }else{
      this.mode = false;
    }
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

    if(this.mode){
      this.doctorService.post(this.doctor).subscribe(
        (data) => {
          this.errorMessage.username = "";
          this.errorMessage.password = "";
          this.success = "Doktor "+this.doctor.username+" uspesno kreiran!";
        },
        error => this.errorMessage.username = "Korisnicko ime je zauzeto. Odaberite novo."
      );
    }else{
      this.doctorService.put(this.doctor, this.doctor.licenceId).subscribe(
        (data) => {
          this.errorMessage.username = "";
          this.errorMessage.password = "";
          this.success = "Doktor "+this.doctor.username+" uspesno izmenjen!";
        },
        error => this.errorMessage.username = "Korisnicko ime je zauzeto. Odaberite novo."
      );
    }
  }
}
