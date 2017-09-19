package org.sapient.ruleservice.impl;

import java.util.List;

import org.sapient.ruleservice.RuleEngineService;
import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.ruleengine.impl.RuleEngineManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ruleEngineService")
public class RuleEngineServiceImpl implements RuleEngineService {

	@Autowired
	private RuleEngineManager ruleEngineManager;
	
	@Override
	public List<RuleResult> runRules(List<Trade> businessObjects) {
		return ruleEngineManager.applyRules(businessObjects);
	}

	@Override
	public List<RuleResult> runRules(final List<Trade> businessObjects,
			final List<String> ruleIds) {
		return ruleEngineManager.applyRules(businessObjects);
	}

}
