import { Routes } from "@angular/router";
import { LoginComponent } from "./login/login.component";
import { MainComponent } from "./main/main.component";
import { MenubarComponent } from "./menubar/menubar.component";
import { MainPageComponent } from "./main-page/main-page.component";
import { ReportsComponent } from "./reports/reports.component";
import { IntensiveCareComponent } from "./intensive-care/intensive-care.component";
import { NewIcPatientComponent } from "./intensive-care/new-ic-patient/new-ic-patient.component";
import { DiagnoseComponent } from "./exam/diagnose/diagnose.component";
import { TherapyComponent } from "./exam/therapy/therapy.component";
import { SymptomsComponent } from "./exam/symptoms/symptoms.component";
import { ExamComponent } from "./exam/exam.component";
import { StartExamComponent } from "./start-exam/start-exam.component";
import { NewPatientComponent } from "./patient/new-patient/new-patient.component";
import { PatientComponent } from "./patient/patient.component";

export const icRoutes: Routes = [
    {
        path: 'intensive-care',
        component: IntensiveCareComponent
    }
];

export const diagnoseRoutes: Routes = [
    {
        path: 'exam/new',
        component: StartExamComponent
    },
    {
        path: 'exam/:id',
        component: ExamComponent,
        children: [
            {
                path: 'diagnose',
                component: DiagnoseComponent
            },
            {
                path: 'therapy',
                component: TherapyComponent
            },
            {
                path: 'symptoms',
                component: SymptomsComponent
            }
        ]
    }
];

export const patientRoutes: Routes = [
    {
        path: '',
        component: NewPatientComponent,
        outlet: 'new-pat-modal'
    },
    /*
    ovde gore staviti da je path '' ali da je outlet neki specif
    i uraditi js da se napravi taj outlet na zahtev
    */
    {
        path: 'patient/:id',
        component: PatientComponent
    }
];

export const pageRoutes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: '',
        component: MainComponent,
        children: [
            {
                path: '',
                outlet: 'menubar',
                component: MenubarComponent
            },
            {
                path: 'home',
                component: MainPageComponent
            },
            {
                path: 'reports',
                component: ReportsComponent
            },
            ...icRoutes,
            ...diagnoseRoutes,
            ...patientRoutes
        ]
    },
    {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
    }
];