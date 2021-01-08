import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';

import { LoadingServiceService } from '../loading-service.service';
import { HttpHelper } from '../../helper/http-hepler';
import { HttpErrorResponseHelper } from '../../helper/http-error-response-helper';
import { AuthenticationService } from '../../services/authentication.service';

import { CompanyWorkflowtypeItemOcrSettingPreset, GeneralData } from '../../ui-models';

@Injectable({
  providedIn: 'root'
})
export class OcrPresetsService extends HttpErrorResponseHelper {
  listPresetsUrl :string = HttpHelper.dataServer + "/ocrpreset/data/list";
  updatePresetUrl :string = HttpHelper.dataServer + "/ocrpreset/data/save";
  deletePresetUrl :string = HttpHelper.dataServer + "/ocrpreset/data/delete";
  listPresetItemsUrl :string = HttpHelper.dataServer + "/ocrpreset/data/read/";
	pageInitUrl :string = HttpHelper.dataServer + "/ocrpreset/data/initpage";

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

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

			return this.http.get<CompanyWorkflowtypeItemOcrSettingPreset[]>(this.listPresetsUrl, httpOptions);

	};

	listPresetItems(presetName:string){

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

			return this.http.get(this.listPresetItemsUrl + presetName, httpOptions);

	};

	pageInitData(){

	    const httpOptions = { headers: HttpHelper.generateJsonHeader() };

			return this.http.get(this.pageInitUrl, httpOptions);

	};

	updatePreset(presetToSave: CompanyWorkflowtypeItemOcrSettingPreset){

			const httpOptions = { headers: HttpHelper.generateJsonHeader() };

			return this.http.post<CompanyWorkflowtypeItemOcrSettingPreset[]>(this.updatePresetUrl, presetToSave, httpOptions);

	};

	deletePreset(presetToDelete: CompanyWorkflowtypeItemOcrSettingPreset){

			const httpOptions = { headers: HttpHelper.generateJsonHeader() };

			return this.http.post<CompanyWorkflowtypeItemOcrSettingPreset[]>(this.deletePresetUrl, presetToDelete, httpOptions);

	};

}
