import { Injectable } from "@angular/core";
import { Router } from '@angular/router';
import { UpdateBus } from './app.updateBus';


export class RouteObj{

    constructor(public url : string, public label : string){

    }
}

@Injectable()
export class RoutingService{
    
    private static routeObjs : RouteObj[];

    static getRoutesObjs(){
        return this.routeObjs;
    }

    static setRoutes(routeString : string){       
      this.routeObjs = [];
      if(routeString != null && routeString.length > 0 && routeString.includes("/")){
        var routesStrings = routeString.split("/");
        for(var i = 0; i < routesStrings.length; i++){            
            var temp = routesStrings[i];
            if(temp.length > 0){                
                if(temp=="dashboard"){
                    // The home should redirect to home.
                    // Change the url to blank
                    this.routeObjs.push(new RouteObj("chart", temp.toUpperCase()));   
                                     
                } else {
                    this.routeObjs.push(new RouteObj(temp, temp.toUpperCase()));    
                }
            } 
        }
      }
    }

    constructor(private bus : UpdateBus){

    }
    
}