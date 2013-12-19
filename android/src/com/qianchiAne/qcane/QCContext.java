package com.qianchiAne.qcane;
import java.util.HashMap;
import java.util.Map;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;


public class QCContext extends FREContext {

	

	@Override
	public Map<String, FREFunction> getFunctions() {
		Map<String, FREFunction> map = new HashMap<String, FREFunction>();
		
		map.put("login", new QCLoginFunction());
		map.put("init", new QCInitFunction());
		map.put("pay", new QCPayFunction());
		map.put("test", new QCtest());
		return map;
	}

@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}
}
