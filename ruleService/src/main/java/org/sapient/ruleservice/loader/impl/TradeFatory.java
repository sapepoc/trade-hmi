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
		String tradeId = tokens[0];
		double rate = Double.parseDouble(tokens[1]);
		double volume = Double.parseDouble(tokens[2]);
		String instrument = tokens[3];
		String direction = tokens[4];
		Date executionDate = TimeDimension.formatter.parse(tokens[5]);
		
		
		String party1 = tokens[6];
		String party2 = tokens[7];
		
		trade.setId(tradeId);
		trade.setRate(rate);
		trade.setVolume(volume);
		trade.setInstrument(instrument);
		trade.setDirection(direction);
		trade.setExecutionDate(LocalDateTime.ofInstant(executionDate.toInstant(), ZoneId.systemDefault()));
		trade.setParty1(party1);
		trade.setParty2(party2);
		return trade;
	}
	
	public List<Trade> getTrades(){
		return new ArrayList<>(tradeMap.values());
	}
	
	public List<Trade> getTrades(final Collection<String> ids){
		final List<Trade> trades = new ArrayList<>();
		ids.forEach((id)->{
			trades.add(tradeMap.get(id));
		});
		return trades;
	}
	
	public List<Trade> findTrades(final Collection<Integer> ids){
		final List<Trade> trades = new ArrayList<>();
		ids.forEach((id)->{
			trades.add(tradeMap.get(String.valueOf(id)));
		});
		return trades;
	}

}
