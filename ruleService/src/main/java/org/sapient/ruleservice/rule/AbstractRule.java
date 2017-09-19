package org.sapient.ruleservice.rule;

public class AbstractRule implements Rule {

	private String id;
	private RuleType type;

	public AbstractRule(final String id, final RuleType type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public RuleType type() {
		return type;
	}

}
