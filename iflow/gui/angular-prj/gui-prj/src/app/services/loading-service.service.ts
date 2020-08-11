import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoadingServiceService {

	loadingSubject = new BehaviorSubject<boolean>(false); 
	
	constructor() { 
		
	}
	
    showLoading(){
    	this.loadingSubject.next(true);
	}
    
    hideLoading(){
    	this.loadingSubject.next(false);
    }
    
}
