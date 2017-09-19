package org.sapient.ruleservice.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.sapient.ruleservice.RuleEngineService;
import org.sapient.ruleservice.loader.impl.TradeFatory;
import org.sapient.ruleservice.models.Trade;
import org.sapient.ruleservice.rule.RuleResult;
import org.sapient.ruleservice.ruleengine.utils.RandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author hgaur2
 *
 */
@RestController
public class RuleServiceController {

	@Autowired
	@Qualifier("ruleEngineService")
	private RuleEngineService ruleEngineService;

	@Autowired
	private TradeFatory tradeFatory;
	
	@Autowired 
	private RandomNumberGenerator randomNumberGenerator;
	
	@Value(value="${rule.list.size}")
	private String randomNumberSize;
	
	@Value(value="${rule.lower.range}")
	private String randomNumberLowerRange;
	
	@Value(value="${rule.upper.range}")
	private String randomNumberUpperRange;


	@RequestMapping(method = RequestMethod.GET, value = "/trades")
	public @ResponseBody Collection<Trade> trades() {
		
		final Set<Integer> ramdomNumbers = randomNumberGenerator.generateNumbers(
				Integer.valueOf(randomNumberSize), Integer.valueOf(randomNumberLowerRange),
				Integer.valueOf(randomNumberUpperRange));
		
		return tradeFatory.findTrades(ramdomNumbers);	
	} 
	
	@RequestMapping(method = RequestMethod.POST, value = "/applyRules", consumes = "application/json")
	public @ResponseBody List<RuleResult> applyRules(@RequestBody List<String> tradeIds){
		System.out.println("apply rule enterted on trades="+tradeIds);
		return ruleEngineService.runRules(tradeFatory.getTrades(tradeIds));
	}
}
