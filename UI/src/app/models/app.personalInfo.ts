import { Address } from './app.address';
import { Education } from './app.education';
import {Work} from './app.work';
import { EmploymentObj } from './app.employmentObj';
import { DocumentObj } from './app.documentObj';


export class PersonalInfo{

    constructor(
        public loginId : string,
        public employeeId: string,
        public firstName: string,
        public middleName: string,
        public lastName: string,
        public dob: Date,
        public country: string,
        public visaStatus: string,
        public email: string,
        public secondEmail: string,
        public phone: string,
        public secondPhone: string,
        public joinedOn: string,
        public hiredOn: string,
        public manager: string,
        public recruiter: string,
        public ssn: string,
        public lastUpdated: string,
        public address: Address,
        public education: Education,
        public work: Work,
        public employmentObj: EmploymentObj[],
        public documnetObj: DocumentObj[]
        ){}

    }
