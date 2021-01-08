import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable, throwError , Subscription } from 'rxjs';

import { GlobalService } from '../services/global.service';
import { InvoiceWorkflowEditService } from '../services/workflow/invoice/invoice-workflow-edit.service';
import { LoadingServiceService } from '../services/loading-service.service';
import { ErrorServiceService } from '../services/error-service.service';

import { User, Department, GeneralData, OcrWord, UploadedFile, UploadedResult, CompanyWorkflowtypeItemOcrSettingPreset } from '../ui-models';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType, WorkflowUploadFileResult,
	InvoiceType, WorkflowUploadedFile, WorkflowFile } from '../wf-models';
import { InvoiceWorkflowSaveRequest } from '../wf-models/invoice/invoice-workflow-save-request';
import { InvoiceWorkflowSaveRequestInit } from '../wf-models/invoice/invoice-workflow-save-request-init';
import { InvoiceTypeControlValidator } from '../custom-validators/invoice-type-controll-validator';
import { GermanDateAdapter, parseDate, formatDate } from '../helper';
import { HttpHelper } from '../helper/http-hepler';
import { EditWorkflowBaseComponent } from './edit-workflow-base.component';

@Component({
  template: ''
})
export class InvoiceBaseComponent extends EditWorkflowBaseComponent implements OnDestroy {

	pageTitle :string = "not-initialized!";

	paymentamountOtherTypesTitle :string = "";
	paymentamountTypePaymentTitle :string = "";

	subscribed: boolean = false;

	listening :boolean = false;

	scanningFileIndex :number = -1;
	scanningFile :UploadedFile = new UploadedFile;
  scanedWordes : Record<string, OcrWord[]> = {};

	showOcrDetailsDialog :boolean = false;

	scannedSelectedValues :Record<string,string> = {};

	ocrResultMessage : any = {};

  webSocket: WebSocket | null = null;

	workflowListUrl :string = "/workflow/list";

	workflowSaveRequest :InvoiceWorkflowSaveRequest | null = null;

  ocrSettingPresets : CompanyWorkflowtypeItemOcrSettingPreset[] = [];
  selectedOcrSettingPreset : CompanyWorkflowtypeItemOcrSettingPreset = new CompanyWorkflowtypeItemOcrSettingPreset;

	selectAssign : boolean[][] = [];

	invoiceTypes : any[] = [];

	calcDiscountDate(){
		//alert("calcDiscountDate ---");

		var enterDate :Date = this.workflowEditForm.controls["discountEnterDate"].value;
		var deadline : number= this.workflowEditForm.controls["discountDeadline"].value;

		if(enterDate != null && deadline != null && deadline > 0){
			var date = new Date(enterDate.getFullYear(), enterDate.getMonth(), enterDate.getDate() + deadline);
			this.workflowEditForm.controls["discountDate"].setValue( formatDate(date, 'dd.mm.yyyy') );
		}

	}

	selectedOcrPresetChanged(preset : CompanyWorkflowtypeItemOcrSettingPreset){
	  this.selectedOcrSettingPreset = preset;
	  //alert(this.selectedOcrSettingPreset.presetName);

	  var message = this.ocrResultMessage;

	  message.selectedPreset = this.selectedOcrSettingPreset.presetName;

	  this.loadingService.showLoading();

		this.editService.processDocumentWords(message).subscribe(
      (data :any) => {

        console.log("Document Process with preset " + this.selectedOcrSettingPreset.presetName + " Result. : ", data);

        this.scanedWordes = data.words;
      },
      response => {
        console.log("Error in document Process", response);
        this.errorService.showErrorResponse(response);
      },
      () => {

        this.loadingService.hideLoading();
      }
	  );


	}

	invoiceDateChanges(){
		var enterDate :Date = this.workflowEditForm.controls["discountEnterDate"].value;

		if(enterDate === null && this.workflowEditForm.controls["invocieDate"].value !== null){
			this.workflowEditForm.controls["discountEnterDate"].setValue( this.workflowEditForm.controls["invocieDate"].value );
		}
	}

	get assignedUsers() : AssignItem[]{
		if(this.workflowSaveRequest != null){
			return this.workflowSaveRequest.assigns;
		}
		return [];
	}

	get paymentamountTitle() :string{

		return this.isPaymentInvoiceType() ? this.paymentamountTypePaymentTitle : this.paymentamountOtherTypesTitle;
	}

