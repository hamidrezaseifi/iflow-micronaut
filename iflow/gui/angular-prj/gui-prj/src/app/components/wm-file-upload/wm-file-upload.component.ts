import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import $ from "jquery";

import { LoadingServiceService } from '../../services/loading-service.service';
import { ErrorServiceService } from '../../services/error-service.service';

import { OcrWord, UploadedFile, UploadedResult } from '../../ui-models';

import { WorkflowEditInterfaceService } from '../../services';

@Component({
  selector: 'app-wm-file-upload',
  templateUrl: './wm-file-upload.component.html',
  styleUrls: ['./wm-file-upload.component.css']
})
export class WmFileUploadComponent implements OnInit {

	@Input('ocrScanningEnabled') ocrScanningEnabled: boolean = false;
	@Input('showHeaderTitle') showHeaderTitle: boolean = true;
	@Input('drawBorder') drawBorder: boolean = false;
	@Input('editService') editService: WorkflowEditInterfaceService = null;
	@Input('uploadedFiles') uploadedFiles :UploadedFile[] = [];	

	@Output() onOcrUploadedFile = new EventEmitter<UploadedFile>();
	@Output() onShowUploadedFileScannDetail = new EventEmitter<UploadedFile>();
	@Output() onUploadedFilesChanged = new EventEmitter<UploadedFile[]>();

	
	previewFile :UploadedFile = new UploadedFile;
	showFilePreviewDialog :boolean = false;

	fileExistsMessage :string = "common.file-exists";

	constructor(
			protected translate: TranslateService,
			protected loadingService: LoadingServiceService,
			protected errorService: ErrorServiceService,
			) { 
		
		translate.get('common.file-exists').subscribe((res: string) => {
        	this.fileExistsMessage =  res;
        });
	}

	ngOnInit() {
		
	}

  
	canShowScanResultsButton(uploaded :UploadedFile):boolean{
		return uploaded.isScanned === true;
	}
	
	canshowOcrUploadButton(uploaded :UploadedFile):boolean{
		return uploaded.isScanned === false;
	}
	
	uploadFile(fileInput: any) {
		
		
		var file = <File>fileInput.target.files[0];
		console.log("file: ", file);
		//alert(file.name);
		if(this.existsUploadedByFileName(file.name)){
			$("#inlineuploadfile")[0].type = "text";
			$("#inlineuploadfile")[0].type = "file";

			this.errorService.showError(this.fileExistsMessage , "");	
			return;
		}
		
		$("#inlineuploadfile")[0].type = "text";
		$("#inlineuploadfile")[0].type = "file";
		
		this.loadingService.showLoading();
		
		this.editService.uploadTempFiles(file).subscribe(
		        (result: UploadedResult) => {		        	
		            console.log("upload invoice file result", result);
		            this.loadingService.hideLoading();
		            
		            if(result.status){
		    			if(result.status === "done"){

		    				var uploaded :UploadedFile = new UploadedFile;
		    				 
			    			uploaded.fileName = result.fileName;
			    			uploaded.scanedPdfPath = result.fileHash;
			    			uploaded.scanedHocrPath = result.hocrFileHash;
			    			uploaded.fileIsPdf = result.isFilePdf;
			    			uploaded.fileIsImage = result.isFileImage;
			    			//uploaded.imageSizeX = result.imageWidth;
			    			//uploaded.imageSizeY = result.imageHeight;
			    			uploaded.uploadResult = result;

			    			this.uploadedFiles.push(uploaded);
			    			
			    			this.onUploadedFilesChanged.emit(this.uploadedFiles);
		    	            
		    			}
		    			if(result.status === "error" && result.errorMessage){
		    				this.errorService.showError(result.errorMessage , result.errorDetail);			
		    			}
		    		}		            
		            
		         		            
		            
		            
		        },
		        response => {
		        	console.log("Error in upload invoice file", response);
		        	this.loadingService.hideLoading();
		        },
		        () => {
		        }
		    );	
		

	}
	
	removeUploadedFile(uploaded :UploadedFile){
		var index = this.uploadedFiles.indexOf(uploaded);
		if(index > -1){
			this.uploadedFiles.splice(index , 1);
			this.onUploadedFilesChanged.emit(this.uploadedFiles);
		}
	}
	
	ocrUploadedFile(uploaded :UploadedFile){
		
		var index = this.uploadedFiles.indexOf(uploaded);
		if(index > -1){
			this.onOcrUploadedFile.emit(this.uploadedFiles[index]);
		}
		
		
        
	}
	
	showScanResults(uploaded :UploadedFile){
		
		var index = this.uploadedFiles.indexOf(uploaded);
		if(index > -1){
			this.onShowUploadedFileScannDetail.emit(this.uploadedFiles[index]);

		}
		
	}
	
	showFilePreview(uploaded :UploadedFile){
		
		this.showFilePreviewDialog = false;
		var index = this.uploadedFiles.indexOf(uploaded);
		if(index > -1){
			this.previewFile = this.uploadedFiles[index];
	    	this.showFilePreviewDialog = true;
	    	
			console.log("preview file : ", this.previewFile);

		}

	}
	
	onFilePreviewDialogClosed(closed: boolean) {
		this.showFilePreviewDialog = false;	
	}  

	private findUploadedByFileName(fileName: string): UploadedFile{
		for(var index in this.uploadedFiles){
			if(this.uploadedFiles[index].fileName === fileName){
				return this.uploadedFiles[index];
			}
		}
		return null;
	}

	private existsUploadedByFileName(fileName: string): boolean{
		
		return this.findUploadedByFileName(fileName) !== null;
	}

}
