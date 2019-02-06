import { Injectable } from '@angular/core';

@Injectable()
export class EmployeeConfig{

    public server = 'http://localhost:8080/';
    public baseResoucce = "employee/";
    public postResource = "employee/add";
    public postUpdateResource = "employee/update";
    public getResource = "employee/retreive";

    public postUploadResource = "/upload";
    public getUploadedResource = "/retreive/docs";

    public deleteIdResource = "/delete";
    public getByIdResource = "/retreive";
    public getAllEmployeesResouce = "employee/retreiveAll"
    public postUrl = this.server + this.postResource;
    public postUpdateUrl = this.server + this.postUpdateResource;
    public getUrl = this.server + this.getResource;
    public getAllEmployeeUrl = this.server + this.getAllEmployeesResouce;
}