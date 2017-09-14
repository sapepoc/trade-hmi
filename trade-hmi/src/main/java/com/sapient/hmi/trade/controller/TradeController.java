package com.sapient.hmi.trade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.hmi.dto.ResponseEntity;
import com.sapient.hmi.trade.service.TradeService;

@RestController
public class TradeController {

	@Autowired
	private TradeService tradeService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/trades")
	public @ResponseBody ResponseEntity getTrades(){
		return new ResponseEntity(Boolean.TRUE, tradeService.readAll());
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/applyRules")
	public @ResponseBody ResponseEntity applyRules(@RequestBody List<String> tradeIds){
		return new ResponseEntity(Boolean.TRUE, tradeService.applyRules(tradeIds));
	}
}
