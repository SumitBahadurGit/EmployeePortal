import {Component, OnChanges, OnInit, Input, ViewChild } from '@angular/core';
import { WebService } from 'src/app/Service/app.webservice';
import { UpdateBus } from 'src/app/Service/app.updateBus';
import { PersonalInfo } from 'src/app/models/app.personalInfo';
import { EmploymentObj } from 'src/app/models/app.employmentObj';
import { DocumentObj } from 'src/app/models/app.documentObj';
import { LoggedUser } from 'src/app/Service/app.LoggedUser';
import { SendEmailComponent } from 'src/app/diaglogues/app.sendEmail';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

declare function drawBarGraph() : any;

@Component ({
    selector: 'portfolio',
    templateUrl: './app.portfolio.html',
    styleUrls: ['./app.portfolio.css'],
})

export class PortFolioComponent implements OnInit{


    employee: PersonalInfo;
    curEmploymentObj: EmploymentObj;

    constructor(private modalService: NgbModal, private updateService : UpdateBus, private dataService : WebService){
    }

    sendEmail(){
       const ref = this.modalService.open(SendEmailComponent);
        ref.componentInstance.to = this.employee.email;

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

    ngOnInit(){
        var type = this.updateService.getExpType();
        if(type == "MASTER"){
            this.updateService.setEmployeeDetail(LoggedUser.getUser());
        }

        this.employee = this.updateService.getEmployeeDetail();
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