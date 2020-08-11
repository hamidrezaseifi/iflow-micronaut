import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHepler } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services';

import { CompanyWorkflowtypeItemOcrSettingPreset, GeneralData } from '../../ui-models';

@Injectable({
  providedIn: 'root'
})
export class WorkflowtypePropertySettingService extends HttpErrorResponseHelper {
  listPresetsUrl :string = "/company/data/readocrpresets";
	updatePresetUrl :string = "/company/data/savereadocrpreset";
  listPresetItemsUrl :string = "/company/data/readpresetallitems/";
	listWorkflowTypeItemsUrl :string = "/company/data/readworkflowtypeitems/";

	constructor(
			protected http: HttpClient,
			protected loadingService: LoadingServiceService,
			protected router: Router, 
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
	) { 
		super(router, route, autService);
		
	}
	
	listPresets(){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
			return this.http.get(this.listPresetsUrl, httpOptions);	    
		
	};
	
	listPresetItems(presetName:string){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
			return this.http.get(this.listPresetItemsUrl + presetName, httpOptions);	    
		
	};
	
	listWorkflowTypeItems(presetName:string){
		
	    const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
			return this.http.get(this.listWorkflowTypeItemsUrl + presetName, httpOptions);	    
		
	};


	updatePreset(presetToSave: CompanyWorkflowtypeItemOcrSettingPreset){
		
			const httpOptions = { headers: HttpHepler.generateJsonHeader() };
	    
			return this.http.post(this.updatePresetUrl, presetToSave, httpOptions);	    
		
	};
	
}
