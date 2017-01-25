package com.danny.bot.service;

import com.danny.cache.KCache;
import com.danny.cache.KittyCache;

/**
 * @author Danny
 *
 */
public class RateLimitingService {
	
	private KCache<String, Integer> userCache;
	private int counter;
	
	private long previousTime = 0;
	
	private static RateLimitingService rateLimitingService;
	
	private static final int userLimit = 10;
	private static final int botLimit = 20;
	/**
	 *  Way to get singleton instance
	 * 
	 * @return
	 */
	public static synchronized RateLimitingService getInstance() {
		if (rateLimitingService == null) {
			rateLimitingService = new RateLimitingService();
		}
		return rateLimitingService;
	}
	
	public boolean canMakeRequest(String name) {
		 if (!canUserMakeRequest(name)) {
			 return false;
		 } else {
			 return canBotMakeRequest();
		 }
	}

	private boolean canBotMakeRequest() {
		if (counter > botLimit) {
			 return false;
		 }
		 counter++;
		 int secondsPassed = getSeconds();
		 counter -= (secondsPassed / 5);
		 return true;
	}

	private boolean canUserMakeRequest(String name) {
		Integer userRequests = userCache.get(name);
		if (userRequests == null) {
			userRequests = 0;
		}
		if (userRequests > userLimit) {
			return false;
		}
		userCache.put(name, userRequests + 1, 60);
		return true;
	}
	
	
	/**
	 * used to get time between request
	 * 
	 * @return
	 */
	private int getSeconds() {
		if (previousTime == 0) {
			previousTime = System.currentTimeMillis();
			return 0;
		} else {
			long currentTime = System.currentTimeMillis();
			int seconds = (int) ((currentTime - previousTime) / 1000);
			previousTime = currentTime;
			return seconds;
		}
	}

	
	private RateLimitingService() {
		userCache = new KittyCache<String, Integer>(100);
	}
	
}
