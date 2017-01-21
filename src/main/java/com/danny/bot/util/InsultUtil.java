package com.danny.bot.util;

import java.util.Random;

/**
 * Utility to Generate Insults
 * 
 * @author Danny
 *
 */
public final class InsultUtil {

	private static Random random = new Random();
	
	public static String getInsult(String name) {
		int randNum = random.nextInt(8);
		switch(randNum) {
		case 0:
			return name + ", i saw that";
		case 1:
			return name + " why you so poop";
		case 2:
			return name + ", my cat can play better than you";
		case 3:
			return name + " you so fat you has to buy two airplane tickets";
		case 4:
			return name + " loses with infinite lives";
		case 5:
			return name + " cant aim cant shoot";
		case 6:
			return name + " needs to buy a kit";
		case 7:
			return name + " needs help";
		default:
			return name + " likes cows";
			
		}
	}
	
}
