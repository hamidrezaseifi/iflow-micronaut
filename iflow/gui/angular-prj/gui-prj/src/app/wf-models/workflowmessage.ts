
import { Workflow } from '../wf-models';

export class WorkflowMessage {
	public workflowIdentity: string;
	public message: string;
	public createdAtString: string;
	public remainingDays: string;
	public status: string;
	public workflow: Workflow;
}

