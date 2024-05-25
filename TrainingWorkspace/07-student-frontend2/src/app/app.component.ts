import { Component } from '@angular/core';
import { NavigationCancel, NavigationEnd, NavigationStart, Router, RouterEvent } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  showLoadingIndicator = true;

  constructor(private router:Router){
    this.router.events.subscribe((routerEvent:RouterEvent) =>{
      if(routerEvent instanceof NavigationStart){
        this.showLoadingIndicator = true;
      }
      if(routerEvent instanceof NavigationCancel){
        this.showLoadingIndicator = false;
      }
      
      if(routerEvent instanceof NavigationEnd){
        this.showLoadingIndicator = false;
      }
    })
  }
}
