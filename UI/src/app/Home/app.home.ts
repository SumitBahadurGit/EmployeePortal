import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { LogInService } from '../Service/app.loginService';
import { Login } from '../models/app.login';
import { LoggedUser } from '../Service/app.LoggedUser';

@Component({
    selector: 'home',
    templateUrl: './app.home.html',
    styleUrls: ['./app.home.css']
})

export class HomeComponent implements  OnInit {

    private userName: string;
    private password: string;
    private role : string;
    private eid : string;
    private loginId : string;


    private rememberMe : boolean = false;

    private errorMessage : string ;
    private isLoggedIn: boolean = false;

    constructor(private router: Router, private logInService: LogInService) {
    }

    ngOnInit(){
        
        if(window.localStorage){
            this.userName = localStorage.getItem("username");
            this.password = localStorage.getItem("password");
        }

        if(this.userName != null && this.password != null){
            this.tryLogIn();
        }

    }

    tryLogIn() {
        var logInObj : Login = new Login(); 
        this.errorMessage = null;
        logInObj = new Login;
        logInObj.setUserName(this.userName);
        logInObj.setPassWord(this.password);
        var success = false;

        this.logInService.logIn<Login>(logInObj)
        .subscribe((data : Login) => {
            logInObj = null;
             if(data != null){
                success = true;
                this.role = data.userRole;
                this.eid = data.eid;
                this.userName = data.userName;
                this.loginId = data.loginId;
            }
        },
        (error) => {
            this.errorMessage = "There was a system error while loggin. ";
        },
        () => {

            if(success == true){
                if(this.userName != null && this.password != null){                       
                        this.logInService.setLoggedIn(this.loginId, true, this.eid, this.role);
                        
                        if(this.rememberMe){
                            if(window.localStorage){
                                localStorage.setItem("username", this.userName);
                                localStorage.setItem("password", this.password);
                            }
                        }

                        this.userName = null;
                        this.password = null;
                        this.logIn();
                }
            } else {
                this.errorMessage = "ID or password is incorrect.";
            }            
        });
     
    }

    logIn() {
        this.isLoggedIn = this.logInService.getLoggedIn();
        if(this.isLoggedIn){
            this.router.navigateByUrl('/dashboard');
        }
    }
}