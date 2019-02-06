import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { routerNgProbeToken } from '@angular/router/src/router_module';

@Component({
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})

export class AppComponent implements OnInit {

  public searchValue = "";

  constructor(private router: Router) {
  }

search(event){

  if (event.keyCode === 13) {
//    alert(this.searchValue);
    this.router.navigate(['Search'], {queryParams : { 'query' : this.searchValue}});
  }

}
  ngOnInit() {

  }

}
