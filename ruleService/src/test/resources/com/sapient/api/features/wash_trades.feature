@WashTradeRule @dev
Feature: Wash Trades
	I want to use this template for my feature file

Scenario: Identify wash trades
Given set of trades 
When Wash Trade rule is applied
Then following response is given
	| Type | Client | Security | Volume |
	| Buy  | Sapient1| Airtel1   | 40     | 
	| Buy  | HCL1    | Airtel1   | 50     | 
	And Wash trade event is generated