	isPaymentInvoiceType() :boolean{
		return this.workflowEditForm.controls["invoiceType"].value === "PAYMENT";
	}

	constructor(
		  protected router: Router,
			protected global: GlobalService,
			protected translate: TranslateService,
			protected editService :InvoiceWorkflowEditService,
			protected loadingService: LoadingServiceService,
			protected http: HttpClient,
			protected errorService: ErrorServiceService,
      protected formBuilder: FormBuilder,
      protected dateAdapter: DateAdapter<Date>
	) {
    super(global, formBuilder.group({
                  			expireDays: [10, Validators.required],

                  			comments: [''],

                  			sender: ['', Validators.required],
                  			registerNumber: ['', Validators.required],
                  			invocieDate: [new Date(), Validators.required],
                  			partnerCode: ['', Validators.required],
                  			vendorNumber: ['', Validators.required],
                  			vendorName: ['', Validators.required],
                  			isDirectDebitPermission: [false],
                  			invoiceType: [InvoiceType.NO_TYPE, [InvoiceTypeControlValidator]],

                  			discountEnterDate: [new Date(), Validators.required],
                  			discountDeadline: [0, Validators.required],
                  			discountRate: [0, Validators.required],
                  			discountDate: ["", Validators.required],

                  			paymentAmount: [0, Validators.required],

                      }));
		this.dateAdapter.setLocale('de');

		for(var o in InvoiceType){
			var str = o + "";
			var num = <number>new Number(o);
			if(isNaN(num)){
				translate.get('invoice-invoicetype-' + str.toLowerCase()).subscribe((res: string) => {
					this.invoiceTypes.push({value: o, title: res});
		        });
			}

		}

		this.translate.get('invoice-paymentamount').subscribe((res: string) => {
        	this.paymentamountOtherTypesTitle = res;
        });

		this.translate.get('invoice-paymentamount-payment').subscribe((res: string) => {
        	this.paymentamountTypePaymentTitle = res;
        });

	}

	ngOnDestroy() {
		this.unsubscribe();
	}



	onOcrUploadedFile(uploadedFile: UploadedFile) {

		var index = this.uploadedFiles.indexOf(uploadedFile);
		if(index > -1){
			this.scanningFileIndex = index;
			this.scanningFile = this.uploadedFiles[index];

			this.loadingService.showLoading();

			this.subscribe(uploadedFile);

		}
	}

	getOcrWords() :Record<string, OcrWord[]>{
	  return this.scanedWordes;
	}

	onShowUploadedFileScannDetail(uploadedFile: UploadedFile) {

		var index = this.uploadedFiles.indexOf(uploadedFile);
		if(index > -1){
			this.scanningFileIndex = index;
			this.scanningFile = this.uploadedFiles[index];
	    this.showOcrDetailsDialog = true;

			console.log("OCR showScanResults : ", this.scanningFile);

		}
	}

	public onRecevieResponse = (message: string) => {

    //if(this.webSocket){
    //  this.webSocket.close();
    //}

		var uploaded = this.uploadedFiles[this.scanningFileIndex ];

		this.ocrResultMessage = JSON.parse(message);
    console.log("Parsed OCR Message: " , this.ocrResultMessage);

		if(this.ocrResultMessage.status){
			if(this.ocrResultMessage.status === "done"){
			  this.loadingService.hideLoading();
				this.unsubscribe();

        //if(this.ocrResultMessage.words){

          this.showOcrDetailsDialog = true;

          this.uploadedFiles[this.scanningFileIndex].foundWords = []; //<OcrWord[]>this.ocrResultMessage.words;
          this.uploadedFiles[this.scanningFileIndex].isScanned = true;
          this.uploadedFiles[this.scanningFileIndex].imageSizeX = this.ocrResultMessage.imageWidth;
          this.uploadedFiles[this.scanningFileIndex].imageSizeY = this.ocrResultMessage.imageHeight;

          console.log("OCR Received Words: " , this.uploadedFiles[this.scanningFileIndex].foundWords);
        //}

			}
			if(this.ocrResultMessage.status === "error" && this.ocrResultMessage.errorMessage){
				this.unsubscribe();
				this.errorService.showError(this.ocrResultMessage.errorMessage , this.ocrResultMessage.errorDetail);
			}
		}

	}

