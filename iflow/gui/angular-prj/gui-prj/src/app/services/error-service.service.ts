import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

import { ErrorDetail, ErrorResponse } from '../ui-models';

@Injectable({
  providedIn: 'root'
})
export class ErrorServiceService {

	errorSubject = new BehaviorSubject<ErrorDetail>(new ErrorDetail("", ""));

	constructor() {

	}

  showError(errorMessage :string, errorDetail :string){

    	if(errorDetail === undefined){
    		errorDetail = "";
    	}

    	var err = new ErrorDetail(errorMessage, errorDetail);
    	this.errorSubject.next(err);
	}

  showErrorResponse(response: any){
    var errResp = new ErrorResponse(response);
    this.showError(errResp.message, errResp.details);
  }
}
