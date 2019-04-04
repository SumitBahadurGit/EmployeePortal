import { Component, OnInit, ViewChild } from "@angular/core";
import { EmploymentObj } from '../models/app.employmentObj';
import { ClientObj } from '../models/app.clientObj';
import { VendorObj } from '../models/app.vendorObj';
import { Address } from '../models/app.address';
import { PersonalInfo } from '../models/app.personalInfo';
import { UpdateBus } from '../Service/app.updateBus';
import { WebService } from '../Service/app.webservice';
import { ErrorSuccessComponent } from '../Templates/app.ERR_SUC';
import { LoggedUser } from '../Service/app.LoggedUser';

@Component({
    selector : "employmentHist",
    templateUrl : "./app.employement.html",
    styleUrls : ["./app.employement.css"]
})

export class EmploymentHistComponent implements OnInit{
    
    @ViewChild(ErrorSuccessComponent)
    statusTemplate : ErrorSuccessComponent;

    cur_VendorObj: VendorObj;
    cur_ClientObj: ClientObj;
    cur_ClientAddressObj: Address;
    cur_VendorAddressObj: Address;
    cur_employmentObj: EmploymentObj;

    cur_employeeObj: PersonalInfo;

    constructor(private updateService: UpdateBus) {
    }

    populateChildren(){
        if(this.cur_employeeObj != null && this.cur_employmentObj != null){
            this.cur_ClientObj = this.cur_employmentObj.client;
            if(this.cur_ClientObj != null){
                this.cur_ClientAddressObj = this.cur_ClientObj.clientAddress;
            }
            this.cur_VendorObj = this.cur_employmentObj.vendor;
            if(this.cur_VendorObj != null){
                this.cur_VendorAddressObj = this.cur_VendorObj.vendorAddress;
            }
        }
    }

    initilizeCurrentEmployment() {

        this.cur_employeeObj =  LoggedUser.getUser();
        this.cur_employmentObj = null;

        if (this.cur_employeeObj != null && this.cur_employeeObj.employmentObj != null) {

            // If Active, make it the current object in display
            for (var i = 0; i < this.cur_employeeObj.employmentObj.length; i++) {
                if (this.cur_employeeObj.employmentObj[i].status == 'Active') {
                    this.cur_employmentObj = this.cur_employeeObj.employmentObj[i];
                    break;
                }
            }
            // If No Active profiles present, choose the first option
            if (this.cur_employmentObj == null && this.cur_employeeObj.employmentObj.length > 0) {
                this.cur_employmentObj = this.cur_employeeObj.employmentObj[0];
            }

            this.populateChildren();
        } else {
            this.statusTemplate.successMessage = "Currently, there is no history of your projects.";
        }
    }

    ngOnInit() {

        this.initilizeCurrentEmployment();


    }

    showDetails(obj: EmploymentObj, event : Event) {

        this.cur_employmentObj = obj;
    }


}