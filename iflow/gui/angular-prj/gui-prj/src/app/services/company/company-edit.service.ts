import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHelper } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services/authentication.service';

import { Company } from '../../ui-models';

@Injectable({
  providedIn: 'root'
})
export class CompanyEditService extends HttpErrorResponseHelper {

	loadDataUrl :string = HttpHelper.dataServer + "/company/data/info";
	updateDataUrl :string = HttpHelper.dataServer + "/company/data/update";

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

	  const httpOptions = { headers: HttpHelper.generateJsonHeader() };

		return this.http.get<Company>(this.loadDataUrl);

	};

	updateData(company: Company){

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };
      const headers = new HttpHeaders().set("Content-Type", "application/json;");
	    return this.http.post<Company>(this.updateDataUrl, company, {headers});

	};

}
