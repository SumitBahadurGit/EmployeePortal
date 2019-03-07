import { Injectable } from '@angular/core';

@Injectable()
export class WebServiceConfig{

    public server =  (window.location.origin).split("4200")[0] + "8080/";
    public baseResoucce = "employee/";
    public postResource = "employee/add";
    public postUpdateResource = "employee/update";
    public getResource = "employee/retreive";

    public postUploadResource = "/upload";
    public getUploadedResource = "retreive/docs";
    public getUpdateDocsResource = "/updateDoc"

    public getDeleteDocsIdResource = "deleteDocs";
    public deleteIdResource = "/delete";
    public getByIdResource = "/retreive";
    public getAllEmployeesResouce = "employee/retreiveAll"
    public getAllEmployessByQueryResouce = "/retreiveAllEmp";

    public getAllEmployessByQueryUrl = this.server + this.baseResoucce + this.getAllEmployessByQueryResouce;
    public postUrl = this.server + this.postResource;
    public postUpdateUrl = this.server + this.postUpdateResource;
    public getUrl = this.server + this.getResource;
    public getAllEmployeeUrl = this.server + this.getAllEmployeesResouce;

    constructor(){

    }
}