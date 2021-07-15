<template>
  <div class="message-panel-top">
    <vue-resizable :width="100vw" :height="170" :active="['t']" @resize:end="onResizeEnd">
      <div class="message-panel-container" id="message-panel-container" v-if="isAppLogged" v-bind:style="{'height' : messagePanelHeightStyle}">
        <div class="message-panel-toolbar">
          <span class="title">Meldungen</span> &nbsp; &nbsp;
          <span v-bind:style="{'color':subscribed ? 'green' : 'red' }">{{status}}</span>
          <button class="toolbar-button" v-if="messagePanelShowed" @click="closeMessages"><i class="material-icons">keyboard_arrow_down</i></button>
          <button class="toolbar-button" v-if="messagePanelShowed == false" @click="showMessages"><i class="material-icons">keyboard_arrow_up</i></button>
          <button class="toolbar-button" v-if="messagePanelShowed" @click="reloadMessages"><i class="material-icons">refresh</i></button>
          <img class="toolbar-image" v-if="isReloadingMessages" src="assets/images/loading200.gif" />

        </div>
        <div class="message-panel-items-container">
          <div class="message-panel-item" v-for="message in messages" :key="message.id">
            <a href="javascript:void(0);" @click="showWorkflowView(message.workflowId)">
              <div>{{message.message}} ({{message.workflow.workflowType.title}}) ({{message.createdAtString}}) ({{message.remainingDays}}) ({{message.status}})</div>
            </a>
          </div>
        </div>

      </div>

    </vue-resizable>

    <div class="modal fade show" tabindex="-1" v-if="viewWorkflow" id="viewworkflowedialog" role="dialog">

      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title dialog-title" id="errorMessagedialogTitle">{{ 'common.view-workflow' | translate }}</h5>
            <button class="dialog-toolbar-button close" @click="hideViewModal" aria-label="Close">
              <i class="material-icons">close</i>
            </button>
          </div>

          <div class="modal-body">

            <app-workflow-inlineview [workflow]="viewWorkflowModel"></app-workflow-inlineview>

          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="hideViewModal"><i class="material-icons">close</i></button>

            <button type="button" class="btn btn-success"  v-if="isWorkflowModelNotAssigned" @click="assignWorkflowMe"><i class="material-icons">assignment_ind</i></button>

            <button type="button" class="btn btn-primary" v-if="isWorkflowModelMeAssigned" @click="editWorkflow" ><i class="material-icons">pageview</i></button>
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
    isAppLogged: {
      type: Boolean,
      default: true
    },
    subscribed: {
      type: Boolean,
      default: true
    },
    status: {
      type: String,
      default: "Not Connected"
    },
    messages: {
      type: Array,
      default() {
        return []
      }
    },
    messagePanelShowed: Boolean,
    isReloadingMessages: Boolean,
    viewWorkflow: Object,
    messagePanelHeight: {
      type: Number,
      default: 170
    },

  },
  computed: {
    isWorkflowModelMeAssigned: function(){
      return false
    },
    isWorkflowModelNotAssigned: function(){
      return false
    },
    messagePanelHeightStyle: function(){
      return this.messagePanelHeight + "px";
    }
  },
  methods: {
    showMessages(){
      this.messagePanelHeightStyle = this.messagePanelHeight + "px";
	  this.messagePanelShowed = true;
    },
    closeMessages(){
	  this.messagePanelHeightStyle = "25px";
	  this.messagePanelShowed = false;
    },
    reloadMessages(){

    },
    showWorkflowView(workflow){
      console.log(workflow)
    },
    hideViewModal(){

    },
    editWorkflow(){

    },
    onResizeEnd(event) {
	  if(event.rectangle && event.rectangle.height){
	    this.messagePanelHeight = event.rectangle.height;
	    this.showMessages();
	  }
	}
  }
}
</script>
<style>

.message-panel-top{
    height: 170px;
    margin-top: 10px;
    width: 100vw;
    bottom: 30px;
    position: fixed !important;
}

.message-panel-container {
    height: 100%;
    margin-top: 10px;
    border: 1px solid gray;
    background-color: #fbfbfb;

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