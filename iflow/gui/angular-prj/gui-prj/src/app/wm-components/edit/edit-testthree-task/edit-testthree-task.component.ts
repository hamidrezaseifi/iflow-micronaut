import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable } from 'rxjs';

import { GlobalService } from '../../../services/global.service';
import { TestthreetaskWorkflowEditService } from '../../../services/workflow/testthreetask/testthreetask-workflow-edit.service';
import { LoadingServiceService } from '../../../services/loading-service.service';
import { ErrorServiceService } from '../../../services/error-service.service';

import { User, Department, GeneralData, UploadedFile, UploadedResult } from '../../../ui-models';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType, WorkflowUploadFileResult, WorkflowUploadedFile, WorkflowFile }
	from '../../../wf-models';
import { GermanDateAdapter, parseDate, formatDate } from '../../../helper';

import { TestThreeTaskWorkflowSaveRequest } from '../../../wf-models/testthreetask/testthreetask-workflow-save-request';
import { TestThreeWorkflowSaveRequestInit } from '../../../wf-models/testthreetask/testthreetask-workflow-save-request-init';
import { EditWorkflowBaseComponent } from '../../edit-workflow-base.component';

@Component({
  selector: 'app-edit-testthree-task',
  templateUrl: './edit-testthree-task.component.html',
  styleUrls: ['../wm-edit.css', './edit-testthree-task.component.css'],
  providers: [{provide: DateAdapter, useClass: GermanDateAdapter}, TestthreetaskWorkflowEditService]
})
export class EditTestthreeTaskComponent extends EditWorkflowBaseComponent implements OnInit {

	saveMessage :string = "";

	workflowIdentity :string = "";

	workflowListUrl :string = "/workflow/list";

	workflowSaveRequest :TestThreeTaskWorkflowSaveRequest = new TestThreeTaskWorkflowSaveRequest();

	uploadedFiles :UploadedFile[] = [];

	get assignedUsers() : AssignItem[]{
		if(this.workflowSaveRequest != null){
			return this.workflowSaveRequest.assigns;
		}
		return [];
	}


	get debugData() :string{
		var ss = formatDate(new Date(), 'dd.mm.yyyy');
		ss += " -- " + parseDate(ss, 'dd.mm.yyyy');
		return ss;
	}

	get isWorkflowDone() :boolean{
		if(this.workflowSaveRequest.workflow.workflow){
			return this.workflowSaveRequest.workflow.workflow.isDone;
		}
		return false;
	}

	get isWorkflowInLastStep() :boolean{
		if(this.workflowSaveRequest.workflow.workflow){
			return this.workflowSaveRequest.workflow.workflow.isLastStep;
		}
		return false;
	}

	get canSave() :boolean{
		if(this.workflowSaveRequest.workflow.workflow){
			return this.workflowSaveRequest.workflow.workflow.canSave;
		}
		return false;
	}

	get canDone() :boolean{
		if(this.workflowSaveRequest.workflow.workflow){
			return this.workflowSaveRequest.workflow.workflow.canDone;
		}
		return false;
	}

	get canArchive() :boolean{
		if(this.workflowSaveRequest.workflow.workflow){
			return this.workflowSaveRequest.workflow.workflow.canArchive;
		}
		return false;
	}

	get canAssign() :boolean{
		if(this.workflowSaveRequest.workflow.workflow){
			return this.workflowSaveRequest.workflow.workflow.canAssign;
		}
		return true;
	}



	constructor(
		  private router: Router,
			protected global: GlobalService,
			private translate: TranslateService,
			public  editService :TestthreetaskWorkflowEditService,
			private loadingService: LoadingServiceService,
			private http: HttpClient,
			private errorService: ErrorServiceService,
      private formBuilder: FormBuilder,
      private dateAdapter: DateAdapter<Date>,
      private route: ActivatedRoute,
	) {
    super(global, formBuilder.group({
                  			expireDays: [10, Validators.required],

                  			controllerIdentity: ['', Validators.required],
                  			comments: [''],

                  	  }));

		this.router.events.subscribe((evt) => {
			if (evt instanceof NavigationEnd) {
				this.workflowIdentity = this.route.snapshot.params['identity'];
				this.loadInitialData();
			}
		});

		this.dateAdapter.setLocale('de');

	}

	ngOnInit() {

	}

	reload() {

		this.loadInitialData();

	}

	private loadInitialData(){

		if(this.workflowIdentity == ''){
			return;
		}

	 	this.loadWorkflowData();

	}

	private loadWorkflowData(){

		this.loadingService.showLoading();
		this.editService.loadEditInitialData(this.workflowIdentity).subscribe(
	        (initialData :TestThreeWorkflowSaveRequestInit) => {

				console.log("set inital-data from workflow-edit. : ", initialData);
				//alert("from app-comp: \n" + JSON.stringify(data));

				if(initialData && initialData !== null){
					this.workflowSaveRequest = initialData.workflowSaveRequest;
					this.viewWorkflowModel = this.workflowSaveRequest.workflow.workflow;
					this.setToControlValues();

				}
				else{
					this.workflowSaveRequest = new TestThreeTaskWorkflowSaveRequest();
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


	setToControlValues(){
		if(this.workflowSaveRequest && this.workflowSaveRequest.workflow){

			this.workflowEditForm.controls["expireDays"].setValue(this.workflowSaveRequest.expireDays);

			this.workflowEditForm.controls["controllerIdentity"].setValue(this.workflowSaveRequest.workflow.workflow.controllerId);
			this.workflowEditForm.controls["comments"].setValue(this.workflowSaveRequest.comments);

			this.uploadedFiles = WorkflowFile.toUploadedFileList(this.workflowSaveRequest.workflow.workflow.files);
		}
	}

	setFormControlValues(){

		this.workflowSaveRequest.expireDays = this.workflowEditForm.controls["expireDays"].value;

		this.workflowSaveRequest.workflow.workflow.controllerId = this.workflowEditForm.controls["controllerIdentity"].value;
		this.workflowSaveRequest.comments = this.workflowEditForm.controls["comments"].value;

		this.workflowSaveRequest.uploadedFiles = WorkflowUploadedFile.loadUploadedFiles(this.uploadedFiles);

	}

	get hasNoAssigns() :boolean{
		if(this.workflowSaveRequest && this.workflowSaveRequest.assigns){
			return this.workflowSaveRequest.assigns.length == 0;
		}
		return false;
	}

	save(makeDone :boolean){

		this.setFormControlValues();

		//return;

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
		//return;

		this.loadingService.showLoading();

		this.archiveWorkflowData();

	}
	private saveWorkflowData(){

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


	onUsersSelected(assigns: AssignItem[]) {
		this.workflowSaveRequest.assigns = [];

		for(var item in assigns){
			var assign = new AssignItem;
			assign.itemId = assigns[item].itemId;
			assign.itemType = assigns[item].itemType;

			this.workflowSaveRequest.assigns.push(assign);
		}

	}

}
