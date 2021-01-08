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
	@Input('selectAssign') selectAssign : Record<string, Record<string, boolean>> = {"None" : {}, "User" : {}, "Department" : {} };

	@Output() onUsersSelected = new EventEmitter<AssignItem[]>();

	assignTypeUser :AssignType = AssignType.USER;
	assignTypeDepartment :AssignType = AssignType.DEPARTMENT;

	constructor() {
	  this.users = [];
	  this.departments = [];
	}


	ngOnInit() {

	}

	applyUserSelect(){
		var assigns :AssignItem[] = [];

    for (let [type, value] of Object.entries(this.selectAssign)) {
      for (let [id, value2] of Object.entries(this.selectAssign[type])) {
				if(this.selectAssign[type][id]){
					var assign = new AssignItem;
					assign.itemId = <string>id;
					assign.itemType = <AssignType>type;

					assigns.push(assign);

				}
			}
    }

		this.onUsersSelected.emit(assigns);
	}


	isItemAssigned(id :string , type: AssignType){

		if(this.selectAssign[type] === undefined){
			this.selectAssign[type] = {};
		}
		if(this.selectAssign[type][id] === undefined){
			this.selectAssign[type][id] = false;
		}

		return this.selectAssign[type][id];
	}

	hideAssignSelect(){
		this.showAssignModal = false;
	}

	toggleAssign(id :string , type: AssignType, isChecked: boolean){
		if(this.selectAssign[type] === undefined){
			this.selectAssign[type] = {};
		}
		this.selectAssign[type][id] = isChecked;

	}

}
