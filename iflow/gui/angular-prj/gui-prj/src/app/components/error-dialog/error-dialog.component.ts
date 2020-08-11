import { Component, OnInit, Input } from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import { ErrorServiceService } from '../../services/error-service.service';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ErrorDetail } from '../../ui-models';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.css']
})
export class ErrorDialogComponent implements OnInit {

	
	showErrorDetail :boolean = false;
	showError :boolean = false;
	errorMessage :string = "";
	errorDetails :string = "";
	
	constructor(
			translate: TranslateService,
			private errorService: ErrorServiceService,
			) {  
		
		translate.setDefaultLang('de');
		translate.use('de');
		
	}
	
	

	ngOnInit() {
		this.subscribeErrorService();		
	}
	
	subscribeErrorService(){
		this.errorService.errorSubject.subscribe((data : ErrorDetail) => {
			if(data && data != null){
				this.errorMessage = data.errorMessage;
				this.errorDetails = data.errorDetail;
				this.showErrorDetail = false;
				this.showError = true;
				  
				//alert("error coms: " + this.errorMessage + " , show: " + (this.showError === true));
			}
			else{
				this.showError = false;
				this.showErrorDetail = false;
				//alert("no error");
			}
		    //this.subscribeErrorService();
		  });
	}

	hideModal(){
		this.showError = false;
		this.showErrorDetail = false;
	}
	
	get hasErrorDetail(): boolean{
		return this.errorDetails !== null && this.errorDetails !== "";
	}
}
