import { Component, EventEmitter, Input, Output, OnInit, ViewChild, ElementRef, AfterViewInit  } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import $ from "jquery";

@Component({
  selector: 'app-file-preview',
  templateUrl: './file-preview.component.html',
  styleUrls: ['./file-preview.component.css']
})
export class FilePreviewComponent implements OnInit {

	
	@Input('fileUrl') fileUrl :string = "";
	@Input('imageSizeX') imageSizeX :number = 300;
	@Input('imageSizeY') imageSizeY :number = 500;
	@Input('fileIsPdf') fileIsPdf: boolean = true;
	@Input('fileIsImage') fileIsImage: boolean = false;

	@Output() dialogClosed = new EventEmitter<boolean>();
	
	@Input('showDialog') showDialog :boolean = false;
	
	closeDialog() {
	    this.showDialog = false;
	    this.dialogClosed.emit(false);
	}
	
	previewWidth :number = 600;
	previewHeight :number = 1100;
	pdfZoom :any = 'page-fit';

	constructor(protected translate: TranslateService,) { 
		
	}

	ngOnInit() {
		
	}

	ngAfterViewInit() {
		
		var yScale = this.previewWidth / this.imageSizeX;
		this.previewHeight = yScale * this.imageSizeY;
				
	}

	get imageFileViewUrl():string {
		return 'url(/general/data/file/view/' + this.fileUrl + ')';
		//return 'url()';
	}
	
	get fileViewUrl():string {
		return '/general/data/file/view/' + this.fileUrl;
		//return 'url()';
	}
		

}
