import { WorkflowTypeStep } from './workflowtypestep';
﻿
export class WorkflowType {
	public id :string = "";
	public identity :string = "";
	public status: Number = 0;
	public title :string = "";
	public comments :string = "";
	public sendToController: boolean = false;
	public assignType :string = "";
	public allowAssign: boolean = false;
	public increaseStepAutomatic: boolean = false;
	public controllerPreffix: string = "";

	public steps: WorkflowTypeStep[] = [];

}
