
import { Observable } from 'rxjs';
import { UploadedResult } from '../../ui-models';

export interface WorkflowEditInterfaceService  {


	getInitCreateUrl(): any;

	getCreateWorkflowUrl(): any;

	getUploadFileUrl(): any;


	getSaveWorkflowUrl(): any;

	getDoneWorkflowUrl(): any;

	getArchiveWorkflowUrl(): any;

	getUploadOcrScanFileUrl(): any;

	getInitEditUrl(identity :string): any;


	uploadTempFiles(ocrScanFile : File): Observable<UploadedResult>;

	loadCreateInitialData(): any;

	loadEditInitialData(identity: string): any;



}
