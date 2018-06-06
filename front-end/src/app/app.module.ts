import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MainComponent } from './main/main.component';
import { MenubarComponent } from './menubar/menubar.component';
import { MainPageComponent } from './main-page/main-page.component';
import { StartExamComponent } from './start-exam/start-exam.component';
import { ExamComponent } from './exam/exam.component';
import { IntensiveCareComponent } from './intensive-care/intensive-care.component';
import { ReportsComponent } from './reports/reports.component';
import { AppRoutingModule } from './app-routing.module';
import { SymptomsComponent } from './exam/symptoms/symptoms.component';
import { DiagnoseComponent } from './exam/diagnose/diagnose.component';
import { TherapyComponent } from './exam/therapy/therapy.component';
import { SelfDiagnoseComponent } from './exam/diagnose/self-diagnose/self-diagnose.component';
import { NewIcPatientComponent } from './intensive-care/new-ic-patient/new-ic-patient.component';
import { PatientComponent } from './patient/patient.component';
import { NewPatientComponent } from './patient/new-patient/new-patient.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MainComponent,
    MenubarComponent,
    MainPageComponent,
    StartExamComponent,
    ExamComponent,
    IntensiveCareComponent,
    ReportsComponent,
    SymptomsComponent,
    DiagnoseComponent,
    TherapyComponent,
    SelfDiagnoseComponent,
    NewIcPatientComponent,
    PatientComponent,
    NewPatientComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
