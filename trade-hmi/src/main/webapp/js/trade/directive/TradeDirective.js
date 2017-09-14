app.directive("tradeDirective",  function() {
    return {
     restrict : "E",
	 replace: true,
	 controller: 'TradeController',
     templateUrl: 'js/trade/template/trade.html'
    };
 });