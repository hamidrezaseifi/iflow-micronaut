import { HttpHeaders, HttpParams, HttpClient } from '@angular/common/http';

import { environment } from '../../environments/environment';

export const InterceptorUseHeader = 'X-Use-Interceptor';

export class HttpHepler {
	public static generateFormHeader(): HttpHeaders{
		        	
		var header :HttpHeaders  = new HttpHeaders({
  		    'Content-Type':  'application/x-www-form-urlencoded',
  		    'Authorization': 'my-auth-token',
  			'Cache-Control': 'no-cache',
        	'Pragma': 'no-cache',
        	'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT'  		    
		});
		
		if (environment.fake === true) {
			header = new HttpHeaders({
	  		    'Content-Type':  'application/x-www-form-urlencoded',
	  		    'Authorization': 'my-auth-token',
	  		    'X-Use-Interceptor' : 'user-fake',
	  	  		'Cache-Control': 'no-cache',
	  	        'Pragma': 'no-cache',
	  	        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT'  		    
			});
		}
		
		//alert(header.keys());
		
		return header;
	}

	public static generateJsonHeader(): HttpHeaders{
		
		var header :HttpHeaders  = new HttpHeaders({
			'Content-Type':  'application/json; charset=UTF-8',
  		    'Authorization': 'my-auth-token',
  	  		'Cache-Control': 'no-cache',
  	        'Pragma': 'no-cache',
  	        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT'  	
		});
		
		if (environment.fake === true) {
			header = new HttpHeaders({
	  		    'Content-Type':  'application/json; charset=UTF-8',
	  		    'Authorization': 'my-auth-token',
	  		    'X-Use-Interceptor' : 'user-fake',
	  	  		'Cache-Control': 'no-cache',
	  	        'Pragma': 'no-cache',
	  	        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT'  	
			});
		}
		//alert(header.keys());
		
		return header;
	}

	public static generateFileUploadHeader(): HttpHeaders{
		
		var header :HttpHeaders  = new HttpHeaders({
			//'Content-Type' : undefined,
  	  		'Cache-Control': 'no-cache',
  	        'Pragma': 'no-cache',
  	        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT'  	
		});
		
		if (environment.fake === true) {
			header = new HttpHeaders({
				//'Content-Type' : undefined,
	  		    'X-Use-Interceptor' : 'user-fake',
	  	  		'Cache-Control': 'no-cache',
	  	        'Pragma': 'no-cache',
	  	        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT'  	
			});
		}
		//alert(header.keys());
		
		return header;
	}

}
