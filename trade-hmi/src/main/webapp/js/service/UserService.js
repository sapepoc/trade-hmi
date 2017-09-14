app.service('userService',['$http', '$cookieStore', '$rootScope', '$timeout',function($http, $cookieStore, $rootScope, $timeout) {

    this.createUser = function (userDetails) {
    	
    	var userData = JSON.stringify(userDetails);
    	
    	return $http({
    		url: '/UIApp/users',
    		method: 'POST',
    		data: $.param({"command":"CREATE_USER","USER_DETAILS":userData}),
    		headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'}
    	}).then(handleSuccess, handleError('Error creating user'));
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