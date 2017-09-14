(function () {
	'use strict';

	app.controller("RegisterController", function($location, $scope, userService, flashService) {

		$scope.createUser = function () {
		$scope.dataLoading = true;

			var userDetails = {
					"firstName": $scope.firstName,
					"lastName": $scope.lastName,
					"emailId": $scope.emailId,
					"password": $scope.password,
					"confirmPassword": $scope.confirmPassword,
			}

			userService.createUser(userDetails).then(function (response) {
				createUserResponseProcess(response);
			});

			function createUserResponseProcess (response) {
				if (response.success) {
					if (response.step === 1) {
						$location.path('/login');

					} else
						$location.path('/register');
				} else {
					flashService.Error(response.message);
					$scope.dataLoading = false;
				}
			}
		};

	});

})();

