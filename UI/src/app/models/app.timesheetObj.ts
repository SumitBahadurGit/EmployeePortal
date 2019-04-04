export class TimeSheetsObj{

    constructor(
        public timeSheetId: number,
        public employeeId : string,
        public status : string,
        public timesheetDate : Date,
        public startTime : string,
        public endTime : string,
        public totalHours : string,
        public overTime : string,
        public projectDetails : string,
        public projectLocation : string,
        public isApproved: string,
        public approvedBy : string,
        public lastUpdated : Date,
        public submittedDate : Date,
        public approvedDate : Date,
        public desc : string
    ){

    }
}