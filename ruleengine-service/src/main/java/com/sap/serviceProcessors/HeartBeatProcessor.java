package com.sap.serviceProcessors;

import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class HeartBeatProcessor implements Processor {

	public void process(Exchange exchange) throws Exception {
		exchange.getIn().setBody("HeartBeat " + new Date());
	}

}