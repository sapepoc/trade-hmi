(function(){
	'use strict';
	app.controller("GameController", function($location, $scope, tradeService) {

		var tradeAvailable = false;
		var alertsAvailable = false;
    	$scope.findTrades = function (){
    		
    		//Restart the alerts
    		$scope.alertsAvailable = false;
    		$scope.alerts = [];
    		
    		tradeService.findTrades().then(function (response) {
    			tradeResponseProcess(response);
    		});
    	};
    	
		function tradeResponseProcess (response) {
			if (response.success) {
				$scope.tradeAvailable = true;
				$scope.trades = response.data;
				$scope.headerContents = ['Id','Part1','Party2','Instrument','ExecutionDate','Direction','Rate','Volume','Select'];
			} else {
				flashService.Error(response.message);
				$scope.tradeAvailable = false;
			}
		}
    	
    	$scope.findAlerts = function () {
    		$scope.selectedTrades = [];
    		var selectedTradeIds = [];
    		angular.forEach($scope.trades, function(trade){
    		    if (trade.selected) {
    		    	$scope.selectedTrades.push(trade);
    		    	selectedTradeIds.push(trade.id);
    		    	}
    		  })
    		  tradeService.applyRules(selectedTradeIds).then(function (response) {
    			ruleResponseProcess(response);
    		});
    		
    	};
    	
    	function ruleResponseProcess (response) {
			if (response.success) {
				$scope.alertsAvailable = true;
				if(response.data.length > 0){
					$scope.alerts = response.data;
					$scope.message = 'Winner,Thanks Ninja for making this world a better place';
				} else {
		$scope.message = 'Sorry next time, but I see great potential of you becoming a high Surveillance Ninja';
					$scope.alerts = [];
				}
	    		
			} else {
				flashService.Error(response.message);
				$scope.alertsAvailable = false;
			}
		}
    	
	 });
})();

