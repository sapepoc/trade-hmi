package org.sapient.ruleservice.rule.impl;

import java.util.ArrayList;
import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.AbstractRule;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.RuleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("abnomalVolumeRule")
public class AbnomalVolumeRule extends AbstractRule {

	@Value(value="${abnormal.volume.rule.threshold}")
	private String abnormalVolumeThreshold;
	
	public AbnomalVolumeRule() {
		super("AbnomalVolume", RuleType.JAVA);
	}
	
	public List<RuleResult> applyRule(final List<Trade> trades){
		
		List<RuleResult> ruleResults = new ArrayList<>();
		final double priceThreshold = Double.parseDouble(abnormalVolumeThreshold);
		trades.forEach((trade)->{
			if(trade.getVolume() > priceThreshold){
				ruleResults.add( new RuleResult("AbnomalVolumeRule", "AbnomalVolumeRule is violated!", trade));
			}
		});
		return ruleResults;
	}
}
