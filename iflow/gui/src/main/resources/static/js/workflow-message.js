


iflowApp.controller('WorkflowMessageController', function ($scope, $http, ShowErrorService) {

	$scope.loadMessageUrl = typeof loadMessageUrl == undefined ? false : loadMessageUrl;
	$scope.assignWorkflowUrl = typeof assignWorkflowUrl == undefined ? false : assignWorkflowUrl;
	
	$scope.messagePanelHeight = 170;
	
	$scope.messages = [];

	$scope.viewWorkflow = {};

	$scope.messagePanelShowed = true;

	$scope.messageSearchInterval = 60000;

	
	$scope.closeMessages = function(){
		$('#message-panel-container').height(25);
		$scope.messagePanelShowed = false;
    };

	$scope.showMessages = function(){
		$('#message-panel-container').height($scope.messagePanelHeight);
		$scope.messagePanelShowed = true;
    };

    $scope.$on("angular-resizable.resizeEnd", function(event, args) {        
    	
        if (args.height && args.id == 'message-panel-container'){
        	$scope.messagePanelHeight = args.height;
        }
        	
    });
    
    
	$scope.reloadMessages = function (resetCach){
		
		//alert($scope.loadMessageUrl); 

		$scope.messages = [];
		
			
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.loadMessageUrl + "?reset=" + (resetCach ? "1" : "0"),
	    }).then(function successCallback(response) {
	    	
	    	buildMessageList(response.data);
	    	
	    	setTimeout(function(){ 
	    		$scope.reloadMessages();
	    	}, $scope.messageSearchInterval);
	
	    }, function errorCallback(response) {
	        
	        ShowErrorService.showError(response, $scope);
	    });
		
	}; 
    
	$scope.showWorkflowView = function (workflowId){
				
		$scope.viewWorkflow = {};
		
		for(o in $scope.messages){
			if($scope.messages[o].workflowId == workflowId){
				$scope.viewWorkflow = $scope.messages[o].workflow;
				$('#viewworkflowdialog').modal({backdrop: true, })
				break;
			}
		}
			
		
		
	}; 

    
	$scope.assignWorkflowMe = function (workflowId){
		
		//alert($scope.loadMessageUrl); 

		$scope.messages = [];
		
			
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.assignWorkflowUrl + workflowId,
	    }).then(function successCallback(response) {
	    	
	    	
	    	$('#viewworkflowdialog').modal('hide');
	    	
	
	    }, function errorCallback(response) {
	        
	        ShowErrorService.showError(response, $scope);
	    });
		
	}; 

	
	function buildMessageList(messages){
		for(o in messages){
			var message = messages[o];
			$scope.messages.push(message);
		}
	}
	

	
	$scope.reloadMessages();

});
