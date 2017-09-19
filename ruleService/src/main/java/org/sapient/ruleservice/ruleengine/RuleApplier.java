package org.sapient.ruleservice.ruleengine;

import java.util.List;

import org.sapient.ruleservice.models.Trade;

public interface RuleApplier
{
	void applyRule(List<Trade> facts);
}
