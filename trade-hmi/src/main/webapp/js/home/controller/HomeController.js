(function(){
	'use strict';
	app.controller("HomeController", function($location, $scope, loginService) {

		$scope.nestedView = false;
    	$scope.trade = function (){
    		$scope.nestedView = true;
    		$scope.directiveName = "trade-directive";
    		//$location.path('/trade');
    	};
    	
	 });
})();

