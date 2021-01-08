import { Component, EventEmitter, Input, Output, OnInit, ViewChild, ElementRef, AfterViewInit  } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import * as $ from 'jquery';

import { OcrWord, OcrBox, CompanyWorkflowtypeItemOcrSettingPreset } from '../../ui-models';
import { HttpHelper } from '../../helper/http-hepler';

@Component({
  selector: 'app-invoice-ocr-detail',
  templateUrl: './invoice-ocr-detail.component.html',
  styleUrls: ['./invoice-ocr-detail.component.css']
})
export class InvoiceOcrDetailComponent implements OnInit, AfterViewInit  {

	@ViewChild('scannedItemPreviewContainer')previewContainer: ElementRef | null = null;

	private _foundWords :Record<string, OcrWord[]> = {};
	propertyLabels :Record<string, string> = {};
	isEditing :Record<string, boolean> = {};

	selectedKey :string = "";
	selectedWord :OcrWord | null = null;
	_scanedPdfPath: string = "";

	selectedOcrPreset : CompanyWorkflowtypeItemOcrSettingPreset | null = null;

	ocrPresetList: CompanyWorkflowtypeItemOcrSettingPreset[] = [];

	@Input('showOcrDetails') showOcrDetails :boolean = false;
	//@Input('scanedPdfPath') scanedPdfPath :string = "";

  @Input('scanedPdfPath') set setScanedPdfPath(value: string) {
      this._scanedPdfPath = value;
      this.pdfFileViewUrl = {
                  url: HttpHelper.dataServer + '/archive/data/file/view/' + value,
                  withCredentials: true
      };
  }


	@Input('scanedHocrPath') scanedHocrPath :string = "";
	@Input('fileIsPdf') fileIsPdf: boolean = true;
	@Input('fileIsImage') fileIsImage: boolean = false;

	@Input('ocrPresetList')
	set setOcrPresetList(_value: CompanyWorkflowtypeItemOcrSettingPreset[]) {
	    this.ocrPresetList = _value != null ? _value : [];
	    if(this.ocrPresetList !== null && this.ocrPresetList.length > 0){
	      this.selectedOcrPreset = this.ocrPresetList[0];
	      this.selectedOcrPresetChanged.emit(this.selectedOcrPreset);
	    }
	    else{
	      this.selectedOcrPreset = null;
	    }

	}

	@Output() selectedOcrPresetChanged = new EventEmitter();


	@Input('imageSizeX') imageSizeX :number = 300;
	@Input('imageSizeY') imageSizeY :number = 500;

	@Input('foundWords')
	set foundWords(foundWordsInput :Record<string, OcrWord[]>) {
	    this._foundWords = foundWordsInput;

	    this.propertyLabels = {};

	    if(this._foundWords){
	    	for(var key in  this._foundWords){
	    		this.translate.get(key).subscribe((res: string) => {
		    		this.propertyLabels[key] = res;
		    		this.isEditing[key] = false;
		    		this.editedValues[key] = "";
		      });
	    	}
	    }

	}

	@Input() editedValues :Record<string,string> = {};
	@Output() editedValuesChange = new EventEmitter<Record<string,string>>();

	updateEditedValues(){
	  this.editedValuesChange.emit(this.editedValues);
	}

	editedValuesChanged(value:string, key:string){
	  this.editedValues[key] = value;
	  this.updateEditedValues();
	}

	pdfZoom :any = 'page-fit';
	showAllPages :boolean = true;
	pdfPageIndex :number = 1;
	showSelectedArea :boolean = false;
	selectedAreaWidth :number = 200;
	selectedAreaHeight :number = 100;
	selectedAreaLeft :number = 400;
	selectedAreaTop :number = 200;

	previewWidth :number = 600;
	previewHeight :number = 1100;

	private yScale :number = 1;

  pdfFileViewUrl = {
       url: HttpHelper.dataServer + '/archive/data/file/view/' + this._scanedPdfPath,
       withCredentials: true
	};

	get foundWords():Record<string, OcrWord[]> {
		    return this._foundWords;
	}

	get keys() {
	    return Object.keys(this._foundWords);
	}

	get imageFileViewUrl():string {
		return 'url(' + HttpHelper.dataServer + '/archive/data/file/view/' + this._scanedPdfPath + ')';
		//return 'url()';
	}

	get fileViewUrl1() {
	  //return HttpHelper.dataServer + '/archive/data/file/view/' + this._scanedPdfPath;

	  return {
       url: HttpHelper.dataServer + '/archive/data/file/view/' + this._scanedPdfPath,
       withCredentials: true
    };

	}

	isWordSelected(foundWord :OcrWord):boolean{
		if(this.selectedWord != null && foundWord != null && this.selectedWord.id === foundWord.id){
			return true;
		}
		return false;
	}

	get debugData():Record<string, string>{
		return this.editedValues;
	}

