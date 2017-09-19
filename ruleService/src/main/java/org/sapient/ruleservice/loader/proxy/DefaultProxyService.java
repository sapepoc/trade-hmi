package org.sapient.ruleservice.loader.proxy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Component;

/**
 * 
 * @author hgaur2
 *
 */
@Component
public class DefaultProxyService implements ProxyService {

	@Override
	public Collection<String> getXMLs(String systemName) {
		// TODO Auto-generated method stub
		return loadFromRemote(systemName);
	}

	private Collection<String> loadFromRemote(String systemName) {
		Collection<String> collection = new ArrayList<>();
		collection.add(loadDummyFile("data/trade_001.xml"));
		collection.add(loadDummyFile("data/trade_2.xml"));

		return collection;
	}

	private String loadDummyFile(String fileName) {
		File datafile = new File(fileName);
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(datafile.toPath());
			return new String(encoded, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
