import { TimeSheetsObj } from './app.timesheetObj';

export class TimesheetsObjWrapper{

    constructor(
        public timesheetsGroupId : number,
        public timesheets : TimeSheetsObj[],
        public startDate: Date,
        public endDate: Date,
        public employeeId: string,
        public submittedDate: Date,
        public includeTimesheets : string,
        public projectLocation : string,
        public totalHours : string,
        public overTime : string
        
    ){}
}