
import { InvoiceWorkflow } from './invoice-workflow';
import { WorkflowProcessCommand, AssignItem, WorkflowUploadedFile } from '../wf-models';
import { UploadedFile } from '../ui-models';

export class InvoiceWorkflowSaveRequest {
	
	workflow :InvoiceWorkflow = new InvoiceWorkflow();
	expireDays :number = 0;
	assigns :AssignItem[] = [];
	command :WorkflowProcessCommand = WorkflowProcessCommand.NONE;
	uploadedFiles :WorkflowUploadedFile[] = [];
	comments :string = "";
	
}

