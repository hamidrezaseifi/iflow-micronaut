import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../../loading-service.service';
import { ErrorServiceService } from '../../error-service.service';
import { HttpHepler } from '../../../helper/http-hepler';
import { AuthenticationService } from '../../authentication.service';

import { InvoiceWorkflowSaveRequestInit } from '../../../wf-models/invoice/invoice-workflow-save-request-init';
import { InvoiceWorkflowSaveRequest } from '../../../wf-models/invoice/invoice-workflow-save-request';
import { InvoiceWorkflow } from '../../../wf-models/invoice/invoice-workflow';

import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType } from '../../../wf-models';

import { WorkflowEditInterfaceService } from '../../../services';
import { HttpErrorResponseHelper } from '../../../helper/http-error-response-helper';



@Injectable({
  providedIn: 'root'
})
export class InvoiceWorkflowEditService extends HttpErrorResponseHelper implements WorkflowEditInterfaceService {

	public workflowSaveRequestInitSubject: BehaviorSubject<InvoiceWorkflowSaveRequestInit> = new BehaviorSubject<InvoiceWorkflowSaveRequestInit>(null);

	workflowSaveRequestInit :InvoiceWorkflowSaveRequestInit = null;

	getInitCreateUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/initcreate";
	}

	getInitEditUrl(identity :string) :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/initedit/" + identity;
	}

	getCreateWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/create";
	}

	getSaveWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/save";
	}

	getDoneWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/done";
	}

	getArchiveWorkflowUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/archive";
	}

	getUploadFileUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/createfile";
	}

	getUploadOcrScanFileUrl() :string{
		return HttpHepler.dataServer + "/general/data/uploadtempfile";
	}

	getProcessDocumentWordsUrl() :string{
		return HttpHepler.dataServer + "/workflow/invoice/data/processdoc";
	}

	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected errorService :ErrorServiceService,
			protected router: Router,
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) {
		super(router, route, autService);
	}


	loadCreateInitialData(){
    	this.loadingService.showLoading();

      const httpOptions = { headers: HttpHepler.generateJsonHeader() };

      console.log("InitCreateUrl: " + this.getInitCreateUrl());

      this.http.post(this.getInitCreateUrl(), null, httpOptions).subscribe(
          (initialData :InvoiceWorkflowSaveRequestInit) => {

              console.log("GET successful edit inital data", initialData);

              this.workflowSaveRequestInit = <InvoiceWorkflowSaveRequestInit> JSON.parse(JSON.stringify(initialData));

              this.workflowSaveRequestInitSubject.next(initialData);


          },
          response => {
            console.log("Error in read edit inital data", response);
            this.errorService.showErrorResponse(response);
          },
          () => {
            this.workflowSaveRequestInitSubject.complete();
            this.loadingService.hideLoading();
          }
      );

	}


	loadEditInitialData(identity: string){

      const httpOptions = { headers: HttpHepler.generateJsonHeader() };

      return this.http.post(this.getInitEditUrl(identity), null, httpOptions);

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

	uploadTempFiles(ocrScanFile : File){

	    const formData = new FormData();
	    formData.append('file', ocrScanFile);
	    formData.append('wids', "0");


        const httpFileUploadOptions = { headers: HttpHepler.generateFileUploadHeader() };

	    return this.http.post(this.getUploadOcrScanFileUrl(), formData, httpFileUploadOptions);

	}


	createWorkflow(workflowSaveRequest :InvoiceWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getCreateWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	saveWorkflow(workflowSaveRequest :InvoiceWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getSaveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	doneWorkflow(workflowSaveRequest :InvoiceWorkflowSaveRequest){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getDoneWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	archiveWorkflow(workflowSaveRequest :InvoiceWorkflow){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getArchiveWorkflowUrl() , workflowSaveRequest, httpOptions);

	}

	processDocumentWords(message :any){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        return this.http.post(this.getProcessDocumentWordsUrl() , message, httpOptions);

	}

}
