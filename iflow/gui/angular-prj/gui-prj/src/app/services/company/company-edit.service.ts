import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHepler } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services';

import { Company } from '../../ui-models';

@Injectable({
  providedIn: 'root'
})
export class CompanyEditService extends HttpErrorResponseHelper {

	loadDataUrl :string = "/company/data/info";
	updateDataUrl :string = "/company/data/update";
	
	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router, 
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) { 
		super(router, route, autService);
		
	}
	
	listData(){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
		return this.http.get(this.loadDataUrl, httpOptions);	    
		
	};
	
	updateData(company: Company){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
		return this.http.post(this.updateDataUrl, company, httpOptions);	    
		
	};

}
