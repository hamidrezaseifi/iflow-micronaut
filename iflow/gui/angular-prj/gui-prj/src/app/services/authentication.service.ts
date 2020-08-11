import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

import { User, LoginResponse, GeneralData } from '../ui-models';

import { GlobalService } from '../services/global.service';
import { LoadingServiceService } from './loading-service.service';
import { HttpHepler } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class AuthenticationService implements CanActivate{
    private currentUserSubject: BehaviorSubject<User>;
    isLoggedIn :boolean = false;
    
    authenticateUrl :string = "/auth/authenticate";
    logoutUrl :string = "/auth/logout";
    
    constructor(
    		private http: HttpClient,
    		private global: GlobalService,
    		private router: Router,
    		private loadingService: LoadingServiceService,
    ) {
        this.currentUserSubject = new BehaviorSubject<User>(null);
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    public get isLogedIn(): boolean {
        return this.currentUserSubject.value != null;
    }

    checkLoginState(returnUrl :string, ) {
    	
    	this.loadingService.showLoading();
    	this.global.loadAllSettingObserv().subscribe(
		        (generalData :GeneralData) => {
		            console.log("GET call successful generaldata", generalData);
		            
								var value = generalData.isLogged + "";
					
		            if(value === "true" && generalData.user){
		            	
		            	this.isLoggedIn = true;
		            	this.currentUserSubject.next(generalData.user.currentUser);
			        	
			        		this.global.loadAllSetting();
			        		this.global.presensSubject.next(true);
			        	
		            	this.router.navigate([returnUrl]);
		            }
		            else{
		            	this.isLoggedIn = false;
		            	this.currentUserSubject.next(null);
		            	
			        		//this.router.navigate(['auth/login'], { queryParams: { returnUrl: returnUrl } });
			        	
			        		window.location.assign("/auth/login?returnUrl=" + returnUrl); 
			        	
		            }
		        	
		        },
		        response => {
		            console.log("Error in read menu list", response);
	            	this.isLoggedIn = false;
	            	this.currentUserSubject.next(null);
		        	this.currentUserSubject.complete();

		            this.router.navigate(['auth/login'], { queryParams: { returnUrl: returnUrl } });
		        },
		        () => {
		            
		            //this.loadingService.hideLoading();
		        }
		); 	
    	
    }

    logout(returnUrl :string) {
        
      window.location.assign("/auth/logout"); 
    }
    
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    	
    	//alert("check authentication fo : " + state.url + " : isLoggedIn: " + this.isLoggedIn);
    	
        if (this.isLoggedIn === true) {
        	        	
            return true;
        }

        this.checkLoginState(state.url);
        
        return false;
    }    

}