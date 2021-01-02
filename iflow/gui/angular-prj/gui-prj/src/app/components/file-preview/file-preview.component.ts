import { Component, EventEmitter, Input, Output, OnInit, ViewChild, ElementRef, AfterViewInit  } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import $ from "jquery";
import { HttpHepler } from '../../helper/http-hepler';
import { GlobalService } from '../../services/global.service';
import { GeneralData } from '../../ui-models';

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

	refreshToken : string = "";

	closeDialog() {
	    this.showDialog = false;
	    this.dialogClosed.emit(false);
	}

	previewWidth :number = 600;
	previewHeight :number = 1100;
	pdfZoom :any = 'page-fit';

	constructor(protected translate: TranslateService,
	            private global: GlobalService) {

	}

	ngOnInit() {
    this.global.currentSessionDataSubject.asObservable().subscribe((res: GeneralData) => {
        if(res == null || res == undefined){
          return;
        }

        this.refreshToken = res.refreshToken;
        //console.log("file preview" , this.refreshToken);
     });
	}

	ngAfterViewInit() {

		var yScale = this.previewWidth / this.imageSizeX;
		this.previewHeight = yScale * this.imageSizeY;

	}

	get authorizationData():string{
	  const auth = "Bearer: " + this.refreshToken;
	  //console.log("file preview Authorization" , auth);
	  return auth;
	}

	getHeaders(): string{
	  return "Bearer: aaaaaaaaaaaaaaaaa";
	}

	get imageFileViewUrl():string {
		return 'url(' + HttpHepler.dataServer + '/archive/data/file/view/' + this.fileUrl + ')';
		//return 'url()';
	}

	get fileViewUrl():string {
		return HttpHepler.dataServer + '/archive/data/file/view/' + this.fileUrl;
		//return 'url()';
	}


}
