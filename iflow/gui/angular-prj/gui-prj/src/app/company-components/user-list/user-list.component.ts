import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { DateAdapter } from '@angular/material/core';
import { Observable } from 'rxjs';
import $ from "jquery";

import { GlobalService } from '../../services/global.service';
import { UserEditService } from '../../services/company/user-edit.service';
import { LoadingServiceService } from '../../services/loading-service.service';
import { ErrorServiceService } from '../../services/error-service.service';
import { GermanDateAdapter, parseDate, formatDate } from '../../helper';
import { UserAccessTypeControllValidator } from '../../custom-validators/user-access-type-controll-validator';

import { User, UserAccessType, MenuItem, GeneralData,  UserDepartment, Department } from '../../ui-models';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css'],
  providers: [{provide: DateAdapter, useClass: GermanDateAdapter}]
})
export class UserListComponent implements OnInit {

	users :User[] = [];

	displayedColumns = ['user-fullname', 'user-email', 'user-access', 'user-status', 'actions'];

	isCreating :boolean = false;
	showEditModal :boolean = false;
	editingUser :User = new User;
	editingUserDepartments: UserDepartment[] = [];

	userEditForm: FormGroup;
	
	deleteMessageBase :string = "";
	deleteMessage :string = "";
	delitingUser :User = new User;
	showDeleteModal :boolean = false;

	viewingUser :User = new User;
	showViewModal :boolean = false;
	viewingDepartmentMember : Object[] = [];

	resetPasswordMessageBase :string = "";
	resetPasswordMessage :string = "";
	resetPasswordResultMessage :string = "";
	resetPasswordResultMessageBase :string = "";
	passwordResetingUser :User = new User;
	showResetPasswordModal :boolean = false;


	generalDataObs :Observable<GeneralData> = null;
	departments :Department[] = [];
	
	activeTab :string = "info";
	
	userDepartmentAccessType :{} = { 0 : "user-department-access-no", 5 : "user-department-access-member", 
			15 : "user-department-access-deputy", 20 : "user-department-access-manager"};
	
	constructor(
		    private router: Router,
			private global: GlobalService,
			translate: TranslateService,
			private editService :UserEditService,
			private loadingService: LoadingServiceService,
			private errorService: ErrorServiceService,
			private route :ActivatedRoute,
			private formBuilder: FormBuilder,
			private dateAdapter: DateAdapter<Date>,
			
	) {
		this.dateAdapter.setLocale('de');
		
        translate.get('user-delete-message').subscribe((res: string) => {
        	this.deleteMessageBase = res;
        });

        translate.get('user-resetpassword-message').subscribe((res: string) => {
        	this.resetPasswordMessageBase = res;
        });

        translate.get('user-resetpassword-result-message').subscribe((res: string) => {
        	this.resetPasswordResultMessageBase = res;
        });

        this.generalDataObs = this.global.currentSessionDataSubject.asObservable();
		this.generalDataObs.subscribe(data => {
			   
			this.departments = data.company.departments;
		});

		for(var index in this.userDepartmentAccessType){
			translate.get(this.userDepartmentAccessType[index]).subscribe((res: string) => {
				this.userDepartmentAccessType[index] = res;
	        });
		}
		
		
	}

	ngOnInit() {
		this.userEditForm = this.formBuilder.group({
			
			email: ['', Validators.email],
			firstName: ['', Validators.required],
		    lastName: ['', Validators.required],
		    birthDate: ['', Validators.required],
		    status: ['1', Validators.required],
			userAccess: [UserAccessType.NONE, [UserAccessTypeControllValidator]],
		
			
        });
		
		this.global.loadAllSetting();
		
		this.reload();
	}

	debug(){
		var s = "";
		for(var cnt in this.userEditForm.controls){
			var ctl = this.userEditForm.controls[cnt];
			//JSON.stringify(ctl.ValidationErrors)
			s += cnt + ": " + ctl.value +  " \n ";
		}
		return s;
	}
	
