app.controller("HeaderController", function($scope, $location, loginService) {

	$scope.logout= function(){
		loginService.ClearCredentials();
        $location.path('/login');
	}
	
 });
