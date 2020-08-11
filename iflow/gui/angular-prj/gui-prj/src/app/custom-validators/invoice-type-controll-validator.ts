
import { AbstractControl } from '@angular/forms';
import { InvoiceType } from '../wf-models';

export function InvoiceTypeControllValidator(control: AbstractControl): { [key: string]: boolean } {
	
	let invoiceType :InvoiceType = (<any>InvoiceType)[control.value]; 

	if (invoiceType !== undefined && (invoiceType === InvoiceType.SUPPLIER || invoiceType === InvoiceType.WORKER || invoiceType === InvoiceType.PAYMENT) ) {
		return null;
	}
	return { invoiceTypeValid: true };
}

