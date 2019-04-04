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
    private loginId : string;
 
    constructor(private http : HttpClient){

            this.server = "http://" + window.location.host + ":8080";
           
        
    }
    
    getLoginId(){
        return this.loginId;        
    }

    setLoginId(id : string){
        this.loginId = id;
    }

    getEid(){
        return this.eid;
    }

    setEid(id : string){
        this.eid = id;

    }
    
    logOut(){
        if(window.localStorage){
            localStorage.clear();
        }
        this.isLoogedIn = false;
    }

    setLoggedIn(loginId : string,value : boolean, eid : string, role : string){
        this.loginId = loginId;
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