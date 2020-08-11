import { Injectable } from '@angular/core';
import * as SockJS from 'sockjs-client';

@Injectable({ providedIn: 'root' })
export class GlobalSocket {
	
	private globalSocket :SockJS = null;

	getGlobalSocket():SockJS {
		if(this.globalSocket === null){
			this.globalSocket = new SockJS('/iflow-guide-websocket');
		}
	    return this.globalSocket;
	}
	
	
  
}
