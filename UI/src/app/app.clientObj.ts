import { Address } from './app.address';

export class ClientObj {

    constructor(
        public clientId: string,
        public clientName: string,
        public clientHrName: string,
        public clientHrPhone: string,
        public clientHrEmail: string,
        public clientContactName: string,
        public clientContactPhone: string,
        public clientContactEmail: string,
        public clientAddress: Address,

    ) {

    }
}