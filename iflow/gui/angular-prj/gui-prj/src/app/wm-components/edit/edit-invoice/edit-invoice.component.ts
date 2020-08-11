import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import $ from "jquery";

import { GlobalService } from '../../../services/global.service';
import { InvoiceWorkflowEditService } from '../../../services/workflow/invoice/invoice-workflow-edit.service';
import { LoadingServiceService } from '../../../services/loading-service.service';
import { ErrorServiceService } from '../../../services/error-service.service';
import { InvoiceBaseComponent } from '../../invoice-base.component';
import { GlobalSocket } from '../../../services/global-socket';

import { User, Department, GeneralData } from '../../../ui-models';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType, WorkflowUploadFileResult, InvoiceType } from '../../../wf-models';
import { InvoiceWorkflowSaveRequest } from '../../../wf-models/invoice-workflow-save-request';
import { InvoiceWorkflowSaveRequestInit } from '../../../wf-models/invoice-workflow-save-request-init';
import { InvoiceTypeControllValidator } from '../../../custom-validators/invoice-type-controll-validator';
import { GermanDateAdapter, parseDate, formatDate } from '../../../helper';

@Component({
  selector: 'app-edit-invoice',
  templateUrl: './edit-invoice.component.html',
  styleUrls: ['../wm-edit.css' , './edit-invoice.component.css'],
  providers: [{provide: DateAdapter, useClass: GermanDateAdapter}, InvoiceWorkflowEditService]
})
export class EditInvoiceComponent extends InvoiceBaseComponent implements OnInit {
	
	workflowIdentity : string = "not-set";	

	saveMessage : string = "";	


	viewWorkflowModel :Workflow = null;

	get canSave() :boolean{
		if(this.workflowSaveRequest.workflow){
			return this.workflowSaveRequest.workflow.canSave;
		}
		return false;
	}
	
	get canDone() :boolean{
		if(this.workflowSaveRequest.workflow){
			return this.workflowSaveRequest.workflow.canDone;
		}
		return false;
	}
	
	get canArchive() :boolean{
		if(this.workflowSaveRequest.workflow){
			return this.workflowSaveRequest.workflow.canArchive;
		}
		return false;
	}
	
	get canAssign() :boolean{
		if(this.workflowSaveRequest.workflow){
			return this.workflowSaveRequest.workflow.canAssign;
		}
		return true;
	}

	get debugData() :string{
		var ss = formatDate(new Date(), 'dd.mm.yyyy');
		ss += " -- " + parseDate(ss, 'dd.mm.yyyy');
		return ss;
	}
	
	
	constructor(
		    protected router: Router,
			protected global: GlobalService,
			protected translate: TranslateService,
			public    editService :InvoiceWorkflowEditService,
			protected loadingService: LoadingServiceService,
			protected http: HttpClient,
			protected errorService: ErrorServiceService,
		  	protected formBuilder: FormBuilder,
		  	protected dateAdapter: DateAdapter<Date>,
		  	protected route: ActivatedRoute,
		  	protected globalSocket: GlobalSocket,
	) {
		
		super(router,
				global,
				translate,
				editService,
				loadingService,
				http,
				errorService,
			  	formBuilder,
			  	dateAdapter,
			  	globalSocket);
		
		this.router.events.subscribe((evt) => {
			if (evt instanceof NavigationEnd) {
				this.workflowIdentity = this.route.snapshot.params['identity'];
				this.loadInitialData();
			}
		});

	}
	
	ngOnInit() {
	
		super.ngOnInit();
		
	}
	
	
	protected loadInitialData(){
		
	 	
		if(this.workflowIdentity == ''){
			return;
		}
	
	 	this.loadWorkflowData();
	 	
	}
	
	reload() {
		
		this.loadInitialData();
	
	}

	private loadWorkflowData(){
		
		this.loadingService.showLoading();	     
		this.editService.loadEditInitialData(this.workflowIdentity).subscribe(
	        (initialData :InvoiceWorkflowSaveRequestInit) => {
	        	
				console.log("set inital-data from workflow-edit. : ", initialData);
				//alert("from app-comp: \n" + JSON.stringify(data));
		 		
				if(initialData && initialData !== null){
					this.workflowSaveRequest = initialData.workflowSaveRequest;
			 		this.ocrSettingPresets = initialData.ocrPresetList;
					this.viewWorkflowModel = this.workflowSaveRequest.workflow;
					this.setToControlValues();
					
				}
				else{
					this.workflowSaveRequest = null;
				}
	            
	        },
	        response => {
	        	console.log("Error in read edit inital data", response);
	        	this.errorService.showErrorResponse(response);
	        },
	        () => {
	        	
	        	this.loadingService.hideLoading();	            
	        }
	    );	       	
		
	}
	
	save(makeDone :boolean){
		
		this.setFormControlValues();
		
		this.loadingService.showLoading();
		
		if(makeDone){
        	this.doneWorkflowData();
        }
        else{
        	this.saveWorkflowData();
        }
	
	}
	
	
	archive(){
		
		this.setFormControlValues();
		
		this.loadingService.showLoading();
		
		this.archiveWorkflowData();
	
	}	
	private saveWorkflowData(){
		
		this.saveMessage = "";
		
        this.editService.saveWorkflow(this.workflowSaveRequest).subscribe(
		        (result) => {		        	
		            console.log("Create workflow result", result);
		            
		            this.translate.get('common.saved').subscribe((res: string) => {
		            	this.saveMessage = res;
		            });
		            
		            this.loadWorkflowData();
		            
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
	
	private doneWorkflowData(){
		
        this.editService.doneWorkflow(this.workflowSaveRequest).subscribe(
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
	
	private archiveWorkflowData(){
		
        this.editService.archiveWorkflow(this.workflowSaveRequest.workflow).subscribe(
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
	
	collapseRecordPanel() {		
		$(".workflow-content-container").removeClass("expanded").addClass("collapsed");
		$(".workflow-inline-content-container").hide();
	}
	
	expandRecordPanel() {		
		$(".workflow-content-container").removeClass("collapsed").addClass("expanded");		
		$(".workflow-inline-content-container").show();
	}


}
