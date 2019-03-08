import { Component, Input } from "@angular/core";
import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Email } from '../models/app.emailObj';
import { MailService } from '../Service/app.mailService';
import { error } from 'util';
import { StatusService } from '../Service/app.statusService';

declare function validateGroupInputsByClassName(className : string) : boolean;


@Component({
    selector : "email",
    templateUrl : "./app.sendEmail.html",
    styleUrls : ['./app.sendEmail.css']
})

export class SendEmailComponent{

    @Input()
    to : string ;

    @Input()
    cc : string;

    @Input()
    subject : string;

    @Input()
    message : string;
    

    
    isFormValid(classes : string[]){
        for(var i = 0; i < classes.length; i++){
           if(!this.validateGroupInputsByClassName(classes[i])){
               return false;
           }
        }
        return  true;
    }

    validateGroupInputsByClassName(c: string) {
       return validateGroupInputsByClassName(c);
    }


    validateAndSubmit(val : string[]){       

        if(this.isFormValid(val)){
            this.sendEmail();
            this.activeModal.close();
        }
    }

    sendEmail(){
        var emailObj = new Email;
        emailObj.to = this.to;
        emailObj.cc = this.cc;
        emailObj.subject = this.subject;
        emailObj.message = this.message;
        this.statusService.statusMessage = "Sending email";

        this.mailService.sendEmail<any>(emailObj)
        .subscribe((data : any) => {},
        (error) => {
            this.statusService.statusMessage = null;
            this.statusService.setError("Error sending email",3000);
            console.log("ERROR SENDING EMAIL: " + error)
        },
        () => {
            console.log("EMAIL SUCCESSFULLY SENT.");
            this.statusService.statusMessage = null;
            this.statusService.setSuccess("Successfully sent",3000);
        });
    }

    constructor(public activeModal: NgbActiveModal,
         private mailService : MailService,
         private statusService : StatusService){

    }

}