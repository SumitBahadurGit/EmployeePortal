export class Address{

    constructor(
        public addressId: string,
        public addressLine1: string,
        public addressLine2: string,
        public city: string,
        public state: string,
        public zipCode: string
    ){}
}