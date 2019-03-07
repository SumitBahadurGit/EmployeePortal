import { Injectable } from "@angular/core";
import { HttpClient } from '@angular/common/http';
import { TimesheetsObjWrapper } from '../models/app.timesheetsObjWrapper';
import { Observable } from 'rxjs';

@Injectable()
export class TimesheetsService {

    private server;
    private baseResource = "timesheets/";
    private saveResource = "save";
    private getAllResource = "getAll";
    private checkResource = "check";
    private saveUrl = this.server + this.baseResource + this.saveResource;



    constructor(private http : HttpClient){
        var serverAddress =  window.location.origin;
        if(serverAddress != null){
            var ip = serverAddress.split("4200")[0];
            this.server = ip + "8080/";
        }
    }

    public save<T>(obj : TimesheetsObjWrapper) : Observable<T>{
        return this.http.post<T>(this.saveUrl, obj);
    }
    public getAll<T>(eid : string, obj : TimesheetsObjWrapper) : Observable<T>{
        return this.http.post<T>(this.server + this.baseResource + "/" + eid + "/" + this.getAllResource, obj);
    }
    public check<T>(eid:string,obj:TimesheetsObjWrapper) : Observable<T>{
        return this.http.post<T>(this.server + this.baseResource + "/" + eid + "/" + this.checkResource, obj);
    }


}
