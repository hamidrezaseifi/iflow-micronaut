import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import $ from "jquery";

import { AssignItem, AssignType } from '../../wf-models';
import { User, Department, GeneralData, UploadedFile, UploadedResult } from '../../ui-models';

@Component({
  selector: 'app-wm-assign-list',
  templateUrl: './wm-assign-list.component.html',
  styleUrls: ['./wm-assign-list.component.css']
})
export class WmAssignListComponent implements OnInit {
	
	@Input('users') users : User[];
	@Input('departments') departments : Department[];
	@Input('assignedUsers') assignedUsers : AssignItem[] = [];

	@Output() onSelectedAssignChanged = new EventEmitter<AssignItem[]>();
	
	selectAssign :boolean[][] = [];
	showAssignModal :boolean = false;

	constructor(
			protected translate: TranslateService,
			) { 
		
	}

	ngOnInit() {
		
	}

	onUsersSelected(assigns: AssignItem[]) {
		this.assignedUsers = [];
		
		for(var item in assigns){
			var assign = new AssignItem;
			assign.itemIdentity = assigns[item].itemIdentity;
			assign.itemType = assigns[item].itemType;
			
			this.assignedUsers.push(assign);						
		}
		
		this.hideAssignSelect();
		this.onSelectedAssignChanged.emit(this.assignedUsers);
	}	
	
	
	showAssignSelect(){
		
		this.selectAssign = [];
		
		for(var index in this.assignedUsers){
			var assign :AssignItem = this.assignedUsers[index];
				
			if(this.selectAssign[assign.itemType] === undefined){
				this.selectAssign[assign.itemType] = [];
			}
			this.selectAssign[assign.itemType][assign.itemIdentity] = true;				
		}
		
		this.showAssignModal = true;
	}
	
	hideAssignSelect(){
		this.showAssignModal = false;
	}

	get hasNoAssigns() :boolean{
		if(this.assignedUsers){
			return this.assignedUsers.length == 0;
		}
		return false;
	}
	
	removeAssign(identity :string , type: AssignType){
		this.assignedUsers = this.assignedUsers.filter(function(value, index, arr){

		    return value.itemIdentity != identity || value.itemType != type;

		});
		
	}

	
	getAssignItemTitle(item :AssignItem){

		if(item.itemType === AssignType.USER){
			for(var index in this.users){
				if(this.users[index].identity === item.itemIdentity){
					return this.users[index].fullName;
				}
			}
			return 'Unknown!';
		}
		
		if(item.itemType === AssignType.DEPARTMENT){
			for(var index in this.departments){
				if(this.departments[index].identity === item.itemIdentity){
					return this.departments[index].title;
				}
			}
			return 'Unknown!';
		}
		
		
		
	}

}
