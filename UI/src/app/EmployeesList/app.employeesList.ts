import { Component, OnInit } from "@angular/core";
import { WebService } from '../Service/app.webservice';
import { PersonalInfo } from '../models/app.personalInfo';
import { map } from 'rxjs/operators';
import { PaginatedWrapper } from '../models/app.paginatedWrapper';
import { SearchRequest } from '../models/app.searchRequest';

@Component({
    selector: 'employees-list',
    templateUrl : './app.employeesList.html',
    styleUrls : ['./app.employeesList.css']
})

export class EmployeesListComponent implements OnInit{

    fieldList : string[] = null;
    activeFieldList : string[] = null;
 
    
    employeesList : PersonalInfo[];
    paginatedSearch : PaginatedWrapper;

    start : number =  0;
    end : number = 0;
    sortBy : string = "none";
    filterBy : string = "empStatus";
    filterByValue : string = "ACTIVE";
    limit: number = 15;
    currPage : number = 1;
    totalPages : number = 0;
    totalRecords: number = 0;
    pageNumbers : number[] = [];


    getPageNumbers(){
        this.pageNumbers = [];
        var count = 10;
        for(var i = this.currPage - 5 ; count > 0; i++){
            if(i > this.totalPages){
                break;
            } else {
                if(i > 0) {
                    this.pageNumbers.push(i);
                    count--;
                } else {
                }
            }
        }
    }
    nextPrevPage(pageNo : number){
        if(pageNo > 1 && pageNo <= this.totalPages){
            this.currPage = pageNo;
            this.getEmployeesListByType();
        }
    }



    constructor(private dataService : WebService){
        this.fieldList = [
        'MANAGER','RECRUITER','SSN','ADDRESS',
        'Client Details','VENDOR DETAIL','ID','NAME','JOINED','STARTED'
        ,'CLIENT','VENDOR','WORK_STATUS'
        ,'JOB_TITLE','SALARY','EMAIL','PHONE'];


        this.activeFieldList = ['ID','NAME','JOINED','STARTED'
        ,'CLIENT','VENDOR','WORK_STATUS'
        ,'JOB_TITLE','SALARY','EMAIL','PHONE'];
        this.fieldList.sort();
        this.activeFieldList.sort();
    }
    
    isActive(field){
        return this.activeFieldList.includes(field);
    }

    clickEmployeeType(event : Event){

        var filterType = (<HTMLUListElement> event.target).innerText;
        if(filterType != null && filterType.length > 0){
            this.filterByValue = filterType.toUpperCase();
            this.currPage = 1;
            this.getEmployeesListByType();
        }

    }

    toggleActive(field : string){

        if(this.activeFieldList.includes(field)){
            var index = this.activeFieldList.indexOf(field);
            this.activeFieldList.splice(index,1);
        } else {
            this.activeFieldList.push(field);
        }

    }

    getTitle(title : string){
        if (this.activeFieldList.includes(title)) {
            return true;
        } else {
            return false;
        }        
    }

    getEmployeesListByType(){
        this.paginatedSearch = new PaginatedWrapper(null,null,null,null,null,this.limit,this.currPage,new SearchRequest(this.sortBy, this.filterBy,this.filterByValue));
        this.dataService.getAllEmployessByQuery<PaginatedWrapper>(this.paginatedSearch)
        .subscribe((data : PaginatedWrapper) => {
            this.paginatedSearch = data;
            this.employeesList = this.paginatedSearch.personalInfo;
            this.totalPages = this.paginatedSearch.totalPages;
            this.totalRecords = this.paginatedSearch.totalRecords;
            this.start = this.paginatedSearch.start;
            this.end = this.paginatedSearch.end;
        },
        (error) => {
            console.log("ERROR getting employees list: " + error);
        },
        () => {
            console.log('SUCCESS getting employees list.');
            this.getPageNumbers();
           
         });
       
    }

    ngOnInit(){

        this.getEmployeesListByType();

    
    }
}