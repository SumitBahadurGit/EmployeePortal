import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class MailService {


    private server = 'http://localhost:8080/';
    private baseResoucce = "login/";
    private getResource = "generateId";

    constructor(private http: HttpClient){
        var serverAddress =  window.location.origin;
        if(serverAddress != null){
            var ip = serverAddress.split("4200")[0];
            this.server = ip + "8080/";
        }

    }

    public genNewId<T>(query : string): Observable<T>{
        return this.http.get<T>(this.getURL() +  "?" + query);
    }


    getURL(){
        return  this.server + this.baseResoucce + this.getResource;
    }
}