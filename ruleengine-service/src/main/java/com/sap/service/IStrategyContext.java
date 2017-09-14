package com.sap.service;

import java.util.List;

import com.sap.data.Trade;
import com.sap.data.TradeData;
import com.sap.exception.SapException;

public interface IStrategyContext {

	public void setTrades(List<TradeData> trades) ;

	public List<TradeData> applyRuleStrategy(IRuleStrategy ruleStrategy) throws SapException;
}
