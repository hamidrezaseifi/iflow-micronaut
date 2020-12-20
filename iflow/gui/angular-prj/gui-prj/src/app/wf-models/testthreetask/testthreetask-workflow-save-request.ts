
import { TestThreeTaskWorkflow } from './testthreetask-workflow';
import { WorkflowProcessCommand, AssignItem, WorkflowUploadedFile } from '../../wf-models';
import { UploadedFile } from '../../ui-models';

export class TestThreeTaskWorkflowSaveRequest {

	workflow :TestThreeTaskWorkflow = new TestThreeTaskWorkflow();
	expireDays :number = 0;
	assigns :AssignItem[] = [];
	command :WorkflowProcessCommand = WorkflowProcessCommand.NONE;
	uploadedFiles :WorkflowUploadedFile[] = [];
	comments :string = "";

}

