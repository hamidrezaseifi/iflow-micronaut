import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import 'rxjs/add/operator/map';

import { User, MenuItem, GeneralData } from '../ui-models';
import { TopBarComponent } from '../top-bar/top-bar.component';
import { LoadingServiceService } from './loading-service.service';
import { HttpHepler } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class GlobalService {

	loadGeneralDataUrl :string = HttpHepler.dataServer + "/users/data/sessiondata";
	public currentSessionDataSubject: BehaviorSubject<GeneralData> = new BehaviorSubject<GeneralData>(null);
	//public currentSessionDataObs :Observable<GeneralData>;

	public presensSubject :BehaviorSubject<boolean> = new BehaviorSubject<boolean>(null);

	public loadedGeneralData : GeneralData = null;
	public sessionId : string = "";

	constructor(private http:HttpClient,
	            private loadingService: LoadingServiceService,
	            private _cookieService:CookieService
	            ) {

	}


	loadAllSetting(){
//alert(JSON.stringify(sessionStorage));
//this._cookieService.set('iflow1', 'my-data')
//alert(JSON.stringify(this._cookieService.getAll()));
//alert(this._cookieService.get('iflow1'));

		    this.loadingService.showLoading();

//generalData :GeneralData
//.map((res: Response) => res.json())
				this.http.get(this.loadGeneralDataUrl, {withCredentials: true}).subscribe(
						(generalData: GeneralData) => {
		            //console.log("GET call successful generaldata", data);
		            //console.log("Response Header cookie", data.headers.get("myxyz"));

		           // var generalData = data.body;
//SESSION
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

		return this.http.get(this.loadGeneralDataUrl);
	}


}
