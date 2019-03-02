import { Component, OnInit, AfterViewInit, ChangeDetectorRef } from "@angular/core";
import { Router } from '@angular/router';
import { WebService } from '../Service/app.webservice';
import { LogInService } from '../Service/app.loginService';
import { UpdateBus } from '../Service/app.updateBus';
import { PersonalInfo } from '../models/app.personalInfo';
import { LoggedUser } from '../Service/app.LoggedUser';
import { LoginStatus } from '../Constants/app.constants';
import { RoutingService } from '../Service/app.router';


declare function dashBoardFunctions(): void;

@Component({
  selector: 'dashboard',
  templateUrl: './app.dashboard.html',
  styleUrls: ['./app.dashboard.css']
})

export class DashboardComponent implements OnInit {

    private userRole : string;
    private isLoggedIn : boolean = false;
    private isReady : boolean = false;

  constructor(private router: Router,
    private dataService: WebService,
    private loginService: LogInService,
    private bus: UpdateBus,
    private cd: ChangeDetectorRef){
   
  }

  isLoadingComplete(){
    return this.isReady;
  }

  showThis(){
    if(this.userRole != null){
      if(this.userRole == "EMPLOYEE"){
        return false;

      } else {      
        return true;
      }
    }
  }

  getRoutes(){
    console.log(JSON.stringify(RoutingService.getRoutesObjs()));
    return RoutingService.getRoutesObjs();
  }

  getUser() {
    return LoggedUser.getUser();
  }

  signOut(){
    LoggedUser.clearUser();
    this.loginService.logOut();
    this.router.navigate(['home']);
  }

  search(event: Event) {
    var el = <HTMLInputElement>document.getElementById('nav-search');
    if (el != null) {
      var query = el.value;
      if (query.length > 0) {
        this.router.navigate(['dashboard/searchResult'], { queryParams: { 'query': query } });
      }
    }
  }

  changeStatus() {
    document.getElementById('db-status-id').click;
  }

  getLogInStatus() {
    return [LoginStatus.ONLINE, LoginStatus.OFFLINE, LoginStatus.BUSY, LoginStatus.AWAY];
  }

  checkIfLoggedIn() {
    if (this.loginService.getLoggedIn() == true) {
      return true;
    } else {
      return false;
    }
  }

  initJS(){
    dashBoardFunctions();
  }

  ngOnInit() {

    this.isReady = false;
    this.isLoggedIn = false;
    if(this.checkIfLoggedIn()){
      this.userRole = this.loginService.getUserRole();
      if(this.userRole != null){

        setTimeout(() => {
          this.dataService.getSingle<PersonalInfo>(this.loginService.getEid())
          .subscribe((data: PersonalInfo) => {
            LoggedUser.createNew(data);
            console.log("******************* LOGGED USER **********************");
            console.log(JSON.stringify(LoggedUser.getUser()))
            console.log("******************* LOGGED USER END **********************");        

          },
            (error) => {
    
            },
            () => {
              this.isLoggedIn = true;
              this.isReady = true;   
            });

        }, 500);

       
      }
    } else{
      this.isLoggedIn = false;
    }



    //this.router.navigate(['dashboard/employees/documents']);
  }
}