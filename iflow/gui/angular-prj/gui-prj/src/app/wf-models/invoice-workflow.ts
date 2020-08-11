
import { User } from '../ui-models';
import { WorkflowType } from '../wf-models';
import { WorkflowTypeStep } from '../wf-models';
import { WorkflowFile } from '../wf-models';
import { WorkflowAction } from '../wf-models';
import { InvoiceType } from '../wf-models';
import { Workflow } from '../wf-models';

export class InvoiceWorkflow extends Workflow {

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




