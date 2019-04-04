import { PersonalInfo } from '../models/app.personalInfo';
import { NodeLogger } from '@angular/core/src/view';

export class LoggedUser{

    private static loggerUser : LoggedUser;

    private user : PersonalInfo;
    private userRole : string;


    private constructor(user : PersonalInfo){
        this.user = user;
    }

    static clearUser(){
        if(this.loggerUser != null){
            this.loggerUser = null;
        }
    }
    static getUser(){
        if(this.loggerUser != null){
            return this.loggerUser.user;

        }
    }

    static setUserRole(role : string){
        this.loggerUser.userRole = role;
    }

    static getUserRole(){
        return this.loggerUser.userRole;
    }

    static createNew(user : PersonalInfo){
        if(this.loggerUser == null){
            this.loggerUser = new LoggedUser(user);
        }

    }

}