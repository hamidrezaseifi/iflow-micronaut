import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../../loading-service.service';
import { HttpHepler } from '../../../helper/http-hepler';
import { AuthenticationService } from '../../authentication.service';
import { WorkflowEditBaseService } from '../workflow-edit-base.service';

import { WorkflowSaveRequestInit } from '../../../wf-models/workflow-save-request-init';
import { WorkflowSaveRequest } from '../../../wf-models/workflow-save-request';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType } from '../../../wf-models';


@Injectable({
  providedIn: 'root'
})
export class TestthreetaskWorkflowEditService extends WorkflowEditBaseService {

	public workflowSaveRequestInitSubject: BehaviorSubject<WorkflowSaveRequestInit> = new BehaviorSubject<WorkflowSaveRequestInit>(null);

	workflowSaveRequestInit :WorkflowSaveRequestInit = null;
	
	getInitCreateUrl() :string{
		return "/workflow/testthreetask/data/initcreate";
	}
	
	getCreateWorkflowUrl() :string{
		return "/workflow/testthreetask/data/create";
	}
	
	getUploadFileUrl() :string{
		return "/workflow/testthreetask/data/createfile";
	}
	
	getSaveWorkflowUrl() :string{
		return "/workflow/testthreetask/data/save";
	}
	
	getDoneWorkflowUrl() :string{
		return "/workflow/testthreetask/data/done";
	}
	
	getArchiveWorkflowUrl() :string{
		return "/workflow/testthreetask/data/archive";
	}
	
	getInitEditUrl(identity :string) :string{
		return "/workflow/testthreetask/data/initedit/" + identity;
	}

	
	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router, 
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) { 
		super(http, loadingService, router, route, autService);
		
	}

}
