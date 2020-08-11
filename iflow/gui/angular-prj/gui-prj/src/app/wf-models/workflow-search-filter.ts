
import { User } from '../ui-models';
import { WorkflowType } from '../wf-models';
import { WorkflowTypeStep } from '../wf-models';
import { WorkflowFile } from '../wf-models';
import { WorkflowAction } from '../wf-models';

export class WorkflowSearchFilter {
	
	meAssigned :boolean = true;
	assignedUserIdSet :string[] = [];
	statusList :string[] = [];
	workflowTypes :string[] = [];
	workflowSteps :string[] = [];

}

