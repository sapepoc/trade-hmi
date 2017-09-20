package org.sapient;

import java.util.List;

import org.sapient.ruleservice.loader.impl.TradeFatory;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.rule.impl.MarkUpDownRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RuleServiceStarter {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(RuleServiceStarter.class, args);
		TradeFatory tradeFatory = applicationContext.getBean(TradeFatory.class);
		MarkUpDownRule markUpDownRule = applicationContext.getBean(MarkUpDownRule.class);
		List<RuleResult> ruleResults = markUpDownRule.applyRule(tradeFatory.getTrades());
		System.out.println(ruleResults.size());
		System.out.println(markUpDownRule.applyRule(tradeFatory.getTrades()));
	}
}
