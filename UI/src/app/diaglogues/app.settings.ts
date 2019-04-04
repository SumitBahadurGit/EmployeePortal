import { Component, OnInit, OnDestroy } from "@angular/core";
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { GlobalSettingsService, Settings } from '../Service/app.globalSettings';
import { WebService } from '../Service/app.webservice';
import { LoggedUser } from '../Service/app.LoggedUser';

@Component({
    selector : "settings",
    templateUrl : "./app.settings.html",
    styleUrls : ['./app.settings.css']
})

export class SettingsDialogue implements OnInit, OnDestroy {

    private activeSettingType : string;
    private activeSetting : Settings;
     
    constructor(public activeModal : NgbActiveModal,
        private webService : WebService,
        private glogbalSetting: GlobalSettingsService){
    }

    getSettingType(){
        return this.glogbalSetting.settingType;
    }
    ngOnDestroy(){
    }

    ngOnInit(){

        var searchObj = new Settings();
        searchObj.employeeId = parseInt(LoggedUser.getUser().employeeId);

        this.activeSettingType = this.getSettingType();

        this.getSettings(searchObj)
        .subscribe((data : Settings) => {
            this.activeSetting = data;
           // alert(JSON.stringify(this.activeSetting));
        },
        (error) => { console.log("ERROR GETTINGS SETTINGS: "  + error);
        },
        () => {
            console.log("SETTINGS RETREIVED");
            if(this.activeSetting == null){                
                this.activeSetting = new Settings;
                this.activeSetting.employeeId = parseInt(LoggedUser.getUser().employeeId);                
                this.activeSetting.type = this.activeSettingType;
            }
        });       
    }
    
    autoApprove(obj : any){
        alert(obj);
    }

    getSettings(searchObj : Settings){
        return this.webService.getSettings<Settings>(searchObj);
    }
    
    updateSettings(){

      //  alert(this.activeSetting.autoApprove);
        console.log(JSON.stringify(this.activeSetting));
        this.webService.updateSettings<Settings>(parseInt(LoggedUser.getUser().employeeId), this.activeSetting)
        .subscribe((data : Settings) => {

        },
        (error) => { console.log("ERROR UPDATING SETTINGS: "  + error);
        },
        () => {
            this.activeModal.close();            
            console.log("SETTINGS UPDATED");
        });

    }
    
}