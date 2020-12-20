
import { SingleTaskWorkflow } from './singletask-workflow';
import { WorkflowProcessCommand, AssignItem, WorkflowUploadedFile } from '../../wf-models';
import { UploadedFile } from '../../ui-models';

export class SingleTaskWorkflowSaveRequest {

	workflow :SingleTaskWorkflow = new SingleTaskWorkflow();
	expireDays :number = 0;
	assigns :AssignItem[] = [];
	command :WorkflowProcessCommand = WorkflowProcessCommand.NONE;
	uploadedFiles :WorkflowUploadedFile[] = [];
	comments :string = "";

}

