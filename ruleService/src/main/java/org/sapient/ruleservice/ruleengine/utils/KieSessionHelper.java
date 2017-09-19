package org.sapient.ruleservice.ruleengine.utils;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

public class KieSessionHelper 
{
	private KieSessionHelper(){}
	
	public static StatelessKieSession createStatelessSession(KieContainer kieContainer, String sessionName)
	{
		return kieContainer.newStatelessKieSession(sessionName);
	}

	public static KieSession createSession(KieContainer kieContainer, String sessionName)
	{
		return kieContainer.newKieSession(sessionName);
	}
}
