package org.sapient.ruleservice.ruleengine.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.Rule;
import org.sapient.ruleservice.rule.RuleFactory;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.RuleType;
import org.sapient.ruleservice.ruleengine.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RuleEngineManager {

	@Autowired
	@Qualifier("droolsRuleEngine")
	private RuleEngine droolsRuleEngine;
	
	@Autowired
	private RuleFactory ruleFactory;
	
	@Autowired
	@Qualifier("javaRuleEngine")
	private RuleEngine javaRuleEngine;
	
	
	public List<RuleResult> applyRules(final List<Trade> businessObjects, final List<String> ruleIds){
		final List<RuleResult> ruleResults = new ArrayList<RuleResult>();
		
		segregateRules(ruleFactory.getRules(), ruleIds).forEach((ruleType, rules)->{
			if(RuleType.JAVA.equals(ruleType)){
				ruleResults.addAll(javaRuleEngine.applyRules(rules, businessObjects));
			} else {
				ruleResults.addAll(droolsRuleEngine.applyRules(rules, businessObjects));
			}
		});
		return ruleResults;
	}
	
	public List<RuleResult> applyRules(final List<Trade> businessObjects){
		final List<RuleResult> ruleResults = new ArrayList<RuleResult>();
		segregateRules(ruleFactory.getRules()).forEach((ruleType,ruleIds)->{
			if(RuleType.JAVA.equals(ruleType)){
				ruleResults.addAll(javaRuleEngine.applyRules(ruleIds, businessObjects));
			} else {
				ruleResults.addAll(droolsRuleEngine.applyRules(ruleIds, businessObjects));
			}
		});
		return ruleResults;
	}
	
	private Map<RuleType, List<Rule>> segregateRules(final List<Rule> rules, final List<String> ruleIds) {
		final Map<RuleType, List<Rule>> typeWiseRules = new HashMap<>();
		rules.forEach((rule)->{
			if(ruleIds.contains(rule.getId())) {
				
				if( !typeWiseRules.containsKey(rule.type())){
					typeWiseRules.put(rule.type(), new ArrayList<Rule>());
				}
				typeWiseRules.get(rule.type()).add(rule);
				
			}
		});
		return typeWiseRules;
	}
	
	private Map<RuleType, List<Rule>> segregateRules(final List<Rule> rules) {
		final Map<RuleType, List<Rule>> typeWiseRules = new HashMap<>();
		rules.forEach((rule)->{
			if( !typeWiseRules.containsKey(rule.type())){
				typeWiseRules.put(rule.type(), new ArrayList<Rule>());
			}
			typeWiseRules.get(rule.type()).add(rule);
		});
		return typeWiseRules;
	}
}
