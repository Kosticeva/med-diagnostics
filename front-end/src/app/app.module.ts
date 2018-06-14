import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

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
import { NewIcPatientComponent } from './intensive-care/new-ic-patient/new-ic-patient.component';
import { PatientComponent } from './patient/patient.component';
import { NewPatientComponent } from './patient/new-patient/new-patient.component';
import { NewSymptomComponent } from './exam/symptoms/new-symptom/new-symptom.component';
import { NewDoctorComponent } from './menubar/new-doctor/new-doctor.component';
import { NewDiseaseComponent } from './menubar/new-disease/new-disease.component';
import { NewDrugComponent } from './menubar/new-drug/new-drug.component';
import { FormsModule } from '@angular/forms';
import { LoginService } from './services/login.service';
import { DoctorService } from './services/doctor.service';
import { ChartService } from './services/chart.service';
import { AllergyService } from './services/allergy.service';
import { DiseaseService } from './services/disease.service';
import { DrugService } from './services/drug.service';
import { ExamService } from './services/exam.service';
import { IngredientService } from './services/ingredient.service';
import { IntensiveCareService } from './services/intensive-care.service';
import { LinkService } from './services/link.service';
import { PatientService } from './services/patient.service';
import { PrescriptionService } from './services/prescription.service';
import { ReportService } from './services/report.service';
import { QueryService } from './services/query.service';
import { SymptomService } from './services/symptom.service';

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
    NewIcPatientComponent,
    PatientComponent,
    NewPatientComponent,
    NewSymptomComponent,
    NewDoctorComponent,
    NewDiseaseComponent,
    NewDrugComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [AllergyService, ChartService, DiseaseService, DoctorService, DrugService, 
    ExamService, IngredientService, IntensiveCareService, LoginService, LinkService, PatientService, 
    PrescriptionService, ReportService, QueryService, SymptomService],
  bootstrap: [AppComponent]
})
export class AppModule { }