	setSelectedPresetId(id: string){
	  if(this.ocrPresetList != null){
	    for(var index in this.ocrPresetList){
	      if(this.ocrPresetList[index].id === id){
	        this.selectedOcrPreset = this.ocrPresetList[index];
	        this.selectedOcrPresetChanged.emit(this.selectedOcrPreset);
	        return;
	      }
	    }
	  }
	}

	getSelectedPresetId(): string{
	  return this.selectedOcrPreset != null ? this.selectedOcrPreset.id : "";
	}

	constructor(protected translate: TranslateService,) {

	}

	ngOnInit() {

	}

	ngAfterViewInit() {

		this.yScale = this.previewWidth / this.imageSizeX;
		this.previewHeight = this.yScale * this.imageSizeY;

		setTimeout(()=>{

		 }, 100);

	}

	selectFoundWord(foundWord :OcrWord){
    if(this.previewContainer == null){
      return;
    }
		this.selectedWord = foundWord;
		this.pdfPageIndex = foundWord.pageIndex + 1;

		var previewLeft = this.previewContainer.nativeElement.offsetLeft + 10;
		var previewTop = this.previewContainer.nativeElement.offsetTop + 10;

		var pdfPage = null;
		var pdfPageWidth = null;
		var pdfPageHeight = null;
		var xscale = (this.previewContainer.nativeElement.offsetWidth - 20) / this.imageSizeX;
		var yscale = (this.previewContainer.nativeElement.offsetHeight - 20) / this.imageSizeY;

		if(this.fileIsPdf){

			pdfPage = $("div.scanned-item-preview-container div.page");
			if(pdfPage.length > foundWord.pageIndex){
				pdfPage = pdfPage[foundWord.pageIndex];
				pdfPageWidth = pdfPage.offsetWidth;
				pdfPageHeight = pdfPage.offsetHeight;

				previewLeft += pdfPage.offsetLeft;
				//previewTop = pdfPage.offsetTop;

				xscale = pdfPageWidth / foundWord.pageWidth;
				yscale = pdfPageHeight / foundWord.pageHeight;

				var scalestr = "page scale    : " + xscale + " : " +  yscale + "\n";
				scalestr += "page dimention: " + pdfPageWidth + " : " + pdfPageHeight + "\n";
				scalestr += "page position : " + previewLeft + " : " + previewTop + "\n";
				scalestr += "foundWord dim : " + foundWord.pageWidth + " : " + foundWord.pageHeight + "\n";

				console.log("pdfPage found:" , pdfPage );
				console.log(scalestr );

			}


		}


		var wordBox = foundWord.box;
		var valueBox = foundWord.value.box;

		var selectBox :OcrBox= this.getSelectBox(foundWord);

		this.selectedAreaWidth = selectBox.width * xscale + 8;
		this.selectedAreaHeight = selectBox.height * yscale + 8;

		this.selectedAreaLeft = previewLeft + (selectBox.left * xscale) - 4;
		this.selectedAreaTop = previewTop + (selectBox.top * yscale) - 4;

		var scalestr = "scale         : " + xscale + " : " +  yscale + "\n";
		scalestr += "wordBox       : " + wordBox.left + " : " + wordBox.top + " : " + wordBox.width + " : " + wordBox.height + "\n";
		scalestr += "valueBox      : " + valueBox.left + " : " + valueBox.top + " : " + valueBox.width + " : " + valueBox.height + " : " + foundWord.value.text + "\n";
		scalestr += "selectBox     : " + selectBox.left + " : " + selectBox.top + " : " + selectBox.width + " : " + selectBox.height + "\n";
		scalestr += "calc dimention: " + this.selectedAreaWidth + " : " + this.selectedAreaHeight + "\n";
		scalestr += "calc position : " + this.selectedAreaLeft + " : " + this.selectedAreaTop + "\n";

		console.log(scalestr );

		this.showSelectedArea = true;
	}

	selectDetailItem(key :string){

		this.selectedKey = key;

		this.showSelectedArea = false;

	}

	private getSelectBox(foundWord :OcrWord):OcrBox{
		var wordBox = foundWord.box;
		var valueBox = foundWord.value.box;

		var box: OcrBox = new OcrBox;

		box.width = Math.abs(valueBox.right - wordBox.left);
		box.height = Math.abs(valueBox.bottom - wordBox.top);
		box.left = Math.min(wordBox.left, valueBox.left);
		box.top = Math.min(wordBox.top, valueBox.top);

		box.right = box.left + box.width;
		box.bottom = box.top + box.height;

		return box;
	}

	startEditKey(key:string){
		this.isEditing[key] = true;
		var item = document.getElementById("valueeditbox" + key);
		if(item){
		  item.focus();
		}

	}

	useFoundWord(foundWord :OcrWord){
		this.editedValues[this.selectedKey] = foundWord.value.text;
	}


}
