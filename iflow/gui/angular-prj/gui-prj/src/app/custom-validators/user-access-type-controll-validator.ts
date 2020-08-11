
import { AbstractControl } from '@angular/forms';
import { UserAccessType } from '../ui-models';

export function UserAccessTypeControllValidator(control: AbstractControl): { [key: string]: boolean } {
	
	let invoiceType :UserAccessType = (<any>UserAccessType)[control.value]; 

	if (invoiceType !== undefined && (invoiceType === UserAccessType.ADMIN || invoiceType === UserAccessType.AGENT || invoiceType === UserAccessType.VIEW) ) {
		return null;
	}
	return { userAccessTypeValid: true };
}

