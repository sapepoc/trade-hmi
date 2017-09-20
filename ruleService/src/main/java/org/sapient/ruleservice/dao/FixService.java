package org.sapient.ruleservice.dao;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.sapient.ruleservice.models.FIX;
import org.springframework.stereotype.Component;

@Component
public class FixService {

	private Map<String, FIX> fixData;
	
	@PostConstruct
	private void loadData() {
		Time fixTime = Time.valueOf("00:00:01");
		this.fixData = new HashMap<>();
		fixData.put("USD/INR", new FIX("USD/INR", fixTime, 300, true));
		fixData.put("USD/AUD", new FIX("USD/AUD", fixTime, 300, true));
		fixData.put("USD/SGD", new FIX("USD/SGD", fixTime, 300, true));
		fixData.put("USD/PAK", new FIX("USD/PAK", fixTime, 300, true));
	}
	
	public FIX getFixData(String currencyPair){
		return fixData.get(currencyPair);
	}
}

