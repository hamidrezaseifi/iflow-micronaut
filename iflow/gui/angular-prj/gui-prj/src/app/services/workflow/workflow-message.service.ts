import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

import { WorkflowMessage } from '../../wf-models/workflowmessage';

import { ErrorServiceService } from '../error-service.service';
import { HttpHepler } from '../../helper/http-hepler';


@Injectable({
  providedIn: 'root'
})
export class WorkflowMessageService {
	
	loadMessageUrl :string = "/general/data/workflowmessages";
	assignWorkflowUrl :string = "/general/data/workflow/assign/";

	isReloadingMessages : boolean = false;
	
	public workflowMessageListSubject: BehaviorSubject<WorkflowMessage[]>;


	constructor(
			private http:HttpClient, 
			private errorService: ErrorServiceService,) { 
		this.workflowMessageListSubject = new BehaviorSubject<WorkflowMessage[]>([]);
        
	}
	
    public get workflowMessageList(): WorkflowMessage[] {
        return this.workflowMessageListSubject.value;
    }
	
	loadMessages(resetCach: boolean){
    	
    	this.isReloadingMessages = true;
    	var url = this.loadMessageUrl + "?reset=" + (resetCach ? "1" : "0");
    	        
        const httpOptions = { headers: HttpHepler.generateJsonHeader() };
    	
    	return this.http.post(url, new HttpParams(), httpOptions);	      
    	
	}
	
	assignMe(workflowIdentity: string){
    	
    	this.isReloadingMessages = true;
    	var url = this.assignWorkflowUrl + workflowIdentity;
    	       
        const httpOptions = { headers: HttpHepler.generateJsonHeader() };
    	
    	return this.http.post(url, new HttpParams(), httpOptions);	      
	}
	
}
