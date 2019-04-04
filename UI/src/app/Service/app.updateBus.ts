import { Injectable } from "@angular/core";
import { PersonalInfo } from '../models/app.personalInfo';
import { WebService } from './app.webservice';
import { Observable } from 'rxjs';
import { LogInService } from './app.loginService';

@Injectable()
export class UpdateBus{

    private user : PersonalInfo;
    private employeeDetail : PersonalInfo;
    private expType : string;

    constructor(private dataService : WebService, private loginService : LogInService){

    }

    initUserDetail(){
        var eid = this.loginService.getEid();
        if(eid != null){
            this.dataService.getSingle<PersonalInfo>(eid)
            .subscribe((data : PersonalInfo) => {
               this.setUserDetail(data);
            },
            (error) => {

            },
            () => {

            }
            );
        }
    }
    
    getUserDetail(){
        if(this.getUserDetail() == null){
            this.initUserDetail();
        }
        return this.user;
    }

    setUserDetail(detail : PersonalInfo){
        this.user = detail;
    }
    getExpType(){
        return this.expType;
    }
    
    setExpType(type : string){
        this.expType = type;
    }

    getEmployeeDetail(){
        return this.employeeDetail;
    }

    setEmployeeDetail(detail :PersonalInfo){
        this.employeeDetail = detail;
    }
    
    resetEmployeeDetail() : Observable<any> {

        return this.dataService.getSingle<PersonalInfo>(this.employeeDetail.employeeId);
        
    }

}