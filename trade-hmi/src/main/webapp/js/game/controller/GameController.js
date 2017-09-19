(function() {
	'use strict';
	app
			.controller(
					"GameController",
					function($location, $scope, tradeService, $rootScope,
							$timeout) {

						$scope.showingMoreText = false;
						var selectedId = 0;
						var selectedIdss = new Array();
						
						$scope.toggleText = function() {
							$scope.showingMoreText = !$scope.showingMoreText;
							$timeout(function() {
								$rootScope.$broadcast("layout", function() {
								});
							}, 20);
						}

						var tradeAvailable = false;
						var alertsAvailable = false;
						$scope.findTrades = function() {

							// Restart the alerts
							$scope.alertsAvailable = false;
							$scope.alerts = [];
							selectedIdss = new Array();
							tradeService.findTrades().then(function(response) {
								document.write('findTrades');
								tradeResponseProcess(response);
							});
						};

						function tradeResponseProcess(response) {
							if (response.success) {
								$scope.tradeAvailable = true;
								$scope.trades = response.data;

								var tradeArr = $scope.trades;
								var myJsonString = JSON.stringify(tradeArr);
								var jsonArg1 = new Object();
								jsonArg1.name = 'template';
								jsonArg1.value = "app/partials/work1.html";

								myJsonString = myJsonString
										.replace(/instrument/g,
												'template\":\"app/partials/work1.html\", \"instrument');

								$scope.cards = JSON.parse(myJsonString);

								$scope.headerContents = [ 'Id', 'Part1',
										'Party2', 'Instrument',
										'ExecutionDate', 'Direction', 'Rate',
										'Volume', 'Select' ];

								$scope.deleteCard = function(id) {
									alert(id);
									var index = -1;
									var i;
									for (i in $scope.cards) {
										if ($scope.cards[i].id == id) {
											index = i;
											break;
										}
									}
									if (index !== -1) {
										$scope.cards.splice(index, 1);
									}
								}
								$scope.filteredItems = $scope.cards;
								$scope.removeFirstCard = function() {
									$scope
											.deleteCard($scope.filteredItems[0].id)
								}

								$scope.filters = [ [ [ 'tabs', 'contains',
										'home' ] ] ];
								$scope.rankers = null;

								$scope.isDropdownOpen = {
									orderBy : false,
									filter : false
								};

								var cardsToAdd = [ {
									id : 9,
									template : "app/partials/work1.html",
									tabs : [ "home", "work" ],
									added : 1474871272105,
								}, {
									id : 10,
									template : "app/partials/work4.html",
									tabs : [ "home", "work" ],
									added : 1467871272105,
								}, {
									id : 11,
									template : "app/partials/education.html",
									tabs : [ "home", "education" ],
									data : {
										"degree" : "PhD",
										"field" : "Artificial Intelligence",
										"school" : "MIT"
									},
									added : 1479871272105
								} ];

								/**
								 * Add a card to the main view Takes a card from
								 * the pile of cardsToAdd and prepend it to the
								 * list of cards. Take a card belonging to the
								 * selected tab
								 */
								$scope.addCard = function() {
									var getCurrentTab = function() {
										return $scope.filters[0][0][2];
									};

									var getIndexOfNextCardInTab = function(tab) {
										var index = -1;
										var i;
										for (i in cardsToAdd) {
											if (cardsToAdd[i].tabs.indexOf(tab) !== -1) {
												index = i;
												break;
											}
										}
										return index;
									};

									var index = getIndexOfNextCardInTab(getCurrentTab());

									if (index !== -1) {
										$scope.cards.unshift(cardsToAdd[index]);
										cardsToAdd.splice(index, 1);
									}
								}

							} else {
								flashService.Error(response.message);
								$scope.tradeAvailable = false;
							}
						}

						$scope.selectedIds = "";
						$scope.selectCard = function(id) {
							if (selectedIdss.indexOf(id) > -1) {
								selectedId -= 1;
								$('#card' + id)
										.css("background-color", "white");
								selectedIdss.pop(id);
							} else {
								selectedId += 1;
								$('#card' + id)
										.css("background-color", "lightgreen");
								selectedIdss.push(id);
							}
							$scope.selectedBusinessIds = selectedIdss;
						}
						$scope.findAlerts = function() {
							var selectedTradeIds = $scope.selectedBusinessIds;
							
							tradeService.applyRules(selectedTradeIds).then(
									function(response) {
										ruleResponseProcess(response);
									});

						};

						function ruleResponseProcess(response) {
							if (response.success) {
								$scope.alertsAvailable = true;
								if (response.data.length > 0) {
									$scope.alerts = response.data;
									$scope.message = 'Winner,Thanks Ninja for making this world a better place';
								} else {
									$scope.message = 'Sorry next time, but I see great potential of you becoming a high Surveillance Ninja';
									$scope.alerts = [];
								}

							} else {
								flashService.Error(response.message);
								$scope.alertsAvailable = false;
							}
						}

					});
})();
