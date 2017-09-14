package com.sap.data;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Value;

import com.sap.helper.Helper;

public class AllRulesPredicate {

	@Value("trade.washtradeRule.Limit")
	private static int volumeLimitValue = 1000; // TODO implement @value logic

	public static Predicate<TradeData> isVolumeExceedForLargeVolRule() {
		return (trade) -> trade.getVolume() > volumeLimitValue;
	}

	public static  BiPredicate<Trade, Trade> isVolumeExceedForWashRulee() {
		return (trade1, trade2) -> trade1.getVolume() == trade2.getVolume()
				&& trade1.getDirection().equalsIgnoreCase(trade2.getDirection()) &&
				Helper.getDifferenceInMins(trade1.getExecutionDate(), trade2.getExecutionDate()) > 5;
	}
	
	public static Predicate<Trade> isVolumeExceedForWashRule() {
		return (trade) -> trade.getVolume() > volumeLimitValue;
	}

}