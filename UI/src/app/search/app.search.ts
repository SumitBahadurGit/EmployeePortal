import { Component, OnChanges, OnInit } from '@angular/core';


import { Router, ActivatedRoute } from '@angular/router';
import { PersonalInfo } from '../models/app.personalInfo';
import { WebService } from '../Service/app.webservice';

var currentTab: any = 0;

@Component({
    selector: 'searchResult',
    templateUrl: './app.search.html',
    styleUrls: ['./app.search.css'],
})

export class SearchComponent implements OnInit {

    searchResultsLoaded : boolean = false;
    dataLoaded : boolean = false;
    noMatches : boolean = false;

    private searchQuery: string;
    private dataList: PersonalInfo[] = [];
    
    constructor(private route: ActivatedRoute,
        private dataService: WebService, private router: Router) {
    }

    personalInfo = null;

    
    displayResults(){
        this.noMatches = false;
        this.searchResultsLoaded = true;
        this.dataLoaded = false;
    }

    seeProfile(event, arg){
       this.getSingle(arg);        
    }
    
    getSingle(query : any){
        this.dataService
            .getSingle<PersonalInfo>(query)
            .subscribe((data : PersonalInfo) => this.personalInfo = data,                
                error => {
                    console.log("ERROR:" + error);
                    this.noMatches = true;
                    this.dataLoaded = false;
                    this.searchResultsLoaded = false;
                },
                () => {
                    console.log("Success");
                    JSON.stringify(this.personalInfo);
                    this.noMatches = false;
                    this.dataLoaded = true;
                    this.searchResultsLoaded = false;
               }                
            );
    }

    ngOnInit() {

        this.route.queryParams.subscribe(params => {
            this.searchQuery = params['query'];
            var query = "?name="+this.searchQuery;
            this.dataService
            .getAll<PersonalInfo[]>(query)
            .subscribe((data: PersonalInfo[]) => this.dataList = data,
            error => {
                    console.log("ERROR:" + error);
                    this.noMatches = true;
                    this.dataLoaded = false;
                    this.searchResultsLoaded = false;
                    console.log(error);
            },
            () => {
                console.log('Success');
                this.displayResults();                
            });


        });
}
}