	reload() {
		this.loadingService.showLoading();
		
		this.editService.listUsers().subscribe(
	        (results :User[]) => {
	        	
	            console.log("User list", results);
	        	
	            this.users = results;
	        },
	        response => {
	        	console.log("Error in get user list", response);
	        	this.loadingService.hideLoading();	 
	        	this.errorService.showErrorResponse(response);
	        },
	        () => {
	        	
	        	this.loadingService.hideLoading();	            
	        }
		);	       	
	}

	showCreateUser() {

		this.isCreating = true;
		this.editingUser = new User;
		this.editingUserDepartments = [];

		this.setToControlValues();
		
		this.activeTab = "info";
		
		this.showEditModal = true;
		
	}

	showEditUser(user: User) {

		this.isCreating = false;
		this.editingUser = user;
		this.editingUserDepartments = this.editingUser.userDepartments;
		
		this.setToControlValues();
		
		
		this.activeTab = "info";
		this.showEditModal = true;
	}

	showViewUser(user: User) {
		this.viewingUser = user;
				
		this.viewingDepartmentMember = [];
			
		for(var index in this.viewingUser.userDepartments){
			var userDepartment :UserDepartment = this.viewingUser.userDepartments[index];
			var dep = this.findDepartment(userDepartment.departmentIdentity);
			this.viewingDepartmentMember.push({"title" : dep != null ? dep.title : "not found!" , "type":this.userDepartmentAccessType[userDepartment.memberType]});
		}

		this.activeTab = "info";
		this.showViewModal = true;
	}

	findDepartment(identity: string):Department{
		for(var index in this.departments){
			if(this.departments[index].identity === identity){
				return this.departments[index];
			}
		}
		return null;
	}
	
	hideViewUserDialog(){
		this.showViewModal = false;
	}
	
	showDeleteUser(user: User) {
		
		this.delitingUser = user;
		this.deleteMessage = this.deleteMessageBase;
		this.deleteMessage = this.deleteMessage.replace("%" , user.fullName);

		this.showDeleteModal = true;
	}
	
	hideDeleteUserDialog(){
		this.showDeleteModal = false;
	}
	
	hideEditUserDialog(){
		this.showEditModal = false;
	}


	hideResetPasswordUserDialog(){
		this.showResetPasswordModal = false;
	}
	
	showUserResetPassword(user: User) {
		
		this.passwordResetingUser = user;
		this.resetPasswordMessage = this.resetPasswordMessageBase;
		this.resetPasswordMessage = this.resetPasswordMessage.replace("%" , user.fullName);
		this.resetPasswordResultMessage = "";

		this.showResetPasswordModal = true;
	}

	isMemberOfDepartment(identity:string): boolean{
		
		if(this.editingUser == null){
			return false;
		}
		
		
		for(var index in this.editingUserDepartments){
			if(this.editingUserDepartments[index].departmentIdentity === identity){
				return true;
			}
		}
		
		
		return false;
	}

	toggleMemberOfDepartment(identity:string){
		
		if(this.editingUser == null){
			return;
		}
		
		if(this.isMemberOfDepartment(identity)){
			this.editingUserDepartments = this.editingUserDepartments.filter(function(userDep){
				return userDep.departmentIdentity != identity;
			});
		}
		else{
			var userDep = new UserDepartment;
			userDep.departmentIdentity = identity;
			userDep.memberType = 5;
			this.editingUserDepartments.push(userDep);
		}
		
	}
	
	meberTypeOfDepartment(identity:string):string{
		
		for(var index in this.editingUserDepartments){
			if(this.editingUserDepartments[index].departmentIdentity === identity){
				console.log("meberTypeOfDepartment: " + identity + " , " + this.editingUserDepartments[index].memberType);

				return this.editingUserDepartments[index].memberType + "";
			}
		}
		console.log("meberTypeOfDepartment: " + identity + " , 0");
		return "0";
	}
	
