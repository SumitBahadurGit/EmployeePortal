import {Component, OnChanges, OnInit, Input } from '@angular/core';
import { PersonalInfo } from './app.personalInfo';
import { UpdateService } from './app.updateService';
import { EmploymentObj } from './app.employmentObj';
import { DataService } from './app.employeeService';
import { DocumentObj } from './app.documentObj';

declare function drawBarGraph() : any;

@Component ({
    selector: 'enroll',
    templateUrl: './app.enroll.html',
    styleUrls: ['./app.enroll.css'],
})

export class EnrollComponent implements OnInit{

    employee: PersonalInfo;
    curEmploymentObj: EmploymentObj;

    constructor( private updateService : UpdateService, private dataService : DataService){

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