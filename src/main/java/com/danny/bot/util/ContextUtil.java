package com.danny.bot.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.danny.yelp.Business;

public final class ContextUtil {

	private static Map<Integer, Business> contextYelpMap = new ConcurrentHashMap<Integer, Business>();
	
	public static String getContext(String message) {
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
	
	public static void addBusiness(List<Business> business) {
		int index = 0;
		for (Business restaurant : business) {
			contextYelpMap.put(index, restaurant);
			index++;
		}
	}
	
	
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
}
