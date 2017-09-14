package com.sap.service;

import java.util.List;

import com.sap.data.TradeData;
import com.sap.exception.SapException;

public interface IRuleStrategy {


	List<TradeData> applyRuleStrategy(List<TradeData> listTrades) throws SapException;

}
