package com.sap.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sap.data.AllRulesPredicate;
import com.sap.data.Trade;
import com.sap.data.TradeData;
import com.sap.exception.SapException;
import com.sap.service.IRuleStrategy;

@Service("largeVolRuleStrategyImpl")
public class LargeVolRuleStrategyImpl implements IRuleStrategy {

	@Value("${trade.largeVolumeRule.Limit: 1000}")
	private int volumeLimitValue = 1000; // TODO implement @value logic

	
	@Override
	public List<TradeData> applyRuleStrategy(List<TradeData> listTrades) throws SapException {

		if (listTrades == null || listTrades.isEmpty()) {
			throw new SapException("Null param");
		}
		List<TradeData> allExceedingTrades = listTrades.stream()
				.filter(AllRulesPredicate.isVolumeExceedForLargeVolRule())
				.collect(Collectors.<TradeData> toList());
		
		return allExceedingTrades;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	// Predicate<Trade> largeVolRule = (trade) -> trade.getVolume() > 1000 ;

		/*@Override
		public List<Trade> applyRuleStrategy(List<Trade> listTrades) throws SapException {

			if (listTrades == null || listTrades.isEmpty()) {
				throw new SapException("Null param");
			}
			List<Trade> allExceedingTrades = listTrades.stream()
					.filter(AllRulesPredicate.isVolumeExceedForLargeVolRule())
					.collect(Collectors.<Trade> toList());
			
			return allExceedingTrades;
		}*/
		
}
