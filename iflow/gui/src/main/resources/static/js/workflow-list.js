/**
 * 
 */


iflowApp.controller('WorkflowTypesController', function WorkflowTypesController($scope, $http, ShowErrorService) {
	  //$scope.phones = [];
	
	$scope.loadUrl = loadUrl;
	$scope.loadInitialUrl = loadInitialUrl;
	$scope.items = [];
	
	$scope.workflowTypes = [];
	$scope.searchFilter = {};

	
	$scope.reloadInitial = function (){
			
			//alert(JSON.stringify($scope.query)); 
	
		$scope.items = [];
			
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.loadInitialUrl,
	    }).then(function successCallback(response) {
	    	
	    	$scope.workflowTypes = response.data.workflowTypes;
	    	$scope.searchFilter = response.data.newSearchFilter;
	    	
	    	$scope.reload();

	    }, function errorCallback(response) {
	        
	    	ShowErrorService.showError(response, $scope);
	    });
			
	};
	
	
	$scope.reload = function (){
		
		//alert(JSON.stringify($scope.query)); 

		query = angular.copy($scope.searchFilter);
		$scope.items = [];
			
		$http({
	        method : "POST",
	        headers: {
	        	'Content-type': 'application/json; charset=UTF-8',
	        },
	        url : $scope.loadUrl,
	        data : query,
	    }).then(function successCallback(response) {
	
	    	$scope.items = prepareWorkflowList(response.data.list);
	    	
	
	    }, function errorCallback(response) {
	        
	    	ShowErrorService.showError(response, $scope);
	    });
		
	};
	
	
	
	$scope.hasNoArhiveStatus = function(){		
		if($scope.searchFilter.statusList){
			//alert($scope.searchFilter.statusList.indexOf(25));
			return $scope.searchFilter.statusList.indexOf(25) == -1;
		}	
		return false;	
	};
	
	$scope.toggleIsArchive = function(){	
		
		var index = $scope.searchFilter.statusList.indexOf(25);
		if(index == -1){
			//alert($scope.searchFilter.statusList.indexOf(25));
			$scope.searchFilter.statusList.push(25);
		}
		else{
			$scope.searchFilter.statusList.splice(index, 1);
		}
	};
	
	$scope.getDebugStatusList = function(){
		
		return JSON.stringify($scope.searchFilter.statusList);
		
	};
	
	
	function prepareWorkflowList(items){
		
		var newItems = [];
		for(index in items){
			var workflow = items[index];
			//workflow.workflowType = $scope.workflowTypes[workflow.workflowTypeId];
			newItems.push(workflow);
		}
		
		return newItems;
	}
	
	
	
	$scope.reloadInitial();
});
