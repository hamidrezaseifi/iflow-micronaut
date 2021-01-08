import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../../loading-service.service';
import { HttpHelper } from '../../../helper/http-hepler';
import { AuthenticationService } from '../../authentication.service';

import { WorkflowEditInterfaceService } from '../../../services';
import { HttpErrorResponseHelper } from '../../../helper/http-error-response-helper';

import { WorkflowProcessCommand, SingleTaskWorkflow, AssignItem, FileTitle, AssignType } from '../../../wf-models';

import { SingleTaskWorkflowSaveRequest } from '../../../wf-models/singletask/singletask-workflow-save-request';
import { SingleTaskWorkflowSaveRequestInit } from '../../../wf-models/singletask/singletask-workflow-save-request-init';
import { UploadedResult } from '../../../ui-models';


@Injectable({
  providedIn: 'root'
})
export class SingleTaskWorkflowEditService extends HttpErrorResponseHelper implements WorkflowEditInterfaceService {

	public workflowSaveRequestInitSubject: BehaviorSubject<SingleTaskWorkflowSaveRequestInit> =
	  new BehaviorSubject<SingleTaskWorkflowSaveRequestInit>(new SingleTaskWorkflowSaveRequestInit);

	workflowSaveRequestInit :SingleTaskWorkflowSaveRequestInit = new SingleTaskWorkflowSaveRequestInit;

	getInitCreateUrl() :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/initcreate";
	}

	getCreateWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/create";
	}

	getUploadFileUrl() :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/createfile";
	}

	getSaveWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/save";
	}

	getDoneWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/done";
	}

	getArchiveWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/archive";
	}

	getInitEditUrl(id :string) :string{
		return HttpHelper.dataServer + "/workflow/singletask/data/initedit/" + id;
	}

	getUploadOcrScanFileUrl() :string{
		return HttpHelper.dataServer + "/archive/data/uploadtempfile";
	}

	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router,
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) {
		super(router, route, autService);

	}

	uploadTempFiles(file : File): Observable<UploadedResult>{

    const formData = new FormData();
    formData.append('file', file);
    formData.append('wids', "0");

    const httpFileUploadOptions = { headers: HttpHelper.generateFileUploadHeader() };

	  return this.http.post<UploadedResult>(this.getUploadOcrScanFileUrl(), formData, {});

	}

	loadCreateInitialData(){
    this.loadingService.showLoading();

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    this.http.post<SingleTaskWorkflowSaveRequestInit>(this.getInitCreateUrl(), null, httpOptions).subscribe(
        (initialData :SingleTaskWorkflowSaveRequestInit) => {
            console.log("GET successful edit inital data", initialData);
            this.workflowSaveRequestInit = <SingleTaskWorkflowSaveRequestInit> JSON.parse(JSON.stringify(initialData));
            this.workflowSaveRequestInitSubject.next(initialData);
        },
        response => {
          console.log("Error in read edit inital data", response);
          this.processErrorResponse(response);
          this.loadingService.hideLoading();
        },
        () => {
          this.workflowSaveRequestInitSubject.complete();
          this.loadingService.hideLoading();
        }
    );

	}

	loadEditInitialData(id: string){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post<SingleTaskWorkflowSaveRequestInit>(this.getInitEditUrl(id), new HttpParams(), httpOptions);

	}

	uploadFiles(fileTitles : FileTitle[]){

	  const formData = new FormData();

		for (var i = 0; i < fileTitles.length; i++) {
		    formData.append('files', fileTitles[i].file);
		    formData.append('titles', fileTitles[i].title);
		    formData.append('wids', i + "");
		}

    const httpFileUploadOptions = { headers: HttpHelper.generateFileUploadHeader() };

	  return this.http.post(this.getUploadFileUrl(), formData, httpFileUploadOptions);

	}

	createWorkflow(workflowSaveRequest :SingleTaskWorkflowSaveRequest){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getCreateWorkflowUrl() , workflowSaveRequest, httpOptions);

	}


	saveWorkflow(workflowSaveRequest :SingleTaskWorkflowSaveRequest){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getSaveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	doneWorkflow(workflowSaveRequest :SingleTaskWorkflowSaveRequest){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getDoneWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	archiveWorkflow(workflowSaveRequest :SingleTaskWorkflow){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getArchiveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}



}
