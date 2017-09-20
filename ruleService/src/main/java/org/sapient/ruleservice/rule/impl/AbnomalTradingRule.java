package org.sapient.ruleservice.rule.impl;

import java.util.ArrayList;
import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.AbstractRule;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.RuleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("abnomalTradingRule")
public class AbnomalTradingRule extends AbstractRule {

	@Value(value="${abnormal.volume.rule.threshold}")
	private String abnormalVolumeThreshold;
	
	public AbnomalTradingRule() {
		super("AbnomalTradingRule", RuleType.JAVA);
	}
	
	public List<RuleResult> applyRule(final List<Trade> trades){
		
		List<RuleResult> ruleResults = new ArrayList<>();
		final double priceThreshold = Double.parseDouble(abnormalVolumeThreshold);
		trades.forEach((trade)->{
			if(trade.getVolume() > priceThreshold){
				ruleResults.add( new RuleResult("AbnomalTradingRule", "AbnomalTradingRule is violated!", trade));
			}
		});
		return ruleResults;
	}
}
