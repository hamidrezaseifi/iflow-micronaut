import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import * as moment from 'moment'; 
import { ResizeEvent } from 'angular-resizable-element';
import { Observable, throwError , Subscription } from 'rxjs';
import { Message } from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';
import { Router, NavigationEnd } from '@angular/router';

import { Workflow } from '../wf-models';
import { WorkflowMessage } from '../wf-models';
import { User, MenuItem } from '../ui-models';
import { GlobalSocket } from '../services/global-socket';

import { WorkflowMessageService } from '../services/workflow/workflow-message.service';
import { ErrorServiceService } from '../services/error-service.service';

@Component({
  selector: 'app-message-bar',
  templateUrl: './message-bar.component.html',
  styleUrls: ['./message-bar.component.css'],
  providers: [ WorkflowMessageService ]
})
export class MessageBarComponent implements OnInit, OnDestroy {

	messages: WorkflowMessage[] = [];
	viewWorkflowModel :Workflow;
	viewWorkflow :boolean = false;

	messageSearchInterval = 60000;
	messageReloadTimeoutId = 0;
	messagePanelHeight = 170;
	messagePanelShowed :boolean= true;
	
	isReloadingMessages : boolean = false;

	private subscription: Subscription;

	public status = "Not Connected";
	public subscribed: boolean;
	public requesting : boolean = false;
	
	private stompClient: any = null;
	

	debugData() :string{
		return (this.viewWorkflowModel && this.viewWorkflowModel != null) ? JSON.stringify(this.viewWorkflowModel) : 'no data';
	}
	
	closeMessages(){
		//$('#message-panel-container').height(25);
		document.getElementById("message-panel-container").style.height = "25px";
		this.messagePanelShowed = false;
    };

	showMessages(){
		//$('#message-panel-container').height(this.messagePanelHeight);
		//alert("show pabel");
		document.getElementById("message-panel-container").style.height = this.messagePanelHeight + "px";
		this.messagePanelShowed = true;
    };
    
	@Input('currentUser') currentUser: User;
	
	private _isLogged: boolean = false;
	
	@Input('isLogged')
	set isLogged(value: boolean) {
	    
		if(this._isLogged !== value){
			if(value === true){			
				this.subscribe();
		    }
			else{
				this.unsubscribe();
				
			}			
		}

		this._isLogged = value;

	}
	
	
	get isAppLogged(): boolean { 
		return this._isLogged; 
	}	

	
	constructor(protected router: Router, 
			private messageService :WorkflowMessageService,
			private errorService: ErrorServiceService,
			private globalSocket: GlobalSocket,
			) { 
		
	}
	

	ngOnInit() {		
		
		console.log("Initialize Messagebar. isLogge: "+ this._isLogged );
		
		if(this._isLogged === true){
			
			this.readMessageList(true);
			this.subscribe();
			
	    }
	}
	
	ngOnDestroy() {
		console.log("Destroy Messagebar. " );
	    this.unsubscribe();
	}
	
	onResizeEnd(event: ResizeEvent): void {
		this.messagePanelHeight = event.rectangle.height;
		document.getElementById("message-panel-container").style.height = this.messagePanelHeight + "px";
	}
	
	private readMessageList(reset: boolean){

		//clearTimeout(this.messageReloadTimeoutId);
		console.log("Start Request Read message list " + (reset ? "with reset" : "without reset"));
		if(this._isLogged === true){
			
			this.isReloadingMessages = true;
			this.messageService.loadMessages(reset).subscribe(
			        (messageList :WorkflowMessage[]) => {
			        	console.log("Read message list", messageList);
			        	
			        	this.messages = messageList;			            
			        },
			        response => {
			        	console.log("Error in read message list", response);
			        	this.messages = [];	
			        	this.isReloadingMessages = false;
			        },
			        () => {
			        	
			        	setTimeout(()=>{ 
			        		this.isReloadingMessages = false;
			        	 }, 500);
			        	
			        	/*this.messageReloadTimeoutId = setTimeout(() =>{ 			  				
		  					this.reloadMessages(false);		  				 
			        	}, this.messageSearchInterval);	*/		        	
			        }
		    	);
			
		}
				
	}
	
	showWorkflowView(identity){
	  
		for(var index in this.messages){
			if(this.messages[index].workflowIdentity == identity){
				this.viewWorkflowModel = this.messages[index].workflow;
				this.viewWorkflow = true;
				break;
			}
		}
  		
  	}
	
	hideViewModal(){
		this.viewWorkflow = false;
  	}
	
	assignWorkflowMe(){
		
		this.messageService.assignMe(this.viewWorkflowModel.identity).subscribe(
		        val => {
		        	console.log("Workflow assigned to me");
		        	//this.readMessageList(true);
		            
		        },
		        response => {
		        	console.log("Error in assigning workflow", response);
		  			this.errorService.showErrorResponse(response);
		        	
		        },
		        () => {
		        	this.viewWorkflow = false;
		        }
	    	);	      
		
  	}
	
	editWorkflow(){
		
		this.viewWorkflow = false;
		this.router.navigate(['/workflow/edit/' + this.viewWorkflowModel.workflowType.identity + '/' + this.viewWorkflowModel.identity]);
		
		
  	}
	
	private setConnected(subscribed) {
		this.subscribed = subscribed;
	    
		this.status = subscribed ? "Connected" : "Not Connected";
	}	
	
	private subscribe() {
		
		if (this.subscribed) {
		      return;
		}
		
		let socket :SockJS = new SockJS('/iflow-guide-websocket');
		this.stompClient = Stomp.over(socket);
		
		const _this = this;
		
		this.stompClient.connect({}, function (frame) {
			_this.setConnected(true);
			_this.stompClient.subscribe('/user/socket/messages', function (message) {
				_this.onReceiveMessage(message);
            });
            //_this.stompClient.reconnect_delay = 2000;
        }, _this.errorCallBack);
		
	    this.setConnected(true);
		
	}
	
	errorCallBack(error) {
        console.log("errorCallBack -> " + error)
        setTimeout(() => {
           // this._connect();
        }, 5000);
    }
	
	public onReceiveMessage = (message: Message) => {

		this.requesting = false;
		
		console.log("Socket Message: " , message.body);
		var parsedMessage = JSON.parse(message.body);
		console.log("Parsed Message: " , parsedMessage);
		
		if(parsedMessage.command && parsedMessage.command === "message-reload"){
			this.readMessageList(false);			
		}
	}	

	reloadMessages(){
		this.readMessageList(true);
	}
	
	private unsubscribe() {
	    if (!this.subscribed) {
	      return;
	    }

	    if (this.stompClient !== null) {
	    	this.stompClient.disconnect();
	    }
	    this.setConnected(false);
	    console.log("Disconnected");

	    this.setConnected(false);
	}
  	
}
