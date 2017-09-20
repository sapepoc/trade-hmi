package org.sapient.ruleservice.loader.impl;

import java.io.File;
import java.nio.file.Files;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.sapient.ruleservice.models.Trade;
import org.sapient.utils.TimeDimension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TradeFatory {
	
	@Value(value="${trade.csv.file.path}")
	private String filePath;
	private Map<String, Trade> tradeMap;
	
	@PostConstruct
	private void loadData() {
		tradeMap = new HashMap<>();
		try {
			List<String> lines = Files.readAllLines(new File(filePath).toPath());
			int i = 0;
			for(String line : lines ){
				if(i != 0){
					final Trade trade = formTrade(line);
					tradeMap.put(trade.getId(), trade);
				}
				i++;
			}
		} catch (Exception e) {
			throw new RuntimeException("Problem in trade data loading...", e);
		}
	}
	
	private Trade formTrade(final String line) throws ParseException{
		Trade trade = new  Trade();
		String[] tokens = line.split(",");
		String id = tokens[0];
		String tradeId = tokens[1];
		double rate = Double.parseDouble(tokens[2]);
		double volume = Double.parseDouble(tokens[3]);
		String instrument = tokens[4];
		String direction = tokens[5];
		Date executionDate = TimeDimension.formatter.parse(tokens[6]);
		
		
		String party1 = tokens[7];
		String party2 = tokens[8];
		
		String inHouse = tokens[9];
		String productId = tokens[10];
		Date selltementDate = TimeDimension.formatter.parse(tokens[11]);
		
		trade.setId(id);
		trade.setTradeId(tradeId);
		trade.setRate(rate);
		trade.setVolume(volume);
		trade.setInstrument(instrument);
		trade.setDirection(direction);
		trade.setExecutionDate(LocalDateTime.ofInstant(executionDate.toInstant(), ZoneId.systemDefault()));
		trade.setParty1(party1);
		trade.setParty2(party2);
		trade.setInHouse(Boolean.valueOf(inHouse));
		trade.setProductId(productId);
		trade.setSettlementDate(selltementDate);
		return trade;
	}
	
	public List<Trade> getTrades(){
		return new ArrayList<>(tradeMap.values());
	}
	
	public List<Trade> getTrades(final Collection<String> ids){
		final List<Trade> trades = new ArrayList<>();
		if(Objects.nonNull(ids)){
			ids.forEach((id)->{
				trades.add(tradeMap.get(id));
			});
		}
		return trades;
	}
	
	public List<Trade> findTrades(final Collection<Integer> ids){
		final List<Trade> trades = new ArrayList<>();
		if(Objects.nonNull(ids)){
			ids.forEach((id)->{
				trades.add(tradeMap.get(String.valueOf(id)));
			});
		}
		return trades;
	}

}
