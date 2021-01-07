
import { User } from '../ui-models';
import { WorkflowType } from '../wf-models';
import { WorkflowTypeStep } from '../wf-models';
import { WorkflowFile } from '../wf-models';
import { WorkflowAction } from '../wf-models';

export class Workflow {
	id: string = "";
	status: string = "";
	workflowType: WorkflowType = new WorkflowType;
	currentStep: WorkflowTypeStep = new WorkflowTypeStep;
	currentStepId: string = "";
	controllerId: string = "";
	controllerUser: User = new User;
	comments: string = "";

	assigned: boolean = false;
	initializing: boolean = false;
	meAssigned: boolean = false;
	notAssigned: boolean = false;
	currentStepIndex :number = 0;
	isDone: boolean = false;
	isLastStep: boolean = false;

	canDone: boolean = false;
	canArchive: boolean = false;
	canSave: boolean = false;
	canAssign: boolean = false;
	canEdit: boolean = false;

	loggedUserControllerAndDone: boolean = false;


	files: WorkflowFile[] = [];
	actions: WorkflowAction[] = [];


}

