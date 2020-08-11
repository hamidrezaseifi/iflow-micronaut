
import { Workflow, WorkflowProcessCommand, AssignItem, WorkflowUploadedFile } from '../wf-models';
import { UploadedFile } from '../ui-models';


export class WorkflowSaveRequest {
	
	workflow :Workflow = null;
	expireDays :number = 0;
	assigns :AssignItem[] = [];
	command :WorkflowProcessCommand = WorkflowProcessCommand.NONE;
	uploadedFiles :WorkflowUploadedFile[] = [];
	comments :string = "";

}

