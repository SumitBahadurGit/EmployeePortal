import { Component, OnInit } from "@angular/core";
import { AppModule } from 'src/app/app.module';
import { TimesheetsObjWrapper } from 'src/app/models/app.timesheetsObjWrapper';
import { TimeSheetsObj } from 'src/app/models/app.timesheetObj';
import { UpdateBus } from 'src/app/Service/app.updateBus';
import { TimesheetsService } from 'src/app/Service/app.timesheetService';
import { Observable } from 'rxjs';
import { validateConfig } from '@angular/router/src/config';
import { LoggedUser } from 'src/app/Service/app.LoggedUser';

@Component({
    selector: 'timesheets',
    templateUrl : './app.timesheets.html',
    styleUrls: ['./app.timesheets.css']
})


export class TimeSheetComponent implements OnInit{

    doesDateExist: boolean = false;
    tsWrapper : TimesheetsObjWrapper;
    oldTsInfo : TimesheetsObjWrapper[];

    errrorMessageQueue : string = null;
    successMessageQueue : string = null;
    errorMessage : string = null;
    successMessage : string = null;
    
    loadingMessage : string = null;
    employeeId : string;
    projectDetails : string = "";
    curStatus : string;
    tsStatus : string[] = ["Submitted", "Awaiting submission", "Approved", "Rejected"];
    projectLocation : string[];
    selectedProject : string;
    startDate : Date;
    endDate : Date;    
    days : String[] =  [        
        'Sun',
        'Mon',
        'Tues',
        'Wed',
        'Thurs',
        'Fri',
        'Sat',
      ];
    months : String[] =  [        
        'Jan',
        'Feb',
        'Mar',
        'Apr',
        'May',
        'June',
        'July',
        'Aug',
        'Sept',
        'Oct',
        'Nov',
        'Dec'

      ];
    time : string[] = ["12 AM", "1 AM", "2 AM",
    "3 AM", "4 AM", "5 AM",
    "6 AM", "7 AM", "8 AM",
    "9 AM", "10 AM", "11 AM","12 PM", "1 PM", "2 PM",
    "3 PM", "4 PM", "5 AM",
    "6 AM", "7 AM", "8 AM","9 PM", "10 PM", "11 PM"]
    hours : number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24];
    dates : Date[];

    timesheetTypes : string[] = ['Weekly', 'Monthly', 'Daily', "Custom"];
    curTimesheetType : string = 'Custom';


    constructor(private updateBus : UpdateBus, private service : TimesheetsService){
        this.defaultDetails();
    }

    setErrorMessgae(message : string, timeout : number){
        if(this.errorMessage == null && this.successMessage == null && this.loadingMessage == null){
            setTimeout(() => {this.errorMessage = message;
                setTimeout(() => {this.errorMessage = null;
               }, timeout);},1);        
        }
    }

    setSuccessMessage(message : string,  timeout : number){
        if(this.errorMessage == null && this.successMessage == null && this.loadingMessage == null){
            setTimeout(() => {this.successMessage = message;
                setTimeout(() => {this.successMessage = null;   
               }, timeout);},1);
        }
    }

    setLoadingMessage(message : string,  timeout : number){
        if(this.errorMessage == null && this.successMessage == null && this.loadingMessage == null){
            setTimeout(() => {this.loadingMessage = message;
                setTimeout(() => {this.loadingMessage = null;  
                    if(this.errrorMessageQueue != null){
                        this.setErrorMessgae(this.errrorMessageQueue, 2000);
                        this.errrorMessageQueue = null;
                    } else if(this.successMessageQueue != null){
                        this.setSuccessMessage(this.successMessageQueue, 2000);
                        this.successMessageQueue = null;
                    }
               }, timeout);},0);
        }
    }

    defaultDetails(){
        this.tsWrapper = null;
        // Date is for the default datePicker
        this.startDate = new Date();
        this.endDate = new Date();
        this.endDate.setDate(this.startDate.getDate() + 7);
        //The default is Custom
        this.curTimesheetType = this.timesheetTypes[3];

        this.curStatus = this.tsStatus[1];

        var type = this.updateBus.getExpType();
        this.projectLocation = [];
        
        var type = this.updateBus.getExpType();

        if(type == "MASTER"){
            this.updateBus.setEmployeeDetail(LoggedUser.getUser());
        }


        this.employeeId = this.updateBus.getEmployeeDetail().employeeId;
        this.updateBus
        .getEmployeeDetail().employmentObj.forEach(e => {
            //if(e.status.toUpperCase() == "ACTIVE")
            {
                if(e.client != null){
                    this.employeeId = e.employeeId;
                    this.projectLocation.push(e.client.clientName);
                }
            }
        });        
        this.selectedProject = this.projectLocation[0];   
        
         
    }

    updateDetails(){

        if(this.tsWrapper != null && this.tsWrapper.timesheets != null){
            var ts = this.tsWrapper.timesheets[0];
            this.curStatus = ts.status;
            this.selectedProject = ts.projectLocation;
            this.projectDetails = ts.projectDetails;            
        }
    }
    
    //Creates new timesheet object for saving
    validateAndCreate(){

        this.setLoadingMessage("CREATING TIMESHEETS",1000);            

        this.service.check<boolean>(this.employeeId,  new TimesheetsObjWrapper(null,this.startDate,null,this.employeeId,null,null,this.selectedProject))
        .subscribe((data : boolean) => {
            this.doesDateExist = data;           
        },
        (error) => {
            console.log("ERROR CHECKING TS" + error);
            this.defaultDetails();
        },
        () => {            
            console.log("SUCEES CHECKING TS");
            if(this.doesDateExist){
                this.errrorMessageQueue = "Timesheet for this date was already submitted.";
                this.defaultDetails();
            }   else {
                this.getDateArray();
                if(this.dates != null && this.dates.length > 0){
                    this.tsWrapper = new TimesheetsObjWrapper(null,this.startDate,this.endDate,this.employeeId,null,"N",null);
                    this.tsWrapper.startDate = this.dates[0];
                    this.tsWrapper.endDate = this.dates[this.dates.length - 1];
                    this.tsWrapper.submittedDate = new Date();
                    this.tsWrapper.timesheets = [];          
                    this.curStatus = this.tsStatus[1];
                    this.dates.forEach(d => {
                        this.tsWrapper.timesheets.push(
                            new TimeSheetsObj(
                                null,
                                null,
                                this.curStatus,
                                d,
                                "0",
                                "0",
                                "0",
                                "0",
                                null,
                                null,
                                "N",
                                null,
                               this.tsWrapper.submittedDate,
                                null,                        
                                "n/a")
                            );              
                    });           
                }
            }   
        }
        );
    }

    parserStartDate(event : any){
        this.startDate = new Date(event.target.value+"T10:20:30Z");
    }

    parserEndDate(event : any){
        this.endDate = new Date(event.target.value+"T10:20:30Z");
    }
    
    //Gets the dates picked between two datePickers
    //Adjust the week, months
    getDateArray(){
        if(this.curTimesheetType == this.timesheetTypes[0] /* Weekly */){
            if(this.startDate != null){

                //Get the current day of week
                var daysToAdjust = this.startDate.getDay();
                console.log("DAYS TO ADJUST: " + daysToAdjust + " in " + this.startDate.getDate());
                this.startDate.setDate(this.startDate.getDate() - daysToAdjust);
                console.log("UP DATE: " + JSON.stringify(this.startDate));
                this.dates = [];
                for (var i = 0 ; i < 7; i++){
                    var d = new Date(this.startDate.toDateString());
                    d.setDate(this.startDate.getDate() + i);
                    this.dates.push(d);
                }
            }
        } else if(this.curTimesheetType == this.timesheetTypes[1] /* Monthly */){
            if(this.startDate != null){
                // Go to the first day of the month
                var newDate = new Date(this.startDate.getFullYear(), this.startDate.getMonth());
                console.log("CUR DATE:" + JSON.stringify(this.startDate));
                console.log("NEW DATE:" + JSON.stringify(newDate));
                this.startDate = newDate;
                console.log("UP CUR DATE:" + JSON.stringify(this.startDate));
                this.dates = [];
                var loop = true;
                var i = 0;
                var curMonth = this.startDate.getMonth();
                while(loop == true){
                    var d = new Date(this.startDate.toDateString());
                    d.setDate(this.startDate.getDate() + i);                    
                    console.log("TEMP DATE:" + JSON.stringify(d));
                    if(d.getMonth() == curMonth){
                        this.dates.push(d);
                        i++;
                    } else {
                        console.log("NOT MATCH: " + curMonth + "-" + d.getMonth());
                        loop = false;
                    }
                }
            }
        } else if(this.curTimesheetType == this.timesheetTypes[3]/* Custom */){
            if(this.startDate != null && this.endDate != null){
                var datediff = this.endDate.getTime() - this.startDate.getTime();
                var daysDiff = datediff / (24 * 60 * 60 * 1000);
                if(datediff > 1){
                    this.dates = [];
                    for (var i = 0 ; i <= daysDiff; i++){
                        var d = new Date(this.startDate.toDateString());
                        d.setDate(this.startDate.getDate() + i);
                        this.dates.push(d);
                    }
                }
            }
        }

    }
 
    // Get all the previous timesheets for a specific location
    ngOnInit(){     
        this.getAllTimeSheetsInfo();
    }

    saveTimeSheets(){
        this.setLoadingMessage("SAVING TIMESHEETS",1000);            

        this.tsWrapper.timesheets.forEach(t => {
            t.projectDetails = this.projectDetails;
            t.projectLocation = this.selectedProject;
            t.status = this.tsStatus[1];
            t.employeeId = this.employeeId;
        });
        this.persistTimeSheets()
        .subscribe((data : TimesheetsObjWrapper) => {this.tsWrapper = data;              
        },
        (error) => {
            console.log("ERROR saving TS: " + error);
            this.defaultDetails();
            this.errrorMessageQueue = "There was an error saving the timesheets.";
        },
        () => {
            console.log("SUCCESSFULLY SAVED TS:********************************** ");
            console.info(JSON.stringify(this.tsWrapper));
            this.dates = [];
            this.tsWrapper = null;
            this.getAllTimeSheetsInfo();
            this.defaultDetails();
            this.successMessageQueue = "Successfully saved.";
        });
    }

    submitTimeSheets(){
        this.setLoadingMessage("SUBMITTING TIMESHEETS",1000);            

        this.tsWrapper.timesheets.forEach(t => {
            t.projectDetails = this.projectDetails;
            t.projectLocation = this.selectedProject;
            t.status = this.tsStatus[0];
            t.employeeId = this.employeeId;            
        });
        this.persistTimeSheets()
        .subscribe((data : TimesheetsObjWrapper) => {this.tsWrapper = data;              
        },
        (error) => {
            console.log("ERROR saving TS: " + error);
            this.defaultDetails();
            this.errrorMessageQueue = "There was an error submitting the timesheets.";
        },
        () => {
            console.log("SUCCESSFULLY SAVED TS:********************************** ");
            console.info(JSON.stringify(this.tsWrapper));
            this.dates = [];
            this.tsWrapper = null;
            this.getAllTimeSheetsInfo();
            this.defaultDetails();
            this.successMessageQueue = "Successfully submitted.";
        });         
    }

    //Gets timesheets for a range
    getSpecificTimeSheets(){

        this.setLoadingMessage("RETREIVING TIMESHEETS",1000);            
        this.tsWrapper.includeTimesheets="Y";
        this.tsWrapper.projectLocation = this.selectedProject;
        if(this.tsWrapper.startDate != null && this.tsWrapper.endDate != null && this.tsWrapper.employeeId != null){
            this.getTimeSheets(this.tsWrapper)
            .subscribe((data : TimesheetsObjWrapper[]) => {
                if(data != null && data.length > 0){
                    data[0].timesheets.forEach(d => {
                        d.timesheetDate = new Date(d.timesheetDate+"T10:20:30Z");
                    });
                    data[0].startDate = new Date(data[0].startDate+"T10:20:30Z");
                    data[0].endDate = new Date(data[0].endDate+"T10:20:30Z");
        
                  this.tsWrapper = data[0];
                } else {
                    this.errrorMessageQueue = "There was an error retreiving timesheets.";
                    this.defaultDetails();
                }
            },
            (error) => {
                console.log("ERROR GETTING TS" + error);
                this.defaultDetails();
                this.errrorMessageQueue = "There was an error retreiving timesheets.";

            },
            () => {
                console.log("SUCEESS GETTING TS");
                this.updateDetails();
            }
            ); 
        }

    }

    //Gets all timesheets
    getAllTimeSheetsInfo(){

            var obj = new TimesheetsObjWrapper(null,null,null,this.employeeId,null,"N",this.selectedProject);

            this.getTimeSheets(obj)
            .subscribe((data : TimesheetsObjWrapper[]) => {
                if(data == null || data.length == 0 ){
                  //  this.setErrorMessgae("There was an error loading older timesheets.");
                    this.defaultDetails();
                } else {
                    this.oldTsInfo = data;
                }

            },
            (error) => {
                console.log("ERROR GETTING TS" + error);
                this.defaultDetails();
                this.setErrorMessgae("There was an error retreiving the timesheets.", 3000);

            },
            () => {
            }
            );       
    }

    //Call backend for retreival
    getTimeSheets(obj : TimesheetsObjWrapper) {
        return this.service.getAll<TimesheetsObjWrapper[]>(this.employeeId, obj);
    }
    
    //Call Backend for saving the timesheets
    persistTimeSheets(){
        console.log("SAVING: " + JSON.stringify(this.tsWrapper));
        return this.service.save<TimesheetsObjWrapper>(this.tsWrapper);
    }
}