package org.sapient.ruleservice.ruleengine;

import java.util.ArrayList;
import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.Rule;
import org.sapient.ruleservice.rule.RuleResult;
import org.springframework.stereotype.Component;

@Component("javaRuleEngine")
public class JavaRuleEngine implements RuleEngine {

	@Override
	public List<RuleResult> applyRules(final List<Rule> ruleTypes, final List<Trade> businessObjects) {
		List<RuleResult> ruleResults= new ArrayList<>();
		ruleTypes.forEach((rule)->{
			ruleResults.addAll(rule.applyRule(businessObjects));
		});
		return ruleResults;
	}
}
