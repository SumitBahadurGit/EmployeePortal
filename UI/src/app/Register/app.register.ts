import { Component, OnChanges, OnInit, ViewChild } from '@angular/core';
import { PersonalInfo } from '../models/app.personalInfo';

import { Address } from '../models/app.address';
import { Work } from '../models/app.work';
import { Education } from '../models/app.education';
import { VendorObj } from '../models/app.vendorObj';
import { ClientObj } from '../models/app.clientObj';
import { EmploymentObj } from '../models/app.employmentObj';
import { UpdateBus } from '../Service/app.updateBus';
import { ActivatedRoute, Router } from '@angular/router';
import { WebService } from '../Service/app.webservice';
import { MailService } from '../Service/app.mailService';
import { template } from '@angular/core/src/render3';
import { ErrorSuccessComponent } from '../Templates/app.ERR_SUC';
import { Message } from '../models/app.message';
import { Login } from '../models/app.login';
import { states } from '../models/app.states';
import { LoggedUser } from '../Service/app.LoggedUser';


@Component({
  selector: 'register',
  templateUrl: './app.register.html',
  styleUrls: ['./app.register.css'],
})

export class RegisterComponent implements OnInit {

  @ViewChild(ErrorSuccessComponent)
  statusTemplate: ErrorSuccessComponent;

  currentTab: any = 0;
  education: Education;
  work: Work;
  address: Address;
  personalInfo: PersonalInfo;
  errorSubmit: boolean = false;
  accessLevel : string = "3";
  userRole : string = "EMPLOYEE"


  vendor: VendorObj;
  client: ClientObj;
  employmentObj: EmploymentObj[];

  creteEmptyObj() {
    this.address = new Address(null, null, null, null, null, null);
    this.education = new Education(null, null, null, null, null, null);
    this.work = new Work(null, null, null, null, null);

    this.personalInfo = new PersonalInfo(null,null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
      null, null, this.address, this.education, this.work, null, null);

    console.log(JSON.stringify(this.personalInfo));
  }

  ngOnInit() {
    this.creteEmptyObj();
    this.showTab(this.currentTab);

  }

  constructor(private dataService: WebService, 
    private updateService: UpdateBus, 
    private route: ActivatedRoute,
    private router: Router,
    private mailService : MailService) {

  }

  submit() {
    this.statusTemplate.loadingMessage="Creating Profile.";
    try{
      var query = "name="+this.personalInfo.firstName 
      +"&email="+this.personalInfo.email
      +"&userRole="+this.userRole;
      this.mailService.genNewId<Login>(query).subscribe((data : Login)  => {
        this.personalInfo.employeeId = data.eid;
      },
      (error) => {
        this.statusTemplate.loadingMessage = null;
        this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error creating new ID."));
      }, 
      () => {
        console.log(JSON.stringify(this.personalInfo));
        this.dataService
          .add(this.personalInfo)
          .subscribe(
            (data: PersonalInfo) => {
              this.personalInfo = data;
              console.log(data);
            },
            (error: any) => {
              this.statusTemplate.loadingMessage = null;
              this.statusTemplate.handleStatusMessage(Message.createErrorMessage("There was an error updating."));
            },
            () => {
              console.log("SUCCESSFULLY ADDED");              
              this.statusTemplate.loadingMessage = null;
              this.statusTemplate.handleStatusMessage(Message.createSuccessMessage("Successfully updated. New #ID: " + this.personalInfo.employeeId),10000);
            }
          );
      });
    } catch(error){
      this.statusTemplate.loadingMessage = null;
        throw ({error : "Error creting profile."});
    } finally{
      this.currentTab = 0;
      this.showTab(this.currentTab);
    }

  }

  getStates(){
    return states;
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
    this.errorSubmit = false;
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
