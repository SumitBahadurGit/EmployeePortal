import { Component, OnInit, AfterViewInit, ChangeDetectorRef, ViewChild } from "@angular/core";
import { Router } from '@angular/router';
import { WebService } from '../Service/app.webservice';
import { LogInService } from '../Service/app.loginService';
import { UpdateBus } from '../Service/app.updateBus';
import { PersonalInfo } from '../models/app.personalInfo';
import { LoggedUser } from '../Service/app.LoggedUser';
import { LoginStatus } from '../Constants/app.constants';
import { RoutingService } from '../Service/app.router';
import { StatusService } from '../Service/app.statusService';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsDialogue } from '../diaglogues/app.settings';
import { GlobalSettingsService } from '../Service/app.globalSettings';
import { ErrorSuccessComponent } from '../Templates/app.ERR_SUC';


declare function dashBoardFunctions(): void;

@Component({
  selector: 'dashboard',
  templateUrl: './app.dashboard.html',
  styleUrls: ['./app.dashboard.css']
})

export class DashboardComponent implements OnInit {

    @ViewChild(ErrorSuccessComponent)
    statusTemplate : ErrorSuccessComponent;

    private userRole : string;
    private isLoggedIn : boolean = false;
    private isReady : boolean = false;
    private isJsInit : boolean = false;

  constructor(private router: Router,
    private dataService: WebService,
    private loginService: LogInService,
    private bus: UpdateBus,
    private globalSetting:GlobalSettingsService,
    private statusService : StatusService,
    private modal : NgbModal){
   
  }

  getSettingsType(){
    return this.globalSetting.settingType;
  }
  
  navigate(url : any){

    this.router.navigateByUrl(url);
  }

  openSettings(){
    const ref = this.modal.open(SettingsDialogue);

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
    } else {
      return true;
    }
  }

  getRoutes(){
    console.log(JSON.stringify(RoutingService.getRoutesObjs()));
    return RoutingService.getRoutesObjs();
  }

  getUser() {
    return LoggedUser.getUser();
  }

  getUserRole(){
    return this.loginService.getUserRole();
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
    if(!this.isJsInit){
      dashBoardFunctions();
      this.isJsInit = true;
    }

  }

  ngOnInit() {

    this.isReady = false;
    this.isLoggedIn = false;
    if(this.checkIfLoggedIn()){
      this.userRole = this.loginService.getUserRole();
      var eid : string = this.loginService.getEid();
      if(eid == null){
        // The user is logging for the first time or the user has not updated PersonalInfo
        this.isLoggedIn = true;
        this.isReady = true;   
        this.router.navigate(['dashboard/update-profile']);
      } else {
        if(this.userRole != null){

          setTimeout(() => {
            this.dataService.getSingle<PersonalInfo>(this.loginService.getEid())
            .subscribe((data: PersonalInfo) => {
              LoggedUser.createNew(data);
              LoggedUser.setUserRole(this.loginService.getUserRole());
              console.log("******************* LOGGED USER **********************");
              console.log(JSON.stringify(LoggedUser.getUser()))
              console.log("******************* LOGGED USER END **********************");        
            },
              (error) => {
                this.isReady = true;
                this.isLoggedIn = false;
                this.statusTemplate.errorMessage = "Sorry, the user does not exist in the system";
              },
              () => {
                this.isLoggedIn = true;
                this.isReady = true;   
               // this.router.navigate(['dashboard/employees/timesheets']);
                
              });  
          }, 500);           
        }
      }
     
    } else{
      this.isLoggedIn = false;
    }



    //this.router.navigate(['dashboard/employees/documents']);
  }
}