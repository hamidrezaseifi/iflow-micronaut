import { HttpInterceptor, HttpHandler, HttpRequest, HttpEvent, HttpResponse, HttpErrorResponse, }   from '@angular/common/http';
import { Injectable } from "@angular/core"
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from '../services';

@Injectable({
	  providedIn: 'root'
	})
export class HttpErrorResponseHelper {

	constructor(
			protected router: Router, 
			protected route :ActivatedRoute,
			protected autService: AuthenticationService,
			) { 
		
	}
	
	processErrorResponse(response: HttpErrorResponse, ): boolean{
		if(response.error && response.error.status){
			if(response.error.status === "UNAUTHORIZED" || response.error.status === 401){
				var currentUrl = this.route.snapshot.url.map(f => f.path).join('/');
				
				this.autService.logout(currentUrl);
				//this.router.navigate(['auth/login'], { queryParams: { returnUrl: currentUrl } });
				return true;
			}
		}
		return false;
	}
	

}
