import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';

export const InterceptorUseHeader = 'X-Use-Interceptor';

export class HttpHepler {

  public static serverPort: string = "localhost:1200";
  public static dataServer: string = "http://localhost:1200";

	public static generateFormHeader(): HttpHeaders{

		var header :HttpHeaders  = new HttpHeaders({
  		    'Content-Type':  'application/x-www-form-urlencoded',
  		    'Authorization': 'my-auth-token',
  			  'Cache-Control': 'no-cache',
        	'Pragma': 'no-cache'
		});

		//alert(header.keys());

		return header;
	}

	public static generateJsonHeader(): HttpHeaders{

		var header :HttpHeaders  = new HttpHeaders({
			'Content-Type':  'application/json; charset=UTF-8'
		});

		return header;
	}

	public static generateFileUploadHeader(): HttpHeaders{

		var header :HttpHeaders  = new HttpHeaders({
			'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Content-Type':  'multipart/form-data'
		});

		return header;
	}

}
