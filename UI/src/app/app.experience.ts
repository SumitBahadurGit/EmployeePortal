import { Component, OnChanges, OnInit } from '@angular/core';
import { UpdateService } from './app.updateService';
import { PersonalInfo } from './app.personalInfo';
import { EmploymentObj } from './app.employmentObj';
import { VendorObj } from './app.vendorObj';
import { ClientObj } from './app.clientObj';
import { Address } from './app.address';
import { DataService } from './app.employeeService';
import { VariableAst } from '@angular/compiler';
import { error } from '@angular/compiler/src/util';
import { TData } from '@angular/core/src/render3/interfaces/view';


@Component({
    selector: 'experience',
    templateUrl: './app.experience.html',
    styleUrls: ['./app.experience.css'],
})

export class ExperienceComponent implements OnInit {

    newEmploymentObj: EmploymentObj;
    newVendorObj: VendorObj;
    newClientObj: ClientObj;
    newClientAddressObj: Address;
    newVendorAddressObj: Address;
    newEmployeeObj: PersonalInfo;

    cur_VendorObj: VendorObj;
    cur_ClientObj: ClientObj;
    cur_ClientAddressObj: Address;
    cur_VendorAddressObj: Address;
    cur_employmentObj: EmploymentObj;

    cur_employeeObj: PersonalInfo;


    constructor(private updateService: UpdateService, private dataService: DataService) {
    }

    delete(id : string){

        this.dataService.delete<string>(id).subscribe((data:any)=>{},
            (error) => {console.log("ERROR DELETING: " + error)},
            () => {
                    console.log("SUCCESS DELETING: ");  
                    this.updateService.resetEmployeeDetail().subscribe(
                        (data: PersonalInfo) => {this.updateService.setEmployeeDetail(data) },
                        (error) => {
                            console.log("ERROR resetting" + error);
                        },
                        () => {
                            console.log("Success resetting");
                            
                            this.ngOnInit();
                        }
                    );

                }
            );
    }

    isFormValid(classes : string[]){
        for(var i = 0; i < classes.length; i++){
           if(this.validateGroupInputsByClassName(classes[i]) == false){
               return false;
           }
        }
        return  true;
    }

    validateGroupInputsByClassName(c: string) {
        var elems = document.getElementsByClassName(c);
        var valid = true;
        var anyInput = false;

        for (var i = 0; i < elems.length; i++) {

            var el = <HTMLInputElement>elems[i];
            var val = el.value;
            if (val.length > 0) {
                anyInput = true;
            }

            if (anyInput && val.length < 1) {
                valid  = false;
                el.setAttribute("style", "border : 1px solid red");
            } else {
                el.setAttribute("style", "border : 1px solid gray");
            }
        }
        return valid;
    }

    submitcurForm() {

        this.updateCurEmployee();
        console.log(JSON.stringify(this.cur_employeeObj));
        this.dataService
            .updateEmployee<PersonalInfo>(this.cur_employeeObj)
            .subscribe((data: any) => this.cur_employeeObj = data,
                error => {
                    console.log("ERROR: " + JSON.stringify(error))
                },
                () => {
                    console.log("SUCCESS------------------------------------------------------------------------");
                    console.log(JSON.stringify(this.cur_employeeObj));
                    this.updateService.setEmployeeDetail(this.cur_employeeObj);
                    document.getElementById("c2").click();
                    this.ngOnInit();

                }

            );
    }

    creteNewEmployee() {

        this.newEmploymentObj.employeeId = this.cur_employeeObj.employeeId;

        if (this.newClientObj.clientName != null) {
            this.newEmploymentObj.client = this.newClientObj;
        }
        if (this.newClientObj != null && this.newClientAddressObj.addressLine1 != null) {
            this.newClientObj.clientAddress = this.newClientAddressObj;
        }
        if (this.newVendorObj.vendorName != null) {
            this.newEmploymentObj.vendor = this.newVendorObj;
        }
        if (this.newVendorObj != null && this.newVendorAddressObj.addressLine1 != null) {
            this.newVendorObj.vendorAddress = this.newVendorAddressObj;
        }

        //New Employee Obj for submit
        this.newEmployeeObj = new PersonalInfo(this.cur_employeeObj.employeeId, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, [this.newEmploymentObj], null);

        console.log("New Employee" + JSON.stringify(this.newEmployeeObj));

    }

