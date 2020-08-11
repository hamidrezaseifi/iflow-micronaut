import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable } from 'rxjs';
import $ from "jquery";

import { GlobalService } from '../../services/global.service';
import { DepartmentEditService } from '../../services/company/department-edit.service';
import { LoadingServiceService } from '../../services/loading-service.service';
import { ErrorServiceService } from '../../services/error-service.service';
import { GermanDateAdapter, parseDate, formatDate } from '../../helper';

import { User, UserAccessType, MenuItem, GeneralData,  UserDepartment, Department } from '../../ui-models';

@Component({
  selector: 'app-departments-list',
  templateUrl: './departments-list.component.html',
  styleUrls: ['./departments-list.component.css']
})
export class DepartmentsListComponent implements OnInit {
	departments :Department[] = [];

	displayedColumns = ['department-title', 'department-manager', 'department-status', 'actions'];
	
	isCreating :boolean = false;
	showEditModal :boolean = false;
	editingDepartment :Department = new Department;

	departmentEditForm: FormGroup;

	deleteMessageBase :string = "";
	deleteMessage :string = "";
	delitingDepartment :Department = new Department;
	showDeleteModal :boolean = false;

	viewingDepartment :Department = new Department;
	showViewModal :boolean = false;
	
	activeTab :string = "info";

	constructor(
		    private router: Router,
			private global: GlobalService,
			translate: TranslateService,
			private editService :DepartmentEditService,
			private loadingService: LoadingServiceService,
			private errorService: ErrorServiceService,
			private route :ActivatedRoute,
			private formBuilder: FormBuilder,
			private dateAdapter: DateAdapter<Date>,
			
	) {
		this.dateAdapter.setLocale('de');
		
        translate.get('department-delete-message').subscribe((res: string) => {
        	this.deleteMessageBase = res;
        });
		
		
	}

	ngOnInit() {
		this.departmentEditForm = this.formBuilder.group({
			
			title: ['', Validators.required],
		    status: ['1', Validators.required],
			
        });
		
		
		
		this.reload();
	}

	debug(){
		var s = "";
		for(var cnt in this.departmentEditForm.controls){
			var ctl = this.departmentEditForm.controls[cnt];
			//JSON.stringify(ctl.ValidationErrors)
			s += cnt + ": " + ctl.value +  " \n ";
		}
		return s;
	}
	
	
	reload() {
		this.loadingService.showLoading();
		
		this.editService.listDepartments().subscribe(
	        (results :Department[]) => {
	        	
	            console.log("Department list", results);
	        	
	            this.departments = results;
	        },
	        response => {
	        	console.log("Error in get department list", response);
	        	this.loadingService.hideLoading();	 
	        	this.errorService.showErrorResponse(response);
	        },
	        () => {
	        	
	        	this.loadingService.hideLoading();	            
	        }
		);	       	
	}

	showCreateDepartment() {

		this.isCreating = true;
		this.editingDepartment = new Department;

		this.setToControlValues();
		
		this.activeTab = "info";
		
		this.showEditModal = true;
		
	}

	showEditDepartment(department: Department) {

		this.isCreating = false;
		this.editingDepartment = department;
		
		this.setToControlValues();
		
		
		this.activeTab = "info";
		this.showEditModal = true;
	}

	showViewDepartment(department: Department) {
		this.viewingDepartment = department;
							
		this.activeTab = "info";
		this.showViewModal = true;
	}
	
	hideViewDepartmentDialog(){
		this.showViewModal = false;
	}
	
	showDeleteDepartment(department: Department) {
		
		this.delitingDepartment = department;
		this.deleteMessage = this.deleteMessageBase;
		this.deleteMessage = this.deleteMessage.replace("%" , department.title);

		this.showDeleteModal = true;
	}
	
	hideDeleteDepartmentDialog(){
		this.showDeleteModal = false;
	}
	
	hideEditDepartmentDialog(){
		this.showEditModal = false;
	}
	

	setToControlValues(){
		if(this.editingDepartment){
			this.departmentEditForm.controls["title"].setValue(this.editingDepartment.title);
			this.departmentEditForm.controls["status"].setValue(this.editingDepartment.status + '');			

		}
	}
	
	setFormControlValues(){
				
		this.editingDepartment.title = this.departmentEditForm.controls["title"].value;
		this.editingDepartment.status = this.departmentEditForm.controls["status"].value;			

	}
	
	departmentHasManager(department: Department):boolean {
		
		return (department.manager != null) && (department.manager.fullName != null);
	}
	
	departmentHasDeputy(department: Department):boolean {
		
		return (department.deputy != null) && (department.deputy.fullName != null);
	}
	
	saveDepartment(){
		this.setFormControlValues();
		
		this.loadingService.showLoading();
		
		if(this.isCreating){
			this.editService.createDepartment(this.editingDepartment).subscribe(
			        (result) => {		        	
			            console.log("Create department result", result);
			            this.showEditModal = false;
			            this.reload();
			            
			        },
			        response => {
			        	console.log("Error in create department", response);
			        	
			        	this.errorService.showErrorResponse(response);
			        	this.loadingService.hideLoading();	 
			        },
			        () => {
			        	
			        	this.loadingService.hideLoading();	 
			        }
			);	     
		}
		else{
			this.editService.updateDepartment(this.editingDepartment).subscribe(
			        (result) => {		        	
			            console.log("Update department result", result);
			            this.showEditModal = false;
			            this.reload();
			            
			        },
			        response => {
			        	console.log("Error in update department", response);
			        	
			        	this.errorService.showErrorResponse(response);
			        	this.loadingService.hideLoading();	 
			        },
			        () => {
			        	
			        	this.loadingService.hideLoading();	 
			        }
			);	     
			
		}
	}
	
	deleteDepartment(){
		this.loadingService.showLoading();

		this.editService.deleteDepartment(this.delitingDepartment).subscribe(
		        (result) => {		        	
		            console.log("Delete department result success.");
		            this.showDeleteModal = false;
		            this.reload();
		            
		        },
		        response => {
		        	console.log("Error in create department", response);
		        	
		        	this.errorService.showErrorResponse(response);
		        	this.loadingService.hideLoading();	 
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	 
		        }
		);	   
	}
}
