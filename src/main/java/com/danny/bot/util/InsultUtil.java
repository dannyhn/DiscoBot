package com.danny.bot.util;

import java.util.Random;

/**
 * Utility to Generate Insults
 * 
 * @author Danny
 *
 */
public final class InsultUtil {

	/**
	 * 
	 */
	private static Random random = new Random();
	
	/**
	 * Returns a random Insult
	 * 
	 * @param name
	 * @return
	 */
	public static String getInsult(String name) {
		int randNum = random.nextInt(9);
		switch(randNum) {
		case 0:
			return name + ", i saw that"; //Danny
		case 1:
			return name + " why you so poop"; //Danny
		case 2:
			return name + ", my cat can play better than you"; //Danny
		case 3:
			return name + " you so fat you has to buy two airplane tickets"; //Danny
		case 4:
			return name + " loses with infinite lives"; //Danny
		case 5:
			return name + " cant aim cant shoot"; //Danny
		case 6:
			return name + " needs to buy a kit"; //Danny
		case 7:
			return name + " needs help"; //Tiffany
		case 8:
			return "someone should call a doctor, " + name + " has a severe burn"; //Tiffany
		default:
			return name + " likes cows";
			
		}
	}
	
}
