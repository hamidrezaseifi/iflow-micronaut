import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../../loading-service.service';
import { ErrorServiceService } from '../../error-service.service';
import { HttpHelper } from '../../../helper/http-hepler';
import { AuthenticationService } from '../../authentication.service';

import { InvoiceWorkflowSaveRequestInit } from '../../../wf-models/invoice/invoice-workflow-save-request-init';
import { InvoiceWorkflowSaveRequest } from '../../../wf-models/invoice/invoice-workflow-save-request';
import { InvoiceWorkflow } from '../../../wf-models/invoice/invoice-workflow';

import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType } from '../../../wf-models';

import { WorkflowEditInterfaceService } from '../../../services';
import { HttpErrorResponseHelper } from '../../../helper/http-error-response-helper';
import { UploadedResult } from '../../../ui-models';



@Injectable({
  providedIn: 'root'
})
export class InvoiceWorkflowEditService extends HttpErrorResponseHelper implements WorkflowEditInterfaceService {

	public workflowSaveRequestInitSubject: BehaviorSubject<InvoiceWorkflowSaveRequestInit> =
	  new BehaviorSubject<InvoiceWorkflowSaveRequestInit>(new InvoiceWorkflowSaveRequestInit);

	workflowSaveRequestInit :InvoiceWorkflowSaveRequestInit = new InvoiceWorkflowSaveRequestInit;

	getInitCreateUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/initcreate";
	}

	getInitEditUrl(id :string) :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/initedit/" + id;
	}

	getCreateWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/create";
	}

	getSaveWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/save";
	}

	getDoneWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/done";
	}

	getArchiveWorkflowUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/archive";
	}

	getUploadFileUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/createfile";
	}

	getUploadOcrScanFileUrl() :string{
		return HttpHelper.dataServer + "/archive/data/uploadtempfile";
	}

	getProcessDocumentWordsUrl() :string{
		return HttpHelper.dataServer + "/workflow/invoice/data/processdoc";
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

      const httpOptions = { headers: HttpHelper.generateJsonHeader() };

      console.log("InitCreateUrl: " + this.getInitCreateUrl());

      this.http.post<InvoiceWorkflowSaveRequestInit>(this.getInitCreateUrl(), null, httpOptions).subscribe(
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


	loadEditInitialData(id: string){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post<InvoiceWorkflowSaveRequestInit>(this.getInitEditUrl(id), null, httpOptions);

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

	uploadTempFiles(ocrScanFile : File): Observable<UploadedResult>{

    const formData = new FormData();
    formData.append('file', ocrScanFile);
    formData.append('wids', "0");

    const httpFileUploadOptions = { headers: HttpHelper.generateFileUploadHeader() };

    return this.http.post<UploadedResult>(this.getUploadOcrScanFileUrl(), formData, {});
	}


	createWorkflow(workflowSaveRequest :InvoiceWorkflowSaveRequest){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getCreateWorkflowUrl() , workflowSaveRequest, httpOptions);
	}

	saveWorkflow(workflowSaveRequest :InvoiceWorkflowSaveRequest){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getSaveWorkflowUrl() , workflowSaveRequest, httpOptions);
	}

	doneWorkflow(workflowSaveRequest :InvoiceWorkflowSaveRequest){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getDoneWorkflowUrl() , workflowSaveRequest, httpOptions);
	}

	archiveWorkflow(workflowSaveRequest :InvoiceWorkflow){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getArchiveWorkflowUrl() , workflowSaveRequest, httpOptions);
	}

	processDocumentWords(message :any){

    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

    return this.http.post(this.getProcessDocumentWordsUrl() , message, httpOptions);
	}

}
