import { VendorObj } from './app.vendorObj';
import { ClientObj } from './app.clientObj';

export class EmploymentObj {

    constructor(
        public employmentId: string,
        public employeeId: string,
        public status: string,
        public startDate: Date,
        public endDate: Date,
        public hourlyWageRecv: string,
        public hourlyWagePay: string,
        public termntnReason: string,
        public jobTitle: string,
        public technology: string,
        public jobDesc: string,
        public empAuthStatus: string,
        public empAuthStart: Date,
        public empAuthEnd: Date,
        public vendor: VendorObj,
        public client: ClientObj,
    ) {

    }

}