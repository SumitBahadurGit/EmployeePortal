import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';
import { DataService } from './app.employeeService';
import { TargetLocator } from 'selenium-webdriver';
import { element } from '@angular/core/src/render3';
import { PersonalInfo } from './app.personalInfo';
import { UpdateService } from './app.updateService';
import { DocumentObj } from './app.documentObj';

@Component({
  selector: 'documents',
  templateUrl: './app.documents.html',
  styleUrls: ['./app.documents.css'],
})

export class Documents implements OnInit {

  employeeId : string;
  formData: FormData = new FormData();
  files: File[];

  filesUploaded : boolean = false;
  employee: PersonalInfo;

  constructor(private dataService: DataService, private updateService: UpdateService) {
    this.files = [];    
    this.employeeId = this.updateService.getEmployeeDetail().employeeId;
  }

  fileSIze(size){
    var s = Number(size/1024).toFixed(2);
    return s + 'Kb';

  }

  removeFile(file){
    for(var i = 0; i < this.files.length; i++){
      if(this.files[i].name === file.name){
        this.files.splice(i,1);
      }
    }
    if(this.files == null || this.files.length == 0){
      this.filesUploaded = false;
    }

  }

  fileChange(event) {
    for (var i = 0; i < event.target.files.length; i++) {
      var file = event.target.files[i];
      this.files.push(file);
    }
    this.filesUploaded = true;
  }


  upload() {
    this.formData = new FormData;
    for(var i = 0; i < this.files.length; i++){

      this.formData.append('file', this.files[i], this.files[i].name);

    }
    this.dataService
      .upload(this.formData, this.employeeId)
      .subscribe((data: any) => {
        console.log(data);
      },
      error => {
        console.log("ERROR: " + JSON.stringify(error))
      },
      () => {
        console.log(" UPLOAD SUCCESS");
        this.reset();
      });

  }

  handleUploadClick(e: any) {

    var el = e.currentTarget;

    var inputChildren = el.getElementsByTagName('INPUT');
    if (inputChildren != null) {
      for (var i = 0; i < inputChildren.length; i++) {
        if (inputChildren[i].type == 'file') {
          inputChildren[i].click();         
        }
      }
    }

  }

  reset(){
   
    this.formData = null;
    this.files.splice(0, this.files.length);
    this.filesUploaded = false;
    this.employee = null;
 
    this.ngOnInit();
  

  }

  ngOnInit() {
    this.dataService
    .getFilesById<PersonalInfo>(this.employeeId)
    .subscribe((resp : PersonalInfo) => this.employee = resp,
      error => console.log("ERROR: " + error),
      () => {
        console.log("DOCUMENT DOWNLOAD SUCCESS");
      }
    );
  }

}
