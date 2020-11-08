import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHepler } from '../helper/http-hepler';
import { HttpErrorResponseHelper } from '../helper/http-error-response-helper';
import { Credential, GeneralData } from '../ui-models';
import { HttpHeaders, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) {

  }

  login(username:string, password:string){

        const httpOptions = { headers: HttpHepler.generateJsonHeader() };

        const credential : Credential = new Credential;
        credential.username = username;
        credential.password = password;

        //const headers = new HttpHeaders().set("Content-Type", "application/json");
        const headers = new HttpHeaders().set("Content-Type", "application/x-www-form-urlencoded");
        const dataJson = "{\"username1\": \"" + username + "\", \"password\": \"" + password + "\"}";
        const dataForm = "username=" + username + "&password=" + password + "&returnUrl=/";

        //return this.http.post(HttpHepler.dataServer + "/login" , credential);
        //, {headers}

        return this.http.post("http://localhost:1200/login", dataForm, {headers});
  }

}
