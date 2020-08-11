import { Injectable } from '@angular/core';
import { HttpRequest, HttpResponse, HttpHandler, HttpEvent, HttpInterceptor, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { delay, mergeMap, materialize, dematerialize } from 'rxjs/operators';
import { InterceptorUseHeader } from './http-hepler';

import { AuthenticationOKResponse, AuthenticationFailedResponse, GeneralDataOkResponse, 
	InitialSearchDataOkResponse, WorkflowSearchResultOkResponse, WorkflowSaveRequestInitOkResponse,
	InvoiceWorkflowSaveRequestInitOkResponse} from '../fake-data';

	
@Injectable()
export class FakeBackendInterceptor implements HttpInterceptor {
	
    constructor() { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        // wrap in delayed observable to simulate server api call
        return of(null).pipe(mergeMap(() => {

        	if (request.headers == undefined || (request.headers && request.headers.has(InterceptorUseHeader) === false)) {
        	      const headers = request.headers;
        	      return next.handle(request.clone({ headers }));
        	}
        	
            // authenticate
            if (request.url.endsWith('/auth/authenticate') && request.method === 'POST') {
            	
            	if(request.body == null || request.body.username == null || request.body.username == "" 
            		|| request.body.password == null || request.body.password == "" || request.body.companyid == null 
            		|| request.body.companyid == "" ){
            		//return throwError({ error: { message: 'Username or password is incorrect' } });
            		return of(new HttpResponse({ status: 200, body: AuthenticationFailedResponse }));
            	}
            	
            	return of(new HttpResponse({ status: 200, body: AuthenticationOKResponse }));
            	
            }

            // get general data
            if (request.url.endsWith('/general/data/generaldatat') && request.method === 'GET') {
            	            	
            	return of(new HttpResponse({ status: 200, body: GeneralDataOkResponse }));            	
            }

            // get workflow search init data
            if (request.url.endsWith('/workflow/general/data/initsearch') && request.method === 'POST') {
            	            	
            	return of(new HttpResponse({ status: 200, body: InitialSearchDataOkResponse }));           	
            }

            // get workflow search result
            if (request.url.endsWith('/workflow/general/data/search') && request.method === 'POST') {
            	            	
            	return of(new HttpResponse({ status: 200, body: WorkflowSearchResultOkResponse }));           	
            }

            // get workflow create & edit initial result
            if (
            		(request.url.endsWith('/workflow/singletask/data/initcreate') || 
            				request.url.endsWith('/workflow/testthreetask/data/initcreate'))
            				&& request.method === 'POST') {
            	            	
            	return of(new HttpResponse({ status: 200, body: WorkflowSaveRequestInitOkResponse }));           	
            }

            // get workflow create & edit initial result
            if (request.url.endsWith('/workflow/invoice/data/initcreate') && request.method === 'POST') {
            	            	
            	return of(new HttpResponse({ status: 200, body: InvoiceWorkflowSaveRequestInitOkResponse }));           	
            }



            // pass through any requests not handled above
            return next.handle(request);
            
        }))

        // call materialize and dematerialize to ensure delay even if an error is thrown (https://github.com/Reactive-Extensions/RxJS/issues/648)
        .pipe(materialize())
        .pipe(delay(500))
        .pipe(dematerialize());
    }
}

export let fakeBackendProvider = {
    // use fake backend in place of Http service for backend-less development
    provide: HTTP_INTERCEPTORS,
    useClass: FakeBackendInterceptor,
    multi: true
};