import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Email } from '../models/app.emailObj';

@Injectable()
export class MailService {


    private server = "http://" + window.location.host + ":8080/";
    private baseResoucce = "login/";
    private getResource = "generateId";

    private emailBaseResoucce = "email/";
    private emailSendResource = "send";

    constructor(private http: HttpClient){
       
    }

    public sendEmail<T>(email : Email) : Observable<T>{
        return this.http.post<T>(this.server + this.emailBaseResoucce + this.emailSendResource, email);
    }

    public genNewId<T>(query : string): Observable<T>{
        return this.http.get<T>(this.getURL() +  "?" + query);
    }


    getURL(){
        return  this.server + this.baseResoucce + this.getResource;
    }
}