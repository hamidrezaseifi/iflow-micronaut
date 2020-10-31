import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { User, MenuItem, GeneralData } from '../ui-models';
import { TopBarComponent } from '../top-bar/top-bar.component';
import { LoadingServiceService } from './loading-service.service';
import { HttpHepler } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class GlobalService {

	loadGeneralDataUrl :string = "http://localhost:1200/users/data/sessiondata";
	public currentSessionDataSubject: BehaviorSubject<GeneralData> = new BehaviorSubject<GeneralData>(null);
	//public currentSessionDataObs :Observable<GeneralData>;

	public presensSubject :BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);

	public loadedGeneralData : GeneralData = null;

	constructor(private http:HttpClient, private loadingService: LoadingServiceService,) {

	}


	loadAllSetting(){

		    this.loadingService.showLoading();

        const httpOptions = { headers: HttpHepler.generateFormHeader() };

				this.http.get(this.loadGeneralDataUrl, httpOptions).subscribe(
						(generalData :GeneralData) => {
		            console.log("GET call successful generaldata", generalData);

		            var islogged = generalData.isLogged + "";
		            generalData.isLogged = islogged === "true";

		            this.loadedGeneralData = <GeneralData> JSON.parse(JSON.stringify(generalData));

		            this.currentSessionDataSubject.next(generalData);
		        	  this.presensSubject.next(true);

		        	  this.loadingService.hideLoading();
		        },
		        response => {
		            console.log("Error in read general list", response);
		            this.loadingService.hideLoading();
		        },
		        () => {

		            this.loadingService.hideLoading();
		        }
		);
	}

	loadAllSettingObserv(){
        const httpOptions = { headers: HttpHepler.generateFormHeader() };

		return this.http.get(this.loadGeneralDataUrl, httpOptions);
	}


}
