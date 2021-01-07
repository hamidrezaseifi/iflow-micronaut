
import { Workflow } from '../wf-models';

export class WorkflowMessage {
	public workflowId :string = "";
	public message :string = "";
	public createdAtString :string = "";
	public remainingDays :string = "";
	public status :string = "";
	public workflow: Workflow = new Workflow;
}

