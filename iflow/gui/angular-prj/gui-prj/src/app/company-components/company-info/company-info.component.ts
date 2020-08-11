import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable } from 'rxjs';
import $ from "jquery";

import { GlobalService } from '../../services/global.service';
import { CompanyEditService } from '../../services/company/company-edit.service';
import { LoadingServiceService } from '../../services/loading-service.service';
import { ErrorServiceService } from '../../services/error-service.service';
import { GermanDateAdapter, parseDate, formatDate } from '../../helper';
import { UserAccessTypeControllValidator } from '../../custom-validators/user-access-type-controll-validator';

import { User, Company, GeneralData } from '../../ui-models';

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']
})
export class CompanyInfoComponent implements OnInit {

	companyInfo: Company = new Company;
	companyEditForm: FormGroup;
	companyTypeList : string[] = ["CUSTOME", "EINZELUNTERNEHMEN", "GBR", "OHG", "KG", "GMBH", "UG"];
	companyTypeTitle : {} = {};
	
	isEditing :boolean= false;

	constructor(
		    private router: Router,
			private global: GlobalService,
			translate: TranslateService,
			private editService :CompanyEditService,
			private loadingService: LoadingServiceService,
			private errorService: ErrorServiceService,
			private route :ActivatedRoute,
			private formBuilder: FormBuilder,
			private dateAdapter: DateAdapter<Date>,
			
	) {
		this.dateAdapter.setLocale('de');
		
        //translate.get('user-delete-message').subscribe((res: string) => {
        //	this.deleteMessageBase = res;
        //});
		
		for(var index in this.companyTypeList){
			translate.get('company-type-' + this.companyTypeList[index]).subscribe((res: string) => {
	        	this.companyTypeTitle[this.companyTypeList[index]] = res;
	        });
		}
		
		
	}
	
	ngOnInit() {
		
		this.companyEditForm = this.formBuilder.group({
			
			companyName: ['', Validators.email],
			companyType: ['', Validators.required],
			companyTypeCustome: [''],
		    status: ['1', Validators.required],
        });

		this.reload();
	}
	
	debug(): string {
		var str = "";
		for(var name in this.companyEditForm.controls){
			str += name + " : " + this.companyEditForm.controls[name].value + " , ";
		}
		
		str += '-----------------------------------------------------,  ' + JSON.stringify(this.companyInfo);
		
		return str;
	}
	
	reload() {
		
		this.loadingService.showLoading();
		
		this.editService.listData().subscribe(
		        (result :Company) => {
		        	
		            console.log("Company Info", result);
		        	
		            this.companyInfo = result;
		            
		            this.isEditing = false;
		        },
		        response => {
		        	console.log("Error in get company Info", response);
		        	this.loadingService.hideLoading();	 
		        	this.errorService.showErrorResponse(response);
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	            
		        }
			);	       	
	}
	
	startEditing(){
		this.setToControlValues();
		this.isEditing = true;
	}
	
	save(){
		this.setFormControlValues();
		
		this.loadingService.showLoading();
		
		this.editService.updateData(this.companyInfo).subscribe(
		        (result :Company) => {
		        	
		            console.log("Save Company", result);
		        	
		            this.companyInfo = result;
		            
		            this.isEditing = false;
		        },
		        response => {
		        	console.log("Error in saving company", response);
		        	this.loadingService.hideLoading();	 
		        	this.errorService.showErrorResponse(response);
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	            
		        }
			);	 
		
	}
	

	setToControlValues(){
		if(this.companyInfo){
			this.companyEditForm.controls["companyName"].setValue(this.companyInfo.companyName);
			this.companyEditForm.controls["companyType"].setValue(this.companyInfo.companyType);
			this.companyEditForm.controls["companyTypeCustome"].setValue(this.companyInfo.companyTypeCustome);

		}
	}
	
	setFormControlValues(){
				
		this.companyInfo.companyName = this.companyEditForm.controls["companyName"].value;
		this.companyInfo.companyType = this.companyEditForm.controls["companyType"].value;
		this.companyInfo.companyTypeCustome = this.companyEditForm.controls["companyTypeCustome"].value;

	}
	
	getTitleForCompanyType(type: string):string{
		//console.log("company-type-title", type + " : " + this.companyTypeTitle[type]);
		return this.companyTypeTitle[type];
	}
	
}
