import { Injectable } from "@angular/core";

@Injectable()
export class StatusService{

    public onError : string;
    public onSuccess : string;
    public statusMessage : string;

    public setError(messge : string, timeout? : number){
        setTimeout(() => {
            this.onError = messge;
            setTimeout(() => {
                this.onError = null;
            },timeout);
        },1);
    }
    public setSuccess(messge : string, timeout? : number){
        setTimeout(() => {
            this.onSuccess = messge;
            setTimeout(() => {
                this.onSuccess = null;
            },timeout);
        },1);
    }
    public setStatusMessage(messge : string, timeout? : number){
        setTimeout(() => {
            this.statusMessage = messge;
            setTimeout(() => {
                this.statusMessage = null;
            },timeout);
        },1);
    }
}