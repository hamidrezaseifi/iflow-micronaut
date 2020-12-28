import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { Title } from '@angular/platform-browser';
import { Observable } from 'rxjs';

import { GlobalService } from './services/global.service';
import { AuthenticationService } from './services';

import { environment } from '../environments/environment';


import { User, MenuItem, GeneralData } from './ui-models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ],
  providers: [ GlobalService ]
})
export class AppComponent implements OnInit  {

	generalData: GeneralData = new GeneralData;

  appShowLoading: boolean = false;

	isPresent: boolean = true;


	constructor(
	  private router: Router,
		private autService: AuthenticationService,
		private global: GlobalService,
		translate: TranslateService,
		private titleService: Title,

	) {

    autService.checkLoginState("/");

		translate.setDefaultLang('de');

		translate.use('de');

		translate.get('site.title').subscribe((res: string) => {
    	this.titleService.setTitle( res );
		});

		this.router.routeReuseStrategy.shouldReuseRoute = function(){
 	  	return false;
		}

		this.router.events.subscribe((evt) => {
 	        if (evt instanceof NavigationEnd) {


 	        }
		});

    this.global.currentSessionDataSubject.asObservable().subscribe((generalData: GeneralData) => {
                                                                                        if(generalData != null){
                                                                                          this.generalData = generalData;
                                                                                        }
                                                                                    });

	}

	ngOnInit() {
		this.global.loadAllSetting();
	}

	showLoading(){

		this.appShowLoading = true;
	}


	onLoggingOut(data: boolean) {
		this.global.logout();
	}

	onTogglingPresens(data: boolean) {
		this.isPresent = !this.isPresent;

		this.global.presensSubject.next(this.isPresent);

	}

}


