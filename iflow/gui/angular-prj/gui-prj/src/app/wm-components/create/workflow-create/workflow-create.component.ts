import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import {TranslateService} from '@ngx-translate/core';
import { Observable } from 'rxjs';

import { GlobalService } from '../../../services/global.service';

import { WorkflowType } from '../../../wf-models/workflowtype';
import { User, GeneralData } from '../../../ui-models';

@Component({
  selector: 'app-workflow-create',
  templateUrl: './workflow-create.component.html',
  styleUrls: ['./workflow-create.component.css']
})
export class WorkflowCreateComponent implements OnInit {
	
	worlflowTypes: WorkflowType[] = [];
	generalDataObs :Observable<GeneralData> = null;

	constructor(
		    private router: Router,
			private global: GlobalService,
			translate: TranslateService,
			
	) {
		
		this.generalDataObs = this.global.currentSessionDataSubject.asObservable();
		this.generalDataObs.subscribe(data => {
			   
			this.worlflowTypes = data.workflow.worlflowTypes;
		});
	}
	  
	ngOnInit() {
					
		this.global.loadAllSetting();
	}
	
	

}

