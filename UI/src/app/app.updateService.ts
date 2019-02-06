import { Injectable } from "@angular/core";
import { PersonalInfo } from './app.personalInfo';
import { DataService } from './app.employeeService';
import { Observable } from 'rxjs';

@Injectable()
export class UpdateService{

    private employeeDetail : PersonalInfo;

    constructor(private dataService : DataService){
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