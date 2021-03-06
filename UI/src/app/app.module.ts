import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';  //<<<< import it here
import { AppComponent } from './app.component';
import { RegisterComponent } from './Register/app.register';
import { UpdateBus } from './Service/app.updateBus';
import { WebServiceConfig } from './Service/app.webServiceConfig';
import { WebService } from './Service/app.webservice';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { SearchComponent } from './search/app.search';
import { ChartComponent } from './chart/app.chart';
import { EmployeesComponent } from './Employees/app.employees';
import { PortFolioComponent } from './Employees/Portfolio/app.portfolio';
import { DocumentsComponent } from './Employees/Documents/app.documents';
import { ExperienceComponent } from './Employees/Experience/app.experience';
import { CalendarComponent } from './Calendar/app.calendar';
import { ContactComponent } from './Contact/app.contact';
import { ChatComponent } from './chat/app.chat';
import { HomeComponent } from './Home/app.home';
import { DashboardComponent } from './Dashboard/app.dashboard';
import { LogInService } from './Service/app.loginService';
import { GeneratorComponent } from './Tools/Generator/app.generator';
import { MailService } from './Service/app.mailService';
import { EmployeesListComponent } from './EmployeesList/app.employeesList';
import { TimeSheetComponent } from './Employees/Timesheeets/app.timesheets';
import { TimesheetsService } from './Service/app.timesheetService';
import { ErrorSuccessComponent } from './Templates/app.ERR_SUC';
import { UpdateProfileComponent } from './profile/update/app.update-profile';
import { SendEmailComponent } from './diaglogues/app.sendEmail';
import { NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { StatusService } from './Service/app.statusService';
import { GlobalSettingsService } from './Service/app.globalSettings';
import { SettingsDialogue } from './diaglogues/app.settings';
import { EmpTimeSheetComponent } from './Dashboard/Timesheets/app.employee-timesheets';
import { ProfileComponent } from './profile/app.profile';
import { EmploymentHistComponent } from './Employment/app.employement';

const routes: Routes = [
 {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path : 'profile',
        component: ProfileComponent
      },
      {
        path : 'update-profile',
        component: UpdateProfileComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      
      {
        path: 'explorer',
        component: DocumentsComponent
      },
      {
        path: 'searchResult',
        component: SearchComponent
      },
      {
        path: 'chart',
        component: ChartComponent
      },
      {
        path: 'portfolio',
        component: PortFolioComponent
      },
      {
        path: 'empHistory',
        component: EmploymentHistComponent
      },
      {
        path: 'documents',
        component: DocumentsComponent
      },
      {
        path: 'timesheets',
        component: EmpTimeSheetComponent
      },
      {
        path: 'employees',
        component: EmployeesComponent,
        children: [
          {
            path: 'portfolio',
            component: PortFolioComponent
          },
          {
            path: 'experience',
            component: ExperienceComponent
          },
          {
            path: 'documents',
            component: DocumentsComponent
          },
          {
            path: 'timesheets',
            component: TimeSheetComponent
          }
        ]
      }, {
        path: 'calendar',
        component: CalendarComponent
      },
      {
        path: 'contact',
        component: ContactComponent
      },
      {
        path: 'generator',
        component : GeneratorComponent
      },
      {
        path: 'listEmployees',
        component : EmployeesListComponent
      }
    ]
  }
];



@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    SearchComponent,
    ChartComponent,
    EmployeesComponent,
    PortFolioComponent,
    ExperienceComponent,
    DocumentsComponent,
    CalendarComponent,
    ContactComponent,
    ChatComponent,
    HomeComponent,
    DashboardComponent,
    GeneratorComponent,
    EmployeesListComponent,
    TimeSheetComponent,
    ErrorSuccessComponent,
    UpdateProfileComponent,
    SendEmailComponent,
    SettingsDialogue,
    EmpTimeSheetComponent,
    ProfileComponent,
    EmploymentHistComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    NgbModalModule
  ],
  entryComponents: [ SendEmailComponent,SettingsDialogue ],
  providers: [UpdateBus, MailService, WebServiceConfig,
      WebService, LogInService,
      TimesheetsService, StatusService, GlobalSettingsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
