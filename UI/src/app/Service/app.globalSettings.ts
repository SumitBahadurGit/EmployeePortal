import { Injectable } from "@angular/core";

@Injectable()
export class GlobalSettingsService{

    settings : Settings;
    settingType : string;

}

export class Settings{

    public type : string;
    public autoApprove : boolean;
    public emailOnAction : string;
    public employeeId : number;
    public settingId : number;

    constructor(){}

}