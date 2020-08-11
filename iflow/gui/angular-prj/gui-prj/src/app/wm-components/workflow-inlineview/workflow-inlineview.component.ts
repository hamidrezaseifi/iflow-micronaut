import { Component, OnInit, Input } from '@angular/core';

import { WorkflowType, Workflow, WorkflowTypeStep, WorkflowFile } from '../../wf-models';
import { User, GeneralData } from '../../ui-models';
import { OcrWord, UploadedFile, UploadedResult } from '../../ui-models';

@Component({
  selector: 'app-workflow-inlineview',
  templateUrl: './workflow-inlineview.component.html',
  styleUrls: ['./workflow-inlineview.component.css']
})
export class WorkflowInlineviewComponent implements OnInit {

	@Input('workflow') 
	viewWorkflowModel :Workflow;

	previewFile :UploadedFile = new UploadedFile;
	showFilePreviewDialog :boolean = false;

	
	constructor() { }

	ngOnInit() {
		
	}
	
	showFilePreview(file :WorkflowFile){
		
		this.showFilePreviewDialog = false;		
		
		this.previewFile = WorkflowFile.toUploadedFile(file);
    	this.showFilePreviewDialog = true;
    	
		console.log("preview file : ", this.previewFile);

	}
	
	onFilePreviewDialogClosed(closed: boolean) {
		this.showFilePreviewDialog = false;	
	}  

}
