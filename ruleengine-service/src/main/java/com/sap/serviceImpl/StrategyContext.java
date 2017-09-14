package com.sap.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sap.data.Trade;
import com.sap.data.TradeData;
import com.sap.exception.SapException;
import com.sap.service.IRuleStrategy;
import com.sap.service.IStrategyContext;
import com.sap.service.ITradeService;

@Service
public class StrategyContext implements IStrategyContext{

	@Autowired
	ITradeService tradeService;

	private List<TradeData> trades = null;

	public List<TradeData> applyRuleStrategy(IRuleStrategy ruleStrategy) throws SapException {
			return ruleStrategy.applyRuleStrategy(trades);
		}


	public void setTrades(List<TradeData> trades) {
		this.trades = trades;
	}
}
