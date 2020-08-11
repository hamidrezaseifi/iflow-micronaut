import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable } from 'rxjs';

import { GlobalService } from '../../services/global.service';
import { WorkflowtypePropertySettingService } from '../../services/company/workflowtype-property-setting.service';
import { LoadingServiceService } from '../../services/loading-service.service';
import { ErrorServiceService } from '../../services/error-service.service';

import { CompanyWorkflowtypeItemOcrSettingPreset, CompanyWorkflowtypeItemOcrSettingPresetItem, GeneralData } from '../../ui-models';
import { WorkflowType } from '../../wf-models/workflowtype';

@Component({
  selector: 'app-workflowtype-property-setting',
  templateUrl: './workflowtype-property-setting.component.html',
  styleUrls: ['./workflowtype-property-setting.component.css']
})
export class WorkflowtypePropertySettingComponent implements OnInit {

	ocrSettingPresets : CompanyWorkflowtypeItemOcrSettingPreset[] = [];
	
	worlflowTypes: WorkflowType[] = [];
	worlflowTypeItems: string[][] = [];

  selectedPreset :CompanyWorkflowtypeItemOcrSettingPreset = null;
  selectedPresetItems :CompanyWorkflowtypeItemOcrSettingPresetItem[] = [];
	
	selectedPresetChanged :boolean = false;

	generalDataObs :Observable<GeneralData> = null;
	
	showList: boolean = true;

	showDetail: boolean = false;
	
	showTextListDialog :boolean = false;
	
	textSeparator :string = ";";
	selectedTextToMakeList :string = "";
	selectedPropertyName :string= "";

	constructor(
		    private router: Router,
			private global: GlobalService,
			translate: TranslateService,
			private editService :WorkflowtypePropertySettingService,
			private loadingService: LoadingServiceService,
			private errorService: ErrorServiceService,
			private route :ActivatedRoute,
			private formBuilder: FormBuilder,
			private dateAdapter: DateAdapter<Date>,
			
	) {
		this.dateAdapter.setLocale('de');
		
       /* translate.get('user-delete-message').subscribe((res: string) => {
        	this.deleteMessageBase = res;
        });*/

        
		this.generalDataObs = this.global.currentSessionDataSubject.asObservable();
		this.generalDataObs.subscribe(data => {
			   
			this.worlflowTypes = data.workflow.worlflowTypes;
			
			for(var id in this.worlflowTypes){
				var type:WorkflowType = this.worlflowTypes[id];
				//this.workflowtypeItemNames[type.identity] = null;
				
				if(this.ocrSettingPresets[type.identity] === undefined){
					this.ocrSettingPresets[type.identity] = [];
				}
			}
		});
		
	}

	ngOnInit() {
		
		
		this.global.loadAllSetting();
		
		this.reload();
	}

	debug():string{
		
		return JSON.stringify(this.ocrSettingPresets);
	}
	
	reload() {
		this.loadingService.showLoading();
		
		this.editService.listPresets().subscribe(
	        (results :CompanyWorkflowtypeItemOcrSettingPreset[]) => {
	        	
	            console.log("CompanyWorkflowtypeItemOcrSetting list", results);
	        	
	            this.ocrSettingPresets = results;
	        },
	        response => {
	        	console.log("Error in get CompanyWorkflowtypeItemOcrSetting list", response);
	        	this.loadingService.hideLoading();	 
	        	this.errorService.showErrorResponse(response);
	        },
	        () => {
	        	
	        	this.loadingService.hideLoading();	            
	        }
		);	       	
	}
	
	addPreset(){
	  this.selectedPreset = new CompanyWorkflowtypeItemOcrSettingPreset();
	  	  
		this.selectedPresetItems = this.selectedPreset.items;
		
		this.showList = false;
		this.showDetail = true;
		this.selectedPresetChanged = false;
	  
	}
	
	showPreset(preset :CompanyWorkflowtypeItemOcrSettingPreset){
	  
	  this.selectedPreset = JSON.parse(JSON.stringify(preset))

		this.selectedPresetItems = preset.items;
		
		this.showList = false;
		this.showDetail = true;
		this.selectedPresetChanged = false;
	}
	
	closePreset(){

		this.showList = true;
		this.showDetail = false;
		this.selectedPresetChanged = false;
		this.reload();
	}
	
