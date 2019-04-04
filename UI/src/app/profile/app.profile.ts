import { Component, ViewChild, OnInit } from "@angular/core";
import { PersonalInfo } from '../models/app.personalInfo';
import { EmploymentObj } from '../models/app.employmentObj';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { UpdateBus } from '../Service/app.updateBus';
import { WebService } from '../Service/app.webservice';

import { DocumentObj } from '../models/app.documentObj';
import { LogInService } from '../Service/app.loginService';
import { ErrorSuccessComponent } from '../Templates/app.ERR_SUC';
import { Router } from '@angular/router';
import { LoggedUser } from '../Service/app.LoggedUser';

@Component({
    selector : "profile",
    templateUrl : "./app.profile.html",
    styleUrls : ["./app.profile.css"]
})

export class ProfileComponent implements OnInit{


    employee: PersonalInfo;
    curEmploymentObj: EmploymentObj;
    @ViewChild(ErrorSuccessComponent)
    public statusTemplate: ErrorSuccessComponent;

    constructor(private modalService: NgbModal,
        private updateService : UpdateBus,
        private dataService : WebService,
        private loginService: LogInService,
        private router: Router
        ){
    }


    getProfilePic(){        
        var obj  = this.searchDocs("profilePic");        
        if ( obj != null) {
            return "http://localhost:8080/files/" + obj.fileName;
        } else {
            return "assets/img/img_male_avatar.png";
        }
    }

    searchDocs(fileName) : DocumentObj{
        var resp : DocumentObj;
        this.employee.documnetObj.forEach(x => {
            if(x.fileName != null && x.fileName.startsWith(fileName)){
                if(resp == null){
                    resp = x;
                } else {
                    if( new Date(x.lastUpdated) > new Date(resp.lastUpdated)){
                        resp = x;
                    }
                }
                    
                return;
            }
        });

        return resp;

    }

    fileChange(event) {

    var files = event.target.files;
    var formData = new FormData;
    if(files != null){
        for(var i = 0; i < files.length; i++){
            formData.append('file', files[i], 'profilePic-'+ files[i].name);
          }
          this.dataService
          .upload(formData, this.employee.employeeId)
          .subscribe((data: any) => {
              this.employee.documnetObj = data;
            console.log(data);
          },
          error => {
            console.log("ERROR: " + JSON.stringify(error))
          },
          () => {
            console.log(" UPLOAD SUCCESS");
            this.updateService.resetEmployeeDetail().subscribe(
                (data: any) => {},
                (error) => {console.log("Error Fetching Employee Details: " + error)},
                () => {
                    alert("HERE");
                    this.ngOnInit();
                }                
            );
          });          
    }
  }

  UpdateProfile(){
      this.router.navigateByUrl("dashboard/update-profile");
  }
    ngOnInit(){
            var eid = this.loginService.getEid();
            if(eid == null){
                    this.statusTemplate.successMessage = "Please update your profile.";
            } else {
                this.employee = LoggedUser.getUser();
                if(this.employee == null){
                    this.statusTemplate.errorMessage = "There was an error retreiving the profile.";
                }
            }


        if(this.employee != null && this.employee.employmentObj != null){
            for(var i = 0; i < this.employee.employmentObj.length; i++){
                if(this.employee.employmentObj[i].status == 'Active') {
                    this.curEmploymentObj = this.employee.employmentObj[i];
                    break;
                }
            }
        }
        
    }
}