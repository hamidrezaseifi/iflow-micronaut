import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';

import { GlobalService } from '../../../services/global.service';
import { SingleTaskWorkflowEditService } from '../../../services/workflow/singletask/singletask-workflow-edit.service';
import { LoadingServiceService } from '../../../services/loading-service.service';
import { ErrorServiceService } from '../../../services/error-service.service';

import { User, Department, GeneralData, UploadedFile, UploadedResult } from '../../../ui-models';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType, WorkflowUploadFileResult, WorkflowUploadedFile, WorkflowFile }
	from '../../../wf-models';
import { GermanDateAdapter, parseDate, formatDate } from '../../../helper';

import { SingleTaskWorkflowSaveRequest } from '../../../wf-models/singletask/singletask-workflow-save-request';
import { SingleTaskWorkflowSaveRequestInit } from '../../../wf-models/singletask/singletask-workflow-save-request-init';
import { EditWorkflowBaseComponent } from '../../edit-workflow-base.component';

@Component({
  selector: 'app-edit-single-task',
  templateUrl: './edit-single-task.component.html',
  styleUrls: ['../wm-edit.css' , './edit-single-task.component.css'],
  providers: [{provide: DateAdapter, useClass: GermanDateAdapter}, SingleTaskWorkflowEditService]
})
export class EditSingleTaskComponent extends EditWorkflowBaseComponent implements OnInit {

	workflowIdentity :string = "";

	workflowSaveRequest :SingleTaskWorkflowSaveRequest = new SingleTaskWorkflowSaveRequest();

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
			public  editService :SingleTaskWorkflowEditService,
			private loadingService: LoadingServiceService,
			private http: HttpClient,
			private errorService: ErrorServiceService,
      protected formBuilder: FormBuilder,
      private dateAdapter: DateAdapter<Date>,
      private route: ActivatedRoute,
	) {
    super(global, formBuilder.group({
                  			expireDays: [10, Validators.required],

                  			controllerId: ['', Validators.required],
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

	 	if(this.global.getSessionData() !== null){

	 	}
	 	else{
	 		this.global.loadAllSetting();
	 	}

	 	this.loadWorkflowData();

	}

	private loadWorkflowData(){

		this.loadingService.showLoading();
		this.editService.loadEditInitialData(this.workflowIdentity).subscribe(
	        (initialData :SingleTaskWorkflowSaveRequestInit) => {

				console.log("set inital-data from workflow-edit. : ", initialData);
				//alert("from app-comp: \n" + JSON.stringify(data));

				if(initialData && initialData !== null){
					this.workflowSaveRequest = initialData.workflowSaveRequest;
					this.viewWorkflowModel = this.workflowSaveRequest.workflow.workflow;
					this.setToControlValues();

				}
				else{
					this.workflowSaveRequest = new SingleTaskWorkflowSaveRequest();
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
		if(this.workflowEditForm && this.workflowSaveRequest && this.workflowSaveRequest.workflow){

			this.workflowEditForm.controls["expireDays"].setValue(this.workflowSaveRequest.expireDays);

			this.workflowEditForm.controls["controllerId"].setValue(this.workflowSaveRequest.workflow.workflow.controllerId);
			this.workflowEditForm.controls["comments"].setValue(this.workflowSaveRequest.comments);

			this.uploadedFiles = [];

			this.uploadedFiles = WorkflowFile.toUploadedFileList(this.workflowSaveRequest.workflow.workflow.files);
		}
	}

	setFormControlValues(){
    if(this.workflowEditForm){
      this.workflowSaveRequest.expireDays = this.workflowEditForm.controls["expireDays"].value;

      this.workflowSaveRequest.workflow.workflow.controllerId = this.workflowEditForm.controls["controllerId"].value;
      this.workflowSaveRequest.comments = this.workflowEditForm.controls["comments"].value;

      this.workflowSaveRequest.uploadedFiles = WorkflowUploadedFile.loadUploadedFiles(this.uploadedFiles);

    }


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

		console.log("Saving workflow", this.workflowSaveRequest);

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

		console.log("Done workflow", this.workflowSaveRequest);

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

}
