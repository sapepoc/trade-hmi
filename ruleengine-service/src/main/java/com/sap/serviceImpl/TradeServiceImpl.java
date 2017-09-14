package com.sap.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sap.dao.ITradeDataDao;
import com.sap.data.AlertResponse;
import com.sap.data.AllRulesPredicate;
import com.sap.data.Market;
import com.sap.data.Trade;
import com.sap.data.TradeData;
import com.sap.exception.SapException;
import com.sap.service.IRuleStrategy;
import com.sap.service.IStrategyContext;
import com.sap.service.ITradeService;

@Service("tradeService")
public class TradeServiceImpl implements ITradeService {

	@Autowired
	@Qualifier("tradeDataDao")
	private ITradeDataDao tradeDataDao;

	private List<Trade> tradeList = new ArrayList<>();
	private final Map<String, Market> dateStrMktMap = new HashMap<>();

	@Autowired
	@Qualifier("strategyContext")
	IStrategyContext strategyContext;

	IRuleStrategy iRLargeVolStrategy = new LargeVolRuleStrategyImpl();
	IRuleStrategy iRWashTradeStrategy = new LargeVolRuleStrategyImpl();

	public List<TradeData> fetchAllTrades(String date) throws SapException {
		// tradeList = tradeDataDao.fetchAllTradesForDate("");
//		Trade t1 = (new Trade("1234", 11551, 11, "B", "", "", new Date()));
		return null;
	}

	/**
	 * 
	 * abstractFactory can also be used but If Rules are considered as
	 * behavioural change in furure then strategy is better.
	 * 
	 */
	public List<TradeData> applyRulesOnDateTrades(String date) throws SapException {

		List<TradeData> combinedList = null;
		try {
			List<TradeData> list = fetchAllTrades(date);
			if (list == null || list.isEmpty()) {
				throw new SapException("no records");
			}
			combinedList = new ArrayList<>();

			strategyContext.setTrades(list);

			List<TradeData> largeVolList = strategyContext.applyRuleStrategy(iRLargeVolStrategy);
			combinedList.addAll(largeVolList);

		} catch (Exception e) {
			throw new SapException(e.getMessage());
		}
		return combinedList;
	}

	/**
	 * 
	 * abstractFactory can also be used but If Rules are considered as
	 * behavioural change in furure then strategy is better.
	 * 
	 */
	@Override
	public AlertResponse applyRulesOnDateTradeList(List<TradeData> allTrades) throws SapException {

		List<TradeData> combinedList = null;
		AlertResponse resoponse;
		try {
			combinedList = new ArrayList<>();

			List<TradeData> allExceedingTrades = allTrades.stream()
					.filter(AllRulesPredicate.isVolumeExceedForLargeVolRule()).collect(Collectors.<TradeData>toList());
			combinedList.addAll(allExceedingTrades);

			resoponse = new AlertResponse("Large Volume Violation", combinedList);

		} catch (Exception e) {
			throw new SapException(e.getMessage());
		}
		return resoponse;
	}

	public Trade getById(String TradeId) {
		return getTradeList().stream().filter((Trade) -> Trade.getTradeId().equals(TradeId)).findFirst().get();
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public ITradeDataDao getTradeDataDao() {
		return tradeDataDao;
	}

	public void setTradeDataDao(ITradeDataDao tradeDataDao) {
		this.tradeDataDao = tradeDataDao;
	}
}
