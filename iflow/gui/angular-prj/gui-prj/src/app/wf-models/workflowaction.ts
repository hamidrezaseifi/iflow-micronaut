
import { User } from '../ui-models';
import { WorkflowTypeStep } from '../wf-models';

export class WorkflowAction {
	private assignToIdentity :string = "";
	private currentStepIdentity :string = "";
	private comments :string = "";
	private status :Number = 0;
	private currentStep :WorkflowTypeStep = new WorkflowTypeStep;
	private assignToUser :User = new User;
	private expireDays :Number = 0;

}

