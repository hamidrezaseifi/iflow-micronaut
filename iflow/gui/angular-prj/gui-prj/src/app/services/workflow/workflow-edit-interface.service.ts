
export interface WorkflowEditInterfaceService  {

	
	getInitCreateUrl();
	
	getCreateWorkflowUrl();
	
	getUploadFileUrl();
	
	
	getSaveWorkflowUrl();
	
	getDoneWorkflowUrl();
	
	getArchiveWorkflowUrl();
	
	getUploadOcrScanFileUrl();

	getInitEditUrl(identity :string);

	
	uploadTempFiles(ocrScanFile : File);

	loadCreateInitialData();
	
	loadEditInitialData(identity: string);

		
	
}
