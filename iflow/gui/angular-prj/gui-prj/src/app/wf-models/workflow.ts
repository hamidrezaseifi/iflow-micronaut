
import { User } from '../ui-models';
import { WorkflowType } from '../wf-models';
import { WorkflowTypeStep } from '../wf-models';
import { WorkflowFile } from '../wf-models';
import { WorkflowAction } from '../wf-models';

export class Workflow {
	identity: string;
	status: string;
	workflowType: WorkflowType;
	currentStep: WorkflowTypeStep;
	currentStepIdentity: string;
	controllerIdentity: string;
	controllerUser: User;
	comments: string;

	assigned: boolean;
	initializing: boolean;
	meAssigned: boolean;
	notAssigned: boolean;
	currentStepIndex :number;
	isDone :boolean;
	isLastStep :boolean;

	canDone :boolean;
	canArchive :boolean;
	canSave :boolean;
	canAssign :boolean;
	canEdit :boolean;

	loggedUserControllerAndDone :boolean;


	files: WorkflowFile[];
	actions: WorkflowAction[];


}

