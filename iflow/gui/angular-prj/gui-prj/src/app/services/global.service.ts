import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import 'rxjs/add/operator/map';

import { User, MenuItem, GeneralData } from '../ui-models';
import { TopBarComponent } from '../top-bar/top-bar.component';
import { LoginService, LoadingServiceService } from '../services';
import { HttpHelper } from '../helper/http-hepler';

@Injectable({ providedIn: 'root' })
export class GlobalService {

	loadGeneralDataUrl :string = HttpHelper.dataServer + "/users/data/sessiondata";
	public currentSessionDataSubject: BehaviorSubject<GeneralData> = new BehaviorSubject<GeneralData>(new GeneralData);

	public presensSubject :BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);


	constructor(private http:HttpClient,
	            private loadingService: LoadingServiceService,
	            private loginService: LoginService,
	            private cookieService:CookieService
	            ) {
	}

	public getSessionData():GeneralData{
	  if(sessionStorage.getItem("session-data")){
	    var data:string = <string>sessionStorage.getItem("session-data");
      return <GeneralData> JSON.parse(data);
	  }
	  return new GeneralData;
	}

	public setSessionData(data:GeneralData){
	  sessionStorage.setItem("session-data", JSON.stringify(data));
	  this.currentSessionDataSubject.next(data);
	}

	public logout(){
    this.cookieService.delete("iflow");
    this.cookieService.delete('Path');
	  this.removeSession();
	  this.loginService.logout().subscribe((data :any) => {});
	  window.location.href = '/auth/login';
	}

	public removeSession(){
	  this.removeSessionData();
	  this.getSessionId();
	}

	public removeSessionData(){
	  sessionStorage.removeItem("session-data");
	  this.currentSessionDataSubject.next(new GeneralData);
	}

	public getSessionId():string{
	  return <string>sessionStorage.getItem("session-id");
	}

	public setSessionId(data:string){
	  sessionStorage.setItem("session-id", data);
	}

	public removeSessionId(){
	  sessionStorage.removeItem("session-id");
	}


	loadAllSetting(){

        if(sessionStorage.getItem("session-data") != null){

            this.currentSessionDataSubject.next(this.getSessionData());
            return;
        }

		    this.loadingService.showLoading();

				this.http.get<GeneralData>(this.loadGeneralDataUrl).subscribe(
						(generalData: GeneralData) => {
		            console.log("GET call successful generaldata", generalData);

		            var islogged = generalData.isLogged + "";
		            generalData.isLogged = (islogged === "true");

		            this.setSessionData(<GeneralData> JSON.parse(JSON.stringify(generalData)));

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
    const httpOptions = { headers: HttpHelper.generateFormHeader() };

		return this.http.get(this.loadGeneralDataUrl);
	}


}
