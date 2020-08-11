/**
 * 
 */

iflowApp.directive('fileModel', [ '$parse', function($parse) {
    return {
        restrict : 'A',
        link : function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;

            element.bind('change', function() {
                scope.$apply(function() {
                   	
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
} ]);

iflowApp.controller('WorkflowCreateController', function WorkflowCreateController($scope, $http, ShowErrorService) {
	  //$scope.phones = [];
	
	$scope.loadUrl = loadUrl;
	$scope.saveUrl = saveUrl;
	$scope.saveFileUrl = saveFileUrl;
	$scope.listUrl = listUrl;
	
	$scope.workflowTypes = [];
	$scope.users = [];
	$scope.departments = [];
	$scope.workflowCreateRequest = {};
	$scope.fileTitles = [];
	$scope.validation = {};
		
	$('input.select-date').datepicker({
	    showButtonPanel: true,
	    selectOtherMonths: true,
	    changeMonth: true,
	    changeYear: true,
	    dateFormat: "dd.mm.yy"
	}, $.datepicker.regional[ "de" ]);
	
	$scope.reload = function (){
		
		//alert(JSON.stringify($scope.query)); 

		$scope.workflowTypes = [];
		$scope.users = [];
		$scope.departments = [];
		$scope.workflowCreateRequest = {};
			
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.loadUrl,
	    }).then(function successCallback(response) {
	    	
	    	$scope.workflowTypes = response.data.workflowTypes;
	    	$scope.workflowCreateRequest = initWorkFlow(response.data.workflowCreateRequest);
	    	$scope.users = response.data.users;
	    	$scope.departments = response.data.departments;
	
	    }, function errorCallback(response) {
	        
	    	ShowErrorService.showError(response, $scope);
	    });
		
	};

	$scope.save = function (){
		
		
		
		var formData = new FormData();
		
		for (var i = 0; i < $scope.fileTitles.length; i++) {
		    formData.append('files', $scope.fileTitles[i].file);
		    formData.append('titles', $scope.fileTitles[i].title);
		    formData.append('wids', i);
		}
				
		$http.post($scope.saveFileUrl, formData,{
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }})
            .then(
                function (response) {
                	//alert( response.data.sessionKey);
                	//alert( response.data.titles);
                	
                	$scope.workflowCreateRequest.sessionKey = response.data.sessionKey;
                	
                	$http({
            	        method : "POST",
            	        headers: {
            	        	'Content-type': 'application/json; charset=UTF-8',
            	        },
            	        url : $scope.saveUrl,
            	        data: $scope.workflowCreateRequest,
            	    }).then(function successCallback(response) {
            	    	
            	    	alert("saved");
            	    	
            	    	window.location = $scope.listUrl;
            	
            	    }, function errorCallback(response) {            	    	
            	    	ShowErrorService.showError(response, $scope);
            	    });
                	
                },
                function (errResponse) {
        	    	ShowErrorService.showError(response, $scope);
                }
            );
		
		
	};
	
	$scope.addFile = function (){
		
		$scope.fileTitles.push({title:'', file:false});
		
	};
	
	$scope.removeFile = function (index){
		
		$scope.fileTitles.splice(index, 1);
		
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
	
	$scope.getWorkflowTypeSteps = function(){
				
		if($scope.workflowCreateRequest.workflow && $scope.workflowCreateRequest.workflow.workflowTypeId != 0){
			for(index in $scope.workflowTypes){
				if($scope.workflowTypes[index].id == $scope.workflowCreateRequest.workflow.workflowTypeId){
					return $scope.workflowTypes[index].steps;
				}
			}
		}
		
		return [];
	};
	
	$scope.hasNoAssigns = function(){
		if($scope.workflowCreateRequest && $scope.workflowCreateRequest.assigns){
			return $scope.workflowCreateRequest.assigns.length == 0;
		}
		return false;
		
	};

	$scope.getUserById = function(identity){
		for(o in $scope.users){
    		var user =$scope.users[o];
    		if(user.identity == identity){
    			return user;
    		}
    	}
		return null;
	};

	
	$scope.testUpload = function (){
		
		//var file = $("#myFile")[0];
		//var file = $scope.myfile;
		
		var formData = new FormData();
		
		for (var i = 0; i < $scope.fileTitles.length; i++) {
		    formData.append('files', $scope.fileTitles[i].file);
		    formData.append('titles', $scope.fileTitles[i].title);
		    formData.append('wids', i);
		}
		
		//formData.append('file', file);
        //formData.append('data', JSON.stringify($scope.workflowCreateRequest));
     
		//alert(JSON.stringify(formData));
		
		$http.post($scope.saveFileUrl, formData,{
            transformRequest : angular.identity,
            headers : {
                'Content-Type' : undefined
            }})
            .then(
                function (response) {
                	alert( response.data.sessionKey);
                	alert( response.data.titles);
                },
                function (errResponse) {
                	alert( errResponse.data);
                }
            );
 		
		
	};
	
	
		
	function initWorkFlow(workflowReq){
		workflowReq.workflow.workflowTypeId = workflowReq.workflow.workflowTypeId + "";
		workflowReq.workflow.controller = workflowReq.workflow.controller + "";
		workflowReq.workflow.assignTo = workflowReq.workflow.assignTo + "";
		workflowReq.workflow.workflowTypeId = workflowReq.workflow.workflowTypeId + "";
		workflowReq.workflow.currentStepId = workflowReq.workflow.currentStepId + "";
		workflowReq.assigns = [];
		
		return workflowReq;
	};
	
	$scope.addFile();
	$scope.reload();
});
