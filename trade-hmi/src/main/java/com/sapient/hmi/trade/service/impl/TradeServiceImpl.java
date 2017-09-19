package com.sapient.hmi.trade.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sapient.hmi.dto.RequestEntity;
import com.sapient.hmi.trade.models.RuleResult;
import com.sapient.hmi.trade.models.Trade;
import com.sapient.hmi.trade.service.TradeService;

@Component
public class TradeServiceImpl implements TradeService {
	
	@Value("${search.trades.url}")
	private String searchTradesUrl;
	
	@Value("${run.rules.url}")
	private String runRulesUrl;
	
	private RestTemplate restTemplate;
	
	@PostConstruct
	private void init(){
		restTemplate = new RestTemplate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Trade> readAll() {
		final List<Trade> trades = (List<Trade>)restTemplate.getForEntity(searchTradesUrl, List.class).getBody();
		return trades;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RuleResult> applyRules(final RequestEntity requestEntity) {
		return (List<RuleResult>)restTemplate.postForObject(runRulesUrl, requestEntity, List.class);
	}

}