	setPresetName(newVal:string){
	  this.selectedPreset.presetName = newVal;
	}
	
	setPresetWorkflowIdentity(newVal:string){
	  this.selectedPreset.workflowTypeIdentity = newVal;
	  
	  this.verifyWorlflowTypeItems(newVal);
	}
	
	verifyWorlflowTypeItems(workflowTypeIdentity: string){
		if(this.worlflowTypeItems[workflowTypeIdentity] === undefined){
	    
	    this.loadingService.showLoading();
			
			this.editService.listWorkflowTypeItems(workflowTypeIdentity).subscribe(
		        (results :string[]) => {
		        	
		            console.log("Workflowtype Items for " + workflowTypeIdentity, results);
		        	
		            this.worlflowTypeItems[workflowTypeIdentity] = results;
		                
		            this.selectedPresetItems = this.resetPresetItems(this.selectedPreset);
		        },
		        response => {
		        	console.log("Error in get Workflowtype Items for " + workflowTypeIdentity, response);
		        	this.loadingService.hideLoading();	 
		        	this.errorService.showErrorResponse(response);
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	            
		        }
			);	   
			
	  }
	}
	
	resetPresetItems(preset :CompanyWorkflowtypeItemOcrSettingPreset): CompanyWorkflowtypeItemOcrSettingPresetItem[]{
	  //this.selectedPreset = new CompanyWorkflowtypeItemOcrSettingPreset();
	  
	  var items: CompanyWorkflowtypeItemOcrSettingPresetItem[] = [];
	  
	  for(var index in this.worlflowTypeItems[preset.workflowTypeIdentity]){
	  	var itemName = this.worlflowTypeItems[preset.workflowTypeIdentity][index];  
	  	var item :CompanyWorkflowtypeItemOcrSettingPresetItem = this.findItemByName(preset, itemName);
	  	if(item === null){
	  	  item = new CompanyWorkflowtypeItemOcrSettingPresetItem();
	  	  item.propertyName = itemName;
	  	  item.value = "";
	  	}
	  	items.push(item);
	  }
	  
	  return items;
	  
		//this.selectedPresetItems = this.selectedPreset.items;
	}
	
	private findItemByName(preset :CompanyWorkflowtypeItemOcrSettingPreset, propertyName: string)
		:CompanyWorkflowtypeItemOcrSettingPresetItem{
	  for(var index in preset.items){
	    if(this.selectedPreset.items[index].propertyName === propertyName){
	      return this.selectedPreset.items[index];
	    }
	  }
	  return null;
	}
	
	setPresetItemValue(name:string, newVal:string){
		
	  this.selectedPresetItems[name].value = newVal;

		this.selectedPresetChanged = true;
	}
	
	savePreset(){
		
		
		this.loadingService.showLoading();
		
		this.editService.updatePreset(this.selectedPreset).subscribe(
	        (results :CompanyWorkflowtypeItemOcrSettingPreset[]) => {
	        	
	            console.log("Update CompanyWorkflowtypeItemOcrSetting result list", results);
	        	
	            this.ocrSettingPresets = results;
	            
	            this.showList = true;
	    		this.showDetail = false;
	    		this.selectedPresetChanged = false;
	    		
	    		this.reload();
	    		
	        },
	        response => {
	        	console.log("Error in Update CompanyWorkflowtypeItemOcrSetting list", response);
	        	this.loadingService.hideLoading();	 
	        	this.errorService.showErrorResponse(response);
	        },
	        () => {
	        	
	        	this.loadingService.hideLoading();	            
	        }
		);	   
		
		
		//this.reload();
	}
	
	selectValueList(prop:CompanyWorkflowtypeItemOcrSettingPresetItem){
		this.selectedTextToMakeList = prop.value;
		this.selectedPropertyName = prop.propertyName;
		
		this.showTextListDialog = true;
	}
	
	selectedTextChanged(text:string){

		this.selectedTextToMakeList = text;
	}
	
	applySelectexTextChanged(){

	  this.selectedPresetItems[this.selectedPropertyName].value = this.selectedTextToMakeList;
	  this.selectedPresetChanged = true;
		this.showTextListDialog = false;
	}
	
	hideTextListDialog(){
		this.showTextListDialog = false;
	}
	
}
