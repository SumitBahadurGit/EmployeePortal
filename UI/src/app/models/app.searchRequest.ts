export class SearchRequest{


    constructor(
        public sortBy : string,
        public filterBy : string,
        public filterByValue : string
    ){}
}