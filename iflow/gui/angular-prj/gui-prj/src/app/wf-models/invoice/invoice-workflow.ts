
import { User } from '../../ui-models';
import { Workflow, WorkflowType, WorkflowTypeStep, WorkflowFile, WorkflowAction, InvoiceType } from '../../wf-models';

export class InvoiceWorkflow  {

  workflow: Workflow = new Workflow;
	sender: string = "";
	registerNumber: string = "";
	invocieDate: string = "";
	partnerCode: string = "";
	vendorNumber: string = "";
	vendorName: string = "";
	isDirectDebitPermission: boolean = false;
	invoiceType: InvoiceType = InvoiceType.NO_TYPE;
	discountEnterDate: string = "";
	discountDeadline: number = 0;
	discountRate: number = 0;
	discountDate: string = "";

	paymentAmount: number = 0;

}




