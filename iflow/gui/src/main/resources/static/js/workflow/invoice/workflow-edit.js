/**
 * 
 */


iflowApp.controller('WorkflowCreateController', function WorkflowTypesController($scope, $http, ShowErrorService) {
	  //$scope.phones = [];
	
	$scope.loadUrl = loadUrl;
	$scope.workflowIdentity = widnt;
	$scope.saveUrl = saveUrl;
	$scope.archiveUrl = archiveUrl;
	$scope.doneUrl = doneUrl;
	$scope.listUrl = listUrl;
	
	$scope.users = [];
	$scope.workflowTypeSteps = [];
	$scope.workflow = {};
	$scope.departments = [];
	
	$scope.showSelectAssign = false;
	
	$scope.workflowCreateRequest = { assigns: []};
	
	$scope.reload = function (){
		
		//alert(JSON.stringify($scope.query)); 

		$scope.users = [];
		$scope.workflow = {};
		$scope.departments = [];
		$scope.workflowCreateRequest = { assigns: []};
			
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.loadUrl + $scope.workflowIdentity,
	    }).then(function successCallback(response) {
	    	
	    	$scope.users = response.data.users;
	    	$scope.workflow = initWorkFlow(response.data.workflow);
	    	$scope.workflowTypeSteps = $scope.workflow.workflowType.steps;
	    	$scope.departments = response.data.departments;
	    	$scope.workflowCreateRequest = response.data.saveRequest;
	    	
	
	    }, function errorCallback(response) {
	        
	        ShowErrorService.showError(response, $scope);
	    });
		
	};	

	$scope.applyUserSelect = function(){
		$scope.workflowCreateRequest.assigns = [];
		
		
		$(".assign-checkbox:checked").each(function(index, item){
			var jitem = $(item);
			
			var type = jitem.data("assigntype") == 'user' ? userAssignType : jitem.data("assigntype") == 'department' ? departmentAssignType : departmentGroupAssignType;
			
			$scope.workflowCreateRequest.assigns.push({itemIdentity: jitem.val(),  itemType: type, title: jitem.data("assigntitle")});
		});
    	$('#assignlistdialog').modal('hide');
	};

	$scope.removeAssign = function(id, type){
		
		$scope.workflowCreateRequest.assigns = $scope.workflowCreateRequest.assigns.filter(function(value, index, arr){

		    return value.itemIdentity != id || value.itemType != type;

		});
    	
	};

	$scope.isItemAssigned = function(id, itype){

		var type = itype == 'user' ? userAssignType : itype == 'department' ? departmentAssignType : departmentGroupAssignType;

		for(o in $scope.workflowCreateRequest.assigns){
			var assign = $scope.workflowCreateRequest.assigns[o];
			
		    if(assign.itemIdentity == id && assign.itemType == type){
		    	return true;
		    }
		}
		
		return false;
    	
	};

	
	$scope.saveWorkflow = function(){
		
		var saveData = angular.copy($scope.workflow);
		
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.saveUrl,
	        data: saveData,
	    }).then(function successCallback(response) {
	    	
	    	alert("saved");
	    	
	    	window.location = $scope.listUrl;
	
	    }, function errorCallback(response) {
	        
	        ShowErrorService.showError(response, $scope);
	    });
	};

	$scope.arhiveWorkflow = function(){
		
		var saveData = angular.copy($scope.workflow);
		
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.archiveUrl,
	        data: saveData,
	    }).then(function successCallback(response) {
	    	
	    	alert("saved");
	    	
	    	window.location = $scope.listUrl;
	
	    }, function errorCallback(response) {
	        
	        ShowErrorService.showError(response, $scope);
	    });
	};

	$scope.makeActionDone = function(id){
		
		var saveData = angular.copy($scope.workflowCreateRequest);
		saveData.currentStepId = id;
		
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.doneUrl,
	        data: saveData,
	    }).then(function successCallback(response) {
	    	
	    	alert("saved");
	    	
	    	window.location = $scope.listUrl;
	
	    }, function errorCallback(response) {
	        
	        ShowErrorService.showError(response, $scope);
	    });
	};
	
	$scope.getUserById = function(id){
		for(o in $scope.users){
    		var user =$scope.users[o];
    		if(user.id == id){
    			return user;
    		}
    	}
		return null;
	};
	
	
	
	
	$scope.getWorkFlowTest = function(){
		return JSON.stringify($scope.workflow);
	};

	
		
	function initWorkFlow(workflow){
		//workflow.assignTo = workflow.assignTo + "";
		
		return workflow;
	};
	
	$scope.listAvaileableSteps = function(){
    	
		var list = []; 
		for(index in $scope.workflowTypeSteps){
			if($scope.workflowTypeSteps[index].stepIndex > $scope.workflow.currentStep.stepIndex){
				list.push($scope.workflowTypeSteps[index]);
			}
		}
		return list;
	}
	
	$scope.reload();
});
