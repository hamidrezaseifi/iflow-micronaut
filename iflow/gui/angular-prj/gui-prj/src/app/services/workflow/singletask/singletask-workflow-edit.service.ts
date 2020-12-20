import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../../loading-service.service';
import { HttpHepler } from '../../../helper/http-hepler';
import { AuthenticationService } from '../../authentication.service';

import { WorkflowEditInterfaceService } from '../../../services';
import { HttpErrorResponseHelper } from '../../../helper/http-error-response-helper';

import { WorkflowProcessCommand, SingleTaskWorkflow, AssignItem, FileTitle, AssignType } from '../../../wf-models';

import { SingleTaskWorkflowSaveRequest } from '../../../wf-models/singletask/singletask-workflow-save-request';
import { SingleTaskWorkflowSaveRequestInit } from '../../../wf-models/singletask/singletask-workflow-save-request-init';


@Injectable({
  providedIn: 'root'
})
export class SingleTaskWorkflowEditService extends HttpErrorResponseHelper implements WorkflowEditInterfaceService {

	public workflowSaveRequestInitSubject: BehaviorSubject<SingleTaskWorkflowSaveRequestInit> = new BehaviorSubject<SingleTaskWorkflowSaveRequestInit>(null);

	workflowSaveRequestInit :SingleTaskWorkflowSaveRequestInit = null;

	getInitCreateUrl() :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/initcreate";
	}

	getCreateWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/create";
	}

	getUploadFileUrl() :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/createfile";
	}

	getSaveWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/save";
	}

	getDoneWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/done";
	}

	getArchiveWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/archive";
	}

	getInitEditUrl(identity :string) :string{
		return HttpHepler.dataServer + "/workflow/singletask/data/initedit/" + identity;
	}

	getUploadOcrScanFileUrl() :string{
		return HttpHepler.dataServer + "/general/data/uploadtempfile";
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

	uploadTempFiles(ocrScanFile : File){

	    const formData = new FormData();
	    formData.append('file', ocrScanFile);
	    formData.append('wids', "0");


      const httpFileUploadOptions = { headers: HttpHepler.generateFileUploadHeader() };

	    return this.http.post(this.getUploadOcrScanFileUrl(), formData, httpFileUploadOptions);

	}

	loadCreateInitialData(){
    	this.loadingService.showLoading();

	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };

      this.http.post(this.getInitCreateUrl(), null, httpOptions).subscribe(
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

	loadEditInitialData(identity: string){

        const httpOptions = { headers: HttpHepler.generateFormHeader() };

        return this.http.post(this.getInitEditUrl(identity), new HttpParams(), httpOptions);

	}

	uploadFiles(fileTitles : FileTitle[]){

	  const formData = new FormData();

		for (var i = 0; i < fileTitles.length; i++) {
		    formData.append('files', fileTitles[i].file);
		    formData.append('titles', fileTitles[i].title);
		    formData.append('wids', i + "");
		}

    const httpFileUploadOptions = { headers: HttpHepler.generateFileUploadHeader() };

	  return this.http.post(this.getUploadFileUrl(), formData, httpFileUploadOptions);

	}

	createWorkflow(workflowSaveRequest :SingleTaskWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getCreateWorkflowUrl() , workflowSaveRequest, httpOptions);

	}


	saveWorkflow(workflowSaveRequest :SingleTaskWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getSaveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	doneWorkflow(workflowSaveRequest :SingleTaskWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getDoneWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	archiveWorkflow(workflowSaveRequest :SingleTaskWorkflow){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getArchiveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}



}
