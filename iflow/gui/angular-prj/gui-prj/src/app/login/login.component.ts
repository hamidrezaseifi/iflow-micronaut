import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LoginService, LoadingServiceService, AuthenticationService } from '../services';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute } from '@angular/router';
import { GlobalService } from '../services/global.service';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isFailed : boolean;
  username :string;
  password :string;
  returnUrl :string;
  logginMessage :string;

  constructor(protected translate: TranslateService,
              private router: Router,
              private global: GlobalService,
              private loginService: LoginService,
              private authenticationService: AuthenticationService,
              private loadingService: LoadingServiceService,
              private cookieService: CookieService ) {
    this.isFailed = false;
    this.username = "admin@iflow.de";
    this.password = "admin";
    this.returnUrl = "";
    this.logginMessage = "";
   }

  ngOnInit() {
  }

  login(){

    this.loadingService.showLoading();
    this.loginService.login(this.username, this.password).subscribe(
                                                    		        (loginData :any) => {
                                                    		            console.log("Login successful data", loginData);
                                                      		        	this.loadingService.hideLoading();
                                                      		        	this.global.setSessionData(loginData["session-data"]);
                                                                    this.global.setSessionId(loginData["session-id"]);

                                                      		        	this.cookieService.set(loginData["session-cookie-name"], loginData["session-cookie-data"] );
                                                      		        	this.cookieService.set('Path', "/" );

                                                      		        	this.router.navigate(['/']);
                                                    		        },
                                                    		        response => {
                                                    		        	console.log("Error in login", response);
                                                    		        	this.loadingService.hideLoading();
                                                    		        },
                                                    		        () => {
                                                    		        	this.loadingService.hideLoading();
                                                    		        }
                                                    		    );

  }

}
