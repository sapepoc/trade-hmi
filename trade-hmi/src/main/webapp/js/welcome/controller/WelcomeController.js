(function(){
	'use strict';
	app.controller("WelcomeController", function($location, $scope, loginService) {

    	$scope.startGame = function (){
    		$scope.nestedView = true;
    		$scope.directiveName = "trade-directive";
    		$location.path('/game');
    	};
    	
	 });
})();

