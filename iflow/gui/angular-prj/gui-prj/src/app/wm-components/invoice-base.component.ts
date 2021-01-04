import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { HttpClient, HttpEventType } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable, throwError , Subscription } from 'rxjs';
import { Message } from '@stomp/stompjs';
import { GlobalSocket } from '../services/global-socket';

import { GlobalService } from '../services/global.service';
import { InvoiceWorkflowEditService } from '../services/workflow/invoice/invoice-workflow-edit.service';
import { LoadingServiceService } from '../services/loading-service.service';
import { ErrorServiceService } from '../services/error-service.service';

import { User, Department, GeneralData, OcrWord, UploadedFile, UploadedResult, CompanyWorkflowtypeItemOcrSettingPreset } from '../ui-models';
import { WorkflowProcessCommand, Workflow, AssignItem, FileTitle, AssignType, WorkflowUploadFileResult,
	InvoiceType, WorkflowUploadedFile, WorkflowFile } from '../wf-models';
import { InvoiceWorkflowSaveRequest } from '../wf-models/invoice/invoice-workflow-save-request';
import { InvoiceWorkflowSaveRequestInit } from '../wf-models/invoice/invoice-workflow-save-request-init';
import { InvoiceTypeControllValidator } from '../custom-validators/invoice-type-controll-validator';
import { GermanDateAdapter, parseDate, formatDate } from '../helper';
import { HttpHepler } from '../helper/http-hepler';

export class InvoiceBaseComponent implements OnInit, OnDestroy {

	pageTitle :string = "not-initialized!";

	paymentamountOtherTypesTitle :string = "";
	paymentamountTypePaymentTitle :string = "";

	private subscription: Subscription;
	private messages: Observable<Message>;
	public subscribed: boolean;

	uploadedFiles :UploadedFile[] = [];

	listening :boolean = false;

	scanningFileIndex :number = -1;
	scanningFile :UploadedFile = null;
  scanedWordes : OcrWord[] = [];

	showOcrDetailsDialog :boolean = false;

	scannedSelectedValues :string[] = [];

	ocrResultMessage : any = null;

  webSocket: WebSocket = null;

	invoiceEditForm: FormGroup;

	workflowListUrl :string = "/workflow/list";

	workflowSaveRequest :InvoiceWorkflowSaveRequest = new InvoiceWorkflowSaveRequest();

  ocrSettingPresets : CompanyWorkflowtypeItemOcrSettingPreset[] = [];
  selectedOcrSettingPreset : CompanyWorkflowtypeItemOcrSettingPreset = null;

	//generalDataObs :Observable<GeneralData> = null;

  generalData : GeneralData = new GeneralData;

	selectAssign : boolean[][] = [];

	invoiceTypes : any[] = [];

	calcDiscountDate(){
		//alert("calcDiscountDate ---");

		var enterDate :Date = this.invoiceEditForm.controls["discountEnterDate"].value;
		var deadline : number= this.invoiceEditForm.controls["discountDeadline"].value;

		if(enterDate != null && deadline != null && deadline > 0){
			var date = new Date(enterDate.getFullYear(), enterDate.getMonth(), enterDate.getDate() + deadline);
			this.invoiceEditForm.controls["discountDate"].setValue( formatDate(date, 'dd.mm.yyyy') );
		}

	}

