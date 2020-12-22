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

import { WorkflowProcessCommand, TestThreeTaskWorkflow, AssignItem, FileTitle, AssignType } from '../../../wf-models';

import { TestThreeTaskWorkflowSaveRequest } from '../../../wf-models/testthreetask/testthreetask-workflow-save-request';
import { TestThreeeWorkflowSaveRequestInit } from '../../../wf-models/testthreetask/testthreetask-workflow-save-request-init';



@Injectable({
  providedIn: 'root'
})
export class TestthreetaskWorkflowEditService extends HttpErrorResponseHelper implements WorkflowEditInterfaceService {

	public workflowSaveRequestInitSubject: BehaviorSubject<TestThreeeWorkflowSaveRequestInit> = new BehaviorSubject<TestThreeeWorkflowSaveRequestInit>(null);

	workflowSaveRequestInit :TestThreeeWorkflowSaveRequestInit = null;

	getInitCreateUrl() :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/initcreate";
	}

	getCreateWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/create";
	}

	getUploadFileUrl() :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/createfile";
	}

	getSaveWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/save";
	}

	getDoneWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/done";
	}

	getArchiveWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/archive";
	}

	getInitEditUrl(id :string) :string{
		return HttpHepler.dataServer + "/workflow/testthreetask/data/initedit/" + id;
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
          (initialData :TestThreeeWorkflowSaveRequestInit) => {

              console.log("GET successful edit inital data", initialData);

              this.workflowSaveRequestInit = <TestThreeeWorkflowSaveRequestInit> JSON.parse(JSON.stringify(initialData));

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

      const httpOptions = { headers: HttpHepler.generateJsonHeader() };

      return this.http.post(this.getInitEditUrl(id), new HttpParams(), httpOptions);

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

	createWorkflow(workflowSaveRequest :TestThreeTaskWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getCreateWorkflowUrl() , workflowSaveRequest, httpOptions);

	}


	saveWorkflow(workflowSaveRequest :TestThreeTaskWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getSaveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	doneWorkflow(workflowSaveRequest :TestThreeTaskWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getDoneWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	archiveWorkflow(workflowSaveRequest :TestThreeTaskWorkflow){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getArchiveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

}
