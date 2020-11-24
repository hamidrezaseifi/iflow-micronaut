import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute } from '@angular/router';

import { User, LoginResponse, GeneralData } from '../ui-models';

import { GlobalService } from '../services/global.service';
import { LoadingServiceService } from './loading-service.service';
import { HttpHepler } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class AuthenticationService implements CanActivate{
    private currentUserSubject: BehaviorSubject<User>;

    authenticateUrl :string = HttpHepler.dataServer + "/auth/authenticate";
    logoutUrl :string = HttpHepler.dataServer + "/auth/logout";

    constructor(
    		private http: HttpClient,
    		private global: GlobalService,
    		private router: Router,
    		private route: ActivatedRoute,
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

      alert(JSON.stringify(this.global.getSessionData()));

      if(this.global.getSessionData() == null){
        //this.global.loadAllSetting();
        this.router.navigate(['auth/login'], { relativeTo: this.route });
      }

    }

    logout(returnUrl :string) {

      window.location.assign("/auth/logout");
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        if (sessionStorage.getItem("session-data") !== null) {

            return true;
        }

        this.checkLoginState(state.url);

        return false;
    }

}
