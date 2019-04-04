import { Component, OnInit, ViewChild, OnDestroy } from "@angular/core";
import { TimesheetsObjWrapper } from 'src/app/models/app.timesheetsObjWrapper';
import { TimesheetsService } from 'src/app/Service/app.timesheetService';
import { LoggedUser } from 'src/app/Service/app.LoggedUser';
import { UpdateBus } from 'src/app/Service/app.updateBus';
import { ErrorSuccessComponent } from 'src/app/Templates/app.ERR_SUC';
import { throwError } from 'rxjs';
import { GlobalSettingsService } from 'src/app/Service/app.globalSettings';

@Component({
    selector: 'timesheets',
    templateUrl: './app.timesheets.html',
    styleUrls: ['./app.timesheets.css']
})


export class TimeSheetComponent implements OnInit, OnDestroy {

    @ViewChild(ErrorSuccessComponent)
    statusTemplate : ErrorSuccessComponent;

    doesDateExist: boolean = false;
    tsWrapper: TimesheetsObjWrapper;
    oldTsInfo: TimesheetsObjWrapper[];

    viewTimeSheets: boolean = false;

    loadingMessage: string = null;
    employeeId: string;
    projectDetails: string = "";
    curStatus: string;
    tsStatus: string[] = ["Submitted", "Awaiting submission", "Approved", "Rejected"];
    projectLocation: string[];
    selectedProject: string;

    startDate: Date;
    endDate: Date;
    desc: string;
    days: String[] = [
        'Sun',
        'Mon',
        'Tues',
        'Wed',
        'Thurs',
        'Fri',
        'Sat',
    ];
    months: String[] = [
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
    hours: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24];
    dates: Date[];
    timesheetTypes: string[] = ['Weekly', 'Monthly', 'Daily', "Custom"];
    curTimesheetType: string = 'Custom';

    constructor(private updateBus: UpdateBus,
         private service: TimesheetsService,
         private globalSetting: GlobalSettingsService) {
     }

    updateDetails() {

        if (this.tsWrapper != null && this.tsWrapper.timesheets != null) {
            var ts = this.tsWrapper.timesheets[0];
            this.curStatus = ts.status;
            this.selectedProject = ts.projectLocation;
            this.projectDetails = ts.projectDetails;
            this.desc = ts.desc;
        }
    }

    parserStartDate(event: any) {
        this.startDate = new Date(event.target.value + "T10:20:30Z");
    }

    parserEndDate(event: any) {
        this.endDate = new Date(event.target.value + "T10:20:30Z");
    }

    approve() {
        try {
            this.tsWrapper.timesheets.forEach(t => {
                t.status = this.tsStatus[2];
                t.isApproved = "Y";
                t.approvedDate = new Date();
                t.approvedBy = LoggedUser.getUser().firstName + " " + LoggedUser.getUser().lastName;
                t.desc = this.desc;
                this.curStatus = this.tsStatus[2];
            });
            this.persistTimeSheets()
                .subscribe((data: TimesheetsObjWrapper) => {
                 //   this.getSpecificTimeSheets();
                },
                    (error) => {
                        this.statusTemplate.setErrorMessgae("There was an error.",1500);
                        console.log("Error approving timesheets: " + error);
                    },
                    () => {
    
                    });
        } catch(error){
            this.statusTemplate.setErrorMessgae("There was an error.",1500);
            console.log("Error approving timesheets: " + error);
        } finally{

        }

    }

    reject() {
        try {
            this.tsWrapper.timesheets.forEach(t => {
                t.status = this.tsStatus[3];
                t.isApproved = "N";
                t.desc = this.desc;
                this.curStatus = this.tsStatus[3];
            });
            this.persistTimeSheets()
                .subscribe((data: TimesheetsObjWrapper) => {
                   // this.getSpecificTimeSheets();
                },
                    (error) => {
                        this.statusTemplate.setErrorMessgae("There was an error.",1500);
                        console.log("Error approving timesheets: " + error);
                    },
                    () => {
    
                    });
        } catch(error){
            this.statusTemplate.setErrorMessgae("There was an error.",1500);
            console.log("Error approving timesheets: " + error);
        } finally {

        }

    }

