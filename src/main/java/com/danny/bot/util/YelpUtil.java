package com.danny.bot.util;

import java.util.List;
import java.util.Random;

import com.danny.cache.KCache;
import com.danny.cache.KittyCache;
import com.danny.yelp.Business;
import com.danny.yelp.YelpAPI;
import com.danny.yelp.YelpConstants;

/**
 * @author Danny
 *
 */
public class YelpUtil {
	
	private static KCache<String, List<Business>> cache;
	
	private static YelpAPI yelp;
	private static Random random = new Random();

	public static String getYelpInfoFromZipCode(String zipcode) {
		if (yelp == null || cache == null) {
			yelp = new YelpAPI(YelpConstants.CONSUMER_KEY, YelpConstants.CONSUMER_SECRET, YelpConstants.TOKEN, YelpConstants.TOKEN_SECRET);
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
	
	private static Business randomBusinessFromList(List<Business> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}
	
	/**
	 * Gets busines in cache if exists
	 * else get from yelp
	 * 
	 * @param zipcode
	 * @return
	 */
	private static List<Business> getBusinessFromZipCode(String zipcode) {
		List<Business> business = cache.get(zipcode);
		if (business == null) {
			business = yelp.queryAPI(zipcode);
			if (business != null) {
				cache.put(zipcode, business, 600);
			}
		}
		return business;

	}
	
	private static void removeEntry(String zipcode, Business businessToDelete) {
		List<Business> business = cache.get(zipcode);
		if (business != null) {
			business.remove(businessToDelete);
			if (business.isEmpty()) {
				cache.remove(zipcode);
			}
		}
	}
	
}
