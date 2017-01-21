package com.danny.bot.util;

import java.util.List;
import java.util.Random;

import com.danny.yelp.Business;
import com.danny.yelp.YelpAPI;
import com.danny.yelp.YelpConstants;

/**
 * @author Danny
 *
 */
public class YelpUtil {
	
	private static YelpAPI yelp;
	private static Random random = new Random();

	public static String getYelpInfoFromZipCode(String zipcode) {
		if (yelp == null) {
			yelp = new YelpAPI(YelpConstants.CONSUMER_KEY, YelpConstants.CONSUMER_SECRET, YelpConstants.TOKEN, YelpConstants.TOKEN_SECRET);
		}
		List<Business> business = yelp.queryAPI(zipcode);
		if (business == null) {
			return "No Restaurant found";
		}
		return randomBusinessFromList(business).toString();
	}
	
	private static Business randomBusinessFromList(List<Business> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}
	
}
