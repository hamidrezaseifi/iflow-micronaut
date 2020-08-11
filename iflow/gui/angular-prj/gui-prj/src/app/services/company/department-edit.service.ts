import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHepler } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services';

import { Department } from '../../ui-models';

@Injectable({
	  providedIn: 'root'
	})
export class DepartmentEditService extends HttpErrorResponseHelper {

	loadDepartmentsUrl :string = "/departments/data/list";
	createDepartmentUrl :string = "/departments/data/create";
	updateDepartmentUrl :string = "/departments/data/update";
	deleteDepartmentUrl :string = "/departments/data/delete";
 
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
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
		return this.http.get(this.loadDepartmentsUrl, httpOptions);	    
		
	};
	
	createDepartment(department: Department){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
		return this.http.post(this.createDepartmentUrl, department, httpOptions);	    
		
	};
	
	updateDepartment(department: Department){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
		return this.http.post(this.updateDepartmentUrl, department, httpOptions);	    
		
	};
	
	deleteDepartment(department: Department){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
		return this.http.post(this.deleteDepartmentUrl, department, httpOptions);	    
		
	};
	

}


