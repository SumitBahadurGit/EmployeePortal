import { Injectable } from "@angular/core";
import { PersonalInfo } from '../models/app.personalInfo';
import { WebService } from './app.webservice';
import { Observable } from 'rxjs';

@Injectable()
export class UpdateBus{

    private employeeDetail : PersonalInfo;
    private expType : string;

    constructor(private dataService : WebService){
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