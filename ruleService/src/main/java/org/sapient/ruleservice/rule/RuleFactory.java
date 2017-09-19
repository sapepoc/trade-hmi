package org.sapient.ruleservice.rule;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.sapient.ruleservice.rule.impl.AbnomalVolumeRule;
import org.sapient.ruleservice.rule.impl.MarkUpDownRule;
import org.sapient.ruleservice.rule.impl.WashTradeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RuleFactory {
	
	@Autowired
	private AbnomalVolumeRule abnomalVolumeRule;
	
	@Autowired
	private WashTradeRule washTradeRule;
	
	@Autowired
	private MarkUpDownRule markUpDownRule;
	
	private List<Rule> rules;
	
	@PostConstruct
	private void init(){
		rules = new ArrayList<Rule>();
		rules.add(abnomalVolumeRule);
		rules.add(washTradeRule);
		rules.add(markUpDownRule);
	}
	
	public List<Rule> getRules(){
		return rules;
	}
}