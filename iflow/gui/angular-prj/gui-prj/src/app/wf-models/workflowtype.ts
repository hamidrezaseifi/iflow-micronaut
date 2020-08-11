import { WorkflowTypeStep } from './workflowtypestep';
﻿
export class WorkflowType {
	public identity: string;
	public status: Number;
	public title: string;
	public comments: string;
	public sendToController: boolean;
	public assignType: string;
	public allowAssign: boolean;
	public increaseStepAutomatic: boolean;

	public steps: WorkflowTypeStep[];

}
