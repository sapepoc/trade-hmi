package org.sapient.ruleservice.rule.impl;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.AbstractRule;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.RuleType;
import org.sapient.utils.TimeDimension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("washTradeRule")
public class WashTradeRule extends AbstractRule {


	@Value(value="${washtrade.duration.threshold.inseconds: 5}")
	private Integer washtradeRuleDurationThreshold;
	
	public WashTradeRule() {
		super("WashTradeRule", RuleType.JAVA);
	}

	@Override
	public List<RuleResult> applyRule(final List<Trade> trades) {

		List<RuleResult> ruleResults = new ArrayList<>();
		List<String> idsToSkip = new ArrayList<>();
		for (int i = 0; i < trades.size() - 1; i++) {
			if (idsToSkip.contains(trades.get(i).getId())) {
				continue;
			}
			Trade trade1 = trades.get(i);
			for (int j = i + 1; j < trades.size(); j++) {
				if (idsToSkip.contains(trades.get(i).getId())) {
					continue;
				}
				Trade trade2 = trades.get(j);
				if (trade1.getVolume() == trade2.getVolume() && trade1.getParty2().equalsIgnoreCase(trade2.getParty2())
						&& trade1.getRate() == trade2.getRate()
						&& trade1.getInstrument().equalsIgnoreCase(trade2.getInstrument())
						// && trade1.getProductID() == trade2.getProductID()
						// <<<<product id is not in the bean
						&& !(trade1.getDirection().equalsIgnoreCase(trade2.getDirection()))
						&& TimeDimension.getDifferenceInTime(trade1.getExecutionDate(), trade2.getExecutionDate(),
								TimeDimension.TimeType.SECONDS.getType()) < washtradeRuleDurationThreshold) {
					ruleResults.add(new RuleResult("WashTradeRule", "WashTradeRule is violated!", trade1));
					ruleResults.add(new RuleResult("WashTradeRule", "WashTradeRule is violated!", trade2));
					idsToSkip.add(trade1.getId());
					idsToSkip.add(trade2.getId());
					break;

				}
			}
		}

		return ruleResults;
	}

	

}
