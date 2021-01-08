import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { UploadedFile, GeneralData } from '../ui-models';
import { Workflow } from '../wf-models';
import { GlobalService } from '../services/global.service';

@Component({
  template: ''
})
export class EditWorkflowBaseComponent implements OnInit {

	saveMessage :string = "";

	isRecordPanelExpanded: boolean = true;

	workflowEditForm: FormGroup | null = null;

	workflowListUrl :string = "/workflow/list";

	uploadedFiles :UploadedFile[] = [];

	viewWorkflowModel :Workflow | null = null;

  generalData : GeneralData = new GeneralData;

  constructor(protected global: GlobalService,) {
    this.global.currentSessionDataSubject.asObservable().subscribe((res: GeneralData) => {
        if(res == null || res == undefined){
          return;
        }
        this.generalData = res;
        console.log("generalData" , this.generalData);
     });
  }

  ngOnInit(): void {
  }

	get forms() {
	  if(this.workflowEditForm){
	    return this.workflowEditForm.controls;
	  }
	  return null;
	}

	onUploadedFilesChanged(uploadedFileList: UploadedFile[]) {

		this.uploadedFiles = uploadedFileList;
		console.log("uploading data changed : ", this.uploadedFiles);
	}

	collapseRecordPanel() {
	  this.isRecordPanelExpanded = false;

	}

	expandRecordPanel() {
	  this.isRecordPanelExpanded = true;

	}

}
