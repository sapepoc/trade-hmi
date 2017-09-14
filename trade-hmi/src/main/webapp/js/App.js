var app = angular.module("sapientApp", ["ngRoute", 'ngCookies','ngSanitize']); 
app.config(function($routeProvider) {
    $routeProvider
    .when("/login", {
        controller: 'LoginController',
        templateUrl: 'js/login/template/login.html'
    })
    .when("/home", {
        controller: 'HomeController',
        templateUrl: 'js/home/template/home.html'
    })
    .when("/welcome", {
        controller: 'WelcomeController',
        templateUrl: 'js/welcome/template/welcome.html'
    })
    .when("/game", {
        controller: 'GameController',
        templateUrl: 'js/game/template/game.html'
    })
    .when("/register", {
        controller: 'RegisterController',
        templateUrl: 'js/register/template/register.html',
    })
    .otherwise({ redirectTo: '/login' });
});

app.run(function($rootScope, $location, $cookieStore, $http){
    // keep user logged in after page refresh
    console.log('Trade Manager App Refreshed ');
    $rootScope.globals = $cookieStore.get('globals') || {};
    console.log('Trade Manager App Refreshed- user '+$rootScope.globals);
    if ($rootScope.globals.currentUser) {
        $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
    }

    $rootScope.$on('$locationChangeStart', function (event, next, current) {
        // redirect to login page if not logged in and trying to access a restricted page
        var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/home', '/welcome','/game']) === -1;
        var loggedIn = $rootScope.globals.currentUser;
        if (restrictedPage && !loggedIn) {
            $location.path('/login');
        }
    });
});