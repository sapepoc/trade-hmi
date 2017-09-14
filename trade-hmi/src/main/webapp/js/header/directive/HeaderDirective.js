app.directive("headerDirective",  function() {
    return {
     restrict : "E",
	 replace: true,
	 controller: 'HeaderController',
         templateUrl: 'js/header/template/header.html'
    };
 });