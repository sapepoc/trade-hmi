package org.sapient.ruleservice;

import java.util.List;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.RuleResult;

public interface RuleEngineService {

	List<RuleResult> runRules(final List<Trade> businessObjects);

	List<RuleResult> runRules(final List<Trade> businessObjects,
			final List<String> ruleIds);
}
