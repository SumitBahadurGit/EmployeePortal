import { Component, OnInit } from "@angular/core";
import { MailService } from 'src/app/Service/app.mailService';

declare function validateFormByClassName(className : string) : boolean;

@Component({
    selector: 'id-generator',
    templateUrl : './app.generator.html',
    styleUrls : ['./app.generator.css']
})

export class GeneratorComponent implements OnInit{
    
    id : string = null;
    email : string = "";
    name : string = "";
    progress : boolean = false;

    onSuccess : string = null;
    onError : string = null;

    constructor(private mailService : MailService){

    }

    ngOnInit(){
        this.id = null;
        this.email = null;
        this.name = null;
        this.onSuccess = null;
        this.onError = null;
        this.progress = false;
    }

    validateAndSubmit(val : string[]){

        if(this.isFormValid(val)){
            this.progress = true;
            this.genNewId();
        }
    }

    isFormValid(classes : string[]){
        for(var i = 0; i < classes.length; i++){
           if(!this.validateGroupInputsByClassName(classes[i])){
               return false;
           }
        }
        return  true;
    }

    validateGroupInputsByClassName(c : string){
        return validateFormByClassName(c);

    }
    genNewId(){
        var query = "name="+this.name+"&email="+this.email;
        this.mailService.genNewId(query).subscribe(
        (data : string) => this.id = data,
        error => {
            console.log("ERROR GENERATING ID: " + error);         
            this.onSuccess = null;   
            this.onError = "There was a problem creating new ID.";
            this.progress = false;
        },
        () => {
            console.log("SUCCESS GENERATING ID");         
            this.onError = null;
            this.onSuccess = "A new ID and password was sent to " + this.email + '.\n The  id is ' + this.id;
            this.email = null;
            this.name = null;   
            this.id = null;
            this.progress = false;
        }
        );
        
    }

}