package com.sap.dao;

import java.util.List;

import com.sap.data.Trade;
import com.sap.exception.SapException;

public interface ITradeDataDao {

	public List<Trade> fetchAllTradesForDate(String date) throws SapException ;
	
}
