app.directive("loginDirective",  function() {
    return {
     restrict : "E",
	 replace: true,
	 controller: 'LoginController',
         templateUrl: 'js/login/template/login.html'
    };
 });