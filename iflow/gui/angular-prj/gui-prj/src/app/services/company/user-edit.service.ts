import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHepler } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services';

import { DashboardCube, User } from '../../ui-models';

@Injectable({
  providedIn: 'root'
})
export class UserEditService extends HttpErrorResponseHelper {

	loadUsersUrl :string = HttpHepler.dataServer + "/users/data/list";
	createUserUrl :string = HttpHepler.dataServer + "/users/data/create";
	updateUserUrl :string = HttpHepler.dataServer + "/users/data/update";
	deleteUserUrl :string = HttpHepler.dataServer + "/users/data/delete";
	resetUserPasswordUrl :string = HttpHepler.dataServer + "/users/data/resetpassword";
  applyDashboardLinksUrl :string = HttpHepler.dataServer + "/users/data/applydashboardlink";

	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router,
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) {
		super(router, route, autService);

	}

	listUsers(){

	  const httpOptions = { headers: HttpHepler.generateJsonHeader() };

		return this.http.get(this.loadUsersUrl, httpOptions);

	};

	createUser(user: User){

	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

		  return this.http.post(this.createUserUrl, user, httpOptions);

	};

	updateUser(user: User){

	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

		  return this.http.post(this.updateUserUrl, user, httpOptions);

	};

	deleteUser(user: User){

	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

		  return this.http.post(this.deleteUserUrl, user, httpOptions);

	};

	resetUserPassword(user: User){

	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

		  return this.http.post(this.resetUserPasswordUrl, user, httpOptions);

	};


  applyDashboardLinks(cubes : DashboardCube[][]) {
    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

    console.log("saving cubes:" , cubes);
    return this.http.post(this.applyDashboardLinksUrl, cubes, httpOptions);
  };
}
