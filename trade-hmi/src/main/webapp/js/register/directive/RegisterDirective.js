app.directive("registerDirective",  function() {
    return {
     restrict : "E",
	 replace: true,
	 controller: 'RegisterController',
         templateUrl: 'js/header/template/register.html'
    };
 });