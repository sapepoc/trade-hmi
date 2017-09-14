app.service('tradeService',['$http', '$cookieStore', '$rootScope', '$timeout',function($http, $cookieStore, $rootScope, $timeout) {

    this.trade = function (tradeDetails) {
    	
    	var tradeData = JSON.stringify(tradeDetails);
    	
    	return $http({
    		url: '/UIApp/trade',
    		method: 'POST',
    		data: $.param({"command":"TRADE","TRADE_DETAILS":tradeData}),
    		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
    	}).then(handleSuccess, handleError('Error in trading'));
    }
    

    this.findTrades = function () {
    	return $http({
    		url: '/trades',
    		method: 'GET'
    	}).then(handleSuccess, handleError('Error in trading'));
    }
    
    this.applyRules = function (tradeIds) {
    	return $http({
    		url: '/applyRules',
    		method: 'POST',
    		data:tradeIds,
    		headers: {'Content-Type': 'application/json;charset=utf-8'}
    	}).then(handleSuccess, handleError('Error in applying rule'));
    }
    
	function handleSuccess(res) {
		return res.data;
	}

	function handleError(error) {
			return function () {
				return { success: false, message: error };
			};
	}

 }

]);