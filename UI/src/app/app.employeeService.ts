import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import {EmployeeConfig} from './app.employeeConfig';
import { PersonalInfo } from './app.personalInfo';
import { EmploymentObj } from './app.employmentObj';

@Injectable()
export class DataService {

    private postActionUrl: string;
    private getActionUrl: string;
    private server : string;
    private getByIdResource : string;
    public baseResoucce : string;
    public allEmployeeUrl: string;
    public postUpdateUrl: string;

    public postUploadResource: string;
    public getUploadedResoure: string;
    public getDeleteIdResource: string;

    constructor(private http: HttpClient, private configuration: EmployeeConfig) {
        this.postActionUrl = configuration.postUrl;
        this.getActionUrl = configuration.getUrl;
        this.server = configuration.server;
        this.getByIdResource = configuration.getByIdResource;
        this.baseResoucce = configuration.baseResoucce;
        this.allEmployeeUrl = configuration.getAllEmployeeUrl;
        this.postUpdateUrl = configuration.postUpdateUrl;
        this.postUploadResource = configuration.postUploadResource;
        this.getUploadedResoure = configuration.getUploadedResource;
        this.getDeleteIdResource = configuration.deleteIdResource;
    }

    public getAllNoQuery<T>(): Observable<T> {

        return this.http.get<T>(this.allEmployeeUrl);
    }

    public getAll<T>(query: string): Observable<T> {
        var updatedUrl = this.getActionUrl + query;

        return this.http.get<T>(updatedUrl);
    }

    public getSingle<T>(id: string): Observable<T> {
        return this.http.get<T>(this.server + this.baseResoucce + id + this.getByIdResource );
    }

    public upload<T>(files: FormData, id: string): Observable<T> {
        return this.http.post<T>(this.server + this.baseResoucce + id + this.postUploadResource, files);
    }

    public getFilesById<T>(id: string): Observable<T> {
        return this.http.get<T>(this.server + this.baseResoucce + id + this.getUploadedResoure);
    }

    public add<T>(personalInfo: PersonalInfo): Observable<T> {

        return this.http.post<T>(this.postActionUrl, personalInfo);
    }

    public  updateEmployee<T>(personalInfo: PersonalInfo): Observable<T> {

        return this.http.post<T>(this.postUpdateUrl, personalInfo);
    }

    public update<T>(id: number, itemToUpdate: any): Observable<T> {
        return this.http
            .put<T>(this.postActionUrl + id, itemToUpdate);
    }

    public delete<T>(id: string): Observable<T> {
        return this.http.delete<T>(this.server + this.baseResoucce  +  id + this.getDeleteIdResource);
    }
}


@Injectable()
export class CustomInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!req.headers.has('Content-Type')) {
            req = req.clone({ headers: req.headers.set('Content-Type', 'application/json') });
        }

        req = req.clone({ headers: req.headers.set('Accept', 'application/json') });
        return next.handle(req);
    }
}