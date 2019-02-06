export class DocumentObj{
    
    constructor(
        public docId: string,
        public employeeId: string,
        public fileName:string,
        public fileType: string,
        public fileSize: string,
        public lastUpdated: string,
        public docDesc:string
        ){

    }
}