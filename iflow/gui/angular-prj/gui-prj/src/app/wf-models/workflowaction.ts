
import { User } from '../ui-models';
import { WorkflowTypeStep } from '../wf-models';

export class WorkflowAction {
	assignToIdentity :string = "";
	currentStepIdentity :string = "";
	comments :string = "";
	status :Number = 0;
	currentStep :WorkflowTypeStep = new WorkflowTypeStep;
	assignToUser :User = new User;
	assignToUserName: string = "";
	expireDays :Number = 0;

}

