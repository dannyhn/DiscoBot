package com.danny.bot.util;

import java.util.Random;

import com.danny.bot.service.RandomWordService;

/**
 * Utility to Generate Insults
 * 
 * @author Danny
 *
 */
public final class InsultUtil {

	
	/**
	 * Returns a random Insult
	 * 
	 * @param name
	 * @return
	 */
	public static synchronized String getInsult(String name) {
		Random random = new Random();
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
	private static synchronized String dynamicInsult(String name) {
		Random random = new Random();
		int randNum = random.nextInt(7);
		RandomWordService randomWordService = RandomWordService.getInstance();
		switch(randNum) {
		case 0:
			return String.format("my %2$s can play better than %1$s", name, randomWordService.randomAnimal()); //Danny		
		case 1:
			return String.format("my %2$s can %3$s better than %1$s", name, randomWordService.randomAnimal(), randomWordService.randomVerb()); //Danny
		case 2:
			return String.format("my %3$s %2$s can play better than %1$s", name, randomWordService.randomAnimal(), randomWordService.randomAdjBad()); //Danny
		case 3:
			return String.format("%1$s is %2$s than a %3$s %4$s", name, randomWordService.randomAdjBader(), randomWordService.randomAdjBad(), randomWordService.randomAnimal()); //Danny
		case 4:
			return String.format("%1$s cant %2$s", name, randomWordService.randomVerb());		
		case 5:
			return String.format("%1$s should learn how to %2$s", name, randomWordService.randomVerb());
		case 6:
			return String.format("hey %1$s my %2$s can teach you how to %3$s", name, randomWordService.randomAnimal(), randomWordService.randomVerb());
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
	private static synchronized String staticInsult(String name) {
		Random random = new Random();
		int randNum = random.nextInt(10);
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
		case 7:
			return name + " plays like a sissy"; // Danny
		case 8:
			return name + " hides under the map"; // Danny
		case 9:
			return name + "programs in BASIC";
		default:
			return name + " likes cows";
			
		}
	}
	
}
