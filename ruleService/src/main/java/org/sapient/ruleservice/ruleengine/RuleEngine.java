package org.sapient.ruleservice.ruleengine;

import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.Rule;
import org.sapient.ruleservice.rule.RuleResult;

public interface RuleEngine
{
	List<RuleResult> applyRules(final List<Rule> ruleTypes, final List<Trade> businessObjects);
}
