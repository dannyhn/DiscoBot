package com.danny.bot.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.danny.yelp.Business;

/**
 * @author Danny
 *
 */
public final class ContextService {

	private Map<Integer, Business> contextYelpMap;
	private static ContextService contextService;
	
	/**
	 *  Way to get singleton instance
	 * 
	 * @return
	 */
	public static synchronized ContextService getInstance() {
		if (contextService == null) {
			contextService = new ContextService();
		}
		return contextService;
	}
	
	public String getContext(String message) {
		if (isInteger(message)) {
			Integer messageInt = Integer.parseInt(message);
			if (contextYelpMap.containsKey(messageInt)) {
				return contextYelpMap.get(messageInt).toString();
			} else {
				return null;
			}
		}
		return null;
	}
	
	public void addBusiness(List<Business> business) {
		int index = 0;
		for (Business restaurant : business) {
			contextYelpMap.put(index, restaurant);
			index++;
		}
	}
	
	
	private boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	
	private ContextService() {
		contextYelpMap = new ConcurrentHashMap<Integer, Business>();
	}
}
