import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';

import { GlobalService } from '../../../services/global.service';
import { TestthreetaskWorkflowEditService } from '../../../services/workflow/testthreetask/testthreetask-workflow-edit.service';
import { LoadingServiceService } from '../../../services/loading-service.service';
import { ErrorServiceService } from '../../../services/error-service.service';

import { User, Department, GeneralData, UploadedFile, UploadedResult } from '../../../ui-models';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType, WorkflowUploadFileResult, WorkflowUploadedFile } 
	from '../../../wf-models';
import { WorkflowSaveRequest } from '../../../wf-models/workflow-save-request';
import { WorkflowSaveRequestInit } from '../../../wf-models/workflow-save-request-init';

@Component({
  selector: 'app-create-testthreetask',
  templateUrl: './create-testthreetask.component.html',
  styleUrls: ['../wm-create.css', './create-testthreetask.component.css'],
providers: [TestthreetaskWorkflowEditService]
})
export class CreateTestthreetaskComponent implements OnInit {

	workflowListUrl :string = "/workflow/list";

	workflowSaveRequest :WorkflowSaveRequest = null;
	generalDataObs :Observable<GeneralData> = null;
	
	showDebug : boolean = false;
	
	uploadedFiles :UploadedFile[] = [];	

	get expireDays() : number{
		if(this.workflowSaveRequest != null){
			return this.workflowSaveRequest.expireDays;
		}
		return 0;
	}
	set expireDays(days: number){
		if(this.workflowSaveRequest != null){
			this.workflowSaveRequest.expireDays = days;
		}
		
	}
	
	get assignedUsers() : AssignItem[]{
		if(this.workflowSaveRequest != null){
			return this.workflowSaveRequest.assigns;
		}
		return [];
	}
	
	get comments() : string{
		if(this.workflowSaveRequest != null){
			return this.workflowSaveRequest.comments;
		}
		return "";
	}
	set comments(value: string){
		if(this.workflowSaveRequest != null){
			this.workflowSaveRequest.comments = value;
		}
		
	}
	
	
	get debugData() :string{
		var ssignstr : string =  (this.workflowSaveRequest && this.workflowSaveRequest.assigns) ? JSON.stringify(this.workflowSaveRequest.assigns) : '--';
		return ssignstr;
	}
	
	
	constructor(
		    private router: Router,
			private global: GlobalService,
			public  translate: TranslateService,
			public  editService :TestthreetaskWorkflowEditService,
			private loadingService: LoadingServiceService,
			private http: HttpClient,
			private errorService: ErrorServiceService,
	) {
		
		this.router.events.subscribe((evt) => {
			if (evt instanceof NavigationEnd) {
		    	this.loadInitialData();
			}
		});
		
		this.generalDataObs = this.global.currentSessionDataSubject.asObservable();
	}
	
	ngOnInit() {
		
		this.loadInitialData();
	
	}
	
	onUploadedFilesChanged(uploadedFileList: UploadedFile[]) {
		
		this.uploadedFiles = uploadedFileList;
	}
	
	private loadInitialData(){
	 	if(this.editService.workflowSaveRequestInit !== null){
	 		this.workflowSaveRequest = this.editService.workflowSaveRequestInit.workflowSaveRequest;
	 		
	 	}
	 	else{
	 		this.subscribeToSearchInitialData();
	 		this.editService.loadCreateInitialData();
	 	}
	 	
	}
	
	private subscribeToSearchInitialData(){
		this.editService.workflowSaveRequestInitSubject.subscribe((data : WorkflowSaveRequestInit) => {
	    	
			console.log("set gloabl-data from workflow-create. : ", data);
			//alert("from app-comp: \n" + JSON.stringify(data));
	 		
			if(data && data !== null){
				this.workflowSaveRequest = data.workflowSaveRequest;
				
			}
			else{
				this.workflowSaveRequest = null;
			}
		  });
		
	}
			
	save(){
		
		this.workflowSaveRequest.uploadedFiles = WorkflowUploadedFile.loadUploadedFiles(this.uploadedFiles);

		this.loadingService.showLoading();
		
        this.editService.createWorkflow(this.workflowSaveRequest).subscribe(
		        (result) => {		        	
		            console.log("Create workflow result", result);
		            
		            this.router.navigate([this.workflowListUrl]);
		        },
		        response => {
		        	console.log("Error in create workflow", response);
		        	
		        	this.errorService.showErrorResponse(response);
		        	this.loadingService.hideLoading();	 
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	 
		        }
		    );	       	
		
	}
			
	
	onUsersSelected(assigns: AssignItem[]) {
		this.workflowSaveRequest.assigns = [];
		
		for(var item in assigns){
			var assign = new AssignItem;
			assign.itemIdentity = assigns[item].itemIdentity;
			assign.itemType = assigns[item].itemType;
			
			this.workflowSaveRequest.assigns.push(assign);						
		}
		
	}	
		
}
