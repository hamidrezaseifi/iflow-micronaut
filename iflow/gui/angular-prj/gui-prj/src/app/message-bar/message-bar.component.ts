import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import * as moment from 'moment';
import { ResizeEvent } from 'angular-resizable-element';
import { Router, NavigationEnd } from '@angular/router';

import { Workflow } from '../wf-models';
import { WorkflowMessage } from '../wf-models';
import { User, MenuItem } from '../ui-models';

import { WorkflowMessageService } from '../services/workflow/workflow-message.service';
import { ErrorServiceService } from '../services/error-service.service';
import { HttpHelper } from '../helper/http-hepler';

@Component({
  selector: 'app-message-bar',
  templateUrl: './message-bar.component.html',
  styleUrls: ['./message-bar.component.css'],
  providers: [ WorkflowMessageService ]
})
export class MessageBarComponent implements OnInit, OnDestroy {

  messages: WorkflowMessage[] = [];
	viewWorkflowModel :Workflow | null = null;
	viewWorkflow :boolean = false;

	webSocket: WebSocket | null = null;

	messageSearchInterval = 60000;
	messageReloadTimeoutId = 0;
	messagePanelHeight = 170;
	messagePanelShowed :boolean= true;

	isReloadingMessages : boolean = false;

	status = "Not Connected";
	subscribed: boolean = false;
	requesting : boolean = false;

  messagePanelHeightStyle: string = this.messagePanelHeight + "px";

	@Input('currentUser') currentUser: User | null;

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

	debugData() :string{
		return (this.viewWorkflowModel && this.viewWorkflowModel != null) ? JSON.stringify(this.viewWorkflowModel) : 'no data';
	}

	closeMessages(){
		//document.getElementById("message-panel-container").style.height = "25px";
		this.messagePanelHeightStyle = "25px";
		this.messagePanelShowed = false;
  };

	showMessages(){
		//document.getElementById("message-panel-container").style.height = this.messagePanelHeight + "px";
		this.messagePanelHeightStyle = this.messagePanelHeight + "px";
		this.messagePanelShowed = true;
  };

  get isWorkflowModelNotAssigned(){
    if(this.viewWorkflowModel){
      return this.viewWorkflowModel.notAssigned;
    }
    return false;
  }

  get isWorkflowModelMeAssigned(){
    if(this.viewWorkflowModel){
      return this.viewWorkflowModel.meAssigned;
    }
    return false;
  }

	constructor(protected router: Router,
			private messageService :WorkflowMessageService,
			private errorService: ErrorServiceService,
			) {
      this.currentUser = null;
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
	  if(event.rectangle && event.rectangle.height){
	    this.messagePanelHeight = event.rectangle.height;
	    this.showMessages();
	  }

		//document.getElementById("message-panel-container").style.height = this.messagePanelHeight + "px";
	}

	private readMessageList(reset: boolean){

		console.log("Start Request Read message list " + (reset ? "with reset" : "without reset"));
		if(this._isLogged === true){
      this.messages = [];
			this.isReloadingMessages = true;
			this.messageService.loadMessages(reset).subscribe(
        (messageList: WorkflowMessage[]) => {

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

        }
		  );

		}

	}

	showWorkflowView(id:string){
    console.log(id);
		for(var index in this.messages){
			if(this.messages[index].workflowId == id){
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

    if(this.viewWorkflowModel){
      this.messageService.assignMe(this.viewWorkflowModel.id).subscribe(
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
    }


	editWorkflow(){

    if(this.viewWorkflowModel){
      this.viewWorkflow = false;
      const url = '/workflow/edit/' + this.viewWorkflowModel.workflowType.identity  + '/' + this.viewWorkflowModel.id;
      this.router.navigate([url]);

    }

  }

	private setConnected(subscribed: boolean) {
		this.subscribed = subscribed;

		this.status = subscribed ? "Connected" : "Not Connected";
	}

	private subscribe() {

		 if (this.subscribed) {
		      return;
		 }

		 if(this.webSocket){
		    return;
		 }

		 if(this.currentUser == null){
		    return;
		 }

     var url = "ws://" + HttpHelper.serverPort + "/websocket/workflowmessages/" + this.currentUser.id;
     this.webSocket = new WebSocket(url);

     var _this = this;
     this.webSocket.onopen = function() {

         console.log("WM websocket connected");
         _this.setConnected(true);
     };

     this.webSocket.onmessage = function (evt) {
        if(evt && evt.data){
          console.log("WM Message is received..." + evt.data);
          _this.onReceiveMessage(evt.data);
        }

     };

     this.webSocket.onclose = function() {
         _this.setConnected(false);
         console.log("WM Message Connection is closed...");
     };


	}

	public onReceiveMessage = (message: string) => {

		this.requesting = false;

		var parsedMessage = JSON.parse(message);
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

	    this.setConnected(false);
	    console.log("WM Message Disconnected");

	}

}
