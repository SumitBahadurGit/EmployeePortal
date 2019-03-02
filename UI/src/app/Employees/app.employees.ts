import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { PersonalInfo } from '../models/app.personalInfo';
import { WebService } from '../Service/app.webservice';
import { UpdateBus } from '../Service/app.updateBus';

declare function autocomplete(param1: any, param2: any): void;


@Component({
    selector: 'employees',
    templateUrl: './app.employees.html',
    styleUrls: ['./app.employees.css']
})

export class EmployeesComponent implements OnInit {

    myControl = new FormControl();
    allEmployees: PersonalInfo[];
    filteredEmployees: Observable<PersonalInfo[]>;
    isFirstSearch: boolean = true;
    employee: PersonalInfo;
    query = "";





    constructor(private route: ActivatedRoute,
        private router: Router,
        private dataService: WebService, private updateService: UpdateBus) {

        this.route.url.subscribe((data: any) => { this.ngOnInit() });

        this.employee = this.updateService.getEmployeeDetail();
    }

    getEmployeeDetails(q) {
        this.dataService
            .getSingle<PersonalInfo>(q)
            .subscribe((data: PersonalInfo) => this.employee = data,
                error => {
                    console.log("ERROR: " + error);
                },
                () => {
                    this.updateService.setEmployeeDetail(this.employee);
                    console.log("SUCCESS getting employee details.");
                    console.log(JSON.stringify(this.employee));
                    console.log("Navigating to Enroll.");
                    this.router.navigate(['portfolio'], { relativeTo: this.route });

                  //  this.router.navigate(['documents'], { relativeTo: this.route });
                });
    }


    ngOnInit() {
       // this.getEmployeeDetails(56331);
      //  return;
        this.employee = null;

        if (this.route.snapshot.paramMap.get("employeeId") != null) {

            this.dataService
                .getSingle<PersonalInfo>(this.route.snapshot.paramMap.get("employeeId"))
                .subscribe((data: PersonalInfo) => this.employee = data,
                    error => {
                        console.log("ERROR: " + error);
                    },
                    () => {
                        this.updateService.setEmployeeDetail(this.employee);
                        console.log("SUCCESS getting employee details.");
                        console.log(JSON.stringify(this.employee));
                        console.log("Navigating to Enroll.");
                        this.router.navigate(['portfolio'], { relativeTo: this.route });
                    });

            this.route.snapshot.paramMap.get("employeeId") == null;

        } else {

            this.dataService
                .getAllNoQuery<PersonalInfo[]>()
                .subscribe((data: PersonalInfo[]) => this.allEmployees = data,
                    error => {
                        console.log("ERROR:" + error);
                    },
                    () => {
                        console.log("Success. " + JSON.stringify(this.allEmployees));
                        var temp = this.allEmployees.map(x => {
                            return  x.firstName + " " + x.lastName + "  (" + x.employeeId+")";
                        });

                        if (document.getElementById("myInput") != null) {
                            autocomplete(document.getElementById("myInput"), temp);
                            document.getElementById("myInput").addEventListener("click", function (e) {

                                if ((<HTMLInputElement>document.getElementById("myInput")).value.length > 0) {

                                    var q = (<HTMLInputElement>document.getElementById("myInput")).value;
                                    if(q != null && q.length > 0 && q.includes("(")){
                                        q = q.substring(q.length - 6,q.length-1);
                                        this.getEmployeeDetails(q);
                                    }

                                   
                                }
                            }.bind(this));

                        }
                    }
                );
        }
    }

}
