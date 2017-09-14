package com.sap.service;

import java.util.List;

import com.sap.data.AlertResponse;
import com.sap.data.TradeData;
import com.sap.exception.SapException;

public interface ITradeService {

	/**
	 * The method takes the union list and returns the filtered list after applying rules.
	 * 
	 * @param date
	 * @return
	 * @throws SapException
	 */
//	public List<Trade> fetchAllTradesForDate(String date) throws SapException ;

	public List<TradeData> applyRulesOnDateTrades(String date) throws SapException ;
	
	public AlertResponse applyRulesOnDateTradeList(List<TradeData> allTrades) throws SapException ;

//	public void saveAlertResponse(List<TradeData> allTrades);


}