    // Get all the previous timesheets for a specific location
    ngOnInit() {
        this.employeeId = this.updateBus.getEmployeeDetail().employeeId;
        this.getAllTimeSheetsInfo();
        this.globalSetting.settingType="TIMESHEETS";

    }

    //Gets timesheets for a range
    getSpecificTimeSheets() {

        try{
            this.statusTemplate.loadingMessage="Loading Timesheets..."
            this.tsWrapper.includeTimesheets = "Y";
            if (this.tsWrapper.startDate != null
                && this.tsWrapper.endDate != null
                && this.tsWrapper.employeeId != null) {
    
                this.getTimeSheets(this.tsWrapper)
                    .subscribe((data: TimesheetsObjWrapper[]) => {
                        this.statusTemplate.loadingMessage = null;
                        if (data != null && data.length > 0) {
                            data[0].timesheets.forEach(d => {
                                d.timesheetDate = new Date(d.timesheetDate + "T10:20:30Z");
                            });
                            data[0].startDate = new Date(data[0].startDate + "T10:20:30Z");
                            data[0].endDate = new Date(data[0].endDate + "T10:20:30Z");
                            this.startDate = data[0].startDate;
                            this.endDate = data[0].endDate;
                            this.projectDetails = data[0].timesheets[0].projectDetails;
    
                            this.tsWrapper = data[0];
    
                        } 
                    },
                        (error) => {
                            this.statusTemplate.setErrorMessgae( "There was an error retreiving timesheets.", 1500);
                            console.log("ERROR GETTING TS" + error);
    
                        },
                        () => {
                            console.log("SUCEESS GETTING TS");
                            this.updateDetails();
                        }
                    );
            }
        } catch(error){
            this.statusTemplate.setErrorMessgae( "There was an error retreiving timesheets.", 1500);
            console.log("ERROR GETTING TS" + error);
        } finally {

        }


    }

    //Gets all timesheets
    getAllTimeSheetsInfo() {
        try{
            this.statusTemplate.loadingMessage = "Loading...";

            var obj = new TimesheetsObjWrapper(null, null, null, null, this.employeeId, null, "N", this.selectedProject, null, null);
            this.getTimeSheets(obj)
                .subscribe((data: TimesheetsObjWrapper[]) => {
                    this.statusTemplate.loadingMessage = null;
                    if (data == null || data.length == 0) {
                        this.statusTemplate.setSuccessMessage("There are currently no timesheets available.", 3000);
                    } else {
                        this.oldTsInfo = data;
                        this.tsWrapper = data[0];        
                        this.getSpecificTimeSheets();                        
                    }

    
                },
                    (error) => {
                        this.statusTemplate.loadingMessage = null;
                        this.statusTemplate.setErrorMessgae("There was an error retreing timesheets info.", 1500);
            
    
                    },
                    () => {
    
                    }
                );
        } catch(error){
            this.statusTemplate.loadingMessage = null;
            this.statusTemplate.setErrorMessgae("There was an error retreing timesheets info.", 1500);
            console.log("ERROR GETTING TS" + error);
        } finally {

        }


    }

    //Call backend for retreival
    getTimeSheets(obj: TimesheetsObjWrapper) {

        return this.service.getAll<TimesheetsObjWrapper[]>(this.employeeId, obj);
    }

    //Call Backend for saving the timesheets
    persistTimeSheets() {
        console.log("SAVING: " + JSON.stringify(this.tsWrapper));
        return this.service.save<TimesheetsObjWrapper>(this.tsWrapper);
    }

    ngOnDestroy(){
        this.globalSetting.settingType = null;
    }
}