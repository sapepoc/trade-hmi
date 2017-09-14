app.service('loginService',['$http', '$cookieStore', '$rootScope', '$timeout',function($http, $cookieStore, $rootScope, $timeout) {

    this.login = function (customerId, password) {
    	return $http({
    		url: '/authenticate',
    		method: 'POST',
    		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8', 
    			'customerId':customerId,
    			'password':password}
    	}).then(handleSuccess, handleError('Error in authentication'));
    }

	function handleSuccess(res) {
		return res.data;
	}

	function handleError(error) {
			return function () {
				return { success: false, message: error };
			};
	}
    
	this.SetCredentials = function (userdata) {
        $rootScope.globals = {
            currentUser: {
            	firstname:userdata.firstName,
            	lastname:userdata.lastName
            }
        };
        $http.defaults.headers.common['Authorization'] = 'Basic ' + userdata.authdata; // jshint ignore:line
        $cookieStore.put('globals', $rootScope.globals);
    }
    
	this.ClearCredentials = function () {
        console.log('clear cookie get called');
        $rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = 'Basic ';
    }
	
 }

]);