import { Component, OnInit, ViewChild } from "@angular/core";
import { AppModule } from 'src/app/app.module';
import { TimesheetsObjWrapper } from 'src/app/models/app.timesheetsObjWrapper';
import { TimeSheetsObj } from 'src/app/models/app.timesheetObj';
import { UpdateBus } from 'src/app/Service/app.updateBus';
import { TimesheetsService } from 'src/app/Service/app.timesheetService';
import { Observable } from 'rxjs';
import { validateConfig } from '@angular/router/src/config';
import { LoggedUser } from 'src/app/Service/app.LoggedUser';
import { ErrorSuccessComponent } from 'src/app/Templates/app.ERR_SUC';

@Component({
    selector: 'employee-timesheets',
    templateUrl: './app.employee-timesheets.html',
    styleUrls: ['./app.employee-timesheets.css']
})


export class EmpTimeSheetComponent implements OnInit {

    doesDateExist: boolean = false;
    tsWrapper: TimesheetsObjWrapper;
    oldTsInfo: TimesheetsObjWrapper[];

    @ViewChild(ErrorSuccessComponent)
    statusTemplate: ErrorSuccessComponent;

    employeeId: string;
    projectDetails: string = "";
    curStatus: string;
    tsStatus: string[] = ["Submitted", "Awaiting submission", "Approved", "Rejected"];
    projectLocation: string[];
    selectedProject: string;
    totalHours: string;
    overTime: string;

    manager: string;
    startDate: Date;
    endDate: Date;
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

    time: string[] = [
        "12 AM", "12:05 AM", "12:10 AM", "12:15 AM", "12:20 AM", "12:25 AM", "12:30 AM", "12:35 AM", "12:40 AM", "12:45 AM", "12:50 AM", "12:55 AM",
        "1 AM", "1:05 AM", "1:10 AM", "1:15 AM", "1:20 AM", "1:25 AM", "1:30 AM", "1:35 AM", "1:40 AM", "1:45 AM", "1:50 AM", "1:55 AM",
        "2 AM", "2:05 AM", "2:10 AM", "2:15 AM", "2:20 AM", "2:25 AM", "2:30 AM", "2:35 AM", "2:40 AM", "2:45 AM", "2:50 AM", "2:55 AM",
        "3 AM", "3:05 AM", "3:10 AM", "3:15 AM", "3:20 AM", "3:25 AM", "3:30 AM", "3:35 AM", "3:40 AM", "3:45 AM", "3:50 AM", "3:55 AM",
        "4 AM", "4:05 AM", "4:10 AM", "4:15 AM", "4:20 AM", "4:25 AM", "4:30 AM", "4:35 AM", "4:40 AM", "4:45 AM", "4:50 AM", "4:55 AM",
        "5 AM", "5:05 AM", "5:10 AM", "5:15 AM", "5:20 AM", "5:25 AM", "5:30 AM", "5:35 AM", "5:40 AM", "5:45 AM", "5:50 AM", "5:55 AM",
        "6 AM", "6:05 AM", "6:10 AM", "6:15 AM", "6:20 AM", "6:25 AM", "6:30 AM", "6:35 AM", "6:40 AM", "6:45 AM", "6:50 AM", "6:55 AM",
        "7 AM", "7:05 AM", "7:10 AM", "7:15 AM", "7:20 AM", "7:25 AM", "7:30 AM", "7:35 AM", "7:40 AM", "7:45 AM", "7:50 AM", "7:55 AM",
        "8 AM", "8:05 AM", "8:10 AM", "8:15 AM", "8:20 AM", "8:25 AM", "8:30 AM", "8:35 AM", "8:40 AM", "8:45 AM", "8:50 AM", "8:55 AM",
        "9 AM", "9:05 AM", "9:10 AM", "9:15 AM", "9:20 AM", "9:25 AM", "9:30 AM", "9:35 AM", "9:40 AM", "9:45 AM", "9:50 AM", "9:55 AM",
        "10 AM", "10:05 AM", "10:10 AM", "10:15 AM", "10:20 AM", "10:25 AM", "10:30 AM", "10:35 AM", "10:40 AM", "10:45 AM", "10:50 AM", "10:55 AM",
        "11 AM", "11:05 AM", "11:10 AM", "11:15 AM", "11:20 AM", "11:25 AM", "11:30 AM", "11:35 AM", "11:40 AM", "11:45 AM", "11:50 AM", "11:55 AM",
        "12 PM", "12:05 PM", "12:10 PM", "12:15 PM", "12:20 PM", "12:25 PM", "12:30 PM", "12:35 PM", "12:40 PM", "12:45 PM", "12:50 PM", "12:55 PM",
        "1 PM", "1:05 PM", "1:10 PM", "1:15 PM", "1:20 PM", "1:25 PM", "1:30 PM", "1:35 PM", "1:40 PM", "1:45 PM", "1:50 PM", "1:55 PM",
        "2 PM", "2:05 PM", "2:10 PM", "2:15 PM", "2:20 PM", "2:25 PM", "2:30 PM", "2:35 PM", "2:40 PM", "2:45 PM", "2:50 PM", "2:55 PM",
        "3 PM", "3:05 PM", "3:10 PM", "3:15 PM", "3:20 PM", "3:25 PM", "3:30 PM", "3:35 PM", "3:40 PM", "3:45 PM", "3:50 PM", "3:55 PM",
        "4 PM", "4:05 PM", "4:10 PM", "4:15 PM", "4:20 PM", "4:25 PM", "4:30 PM", "4:35 PM", "4:40 PM", "4:45 PM", "4:50 PM", "4:55 PM",
        "5 PM", "5:05 PM", "5:10 PM", "5:15 PM", "5:20 PM", "5:25 PM", "5:30 PM", "5:35 PM", "5:40 PM", "5:45 PM", "5:50 PM", "5:55 PM",
        "6 PM", "6:05 PM", "6:10 PM", "6:15 PM", "6:20 PM", "6:25 PM", "6:30 PM", "6:35 PM", "6:40 PM", "6:45 PM", "6:50 PM", "6:55 PM",
        "7 PM", "7:05 PM", "7:10 PM", "7:15 PM", "7:20 PM", "7:25 PM", "7:30 PM", "7:35 PM", "7:40 PM", "7:45 PM", "7:50 PM", "7:55 PM",
        "8 PM", "8:05 PM", "8:10 PM", "8:15 PM", "8:20 PM", "8:25 PM", "8:30 PM", "8:35 PM", "8:40 PM", "8:45 PM", "8:50 PM", "8:55 PM",
        "9 PM", "9:05 PM", "9:10 PM", "9:15 PM", "9:20 PM", "9:25 PM", "9:30 PM", "9:35 PM", "9:40 PM", "9:45 PM", "9:50 PM", "9:55 PM",
        "10 PM", "10:05 PM", "10:10 PM", "10:15 PM", "10:20 PM", "10:25 PM", "10:30 PM", "10:35 PM", "10:40 PM", "10:45 PM", "10:50 PM", "10:55 PM",
        "11 PM", "11:05 PM", "11:10 PM", "11:15 PM", "11:20 PM", "11:25 PM", "11:30 PM", "11:35 PM", "11:40 PM", "11:45 PM", "11:50 PM", "11:55 PM",
    ]

