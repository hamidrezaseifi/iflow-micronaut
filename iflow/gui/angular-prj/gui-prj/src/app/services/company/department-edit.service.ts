import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHelper } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services/authentication.service';

import { Department } from '../../ui-models';

@Injectable({
	  providedIn: 'root'
	})
export class DepartmentEditService extends HttpErrorResponseHelper {

	loadDepartmentsUrl :string = HttpHelper.dataServer + "/departments/data/list";
	createDepartmentUrl :string = HttpHelper.dataServer + "/departments/data/create";
	updateDepartmentUrl :string = HttpHelper.dataServer + "/departments/data/update";
	deleteDepartmentUrl :string = HttpHelper.dataServer + "/departments/data/delete";

	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router,
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) {
		super(router, route, autService);

	}

	listDepartments(){

	  const httpOptions = { headers: HttpHelper.generateJsonHeader() };

		return this.http.get<Department[]>(this.loadDepartmentsUrl, httpOptions);

	};

	createDepartment(department: Department){

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

		  return this.http.post<Department>(this.createDepartmentUrl, department, httpOptions);

	};

	updateDepartment(department: Department){

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

		return this.http.post<Department>(this.updateDepartmentUrl, department, httpOptions);

	};

	deleteDepartment(department: Department){

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

		return this.http.post(this.deleteDepartmentUrl, department, httpOptions);

	};


}


