import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import {WebServiceConfig} from './app.webServiceConfig';
import { PersonalInfo } from '../models/app.personalInfo';
import { PaginatedWrapper } from '../models/app.paginatedWrapper';
import { DocumentObj } from '../models/app.documentObj';
import { Settings } from './app.globalSettings';

@Injectable()
export class WebService {

    private postActionUrl: string;
    private getActionUrl: string;
    private server : string;
    private getByIdResource : string;
    public baseResoucce : string;
    public allEmployeeUrl: string;
    public postUpdateUrl: string;
    public getAllEmployessByQueryUrl: string;

    public postUploadResource: string;
    public getUploadedResoure: string;
    public getUpdateDocsResource: string;

    public getDeleteIdResource: string;
    public getDeleteDocsIdResource : string;
    public updateSettingsResource = "settings";
    public getSettingsResource = "retreive/settings"

    constructor(private http: HttpClient, private configuration: WebServiceConfig) {
        this.postActionUrl = configuration.postUrl;
        this.getActionUrl = configuration.getUrl;
        this.server = configuration.server;
        this.getByIdResource = configuration.getByIdResource;
        this.baseResoucce = configuration.baseResoucce;
        this.allEmployeeUrl = configuration.getAllEmployeeUrl;
        this.postUpdateUrl = configuration.postUpdateUrl;
        this.postUploadResource = configuration.postUploadResource;
        this.getUploadedResoure = configuration.getUploadedResource;
        this.getUpdateDocsResource = configuration.getUpdateDocsResource;
        this.getDeleteIdResource = configuration.deleteIdResource;
        this.getDeleteDocsIdResource = configuration.getDeleteDocsIdResource;
        this.getAllEmployessByQueryUrl = configuration.getAllEmployessByQueryUrl;
    }

    public getAllEmployessByQuery<T>(request : PaginatedWrapper): Observable<T>{
        return this.http.post<T>(this.getAllEmployessByQueryUrl, request);
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

    public getAllFiles<T>(): Observable<T> {
        return this.http.get<T>(this.server + this.baseResoucce + this.getUploadedResoure);
    }

    public getFilesById<T>(id: string): Observable<T> {
        return this.http.get<T>(this.server + this.baseResoucce + id + "/" + this.getUploadedResoure);
    }

    public updateFiles<T>(id: string, obj : DocumentObj[]): Observable<T> {
        return this.http.post<T>(this.server + this.baseResoucce + id + this.getUpdateDocsResource, obj);
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

    public getSettings<T>(itemToSearch: Settings): Observable<T> {
        return this.http
            .post<T>(this.server + this.baseResoucce  + this.getSettingsResource, itemToSearch);
    }

    public updateSettings<T>(id: number, itemToUpdate: Settings): Observable<T> {
        return this.http
            .post<T>(this.server + this.baseResoucce  + id + "/" + this.updateSettingsResource, itemToUpdate);
    }

    public delete<T>(id: string): Observable<T> {
        return this.http.delete<T>(this.server + this.baseResoucce  +  id + this.getDeleteIdResource);
    }

    public deleteDocs<T>(body: DocumentObj[]): Observable<T> {
        return this.http.post<T>(this.server + this.baseResoucce + this.getDeleteDocsIdResource, body);
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