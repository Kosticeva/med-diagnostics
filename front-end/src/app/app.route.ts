import { Routes, Route } from "@angular/router";
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

export const menuBarRoute: Route = {
    path: '',
    outlet: 'menubar',
    component: MenubarComponent
};

export const icRoutes: Routes = [
    {
        path: 'intensive-care',
        component: MainComponent,
        children: [
            menuBarRoute,
            {
                path: '',
                component: IntensiveCareComponent
            }
        ]
    }
];

export const diagnoseRoutes: Routes = [
    {
        path: 'exam/new',
        component: MainComponent,
        children: [
            menuBarRoute,
            {
                path: '',
                component: StartExamComponent
            }
        ]
    },
    {
        path: 'exam/:id',
        component: MainComponent,
        children: [
            menuBarRoute,
            {
                path: '',
                component: ExamComponent
            }
        ]
    }
];

export const patientRoutes: Routes = [
    {
        path: 'patient/:id',
        component: MainComponent,
        children: [
            menuBarRoute,
            {
                path: '',
                component: PatientComponent
            }
        ]
    }
];

export const pageRoutes: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'home',
        component: MainComponent,
        children: [
            menuBarRoute,
            {
                path: '',
                component: MainPageComponent
            }
        ]
    },
    {
        path: 'reports',
        component: MainComponent,
        children: [
            menuBarRoute,
            {
                path: '',
                component: ReportsComponent
            }
        ]
    },
    ...patientRoutes,
    ...icRoutes,
    ...diagnoseRoutes,
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    }
];