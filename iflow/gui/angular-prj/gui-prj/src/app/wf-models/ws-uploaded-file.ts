
import { UploadedFile } from '../ui-models';

export class WorkflowUploadedFile {
		
	fileName: string = "";
	filePathHashed: string = "";

	constructor(
			uploadedFile?: UploadedFile,
	) {
		if(uploadedFile){
			this.fileName = uploadedFile.fileName;
			this.filePathHashed = uploadedFile.scanedPdfPath;
		}
		
	}
	
	public static loadUploadedFiles(uploadedFiles :UploadedFile[]):WorkflowUploadedFile[]{
		
		var wUploadedFiles: WorkflowUploadedFile[] = [];
		for(var index in uploadedFiles){
			var wUploadedFile :WorkflowUploadedFile = new WorkflowUploadedFile(uploadedFiles[index]);
			wUploadedFiles.push(wUploadedFile);
		}
		return wUploadedFiles;
	}
	
	
}
