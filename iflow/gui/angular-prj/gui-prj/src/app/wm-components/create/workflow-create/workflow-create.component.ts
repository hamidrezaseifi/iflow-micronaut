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

	workflowTypes: WorkflowType[] = [];

	constructor(
		    private router: Router,
			private global: GlobalService,
			translate: TranslateService,

	) {

		this.global.currentSessionDataSubject.asObservable().subscribe(data => {

			this.workflowTypes = data.workflow.workflowTypes;
		});
	}

	ngOnInit() {

		this.global.loadAllSetting();
	}



}

