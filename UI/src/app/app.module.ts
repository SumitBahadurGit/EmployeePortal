import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Component } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule, Routes } from '@angular/router';
import { AppDashboardComponent } from './app.dashboard';
import { RegisterComponent } from './app.register';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { DataService } from './app.employeeService';
import { EmployeeConfig } from './app.employeeConfig';
import { Search } from './app.search';
import { Update } from './app.update';
import { MaterialModule } from './app.material-module';
import {platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatNativeDateModule} from '@angular/material';
import { EnrollComponent } from './app.enroll';
import { ExperienceComponent } from './app.experience';
import { UpdateService } from './app.updateService';
import * as $ from 'jquery';
import { Documents } from './app.documents';


const routes: Routes = [
  { path: 'Dashboard',
  component: AppDashboardComponent },
  { path: 'Register',
    component: RegisterComponent
  },
  {
    path: 'Search',
    component: Search
  },
  {
    path: 'Update',
    component: Update,
    children: [
     { 
      path: 'Enroll',
      component: EnrollComponent
    },
    {
      path : "experience",
      component: ExperienceComponent
    },
    {
      path : "documents",
      component: Documents
    }
    ]
  }
];

@NgModule({
  declarations: [
    Update,
    Search,
    AppComponent,
    AppDashboardComponent,
    RegisterComponent,
    EnrollComponent,
    ExperienceComponent,
    Documents

  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    BrowserAnimationsModule,
    MatNativeDateModule
    ],
  providers: [DataService,EmployeeConfig,UpdateService],
  bootstrap: [AppComponent]
})
export class AppModule { }
