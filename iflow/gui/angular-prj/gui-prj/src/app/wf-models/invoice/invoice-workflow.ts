
import { User } from '../../ui-models';
import { Workflow, WorkflowType, WorkflowTypeStep, WorkflowFile, WorkflowAction, InvoiceType } from '../../wf-models';

export class InvoiceWorkflow  {

  workflowBase: Workflow;
	sender: string;
	registerNumber: string;
	invocieDate: string;
	partnerCode: string;
	vendorNumber: string;
	vendorName: string;
	isDirectDebitPermission: boolean;
	invoiceType: InvoiceType;

	discountEnterDate: string;
	discountDeadline: number;
	discountRate: number;
	discountDate: string;

	paymentAmount: number;

}




