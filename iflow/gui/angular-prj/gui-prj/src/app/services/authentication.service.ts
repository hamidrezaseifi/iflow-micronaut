import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute } from '@angular/router';

import { User, LoginResponse, GeneralData } from '../ui-models';

import { GlobalService } from '../services/global.service';
import { LoadingServiceService } from './loading-service.service';
import { HttpHelper } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class AuthenticationService implements CanActivate{

    authenticateUrl :string = HttpHelper.dataServer + "/auth/authenticate";
    logoutUrl :string = HttpHelper.dataServer + "/auth/logout";

    constructor(
    		private http: HttpClient,
    		private global: GlobalService,
    		private router: Router,
    		private route: ActivatedRoute,
    		private loadingService: LoadingServiceService,
    ) {

    }

    checkLoginState(returnUrl :string, ) {

      const geberalData:GeneralData = this.global.getSessionData();

      if(geberalData == null || geberalData.isLogged == false){

        this.router.navigate(['auth/login'], { relativeTo: this.route });
        return false;
      }

      return true;
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
