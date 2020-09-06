import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';

import { User, MenuItem, GeneralData } from '../ui-models';
import { TopBarComponent } from '../top-bar/top-bar.component';
import { LoadingServiceService } from './loading-service.service';
import { HttpHepler } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class GlobalService {

	loadGeneralDataUrl :string = "/users/sessiondata";
	public currentSessionDataSubject: BehaviorSubject<GeneralData> = new BehaviorSubject<GeneralData>(null);
	//public currentSessionDataObs :Observable<GeneralData>;

	public presensSubject :BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);

	public loadedGeneralData : GeneralData = null;

	constructor(private http:HttpClient, private loadingService: LoadingServiceService,) {

	}


	loadAllSetting(){
	alert("start loadAllSetting");
		    this.loadingService.showLoading();

        const httpOptions = { headers: HttpHepler.generateFormHeader() };

				this.http.get(this.loadGeneralDataUrl, httpOptions).subscribe(
						(generalData :GeneralData) => {
		            console.log("GET call successful generaldata", generalData);
		            alert(generalData.isLogged);
		            var islogged = generalData.isLogged + "";
		            generalData.isLogged = islogged === "true";
alert("loaded generaldata islogged" + generalData.isLogged + "   data:" + JSON.stringify(generalData));

		            this.loadedGeneralData = <GeneralData> JSON.parse(JSON.stringify(generalData));
alert("parsed generaldata islogged" + this.loadedGeneralData.isLogged + "   data:" + JSON.stringify(this.loadedGeneralData));

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

	/*setGeneralData(generalData :GeneralData){
		this.currentSessionDataSubject.next(generalData);
		//this.currentSessionDataSubject.complete();
	}*/

	loadAllSettingObserv(){
        const httpOptions = { headers: HttpHepler.generateFormHeader() };

		return this.http.get(this.loadGeneralDataUrl, httpOptions);
	}

	/*clear(){

		//alert("clear global");
		this.currentSessionDataSubject.next(null);
		//this.currentSessionDataSubject.complete();
	}*/

}
