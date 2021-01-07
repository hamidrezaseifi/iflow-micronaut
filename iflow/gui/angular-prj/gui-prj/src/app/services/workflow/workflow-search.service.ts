import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { HttpHepler } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { LoadingServiceService } from '../loading-service.service';
import { AuthenticationService } from '../../services/authentication.service';

import { WorkflowSearchFilter, WorkflowListInitialData, Workflow, WorkflowSearchResult } from '../../wf-models';


@Injectable({
  providedIn: 'root'
})
export class WorkflowSearchService extends HttpErrorResponseHelper {

	public searchInitialDataSubject: BehaviorSubject<WorkflowListInitialData> = new BehaviorSubject<WorkflowListInitialData>(new WorkflowListInitialData);

	loadInitialUrl :string = HttpHepler.dataServer + "/workflow/data/general/initsearch";
	searchUrl :string = HttpHepler.dataServer + "/workflow/data/general/search";
	listInitialData :WorkflowListInitialData = new WorkflowListInitialData;

	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router,
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) {
		super(router, route, autService);

	}


  loadInitialData() {

    this.loadingService.showLoading();

    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

    this.http.post<WorkflowListInitialData>(this.loadInitialUrl, null, httpOptions).subscribe(
        (initialData :WorkflowListInitialData) => {

            console.log("GET successful search inital data", initialData);

            this.listInitialData = <WorkflowListInitialData> JSON.parse(JSON.stringify(initialData));

            this.searchInitialDataSubject.next(initialData);


        },
        response => {
          console.log("Error in read search inital data", response);
          this.processErrorResponse(response);
          this.loadingService.hideLoading();
        },
        () => {
          this.searchInitialDataSubject.complete();
          this.loadingService.hideLoading();
        }
    );

  }

	search(searchFilter: WorkflowSearchFilter){

	  const httpOptions = { headers: HttpHepler.generateJsonHeader() };
		return this.http.post<WorkflowSearchResult>(this.searchUrl, searchFilter, httpOptions);

	};


}
