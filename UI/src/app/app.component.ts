import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router, NavigationStart, NavigationEnd, NavigationError } from '@angular/router';
import { LogInService } from './Service/app.loginService';
import { Navigation } from 'selenium-webdriver';
import { RoutingService } from './Service/app.router';
import { UpdateBus } from './Service/app.updateBus';
import { IfStmt } from '@angular/compiler';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{


  isLoggedIn(){
    return this.logInService.getLoggedIn();    
  }

  constructor(private router : Router, private route : ActivatedRoute, private logInService: LogInService, private bus: UpdateBus){
    this.router.events.subscribe((data : any) => {
      this.processRoutes(data);
    });
  }

  processRoutes(event : any){

    if(event instanceof NavigationStart){
      RoutingService.setRoutes(event.url);
      
      if(event.url.includes("employees")){
        this.bus.setExpType("EMPLOYEE");
        
      } else if(event.url.includes("dashboard")){
        this.bus.setExpType("MASTER");
      }
    }
  }

  ngOnInit(){

    if(this.logInService.getLoggedIn()){
      this.router.navigateByUrl('/dashboard');
    } else {
      this.router.navigateByUrl('/home');
    }
  }

}
