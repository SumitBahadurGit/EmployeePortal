import { Component, OnChanges, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Education } from 'src/app/models/app.education';
import { Work } from 'src/app/models/app.work';
import { Address } from 'src/app/models/app.address';
import { PersonalInfo } from 'src/app/models/app.personalInfo';
import { VendorObj } from 'src/app/models/app.vendorObj';
import { ClientObj } from 'src/app/models/app.clientObj';
import { EmploymentObj } from 'src/app/models/app.employmentObj';
import { WebService } from 'src/app/Service/app.webservice';
import { UpdateBus } from 'src/app/Service/app.updateBus';
import { LoggedUser } from 'src/app/Service/app.LoggedUser';
import { ErrorSuccessComponent } from 'src/app/Templates/app.ERR_SUC';
import { Message } from 'src/app/models/app.message';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { LogInService } from 'src/app/Service/app.loginService';



@Component({
  selector: 'update-profile',
  templateUrl: './app.update-profile.html',
  styleUrls: ['./app.update-profile.css'],
})

export class UpdateProfileComponent implements OnInit {

  @ViewChild(ErrorSuccessComponent)
  statusTemplate: ErrorSuccessComponent;

  currentTab: any = 0;
  education: Education;
  work: Work;
  address: Address;
  personalInfo: PersonalInfo;

  vendor: VendorObj;
  client: ClientObj;
  employmentObj: EmploymentObj[];

  creteEmptyObj() {
    this.address = new Address(null, null, null, null, null, null);
    this.education = new Education(null, null, null, null, null, null);
    this.work = new Work(null, null, null, null, null);

    this.personalInfo = new PersonalInfo(this.loginService.getLoginId(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
      null, null, this.address, this.education, this.work, null, null);

    console.log(JSON.stringify(this.personalInfo));
  }

  ngOnInit() {      
    var eid = this.loginService.getEid();
    if(eid == null){
      this.creteEmptyObj();                
      this.statusTemplate.setErrorMessgae("Looks like you have not updated the profile. Please update your personal info.", 5000);

      this.showTab(this.currentTab);
    } else {
      this.retreiveProfile(eid)
      .subscribe((data : PersonalInfo) => {
          this.personalInfo = data;
      },
      (error) => {
          this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error retreiving the profile."));            
        }, 
      () => {
          this.showTab(this.currentTab);
      });
    }

  }


  constructor(private dataService: WebService,
     private updateService: UpdateBus, private route: ActivatedRoute,
     private loginService: LogInService,
     private router: Router) {

  }

  retreiveProfile(eid : string){

      return  this.dataService.getSingle<PersonalInfo>(eid);
  }

  update(){
    this.dataService
    .updateEmployee(this.personalInfo)
    .subscribe(
      (data: PersonalInfo) => {
        this.personalInfo = data;
        console.log(data);
      },
      (error: any) => {
        console.log("ERROR ADDING:" + error);
        this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error updating the info."));
      },
      () => {
        console.log("SUCCESSFULLY ADDED");
        this.statusTemplate.handleStatusMessage(Message.createSuccessMessage("Successfully Updated."));
      }
    );
  }
  save(){

    this.dataService
    .add(this.personalInfo)
    .subscribe(
      (data: PersonalInfo) => {
        this.personalInfo = data;
        this.loginService.setEid(this.personalInfo.employeeId);
        LoggedUser.createNew(this.personalInfo);
        LoggedUser.setUserRole(this.loginService.getUserRole());
        console.log(data);
      },
      (error: any) => {
        console.log("ERROR ADDING:" + error);
        this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error updating the info."));
      },
      () => {
        console.log("SUCCESSFULLY ADDED");
        this.statusTemplate.handleStatusMessage(Message.createSuccessMessage("Successfully Updated."));
      }
    );
  }
  submit() {

    console.log(JSON.stringify(this.personalInfo));
    this.currentTab = 0;
    this.showTab(this.currentTab);
    if(this.loginService.getEid() == null){
      this.save();
    } else {
      this.update();
    }

  }

  showTab(n) {
    // This function will display the specified tab of the form ...
    var x = document.getElementsByClassName("tab");

    if (x != null && x.length > 0) {
      (<any>x[n]).style.display = "block";
      // ... and fix the Previous/Next buttons:
      if (n == 0) {
        document.getElementById("prevBtn").style.display = "none";
      } else {
        document.getElementById("prevBtn").style.display = "inline";
      }
      if (n == (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Submit";
      } else {
        document.getElementById("nextBtn").innerHTML = "Next";
      }
      // ... and run a function that displays the correct step indicator:
      this.fixStepIndicator(n);
    }

  }

  nextPrev(n) {
    var formValid = true;

    // This function will figure out which tab to display
    var x = document.getElementsByClassName("tab");
    // Exit the function if any field in the current tab is invalid:
    if( n > 0 && n < x.length){
      if (!this.validateForm()) return false;
    }

    // Hide the current tab:
    (<any>x[this.currentTab]).style.display = "none";
    // Increase or decrease the current tab by 1:
    this.currentTab = this.currentTab + n;
    // if you have reached the end of the form... :
    if (this.currentTab >= x.length) {
      //...the form gets submitted:
      this.submit();
      return false;
    }
    // Otherwise, display the correct tab:
    this.showTab(this.currentTab);
  }

  validateForm() {
    // This function deals with validation of the form fields
    var x, y, i, valid = true;

    x = document.getElementsByClassName("tab");

    y = x[this.currentTab].getElementsByTagName("input");

    // A loop that checks every input field in the current tab:
    for (i = 0; i < y.length; i++) {
      // If a field is empty...
      if (y[i].value == null || y[i].value.length == 0) {
        // add an "invalid" class to the field:
        var className = <string>y[i].className;
        if (className.includes("req")) {
          y[i].className += " invalid";
          valid = false;
          break;
        }
        // and set the current valid status to false:
      }
    }
    // If the valid status is true, mark the step as finished and valid:
    if (valid) {
      this.fixStepFinish();

    }
    return valid; // return the valid status
  }

  fixStepFinish() {
    // This function removes the "active" class of all steps...
    var i = 0;
    var x = document.getElementById("step-wrapper").children;

    //... and adds the "active" class to the current step:
    x[this.currentTab].className += " finish";

  }

  fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    var i = 0;
    var x = document.getElementById("step-wrapper").children;

    for (i = 0; i < x.length; i++) {

      x[i].className = x[i].className.replace(" active", " ");

    }
    //... and adds the "active" class to the current step:
    x[n].className += " active";

  }

}
