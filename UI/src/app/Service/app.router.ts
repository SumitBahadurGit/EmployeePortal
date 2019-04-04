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

                    var url = null;

                    if(this.routeObjs != null && this.routeObjs.length > 0){
                        url = this.routeObjs[this.routeObjs.length-1].url;
                    }
                    if(url != null){
                        url = url + "/" + temp;
                    } else {
                        url = temp;
                    }

                    this.routeObjs.push(new RouteObj(url, temp.toUpperCase()));    
                
            } 
        }
      }
    }

    constructor(private bus : UpdateBus){

    }
    
}