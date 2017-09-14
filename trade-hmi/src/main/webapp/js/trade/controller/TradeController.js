(function () {
	'use strict';

	app.controller("TradeController", function($location, $scope, tradeService, flashService) {
		$scope.tradeTypes = {
        		type: 'BUY',
        	    availableOptions: [
        	      {id: 'BUY', name: 'BUY'},
        	      {id: 'SELL', name: 'SELL'}
        	    ],
        	   };
		
		$scope.tradeCompanies = {
        		name: null,
        	    availableOptions: [
        	      {id: 'AIRTEL', name: 'AIRTEL'},
        	      {id: 'NOIKA', name: 'NOIKA'},
        	      {id: 'VODAFONE', name: 'VODAFONE'},
        	      {id: 'IDEA', name: 'IDEA'},
        	      {id: 'RELIENCE', name: 'RELIENCE'}
        	    ],
        	   };


		$scope.trade = function () {
		$scope.dataLoading = true;
			var tradeDetails = {
					"bankAccountNumber": $scope.bankAccountId,
					"tradeAccountNumber": $scope.tradeAccountId,
					"companyName": $scope.tradeCompanies.name,
					"stockName": $scope.stockName,
					"tradeType": $scope.tradeTypes.type,
					"stockNumber": $scope.stockNumber,
			}

			tradeService.trade(tradeDetails).then(function (response) {
				tradeResponseProcess(response);
			});

			function tradeResponseProcess (response) {
				if (response.success) {
					if (response.step === 1) {
						$location.path('/home');

					} else
						$location.path('/home');
				} else {
					flashService.Error(response.message);
					$scope.dataLoading = false;
				}
			}
		};

	});

})();