	onMeberTypeOfDepartmentChange(event, identity:string, value:number){

		for(var index in this.editingUserDepartments){
			if(this.editingUserDepartments[index].departmentIdentity === identity){
				this.editingUserDepartments[index].memberType = value;
				return;
			}
		}
		
	}
		
	deleteUser(){
		
		this.loadingService.showLoading();

		this.editService.deleteUser(this.delitingUser).subscribe(
		        (result) => {		        	
		            console.log("Delete user result success.");
		            this.showDeleteModal = false;
		            this.reload();
		            
		        },
		        response => {
		        	console.log("Error in create user", response);
		        	
		        	this.errorService.showErrorResponse(response);
		        	this.loadingService.hideLoading();	 
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	 
		        }
		);	     
		

	}
	
	resetUserPassword(){
		
		this.loadingService.showLoading();	

		this.editService.resetUserPassword(this.passwordResetingUser).subscribe(
		        (resultUser: User) => {		        	
		            console.log("Reset user password result success.", resultUser);
		            //this.showDeleteModal = false;
		            //this.reload();
		            
		    		this.resetPasswordResultMessage = this.resetPasswordResultMessageBase;
		    		this.resetPasswordResultMessage = this.resetPasswordResultMessage.replace("%" , resultUser.password);

		            
		        },
		        response => {
		        	console.log("Error reset user password", response);
		        	
		        	this.errorService.showErrorResponse(response);
		        	this.loadingService.hideLoading();	 
		        },
		        () => {
		        	
		        	this.loadingService.hideLoading();	 
		        }
		);	  
	}
	
	saveUser() {
		this.setFormControlValues();
				
		this.loadingService.showLoading();
		
		if(this.isCreating){
			this.editService.createUser(this.editingUser).subscribe(
			        (result) => {		        	
			            console.log("Create user result", result);
			            this.showEditModal = false;
			            this.reload();
			            
			        },
			        response => {
			        	console.log("Error in create user", response);
			        	
			        	this.errorService.showErrorResponse(response);
			        	this.loadingService.hideLoading();	 
			        },
			        () => {
			        	
			        	this.loadingService.hideLoading();	 
			        }
			);	     
		}
		else{
			this.editService.updateUser(this.editingUser).subscribe(
			        (result) => {		        	
			            console.log("Update user result", result);
			            this.showEditModal = false;
			            this.reload();
			            
			        },
			        response => {
			        	console.log("Error in update user", response);
			        	
			        	this.errorService.showErrorResponse(response);
			        	this.loadingService.hideLoading();	 
			        },
			        () => {
			        	
			        	this.loadingService.hideLoading();	 
			        }
			);	     
			
		}
		
		
		
		
	}


	setToControlValues(){
		if(this.editingUser && this.editingUser){
			this.userEditForm.controls["email"].setValue(this.editingUser.email);
			this.userEditForm.controls["firstName"].setValue(this.editingUser.firstName);
			this.userEditForm.controls["lastName"].setValue(this.editingUser.lastName);
			this.userEditForm.controls["userAccess"].setValue(this.editingUser.userAccess);
			this.userEditForm.controls["status"].setValue(this.editingUser.status + '');			
			this.userEditForm.controls["birthDate"].setValue(parseDate(this.editingUser.birthDate, 'dd.mm.yyyy'));

		}
	}
	
	setFormControlValues(){
				
		this.editingUser.email = this.userEditForm.controls["email"].value;
		this.editingUser.firstName = this.userEditForm.controls["firstName"].value;
		this.editingUser.lastName = this.userEditForm.controls["lastName"].value;
		this.editingUser.userAccess = this.userEditForm.controls["userAccess"].value;
		this.editingUser.status = this.userEditForm.controls["status"].value;			
		this.editingUser.birthDate = formatDate(this.userEditForm.controls["birthDate"].value, 'dd.mm.yyyy');
		
		this.editingUser.userDepartments = this.editingUserDepartments;

	}
	
	
}
