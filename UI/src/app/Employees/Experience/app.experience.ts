import { Component, OnChanges, OnInit } from '@angular/core';
import { VariableAst } from '@angular/compiler';
import { error } from '@angular/compiler/src/util';
import { TData } from '@angular/core/src/render3/interfaces/view';
import { EmploymentObj } from 'src/app/models/app.employmentObj';
import { VendorObj } from 'src/app/models/app.vendorObj';
import { ClientObj } from 'src/app/models/app.clientObj';
import { Address } from 'src/app/models/app.address';
import { PersonalInfo } from 'src/app/models/app.personalInfo';
import { WebService } from 'src/app/Service/app.webservice';
import { UpdateBus } from 'src/app/Service/app.updateBus';

declare function validateGroupInputsByClassName(className : string) : boolean;

@Component({
    selector: 'experience',
    templateUrl: './app.experience.html',
    styleUrls: ['./app.experience.css']
})

export class ExperienceComponent implements OnInit {

    temp_EmploymentObj: EmploymentObj;
    temp_VendorObj: VendorObj;
    temp_ClientObj: ClientObj;
    temp_ClientAddressObj: Address;
    temp_VendorAddressObj: Address;

    temp_EmployeeObj: PersonalInfo;

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
    errorMessage : string = "Please fill all the required fields: "

    constructor(private updateService: UpdateBus, private dataService: WebService) {
    }

    toggleCollapse(collapseId, obj : EmploymentObj){

        if(collapseId == 'edit' && obj != null){

            this.temp_EmployeeObj = this.cur_employeeObj;
            this.temp_EmploymentObj = obj;
            this.temp_ClientObj = obj.client != null ? obj.client : this.newClientObj;
            this.temp_ClientAddressObj = this.temp_ClientObj.clientAddress != null ? this.temp_ClientObj.clientAddress : this.newClientAddressObj;
            this.temp_VendorObj = obj.vendor != null ? obj.vendor : this.newVendorObj;
            this.temp_VendorAddressObj = this.temp_VendorObj.vendorAddress != null ? this.temp_VendorObj.vendorAddress : this.newVendorAddressObj;

        } else if (collapseId == null || collapseId.length == 0 ||collapseId == 'new'){
            this.temp_ClientAddressObj = this.newClientAddressObj;
            this.temp_ClientObj = this.newClientObj;
            this.temp_EmployeeObj = this.newEmployeeObj;
            this.temp_EmploymentObj = this.newEmploymentObj;
            this.temp_VendorAddressObj = this.newVendorAddressObj;
            this.temp_VendorObj = this.newVendorObj;
        } 

        var curDisplay = (<HTMLElement>(document.getElementById('collapsible_edit_new'))).style.display;
        (<HTMLElement>(document.getElementById('collapsible_edit_new'))).style.display = 
        curDisplay == 'none' ? 'block' : 'none';

        curDisplay = (<HTMLElement>(document.getElementById('d-container'))).style.display;
        var curDisplay = (<HTMLElement>(document.getElementById('d-container'))).style.display;
        (<HTMLElement>(document.getElementById('d-container'))).style.display = 
        curDisplay == 'none' ? 'block' : 'none';

        
    
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
           if(!this.validateGroupInputsByClassName(classes[i])){
               return false;
           }
        }
        return  true;
    }

    validateGroupInputsByClassName(c: string) {
       return validateGroupInputsByClassName(c);
    }

    creteNewEmployee() {

        this.temp_EmploymentObj.employeeId = this.cur_employeeObj.employeeId;

        if (this.temp_ClientObj.clientName != null) {
            this.temp_EmploymentObj.client = this.temp_ClientObj;
        }
        if (this.temp_ClientObj != null && this.temp_ClientAddressObj.addressLine1 != null) {
            this.temp_ClientObj.clientAddress = this.temp_ClientAddressObj;
        }
        if (this.temp_VendorObj.vendorName != null) {
            this.temp_EmploymentObj.vendor = this.temp_VendorObj;
        }
        if (this.temp_VendorObj != null && this.temp_VendorAddressObj.addressLine1 != null) {
            this.temp_VendorObj.vendorAddress = this.temp_VendorAddressObj;
        }

        //New Employee Obj for submit
        this.temp_EmployeeObj = new PersonalInfo(this.cur_employeeObj.employeeId, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
            null, null, null, [this.temp_EmploymentObj], null);

        console.log("New Employee" + JSON.stringify(this.temp_EmployeeObj));

    }

    validateAndSubmit(val : string[]){

        if(this.isFormValid(val)){
            this.submitForm();
        }
    }

    submitForm() {

            this.creteNewEmployee();
            this.dataService
                .updateEmployee<PersonalInfo>(this.temp_EmployeeObj)
                .subscribe((data: any) => this.cur_employeeObj = data,
                    error => {
                        console.log("ERROR: " + JSON.stringify(error))
                        this.errorMessage = "There was an error processing the request."
                    },
                    () => {
                        console.log("SUCCESS");                        
                        console.log(JSON.stringify(this.cur_employeeObj));
                        this.updateService.setEmployeeDetail(this.cur_employeeObj);
                        this.errorMessage = "Please fill all the required fields:";
                        this.toggleCollapse(null,null);
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
    
        this.temp_ClientAddressObj = this.newClientAddressObj;
        this.temp_ClientObj = this.newClientObj;
        this.temp_EmployeeObj = this.newEmployeeObj;
        this.temp_EmploymentObj = this.newEmploymentObj;
        this.temp_VendorAddressObj = this.newVendorAddressObj;
        this.temp_VendorObj = this.newVendorObj;
    
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
        this.updateCurrentEmploymentDetails(obj);
    }
}