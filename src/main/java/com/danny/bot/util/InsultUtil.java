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
				
		int randNum = random.nextInt(2);
		switch(randNum) {
		case 0:
			return staticInsult(name); // Danny
		case 1:
			return dynamicInsult(name); // Danny
		default:
			return name + " likes cows";
			
		}
		
	}
	
	/**
	 * Insult is randomized with words from randomWordUtil
	 *
	 * @param name
	 * @return
	 */
	private static String dynamicInsult(String name) {
		int randNum = random.nextInt(7);
		switch(randNum) {
		case 0:
			return String.format("my %2$s can play better than %1$s", name, RandomWordUtil.randomAnimal()); //Danny		
		case 1:
			return String.format("my %2$s can %3$s better than %1$s", name, RandomWordUtil.randomAnimal(), RandomWordUtil.randomVerb()); //Danny
		case 2:
			return String.format("my %3$s %2$s can play better than %1$s", name, RandomWordUtil.randomAnimal(), RandomWordUtil.randomAdjBad()); //Danny
		case 3:
			return String.format("%1$s is %2$s than a %3$s %4$", name, RandomWordUtil.randomAdjBader(), RandomWordUtil.randomAdjBad(), RandomWordUtil.randomAnimal()); //Danny
		case 4:
			return String.format("%1$s cant %2$s", name, RandomWordUtil.randomVerb());		
		case 5:
			return String.format("%1$s should learn how to %2$s", name, RandomWordUtil.randomVerb());
		case 6:
			return String.format("hey %1$s my %2$s can teach you how to %3$s", name, RandomWordUtil.randomAnimal(), RandomWordUtil.randomVerb());
		default:
			return name + " likes cows";
			
		}
	}
	
	/**
	 * returns a static insult
	 * 
	 * @param name
	 * @return
	 */
	private static String staticInsult(String name) {
		int randNum = random.nextInt(7);
		switch(randNum) {
		case 0:
			return name + " why you so poop"; //Danny
		case 1:
			return name + " you so fat you has to buy two airplane tickets"; //Danny
		case 2:
			return name + " loses with infinite lives"; //Danny
		case 3:
			return name + " cant aim cant shoot"; //Danny
		case 4:
			return name + " needs to buy a kit"; //Danny
		case 5:
			return name + " needs help"; //Tiffany
		case 6:
			return "someone should call a doctor, " + name + " has a severe burn"; //Tiffany
		default:
			return name + " likes cows";
			
		}
	}
	
}