	selectedOcrPresetChanged(preset : CompanyWorkflowtypeItemOcrSettingPreset){
	  this.selectedOcrSettingPreset = preset;
	  //alert(this.selectedOcrSettingPreset.presetName);

	  //this.scanedWordes
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
		var enterDate :Date = this.invoiceEditForm.controls["discountEnterDate"].value;

		if(enterDate === null && this.invoiceEditForm.controls["invocieDate"].value !== null){
			this.invoiceEditForm.controls["discountEnterDate"].setValue( this.invoiceEditForm.controls["invocieDate"].value );
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
		return this.invoiceEditForm.controls["invoiceType"].value === "PAYMENT";
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
      protected dateAdapter: DateAdapter<Date>,
      protected globalSocket: GlobalSocket,
	) {

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

		//this.generalDataObs = this.global.currentSessionDataSubject.asObservable();
    this.global.currentSessionDataSubject.asObservable().subscribe((res: GeneralData) => {
        if(res == null || res == undefined){
          return;
        }
        this.generalData = res;
        console.log("invoice-generalData" , this.generalData);
     });

	}

	ngOnInit() {

		this.invoiceEditForm = this.formBuilder.group({
			expireDays: [10, Validators.required],

			comments: [''],

			sender: ['', Validators.required],
			registerNumber: ['', Validators.required],
			invocieDate: [new Date(), Validators.required],
			partnerCode: ['', Validators.required],
			vendorNumber: ['', Validators.required],
			vendorName: ['', Validators.required],
			isDirectDebitPermission: [false],
			invoiceType: [InvoiceType.NO_TYPE, [InvoiceTypeControllValidator]],

			discountEnterDate: [new Date(), Validators.required],
			discountDeadline: [0, Validators.required],
			discountRate: [0, Validators.required],
			discountDate: ["", Validators.required],

			paymentAmount: [0, Validators.required],

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

	getOcrWords() :OcrWord[]{
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

	onUploadedFilesChanged(uploadedFileList: UploadedFile[]) {

		this.uploadedFiles = uploadedFileList;
	}


	public onRecevieResponse = (message: string) => {

    //if(this.webSocket){
    //  this.webSocket.close();
    //}

		var uploaded = this.uploadedFiles[this.scanningFileIndex ];

		this.loadingService.hideLoading();
		this.ocrResultMessage = JSON.parse(message);
    console.log("Parsed OCR Message: " , this.ocrResultMessage);

		if(this.ocrResultMessage.status){
			if(this.ocrResultMessage.status === "done"){
				this.unsubscribe();

        if(this.ocrResultMessage.words){

          this.showOcrDetailsDialog = true;

          this.uploadedFiles[this.scanningFileIndex].foundWords = <OcrWord[]>this.ocrResultMessage.words;
          this.uploadedFiles[this.scanningFileIndex].isScanned = true;
          this.uploadedFiles[this.scanningFileIndex].imageSizeX = this.ocrResultMessage.imageWidth;
          this.uploadedFiles[this.scanningFileIndex].imageSizeY = this.ocrResultMessage.imageHeight;

          console.log("OCR Received Words: " , this.uploadedFiles[this.scanningFileIndex].foundWords);
        }

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

    var url = "ws://" + HttpHepler.serverPort + "/websocket/workflowocr/" + this.generalData.currentUserId;

    var msg = uploadedFile.uploadResult;
    msg.token = this.generalData.refreshToken;
    var msgstr = JSON.stringify(msg);

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
       _this.webSocket = null;
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
    this.webSocket = null;

	  this.setConnected(false);
	  console.log("OCR Disconnected");

	}

	private setConnected(subscribed) {
		this.subscribed = subscribed;

	}

	private setPageTitle(){
		var pageLabelId = "invoice-assignview-title";

		if(this.workflowSaveRequest.workflow.workflow.currentStepIndex === 1){
			pageLabelId = "invoice-assignview-title";
		}

		if(this.workflowSaveRequest.workflow.workflow.currentStepIndex === 2){
			pageLabelId = "invoice-testingview-title";
		}

		if(this.workflowSaveRequest.workflow.workflow.currentStepIndex === 3){
			pageLabelId = "invoice-releaseview-title";
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

			this.invoiceEditForm.controls["expireDays"].setValue(this.workflowSaveRequest.expireDays);

			this.invoiceEditForm.controls["comments"].setValue(this.workflowSaveRequest.comments);

			this.invoiceEditForm.controls["sender"].setValue(this.workflowSaveRequest.workflow.sender);
			this.invoiceEditForm.controls["registerNumber"].setValue(this.workflowSaveRequest.workflow.registerNumber);
			this.invoiceEditForm.controls["invocieDate"].setValue(parseDate(this.workflowSaveRequest.workflow.invocieDate, 'dd.mm.yyyy'));
			this.invoiceEditForm.controls["partnerCode"].setValue(this.workflowSaveRequest.workflow.partnerCode);
			this.invoiceEditForm.controls["vendorNumber"].setValue(this.workflowSaveRequest.workflow.vendorNumber);
			this.invoiceEditForm.controls["vendorName"].setValue(this.workflowSaveRequest.workflow.vendorName);
			this.invoiceEditForm.controls["isDirectDebitPermission"].setValue(this.workflowSaveRequest.workflow.isDirectDebitPermission);
			this.invoiceEditForm.controls["invoiceType"].setValue(this.workflowSaveRequest.workflow.invoiceType);
			this.invoiceEditForm.controls["discountEnterDate"].setValue(parseDate(this.workflowSaveRequest.workflow.discountEnterDate, 'dd.mm.yyyy'));
			this.invoiceEditForm.controls["discountDeadline"].setValue(this.workflowSaveRequest.workflow.discountDeadline);
			this.invoiceEditForm.controls["discountRate"].setValue(this.workflowSaveRequest.workflow.discountRate);
			this.invoiceEditForm.controls["discountDate"].setValue(this.workflowSaveRequest.workflow.discountDate);
			this.invoiceEditForm.controls["paymentAmount"].setValue(this.workflowSaveRequest.workflow.paymentAmount);

			this.uploadedFiles = WorkflowFile.toUploadedFileList(this.workflowSaveRequest.workflow.workflow.files);

		}
	}

	setFormControlValues(){

		this.workflowSaveRequest.uploadedFiles = WorkflowUploadedFile.loadUploadedFiles(this.uploadedFiles);

		this.workflowSaveRequest.expireDays = this.invoiceEditForm.controls["expireDays"].value;

		this.workflowSaveRequest.comments = this.invoiceEditForm.controls["comments"].value;

		this.workflowSaveRequest.workflow.sender = this.invoiceEditForm.controls["sender"].value;
		this.workflowSaveRequest.workflow.registerNumber = this.invoiceEditForm.controls["registerNumber"].value;
		this.workflowSaveRequest.workflow.invocieDate = formatDate(this.invoiceEditForm.controls["invocieDate"].value, 'dd.mm.yyyy');
		this.workflowSaveRequest.workflow.partnerCode = this.invoiceEditForm.controls["partnerCode"].value;
		this.workflowSaveRequest.workflow.vendorNumber = this.invoiceEditForm.controls["vendorNumber"].value;
		this.workflowSaveRequest.workflow.vendorName = this.invoiceEditForm.controls["vendorName"].value;
		this.workflowSaveRequest.workflow.isDirectDebitPermission = this.invoiceEditForm.controls["isDirectDebitPermission"].value;
		this.workflowSaveRequest.workflow.invoiceType = this.invoiceEditForm.controls["invoiceType"].value;
		this.workflowSaveRequest.workflow.discountEnterDate = formatDate(this.invoiceEditForm.controls["discountEnterDate"].value, 'dd.mm.yyyy');
		this.workflowSaveRequest.workflow.discountDeadline = this.invoiceEditForm.controls["discountDeadline"].value;
		this.workflowSaveRequest.workflow.discountRate = this.invoiceEditForm.controls["discountRate"].value;
		this.workflowSaveRequest.workflow.discountDate = this.invoiceEditForm.controls["discountDate"].value;
		this.workflowSaveRequest.workflow.paymentAmount = this.invoiceEditForm.controls["paymentAmount"].value;
	}


	get forms() { return this.invoiceEditForm.controls; }

	onUsersSelected(assigns: AssignItem[]) {
		this.workflowSaveRequest.assigns = [];

		for(var item in assigns){
			var assign = new AssignItem;
			assign.itemId = assigns[item].itemId;
			assign.itemType = assigns[item].itemType;

			this.workflowSaveRequest.assigns.push(assign);
		}

	}


	hideOcrDetails(){
		this.showOcrDetailsDialog = false;
	}

	onApplyScannedValues() {

		if(this.scannedSelectedValues["invoice-invoicedate"] && this.scannedSelectedValues["invoice-invoicedate"] != ""){

			this.invoiceEditForm.controls["invocieDate"].setValue(parseDate(this.scannedSelectedValues["invoice-invoicedate"], 'dd.mm.yyyy'));
			this.invoiceEditForm.controls["discountEnterDate"].setValue(parseDate(this.scannedSelectedValues["invoice-invoicedate"], 'dd.mm.yyyy'));
			//this.invoiceEditForm.controls["discountDate"].setValue(parseDate(this.scannedSelectedValues["invoice-invoicedate"], 'dd.mm.yyyy'));

		}

		if(this.scannedSelectedValues["invoice-invoicenumber"] && this.scannedSelectedValues["invoice-invoicenumber"] != ""){
			this.invoiceEditForm.controls["registerNumber"].setValue(this.scannedSelectedValues["invoice-invoicenumber"]);
		}

		if(this.scannedSelectedValues["invoice-paymentamount"] && this.scannedSelectedValues["invoice-paymentamount"] != ""){
			var foundPayment = this.scannedSelectedValues["invoice-paymentamount"].replace(/\./g, "").replace(",", ".");

			if(isNaN(foundPayment) === false){
				var foundPaymentFloat = parseFloat(foundPayment);
				this.invoiceEditForm.controls["paymentAmount"].setValue(foundPaymentFloat);
			}

		}

		if(this.scannedSelectedValues["invoice-sender"] && this.scannedSelectedValues["invoice-sender"] != ""){
			this.invoiceEditForm.controls["sender"].setValue(this.scannedSelectedValues["invoice-sender"]);
		}

		this.showOcrDetailsDialog = false;
	}
}
