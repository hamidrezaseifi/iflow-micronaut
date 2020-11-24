import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { LoginService, LoadingServiceService, AuthenticationService, GlobalService } from '../services';
import { User, MenuItem, GeneralData } from '../ui-models';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-top-bar',
  templateUrl: './top-bar.component.html',
  styleUrls: ['./top-bar.component.css'],
})
export class TopBarComponent implements OnInit {

	menus: MenuItem[] = [];
	currentUser: User=null;
	isLogged: boolean=false;
	isPresent : boolean=false;

	@Input('menus')
	set setMenus(_menus: MenuItem[]) {

	    if(Array.isArray(_menus) == false){
	      return;
	    }

	    this.menus = _menus;

	    for(var index in this.menus){
			  this.setMenuLabel(this.menus[index]);
		  }
	}

	@Output() loggingOut = new EventEmitter<boolean>();
	@Output() togglingPresens = new EventEmitter<boolean>();

	constructor(
		    private router: Router,
		    private global: GlobalService,
		    private translate: TranslateService,
		    private loginService: LoginService,
		    private loadingService: LoadingServiceService,
		    private cookieService: CookieService
		) {

      this.global.currentSessionDataSubject.asObservable().subscribe((generalData: GeneralData) => {
                                                                                    if(generalData != null){
                                                                                      console.log("generaldata in topbar", generalData);
                                                                                      if(generalData.user != null){
                                                                                        this.currentUser = generalData.user.currentUser;
                                                                                      }
                                                                                      this.isLogged = generalData.isLogged;
                                                                                      if(generalData.app != null){
                                                                                        this.menus=generalData.app.menus;
                                                                                      }
                                                                                      this.isPresent = false;
                                                                                    }
                                                                                    else{
                                                                                      console.log("generaldata in topbar is null");
                                                                                      this.currentUser = null;
                                                                                      this.isLogged = false;
                                                                                      this.menus = [];
                                                                                      this.isPresent = false;
                                                                                    }

                                                                     		        });
 	}

	ngOnInit() {

	}

	setMenuLabel(menu:MenuItem) {

		this.translate.get(menu.id).subscribe((res: string) => {
			menu.label = res;
	    });

		for(var index in menu.children){
			this.setMenuLabel(menu.children[index]);
		}
	}

	logout(){

    this.global.removeSessionData();
    this.global.removeSessionId();

    this.cookieService.delete("iflow");
    this.cookieService.delete('Path');

	  this.loadingService.showLoading();
    this.loginService.logout().subscribe(
                                          (data :any) => {
                                              console.log("Logout successful data", data);
                                              this.loadingService.hideLoading();
                                              this.global.removeSessionData();
                                              this.global.removeSessionId();

                                              this.cookieService.delete(data["session-cookie-name"]);
                                              this.cookieService.delete('Path');

                                              this.router.navigate(['/auth/login']);
                                          },
                                          response => {
                                            console.log("Error in logout", response);
                                            this.loadingService.hideLoading();
                                          },
                                          () => {
                                            this.loadingService.hideLoading();
                                          }
                                      );


		//this.loggingOut.emit(true);

	}

	togglePresens(){
	  this.togglingPresens.emit(true);
	}

	showProfile(){

	}

}