    validateAndSubmitNew(val : string[]){

        if(this.isFormValid(val)){
            this.submitNewForm();
        }
    }

    validateAndSubmitCur(val : string[]){

        if(this.isFormValid(val)){
            this.submitcurForm();
        }
    }

    submitNewForm() {


            this.creteNewEmployee();
            this.dataService
                .updateEmployee<PersonalInfo>(this.newEmployeeObj)
                .subscribe((data: any) => this.cur_employeeObj = data,
                    error => {
                        console.log("ERROR: " + JSON.stringify(error))
                    },
                    () => {
                        console.log("SUCCESS");
                        console.log(JSON.stringify(this.cur_employeeObj));
                        this.updateService.setEmployeeDetail(this.cur_employeeObj);
                        document.getElementById("c1").click();
                        this.ngOnInit();
                    }
                );
        
    }

    intializeNewEmployment() {
        this.newClientAddressObj = new Address(null, null, null, null, null, null);
        this.newVendorAddressObj = new Address(null, null, null, null, null, null);
        this.newVendorObj = new VendorObj(null, null, null, null, null, null, null, null, null);
        this.newClientObj = new ClientObj(null, null, null, null, null, null, null, null, null);
        this.newEmploymentObj = new EmploymentObj(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    initilizeCurrentEmployment() {

        this.cur_employeeObj = this.updateService.getEmployeeDetail();
        this.cur_employmentObj = null


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

            // Update nested objects (for easier rendering in html)
            if (this.cur_employmentObj != null) {
                this.updateCurrentEmploymentDetails(this.cur_employmentObj);
            }

        }        
    }

    updateCurrentEmploymentDetails(obj: EmploymentObj) {

        this.cur_ClientObj = obj.client != null ? obj.client : new ClientObj(null, null, null, null, null, null, null, null, null);
        this.cur_VendorObj = obj.vendor != null ? obj.vendor : new VendorObj(null, null, null, null, null, null, null, null, null);
        this.cur_ClientAddressObj = this.cur_ClientObj.clientAddress != null ? this.cur_ClientObj.clientAddress : new Address(null, null, null, null, null, null);
        this.cur_VendorAddressObj = this.cur_VendorObj.vendorAddress != null ?  this.cur_VendorObj.vendorAddress : new Address(null, null, null, null, null, null);
    }

    updateCurEmployee() {

        if (this.cur_ClientObj.clientName != null) {
            this.cur_employmentObj.client = this.cur_ClientObj;

            if (this.cur_ClientObj != null && this.cur_ClientAddressObj != null) {
                if(this.cur_ClientAddressObj.addressLine1 != null && this.cur_ClientAddressObj.addressLine1 != "" ){
                    this.cur_ClientObj.clientAddress = this.cur_ClientAddressObj;
                } else {
                    this.cur_ClientObj.clientAddress = null;
                }
            }
        } else {
            this.cur_ClientObj = null;
        }



        if (this.cur_VendorObj.vendorName != null) {
            this.cur_employmentObj.vendor = this.cur_VendorObj;

            if (this.cur_VendorObj != null && this.cur_VendorAddressObj != null) {
                if(this.cur_VendorAddressObj.addressLine1 != null && this.cur_VendorAddressObj.addressLine1 != ""){
                    this.cur_VendorObj.vendorAddress = this.cur_VendorAddressObj;
                } else {
                    this.cur_VendorObj.vendorAddress = null;
                }
            }
        } else {
            this.cur_VendorObj = null;
        }



        console.log("Updated Employee: " + JSON.stringify(this.cur_employmentObj));

    }

    ngOnInit() {

        this.initilizeCurrentEmployment();
        this.intializeNewEmployment();

    }

    showDetails(obj: EmploymentObj, event : Event) {

        this.cur_employmentObj = obj;
    }
}