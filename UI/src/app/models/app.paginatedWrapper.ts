import { PersonalInfo } from './app.personalInfo';
import { SearchRequest } from './app.searchRequest';

export class PaginatedWrapper {

    constructor(
        public personalInfo : PersonalInfo[],
        public totalRecords : number,
        public start : number,
        public end : number,
        public totalPages : number,
        public limit : number,
        public currPage : number, 
        public searchRequest : SearchRequest
    ){
    }
}