	subscribe(uploadedFile: UploadedFile) {

		if (this.subscribed) {
		  this.unsubscribe();
		}

    var url = "ws://" + HttpHelper.serverPort + "/websocket/workflowocr/" + this.generalData.companyId + "/" + this.generalData.currentUserId;

    var msg = uploadedFile.uploadResult;
    msg.token = this.generalData.refreshToken;
    var msgstr = JSON.stringify(msg);
    var msgparsed = JSON.parse(msgstr);
    msgparsed['workflow-type'] = 'invoiceworkflowtype';
    var msgstr = JSON.stringify(msgparsed);

    console.log("ocr socket msg to send ", msgstr);


	  this.webSocket = new WebSocket(url);

    var _this = this;

    this.webSocket.onmessage = function (evt) {
       if(evt && evt.data){
        console.log("OCR Message is received..." + evt.data);
        _this.onRecevieResponse(evt.data);
       }
    };

    this.webSocket.onclose = function() {
       _this.setConnected(false);
       console.log("OCR Connection is closed...");
    };

    this.webSocket.onopen = function() {
       console.log("OCR websocket connected");
       _this.setConnected(true);
       this.send(msgstr);
    };

    this.listening = true;
	}

	unsubscribe() {
		this.listening = false;
    if (!this.subscribed || this.webSocket == null) {
      return;
    }

    this.webSocket.close();

	  this.setConnected(false);
	  console.log("OCR Disconnected");

	}

	private setConnected(subscribed: boolean) {
		this.subscribed = subscribed;

	}

	private setPageTitle(){
		var pageLabelId = "invoice-assignview-title";

    if(this.workflowSaveRequest){
      if(this.workflowSaveRequest.workflow.workflow.currentStepIndex === 1){
        pageLabelId = "invoice-assignview-title";
      }

      if(this.workflowSaveRequest.workflow.workflow.currentStepIndex === 2){
        pageLabelId = "invoice-testingview-title";
      }

      if(this.workflowSaveRequest.workflow.workflow.currentStepIndex === 3){
        pageLabelId = "invoice-releaseview-title";
      }

    }

    this.translate.get(pageLabelId).subscribe((res: string) => {
      this.pageTitle = res;
    });

	}

	setToControlValues(){
		if(this.workflowSaveRequest && this.workflowSaveRequest.workflow){

			this.setPageTitle();

			if( (this.workflowSaveRequest.workflow.discountEnterDate === null || this.workflowSaveRequest.workflow.discountEnterDate === '')
					&& this.workflowSaveRequest.workflow.invocieDate !== null){
				this.workflowSaveRequest.workflow.discountEnterDate = this.workflowSaveRequest.workflow.invocieDate;
			}

			this.workflowEditForm.controls["expireDays"].setValue(this.workflowSaveRequest.expireDays);

			this.workflowEditForm.controls["comments"].setValue(this.workflowSaveRequest.comments);

			this.workflowEditForm.controls["sender"].setValue(this.workflowSaveRequest.workflow.sender);
			this.workflowEditForm.controls["registerNumber"].setValue(this.workflowSaveRequest.workflow.registerNumber);
			this.workflowEditForm.controls["invocieDate"].setValue(parseDate(this.workflowSaveRequest.workflow.invocieDate, 'dd.mm.yyyy'));
			this.workflowEditForm.controls["partnerCode"].setValue(this.workflowSaveRequest.workflow.partnerCode);
			this.workflowEditForm.controls["vendorNumber"].setValue(this.workflowSaveRequest.workflow.vendorNumber);
			this.workflowEditForm.controls["vendorName"].setValue(this.workflowSaveRequest.workflow.vendorName);
			this.workflowEditForm.controls["isDirectDebitPermission"].setValue(this.workflowSaveRequest.workflow.isDirectDebitPermission);
			this.workflowEditForm.controls["invoiceType"].setValue(this.workflowSaveRequest.workflow.invoiceType);
			this.workflowEditForm.controls["discountEnterDate"].setValue(parseDate(this.workflowSaveRequest.workflow.discountEnterDate, 'dd.mm.yyyy'));
			this.workflowEditForm.controls["discountDeadline"].setValue(this.workflowSaveRequest.workflow.discountDeadline);
			this.workflowEditForm.controls["discountRate"].setValue(this.workflowSaveRequest.workflow.discountRate);
			this.workflowEditForm.controls["discountDate"].setValue(this.workflowSaveRequest.workflow.discountDate);
			this.workflowEditForm.controls["paymentAmount"].setValue(this.workflowSaveRequest.workflow.paymentAmount);

			this.uploadedFiles = WorkflowFile.toUploadedFileList(this.workflowSaveRequest.workflow.workflow.files);

		}
	}

