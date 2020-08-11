import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';

import { AssignItem, AssignType } from '../../wf-models';
import { User, Department, GeneralData, UploadedFile, UploadedResult } from '../../ui-models';

@Component({
  selector: 'app-select-user',
  templateUrl: './select-user.component.html',
  styleUrls: ['./select-user.component.css']
})
export class SelectUserComponent implements OnInit {

	@Input('users') users : User[];
	@Input('departments') departments : Department[];
	@Input('showAssignModal') showAssignModal :boolean = false;
	@Input('selectAssign') selectAssign : boolean[][] = [];

	@Output() onUsersSelected = new EventEmitter<AssignItem[]>();

	assignTypeUser :AssignType = AssignType.USER;
	assignTypeDepartment :AssignType = AssignType.DEPARTMENT;

	constructor() { }
	

	ngOnInit() {
		
	}

	applyUserSelect(){
		var assigns :AssignItem[] = [];
		
		for(var type in this.selectAssign){
			for(var identity in this.selectAssign[type]){
				
				if(this.selectAssign[type][identity]){
					var assign = new AssignItem;
					assign.itemIdentity = <string>identity;
					assign.itemType = <AssignType>type;
					
					assigns.push(assign);
					
				}
			}			
		}
		
		this.onUsersSelected.emit(assigns);
	}

	
	isItemAssigned(identity :string , type: AssignType){
		
		if(this.selectAssign[type] === undefined){
			this.selectAssign[type] = [];
		}
		if(this.selectAssign[type][identity] === undefined){
			this.selectAssign[type][identity] = false;
		}
	
		return this.selectAssign[type][identity];
	}
	
	hideAssignSelect(){
		this.showAssignModal = false;
	}
	
	toggleAssign(identity :string , type: AssignType, isChecked: boolean){
		if(this.selectAssign[type] === undefined){
			this.selectAssign[type] = [];
		}
		this.selectAssign[type][identity] = isChecked;
		
	}
	
}
