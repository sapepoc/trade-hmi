package org.sapient.ruleservice.ruleengine;

import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.Rule;
import org.sapient.ruleservice.rule.RuleResult;
import org.springframework.stereotype.Component;

@Component("droolsRuleEngine")
public class DroolsRuleEngine implements RuleEngine {

	@Override
	public List<RuleResult> applyRules(List<Rule> ruleTypes, List<Trade> businessObjects) {
		
		return null;
	}
}
