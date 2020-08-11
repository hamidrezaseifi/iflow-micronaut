
import { User } from '../ui-models';
import { WorkflowTypeStep } from '../wf-models';

export class WorkflowAction {
	private assignToIdentity :string;
	private currentStepIdentity :string;
	private comments :string;
	private status :Number;
	private currentStep :WorkflowTypeStep;
	private assignToUser :User;
	private expireDays :Number;

}

