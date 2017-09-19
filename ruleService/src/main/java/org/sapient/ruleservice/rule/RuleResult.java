package org.sapient.ruleservice.rule;

public class RuleResult {
	private String ruleName;
	private String message;
	private Object businessObject;

	public RuleResult(String ruleName, String message, Object businessObject) {
		this.ruleName = ruleName;
		this.message = message;
		this.businessObject = businessObject;
	}

	/**
	 * @return the ruleName
	 */
	public String getRuleName() {
		return ruleName;
	}

	/**
	 * @param ruleName
	 *            the ruleName to set
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the businessObject
	 */
	public Object getBusinessObject() {
		return businessObject;
	}

	/**
	 * @param businessObject
	 *            the businessObject to set
	 */
	public void setBusinessObject(Object businessObject) {
		this.businessObject = businessObject;
	}
}
