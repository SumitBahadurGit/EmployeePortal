import { Address } from './app.address';

export class VendorObj {

    constructor(
        public vendorId: string,
        public vendorName: string,
        public vendorHrName: string,
        public vendorHrEmail: string,
        public vendorHrPhone: string,
        public vendorContactName: string,
        public vendorContactEmail: string,
        public vendorContactPhone: string,
        public vendorAddress: Address,
    ) {


    }
}