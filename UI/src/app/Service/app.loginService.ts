import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Login } from '../models/app.login';
import { Injectable } from '@angular/core';

@Injectable()
export class LogInService{


    private server;
    private baseResoucce = "/login";
    private loginResource = "/check";

    private isLoogedIn : boolean = false;
    private eid : string;
    private userRole : string;
 
    constructor(private http : HttpClient){
        var serverAddress =  window.location.origin;
        if(serverAddress != null){
            var ip = serverAddress.split("4200")[0];
            this.server = ip + "8080/";
           
        }
    }

    getEid(){
        return this.eid;
    }
    
    logOut(){
        if(window.localStorage){
            localStorage.clear();
        }
        this.isLoogedIn = false;
    }

    setLoggedIn(value : boolean, eid : string, role : string){
        this.isLoogedIn = value;
        this.eid = eid;
        this.userRole = role;
    }

    setUserRole(role : string){
        this.userRole = role;
    }

    getUserRole(){
        return this.userRole;
    }

    getLoggedIn(){
        return this.isLoogedIn;
    }

    public logIn<Login>(login : Login): Observable<Login>{
        return this.http.post<Login>(this.server + this.baseResoucce + this.loginResource, login);
    }

}