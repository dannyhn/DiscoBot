package com.danny.bot.service;

import java.util.List;
import java.util.Random;

import com.danny.cache.KCache;
import com.danny.cache.KittyCache;
import com.danny.yelp.Business;
import com.danny.yelp.YelpAPI;
//import com.danny.yelp.YelpConstants;

/**
 * @author Danny
 *
 */
public class YelpService {
	
	private KCache<String, List<Business>> cache;
	
	private YelpAPI yelp;
	private Random random = new Random();
	
	private static YelpService yelpService;
	
	/**
	 *  Way to get singleton instance
	 * 
	 * @return
	 */
	public static synchronized YelpService getInstance() {
		if (yelpService == null) {
			yelpService = new YelpService();
		}
		return yelpService;
	}

	public String getYelpInfoFromZipCode(String zipcode) {
		if (yelp == null || cache == null) {
			yelp = new YelpAPI("", "", "", "");
			//yelp = new YelpAPI(YelpConstants.CONSUMER_KEY, YelpConstants.CONSUMER_SECRET, YelpConstants.TOKEN, YelpConstants.TOKEN_SECRET);
			cache = new KittyCache<String, List<Business>>(100);
		}
		List<Business> business = getBusinessFromZipCode(zipcode);
		if (business == null) {
			return "No Restaurant found";
		}
		Business randomBusiness = randomBusinessFromList(business);
		removeEntry(zipcode, randomBusiness);
		return randomBusiness.toString();
	}
	
	public String getYelpListInfoFromZipCode(String zipcode) {
		if (yelp == null || cache == null) {
			yelp = new YelpAPI("", "", "", "");
			//yelp = new YelpAPI(YelpConstants.CONSUMER_KEY, YelpConstants.CONSUMER_SECRET, YelpConstants.TOKEN, YelpConstants.TOKEN_SECRET);
			cache = new KittyCache<String, List<Business>>(100);
		}
		List<Business> business = getBusinessFromZipCode(zipcode);
		if (business == null) {
			return "No Restaurant found";
		}
		String result = "";
		int index = 0;
		for (Business restaurant : business) {
			result += index++ + ") " + restaurant.getName() + "\n";
		}
		ContextService.getInstance().addBusiness(business);
		return result;
	}
	
	private Business randomBusinessFromList(List<Business> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}
	
	/**
	 * Gets business in cache if exists
	 * else get from yelp
	 * 
	 * @param zipcode
	 * @return
	 */
	private List<Business> getBusinessFromZipCode(String zipcode) {
		List<Business> business = cache.get(zipcode);
		if (business == null) {
			business = yelp.queryAPI(zipcode);
			if (business != null) {
				cache.put(zipcode, business, 600);
			}
		}
		return business;

	}
	
	private void removeEntry(String zipcode, Business businessToDelete) {
		List<Business> business = cache.get(zipcode);
		if (business != null) {
			business.remove(businessToDelete);
			if (business.isEmpty()) {
				cache.remove(zipcode);
			}
		}
	}
	
	private YelpService() {
		
	}
	
}
