import { Component, Input } from "@angular/core";
import { Message } from '../models/app.message';

@Component({
    selector: 'err-succ',
    templateUrl : 'app.ERR_SUC.html',
    styleUrls : ['app.ERR_SUC.css']
})

export class ErrorSuccessComponent{

    errSuc : Message;
    errrorMessageQueue : string = null;
    successMessageQueue : string = null;
    errorMessage : string = null;
    successMessage : string = null;
    loadingMessage : string = null;

    constructor(){
    }

    handleStatusMessage(errSuc : Message){
        this.errSuc = errSuc;
                
        if(this.errSuc.loadingMessage != null){

            this.setLoadingMessage(this.errSuc.loadingMessage, 1000);
        } else {
            if(this.errSuc.errorMessage != null){
                this.setErrorMessgae(this.errSuc.errorMessage, 1000);

            } else if(this.errSuc.successMessage != null){
                this.setSuccessMessage(this.errSuc.successMessage, 1000);            
            }
        }

    }

    setErrorMessgae(message : string, timeout : number){
        if(this.errorMessage == null && this.successMessage == null && this.loadingMessage == null){
            setTimeout(() => {this.errorMessage = message;
                setTimeout(() => {this.errorMessage = null;                   
               }, timeout);},1);        
        }
    }

    setSuccessMessage(message : string,  timeout : number){
        if(this.errorMessage == null && this.successMessage == null && this.loadingMessage == null){
            setTimeout(() => {this.successMessage = message;
                setTimeout(() => {this.successMessage = null;   
               }, timeout);},1);
        }
    }

    setLoadingMessage(message : string,  timeout : number){
        if(this.errorMessage == null && this.successMessage == null && this.loadingMessage == null){
            setTimeout(() => {this.loadingMessage = message;
                setTimeout(() => {this.loadingMessage = null;  
                    if(this.errrorMessageQueue != null){
                        this.setErrorMessgae(this.errrorMessageQueue, 2000);
                        this.errrorMessageQueue = null;
                    } else if(this.successMessageQueue != null){
                        this.setSuccessMessage(this.successMessageQueue, 2000);
                        this.successMessageQueue = null;
                    }
               }, timeout);},0);
        }
    }
}