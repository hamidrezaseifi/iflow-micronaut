<template>
  <div class="hello">
    <div mwlResizable [enableGhostResize]="true" [resizeEdges]="{ bottom: false, right: false, top: true, left: false }"
         (resizeEnd)="onResizeEnd($event)" class="message-panel-container" id="message-panel-container"
         *ngIf="isAppLogged" [ngStyle]="{'height' : messagePanelHeightStyle}">
      <div class="message-panel-toolbar">
        <span class="title">Meldungen</span> &nbsp; &nbsp; <span [ngStyle]="{'color':subscribed ? 'green' : 'red' }">{{status}}</span>
        <button class="toolbar-button" *ngIf="messagePanelShowed" (click)="closeMessages();"><i class="material-icons">keyboard_arrow_down</i></button>
        <button class="toolbar-button" *ngIf="messagePanelShowed == false" (click)="showMessages();"><i class="material-icons">keyboard_arrow_up</i></button>
        <button class="toolbar-button" *ngIf="messagePanelShowed" (click)="reloadMessages();"><i class="material-icons">refresh</i></button>
        <img class="toolbar-image" *ngIf="isReloadingMessages" src="assets/images/loading200.gif" />

      </div>
      <div class="message-panel-items-container">
        <div class="message-panel-item" *ngFor="let message of messages;">
          <a href="javascript:void(0);" (click)="showWorkflowView(message.workflowId)">
            <div>{{message.message}} ({{message.workflow.workflowType.title}}) ({{message.createdAtString}}) ({{message.remainingDays}}) ({{message.status}})</div>
          </a>
        </div>
      </div>

    </div>

    <div class="modal fade show" tabindex="-1" *ngIf="viewWorkflow" id="viewworkflowedialog" role="dialog">

      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title dialog-title" id="errorMessagedialogTitle">{{ 'common.view-workflow' | translate }}</h5>
            <button class="dialog-toolbar-button close" (click)="hideViewModal()" aria-label="Close">
              <i class="material-icons">close</i>
            </button>
          </div>

          <div class="modal-body">

            <app-workflow-inlineview [workflow]="viewWorkflowModel"></app-workflow-inlineview>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" (click)="hideViewModal()"><i class="material-icons">close</i></button>

            <button type="button" class="btn btn-success"  *ngIf="isWorkflowModelNotAssigned" (click)="assignWorkflowMe()"><i class="material-icons">assignment_ind</i></button>

            <button type="button" class="btn btn-primary" *ngIf="isWorkflowModelMeAssigned" (click)="editWorkflow()" ><i class="material-icons">pageview</i></button>
          </div>
        </div>
      </div>



    </div>

  </div>
</template>

<script>
export default {
  name: 'AppMessageBar',
  props: {
    msg: String
  }
}
</script>
<style>
@charset "ISO-8859-1";

.message-panel-container {
    height: 170px;
    margin-top: 10px;
    border: 1px solid gray;
    background-color: #fbfbfb;
    position: fixed !important;
    width: 100vw;
    bottom: 30px;
}

.message-panel-toolbar{
	height: 30px;
	background-color: #dddddd;
	padding-top: 2px;
	padding-right: 10px;
}

.message-panel-toolbar span.title{
	padding-left: 20px;
	font-weight: bold;
	font-size: 14px;
}

.message-panel-toolbar .toolbar-button{
	float: right;
}

.message-panel-items-container{
	height: calc(100% - 30px);
	padding: 4px 4px;
	overflow: auto;
}

.message-panel-items-container .message-panel-item{
	height: 33px;
	line-height: 22px;
	margin: 3px 0;
	background-color: #dceefa;
	padding: 5px;
	padding-left: 15px;
	border: 1px solid #d0cfcf;
}

.message-panel-items-container .message-panel-item:hover{
	background-color: #dbfbf0;
}

.message-panel-items-container .message-panel-item a{
	color: gray;
}

.message-panel-items-container .message-panel-item a:hover{
	color: #946b73;
}

.toolbar-image {
    border: none;
    margin-right: 10px;
    width: 24px;
    height: 24px;
    float: right;
}

div.workflowview-dialog-content-container div.item-row {
    padding: 5px 0;
}

</style>