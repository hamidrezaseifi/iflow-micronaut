import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHepler } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services';

import { WorkflowSaveRequestInit } from '../../wf-models/workflow-save-request-init';
import { WorkflowSaveRequest } from '../../wf-models/workflow-save-request';

import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType } from '../../wf-models';

import { WorkflowEditInterfaceService } from '../../services';

@Injectable({
  providedIn: 'root'
})
export class WorkflowEditBaseService extends HttpErrorResponseHelper implements WorkflowEditInterfaceService {

	public workflowSaveRequestInitSubject: BehaviorSubject<WorkflowSaveRequestInit> = new BehaviorSubject<WorkflowSaveRequestInit>(null);

	workflowSaveRequestInit :WorkflowSaveRequestInit = null;

	getInitCreateUrl() :string{
		return "";
	}
	
	getCreateWorkflowUrl() :string{
		return "";
	}
	
	getUploadFileUrl() :string{
		return "";
	}
	
	
	getSaveWorkflowUrl() :string{
		return "";
	}
	
	getDoneWorkflowUrl() :string{
		return "";
	}
	
	getArchiveWorkflowUrl() :string{
		return "";
	}
	
	getUploadOcrScanFileUrl() :string{
		return "/general/data/uploadtempfile";
	}

	getInitEditUrl(identity :string) :string{
		return "";
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
    	
        const httpOptions = { headers: HttpHepler.generateFormHeader() };
        
        this.http.post(this.getInitCreateUrl(), new HttpParams(), httpOptions).subscribe(
		        (initialData :WorkflowSaveRequestInit) => {
		        	
		            console.log("GET successful edit inital data", initialData);
		            
		            this.workflowSaveRequestInit = <WorkflowSaveRequestInit> JSON.parse(JSON.stringify(initialData));
		            
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
	
	createWorkflow(workflowSaveRequest :WorkflowSaveRequest){
    	
        const httpOptions = { headers: HttpHepler.generateJsonHeader() };
        
        return this.http.post(this.getCreateWorkflowUrl() , workflowSaveRequest, httpOptions);	       	

	}
	
	
	saveWorkflow(workflowSaveRequest :WorkflowSaveRequest){
    	
        const httpOptions = { headers: HttpHepler.generateJsonHeader() };
        
        return this.http.post(this.getSaveWorkflowUrl() , workflowSaveRequest, httpOptions);	       	

	}	
	
	doneWorkflow(workflowSaveRequest :WorkflowSaveRequest){
    	
        const httpOptions = { headers: HttpHepler.generateJsonHeader() };
        
        return this.http.post(this.getDoneWorkflowUrl() , workflowSaveRequest, httpOptions);	       	

	}	
	
	archiveWorkflow(workflowSaveRequest :Workflow){
    	
        const httpOptions = { headers: HttpHepler.generateJsonHeader() };
        
        return this.http.post(this.getArchiveWorkflowUrl() , workflowSaveRequest, httpOptions);	       	

	}	
	
}
