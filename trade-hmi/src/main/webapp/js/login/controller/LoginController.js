(function () {
    'use strict';

    app.controller("LoginController", function($location, $scope, loginService, flashService) {

    	$scope.register = function (){
    		loginService.ClearCredentials();
    		$location.path('/register');
    	};

    	$scope.login = function(){
    		$scope.dataLoading = true;
    		loginService.login($scope.customerId, $scope.password).then(function (response) {
    			loginResponseProcess(response);
    		});
    	}

    	function loginResponseProcess(response){
    		if (response.result) {
    				var userData = [{'firstName':response.firstName,'lastName':response.lastName}];
    				loginService.SetCredentials(userData);
    				$location.path('/welcome');
    				//$location.path('/register');
    		} else {
    			flashService.Error(response.message);
    			$scope.dataLoading = false;
    		}

    	}

    });
    
})();
