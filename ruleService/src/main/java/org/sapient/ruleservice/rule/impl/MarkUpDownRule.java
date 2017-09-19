package org.sapient.ruleservice.rule.impl;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.AbstractRule;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.RuleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("markUpDownRule")
public class MarkUpDownRule extends AbstractRule {


	@Value(value="${markupdown.partypercentage: 1}")
	private Integer thresoldPercentage;
	
	@Value(value="${markupdown.timethresoldinmins: 1}")
	private Integer timeThresoldInMins;
	
	public MarkUpDownRule() {
		super("MarkUpDownRule", RuleType.JAVA);
	}

	@Override
	public List<RuleResult> applyRule(final List<Trade> trades) {

		List<RuleResult> ruleResults = new ArrayList<>();

		final List<Trade> inhouseTrades = new ArrayList<>();
		final List<Trade> clientTrades = new ArrayList<>();
		trades.forEach((trade) -> {
			if (trade.isInHouse()) {
				inhouseTrades.add(trade);
			} else {
				clientTrades.add(trade);
			}
		});
		
		inhouseTrades.forEach((inhouseTrade)->{
			
			clientTrades.forEach((clientTrade)->{
				
				if(clientTrade.getInstrument().equals(inhouseTrade.getInstrument())
						&& clientTrade.getExecutionDate().equals(inhouseTrade.getSettlementDate())
						&& clientTrade.getProductId().equals(inhouseTrade.getProductId())
						&& clientTrade.getVolume() == inhouseTrade.getVolume()
						&& !clientTrade.getDirection().equals(inhouseTrade.getDirection())){
					
					
					long executionTimeDiff = Math.abs(
							ChronoUnit.MINUTES.between(inhouseTrade.getExecutionDate(), 
									clientTrade.getExecutionDate()));
					
					boolean isMarkDownAlertRaised = false;
					double partyPercentage = 0;
					if(executionTimeDiff < timeThresoldInMins){

						List<Trade> captureTrades = new ArrayList<>();
						captureTrades.add(clientTrade);
						captureTrades.add(inhouseTrade);
						
						if("BUY".equalsIgnoreCase(inhouseTrade.getDirection()) 
								&& "SELL".equalsIgnoreCase(clientTrade.getDirection())){
							
							if((clientTrade.getRate() - inhouseTrade.getRate()) < 0){

								double markDownPerc = ((inhouseTrade.getRate() - clientTrade.getRate()) / clientTrade.getRate())*100;
								if( Math.abs(markDownPerc) > thresoldPercentage){
									isMarkDownAlertRaised = true;
								}
							} else if((clientTrade.getRate() - inhouseTrade.getRate()) > 0){
								partyPercentage = ((clientTrade.getRate() - inhouseTrade.getRate()) / inhouseTrade.getRate())*100;
							}
						}
						else if("SELL".equalsIgnoreCase(inhouseTrade.getDirection()) 
								&& "BUY".equalsIgnoreCase(clientTrade.getDirection())) {
							
							if((inhouseTrade.getRate() - clientTrade.getRate()) < 0){

								double markDownPerc = ((clientTrade.getRate() - inhouseTrade.getRate()) / inhouseTrade.getRate())*100;
								if( Math.abs(markDownPerc) > thresoldPercentage){
									isMarkDownAlertRaised = true;
								}
							} else if((inhouseTrade.getRate() - clientTrade.getRate()) > 0){
								partyPercentage = ((inhouseTrade.getRate() - clientTrade.getRate()) / inhouseTrade.getRate())*100;
							}
						}
						
						
						if(partyPercentage > thresoldPercentage){
							
							ruleResults.add(new RuleResult("Unusual Markdownp detected for "+ inhouseTrade.getInstrument(), 
									"Markup trade rule-violated!- partypercentage="+partyPercentage, captureTrades));
						}
						
						if(isMarkDownAlertRaised){
							
							ruleResults.add(new RuleResult("Unusual Markdown detected for "+ inhouseTrade.getInstrument(), 
									"Markdown trade rule-violated!- partypercentage="+partyPercentage, captureTrades));
						}
						
					}
					
				}
			});
		});

		return ruleResults;
	}

	

}
