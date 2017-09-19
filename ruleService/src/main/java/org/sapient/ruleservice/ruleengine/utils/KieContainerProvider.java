package org.sapient.ruleservice.ruleengine.utils;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

public class KieContainerProvider 
{
	private static KieContainer kContainer;
	
	public static KieContainer createKieContainer()
	{
		if(kContainer == null)
		{
	        KieServices ks = KieServices.Factory.get();
		    kContainer = ks.getKieClasspathContainer();
		}
		return kContainer;
	}
}