    hours: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24];
    dates: Date[];

    timesheetTypes: string[] = ['Weekly', 'Monthly', 'Daily', "Custom"];
    curTimesheetType: string = 'Custom';


    constructor(private updateBus: UpdateBus, private service: TimesheetsService) {
      
    }



    isSubmitReady(){
        if(this.employeeId != null 
            && this.selectedProject != null 
            && this.manager != null && this.manager.length > 0
            && this.startDate != null){
            return true;
        } else {
            return false;
        }
    }

    defaultDetails() {
        this.employeeId = LoggedUser.getUser().employeeId;
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

        if (type == "MASTER") {
            this.updateBus.setEmployeeDetail(LoggedUser.getUser());
        }

        if(LoggedUser.getUser().employmentObj != null && LoggedUser.getUser().employmentObj.length > 0){
            LoggedUser.getUser().employmentObj.forEach(e => {
                //if(e.status.toUpperCase() == "ACTIVE")
                {
                    if (e.client != null) {
                        this.employeeId = e.employeeId;
                        this.projectLocation.push(e.client.clientName);
                    }

                }
            });
        this.selectedProject = this.projectLocation[0];
        this.manager = this.updateBus.getEmployeeDetail().manager;
        }
    }

    updateDetails() {

        if (this.tsWrapper != null && this.tsWrapper.timesheets != null) {
            var ts = this.tsWrapper.timesheets[0];
            this.curStatus = ts.status;
            this.selectedProject = ts.projectLocation;
            this.projectDetails = ts.projectDetails;
        }
    }

    //Creates new timesheet object for saving
    validateAndCreate() {

        try {
            this.statusTemplate.loadingMessage = "Creating timesheets....";
            this.service.check<boolean>(this.employeeId, new TimesheetsObjWrapper(null,null, this.startDate, null, this.employeeId, null, null, this.selectedProject, null, null))
                .subscribe((data: boolean) => {
                    this.doesDateExist = data;
                },
                    (error) => {
                        this.statusTemplate.loadingMessage = null;
                        this.statusTemplate.setErrorMessgae("Error creating timesheets", 1000);
            
                        console.log("ERROR CHECKING TS" + error);
                        this.defaultDetails();
                    },
                    () => {
                        console.log("SUCEES CHECKING TS");
                        if (this.doesDateExist) {
                            this.statusTemplate.loadingMessage = null;
                            this.statusTemplate.setErrorMessgae("Timesheets for this date was already created.", 1000);
                            this.defaultDetails();
                        } else {
                            this.getDateArray();
                            if (this.dates != null && this.dates.length > 0) {
                                this.tsWrapper = new TimesheetsObjWrapper(null, null, this.startDate, this.endDate, this.employeeId, null, "N", null, null, null);
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
                                            "9 AM",
                                            "9 AM",
                                            "0",
                                            "0",
                                            null,
                                            null,
                                            "N",
                                            null,
                                            null,
                                            this.tsWrapper.submittedDate,
                                            null,
                                            "n/a")
                                    );
                                });
                            }
                        }
                        this.statusTemplate.loadingMessage = null;
                    }
                );
        } catch (error) {
            this.statusTemplate.loadingMessage = null;
            this.statusTemplate.setErrorMessgae("Error creating timesheets", 1000);

            console.log("ERROR CHECKING TS" + error);
            this.defaultDetails();

        } finally {

        }

    }

    parserStartDate(event: any) {
        this.startDate = new Date(event.target.value + "T10:20:30Z");
    }

    parserEndDate(event: any) {
        this.endDate = new Date(event.target.value + "T10:20:30Z");
    }

    //Gets the dates picked between two datePickers
    //Adjust the week, months
    getDateArray() {
        if (this.curTimesheetType == this.timesheetTypes[0] /* Weekly */) {
            if (this.startDate != null) {

                //Get the current day of week
                var daysToAdjust = this.startDate.getDay();
                console.log("DAYS TO ADJUST: " + daysToAdjust + " in " + this.startDate.getDate());
                this.startDate.setDate(this.startDate.getDate() - daysToAdjust);
                console.log("UP DATE: " + JSON.stringify(this.startDate));
                this.dates = [];
                for (var i = 0; i < 7; i++) {
                    var d = new Date(this.startDate.toDateString());
                    d.setDate(this.startDate.getDate() + i);
                    this.dates.push(d);
                }
            }
        } else if (this.curTimesheetType == this.timesheetTypes[1] /* Monthly */) {
            if (this.startDate != null) {
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
                while (loop == true) {
                    var d = new Date(this.startDate.toDateString());
                    d.setDate(this.startDate.getDate() + i);
                    console.log("TEMP DATE:" + JSON.stringify(d));
                    if (d.getMonth() == curMonth) {
                        this.dates.push(d);
                        i++;
                    } else {
                        console.log("NOT MATCH: " + curMonth + "-" + d.getMonth());
                        loop = false;
                    }
                }
            }
        } else if (this.curTimesheetType == this.timesheetTypes[3]/* Custom */) {
            if (this.startDate != null && this.endDate != null) {
                var datediff = this.endDate.getTime() - this.startDate.getTime();
                var daysDiff = datediff / (24 * 60 * 60 * 1000);
                if (datediff > 1) {
                    this.dates = [];
                    for (var i = 0; i <= daysDiff; i++) {
                        var d = new Date(this.startDate.toDateString());
                        d.setDate(this.startDate.getDate() + i);
                        this.dates.push(d);
                    }
                }
            }
        }

    }

    // Get all the previous timesheets for a specific location
    ngOnInit() {
        if(LoggedUser.getUser() != null && LoggedUser.getUser().employeeId != null){
            this.defaultDetails();
            this.getAllTimeSheetsInfo();
        }
    }

    saveTimeSheets() {

        try {
            this.statusTemplate.loadingMessage = " Saving...";
            this.tsWrapper.timesheets.forEach(t => {
                t.projectDetails = this.projectDetails;
                t.projectLocation = this.selectedProject;
                t.status = this.tsStatus[1];
                t.employeeId = this.employeeId;
            });
            this.persistTimeSheets()
                .subscribe((data: TimesheetsObjWrapper) => {
                    this.tsWrapper = data;
                },
                    (error) => {
                        this.statusTemplate.loadingMessage = null;
                        this.statusTemplate.setErrorMessgae("Error saving timesheets", 1500);
                        console.log("ERROR saving TS: " + error);
                        this.defaultDetails();
                    },
                    () => {
                        this.statusTemplate.loadingMessage = null;
                        this.statusTemplate.setSuccessMessage("Saved", 1500);
                        console.log("SUCCESSFULLY SAVED TS:********************************** ");
                        console.info(JSON.stringify(this.tsWrapper));
                        this.dates = [];
                        this.tsWrapper = null;
                        this.getAllTimeSheetsInfo();
                        this.defaultDetails();
                    });
        } catch (error) {
            this.statusTemplate.loadingMessage = null;
            this.statusTemplate.setErrorMessgae("Error saving timesheets", 1500);
            console.log("ERROR saving TS: " + error);
            this.defaultDetails();
        } finally {

        }

    }

    submitTimeSheets() {

        try {
            this.statusTemplate.loadingMessage = " Submitting..."
            this.tsWrapper.timesheets.forEach(t => {
                t.projectDetails = this.projectDetails;
                t.projectLocation = this.selectedProject;
                t.status = this.tsStatus[0];
                t.employeeId = this.employeeId;
            });
            this.persistTimeSheets()
                .subscribe((data: TimesheetsObjWrapper) => {
                    this.tsWrapper = data;
                },
                    (error) => {
                        console.log("ERROR saving TS: " + error);
                        this.defaultDetails();
                                },
                    () => {
                        this.statusTemplate.loadingMessage = null;
                        this.statusTemplate.setSuccessMessage(" Submitted ", 1500);
                        console.log("SUCCESSFULLY SAVED TS:********************************** ");
                        console.info(JSON.stringify(this.tsWrapper));
                        this.dates = [];
                        this.tsWrapper = null;
                        this.getAllTimeSheetsInfo();
                        this.defaultDetails();
                    });

        } catch (error) {
            console.log("ERROR saving TS: " + error);
            this.defaultDetails();

        } finally {

        }

    }

    //Gets timesheets for a range
    getSpecificTimeSheets() {

        try {
            this.statusTemplate.loadingMessage = " Retreiving timesheets";
            this.tsWrapper.includeTimesheets = "Y";
            if (this.tsWrapper.startDate != null && this.tsWrapper.endDate != null && this.tsWrapper.employeeId != null) {
                this.getTimeSheets(this.tsWrapper)
                    .subscribe((data: TimesheetsObjWrapper[]) => {
                        if (data != null && data.length > 0) {
                            data[0].timesheets.forEach(d => {
                                d.timesheetDate = new Date(d.timesheetDate + "T10:20:30Z");
                            });
                            data[0].startDate = new Date(data[0].startDate + "T10:20:30Z");
                            data[0].endDate = new Date(data[0].endDate + "T10:20:30Z");

                            this.totalHours = data[0].totalHours;
                            this.overTime = data[0].overTime;
                            this.tsWrapper = data[0];
                        } else {
                            this.defaultDetails();
                        }
                    },
                        (error) => {
                            this.statusTemplate.loadingMessage = null;
                            this.statusTemplate.setErrorMessgae("Error retreving timesheets", 1500);
                            console.log("ERROR GETTING TS" + error);
                            this.defaultDetails();
                
                        },
                        () => {
                            console.log("SUCEESS GETTING TS");
                            this.updateDetails();
                        }
                    );
                this.statusTemplate.loadingMessage = null;
            }
        } catch (error) {
            this.statusTemplate.loadingMessage = null;
            this.statusTemplate.setErrorMessgae("Error retreving timesheets", 1500);
            console.log("ERROR GETTING TS" + error);
            this.defaultDetails();

        } finally {

        }



    }

    //Gets all timesheets
    getAllTimeSheetsInfo() {

        try {
            var obj = new TimesheetsObjWrapper(null,null, null, null, this.employeeId, 
                null, "N", this.selectedProject, null, null);

            this.getTimeSheets(obj)
                .subscribe((data: TimesheetsObjWrapper[]) => {
                    if (data == null || data.length == 0) {
                        //  this.setErrorMessgae("There was an error loading older timesheets.");
                        this.defaultDetails();
                    } else {
                        this.oldTsInfo = data;
                    }

                },
                    (error) => {
                        this.statusTemplate.setErrorMessgae("There was an error retreiving old timesheets.", 1500);
                        console.log("ERROR GETTING TS" + error);
                        this.defaultDetails();
            
                    },
                    () => {
                    }
                );
        } catch (error) {
            this.statusTemplate.setErrorMessgae("There was an error retreiving old timesheets.", 1500);
            console.log("ERROR GETTING TS" + error);
            this.defaultDetails();

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
}