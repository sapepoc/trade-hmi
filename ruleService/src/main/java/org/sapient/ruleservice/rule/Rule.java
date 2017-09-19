package org.sapient.ruleservice.rule;

import java.util.ArrayList;
import java.util.List;

import org.sapient.ruleservice.models.Trade;

public interface Rule {
	String getId();

	RuleType type();

	default List<RuleResult> applyRule(List<Trade> trades) {
		return new ArrayList<>();
	}
}
