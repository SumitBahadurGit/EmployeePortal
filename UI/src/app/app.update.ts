import { Component, OnInit } from '@angular/core';
import { DataService } from './app.employeeService';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { EnrollComponent } from './app.enroll';
import { PersonalInfo } from './app.personalInfo';
import { UpdateService } from './app.updateService';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
    selector: 'update',
    templateUrl: './app.update.html',
    styleUrls: ['./app.update.css']
})

export class Update implements OnInit {

    myControl = new FormControl();
    allEmployees: PersonalInfo[];
    filteredEmployees: Observable<PersonalInfo[]>;
    isFirstSearch : boolean = true;
    employee: PersonalInfo;

    constructor(	private route: ActivatedRoute,
        private router: Router,
        private dataService: DataService, private updateService:UpdateService) {

            this.route.url.subscribe((data:any) => {this.ngOnInit()});

            this.employee = this.updateService.getEmployeeDetail();
    }

    getEmployeeDetails(){
        this.dataService
            .getSingle<PersonalInfo>(this.employee.employeeId)
            .subscribe((data : PersonalInfo) => this.employee = data,
                error => {
                    console.log("ERROR: " + error);
                },
                () => {
                    this.updateService.setEmployeeDetail(this.employee);
                    console.log("SUCCESS getting employee details.");
                    console.log(JSON.stringify(this.employee));
                    console.log("Navigating to Enroll.");
                    this.router.navigate(['Enroll'], { relativeTo: this.route });
                });
        }

    onSearchSearch(value : any){
        this.employee = value;
        this.isFirstSearch = false;       
        this.getEmployeeDetails();
        console.log("Selected Employee:" + JSON.stringify(this.employee));
        
    }

    ngOnInit() {
        
        this.employee = null;

        if(this.route.snapshot.paramMap.get("employeeId") != null){

            this.dataService
            .getSingle<PersonalInfo>(this.route.snapshot.paramMap.get("employeeId"))
            .subscribe((data : PersonalInfo) => this.employee = data,
                error => {
                    console.log("ERROR: " + error);
                },
                () => {
                    this.updateService.setEmployeeDetail(this.employee);
                    console.log("SUCCESS getting employee details.");
                    console.log(JSON.stringify(this.employee));
                    console.log("Navigating to Enroll.");
                    this.router.navigate(['Enroll'], { relativeTo: this.route });
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
                    console.log("Success");
                    this.filteredEmployees = this.myControl.valueChanges.pipe(
                        startWith(''),
                        map(value => this.filter(value))
                    );
                }
            );
        }


    }

    private filter(value: string): PersonalInfo[] {
        return this.allEmployees.filter(option => 
            (option.firstName+" "+option.lastName).startsWith(value) == true
            );

    }
}