	setFormControlValues(){

    if(this.workflowSaveRequest){
      this.workflowSaveRequest.uploadedFiles = WorkflowUploadedFile.loadUploadedFiles(this.uploadedFiles);

      this.workflowSaveRequest.expireDays = this.workflowEditForm.controls["expireDays"].value;

      this.workflowSaveRequest.comments = this.workflowEditForm.controls["comments"].value;

      this.workflowSaveRequest.workflow.sender = this.workflowEditForm.controls["sender"].value;
      this.workflowSaveRequest.workflow.registerNumber = this.workflowEditForm.controls["registerNumber"].value;
      this.workflowSaveRequest.workflow.invocieDate = formatDate(this.workflowEditForm.controls["invocieDate"].value, 'dd.mm.yyyy');
      this.workflowSaveRequest.workflow.partnerCode = this.workflowEditForm.controls["partnerCode"].value;
      this.workflowSaveRequest.workflow.vendorNumber = this.workflowEditForm.controls["vendorNumber"].value;
      this.workflowSaveRequest.workflow.vendorName = this.workflowEditForm.controls["vendorName"].value;
      this.workflowSaveRequest.workflow.isDirectDebitPermission = this.workflowEditForm.controls["isDirectDebitPermission"].value;
      this.workflowSaveRequest.workflow.invoiceType = this.workflowEditForm.controls["invoiceType"].value;
      this.workflowSaveRequest.workflow.discountEnterDate = formatDate(this.workflowEditForm.controls["discountEnterDate"].value, 'dd.mm.yyyy');
      this.workflowSaveRequest.workflow.discountDeadline = this.workflowEditForm.controls["discountDeadline"].value;
      this.workflowSaveRequest.workflow.discountRate = this.workflowEditForm.controls["discountRate"].value;
      this.workflowSaveRequest.workflow.discountDate = this.workflowEditForm.controls["discountDate"].value;
      this.workflowSaveRequest.workflow.paymentAmount = this.workflowEditForm.controls["paymentAmount"].value;

    }
	}


	get forms() { return this.workflowEditForm.controls; }

	onUsersSelected(assigns: AssignItem[]) {

	  if(this.workflowSaveRequest){

      this.workflowSaveRequest.assigns = [];

      for(var item in assigns){
        var assign = new AssignItem;
        assign.itemId = assigns[item].itemId;
        assign.itemType = assigns[item].itemType;

        this.workflowSaveRequest.assigns.push(assign);
      }
	  }


	}


	hideOcrDetails(){
		this.showOcrDetailsDialog = false;
	}

	onApplyScannedValues() {

		if(this.scannedSelectedValues["invoice-invoicedate"] && this.scannedSelectedValues["invoice-invoicedate"] != ""){

			this.workflowEditForm.controls["invocieDate"].setValue(parseDate(this.scannedSelectedValues["invoice-invoicedate"], 'dd.mm.yyyy'));
			this.workflowEditForm.controls["discountEnterDate"].setValue(parseDate(this.scannedSelectedValues["invoice-invoicedate"], 'dd.mm.yyyy'));
			//this.workflowEditForm.controls["discountDate"].setValue(parseDate(this.scannedSelectedValues["invoice-invoicedate"], 'dd.mm.yyyy'));

		}

		if(this.scannedSelectedValues["invoice-invoicenumber"] && this.scannedSelectedValues["invoice-invoicenumber"] != ""){
			this.workflowEditForm.controls["registerNumber"].setValue(this.scannedSelectedValues["invoice-invoicenumber"]);
		}

		if(this.scannedSelectedValues["invoice-paymentamount"] && this.scannedSelectedValues["invoice-paymentamount"] != ""){
			var foundPayment = this.scannedSelectedValues["invoice-paymentamount"].replace(/\./g, "").replace(",", ".");

			if(isNaN(Number(foundPayment)) === false){
				var foundPaymentFloat = parseFloat(foundPayment);
				this.workflowEditForm.controls["paymentAmount"].setValue(foundPaymentFloat);
			}

		}

		if(this.scannedSelectedValues["invoice-sender"] && this.scannedSelectedValues["invoice-sender"] != ""){
			this.workflowEditForm.controls["sender"].setValue(this.scannedSelectedValues["invoice-sender"]);
		}

		this.showOcrDetailsDialog = false;
	}
}
