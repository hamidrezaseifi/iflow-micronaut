
import { User } from '../ui-models';
import { WorkflowFileVersion } from '../wf-models';
import { UploadedFile } from '../ui-models';

export class WorkflowFile {
	createdByIdentity :string = "";
	title :string = "";
	extention :string = "";
	activeFilePath :string = "";
	comments :string = "";
	activeFileVersion :Number = 0;
	assignToUser :User = new User;
	status :Number = 0;
	fileVersions :WorkflowFileVersion[] = [];
	fileIsPdf: boolean = false;
	fileIsImage: boolean = false;
	activeFilePathHash :string = "";


	public static toUploadedFile(workflowFile :WorkflowFile) :UploadedFile{
		var uploadedFile :UploadedFile = new UploadedFile;

		uploadedFile.fileName = workflowFile.title;
		uploadedFile.scanedPdfPath = workflowFile.activeFilePathHash;
		uploadedFile.fileIsPdf = workflowFile.fileIsPdf;
		uploadedFile.fileIsImage = workflowFile.fileIsImage;

		return uploadedFile;
	}

	public static toUploadedFileList(workfloeFiles :WorkflowFile[]):UploadedFile[]{

		var uploadedFiles: UploadedFile[] = [];
		for(var index in workfloeFiles){

			uploadedFiles.push(WorkflowFile.toUploadedFile(workfloeFiles[index]));
		}
		return uploadedFiles;
	}
}

