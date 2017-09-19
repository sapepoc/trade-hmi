package com.sapient.hmi.trade.service;

import java.util.List;

import com.sapient.hmi.dto.RequestEntity;
import com.sapient.hmi.trade.models.RuleResult;
import com.sapient.hmi.trade.models.Trade;

public interface TradeService {
	List<Trade> readAll();

	List<RuleResult> applyRules(final RequestEntity requestEntity);
}
