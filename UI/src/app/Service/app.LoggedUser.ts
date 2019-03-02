import { PersonalInfo } from '../models/app.personalInfo';
import { NodeLogger } from '@angular/core/src/view';

export class LoggedUser{

    private static loggerUser : LoggedUser;

    private user : PersonalInfo;


    private constructor(user : PersonalInfo){
        this.user = user;
    }

    static clearUser(){
        this.loggerUser = null;
    }
    static getUser(){
        return this.loggerUser.user;
    }

    static createNew(user : PersonalInfo){
        if(this.loggerUser == null){
            this.loggerUser = new LoggedUser(user);
        }

    }

}