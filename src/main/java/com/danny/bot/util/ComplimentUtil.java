package com.danny.bot.util;

import java.util.Random;


import com.danny.bot.service.RandomWordService;

 /**
 * @author Tiffany
 *
 */
public class ComplimentUtil {

	
	/**
	 * Returns a random compliment <3
	 * 
	 * @param name
	 * @return
	 */
	public static synchronized String getCompliment(String name) {
		Random random = new Random();
		int randNum = random.nextInt(2);
		switch(randNum) {
		case 0:
			return staticCompliment(name); // Tiffany
		case 1:
			//return dynamicCompliment(name); // Tiffany
		default:
			return name + " likes rainbows";
			
		}
		
	}
	
	/**
	 * Compliment is randomized with words from randomWordUtil
	 *
	 * @param name
	 * @return
	 */
	//TODO Create random words for randomWordUtil and come up with cute phrases xoxoxo
	private static synchronized String dynamicCompliment(String name) {
		Random random = new Random();
		int randNum = random.nextInt(7);
		RandomWordService randomWordService = RandomWordService.getInstance();
		switch(randNum) {
		case 0:
			return String.format("%2$s" + "s trusts %1$s, you can too!", name, randomWordService.randomAnimal()); //Tiffany
		case 1:
			return String.format("my %2$s can %3$s better than %1$s", name, randomWordService.randomAnimal(), randomWordService.randomVerb()); //Tiffany
		case 2:
			return String.format("my %3$s %2$s can play better than %1$s", name, randomWordService.randomAnimal(), randomWordService.randomAdjBad()); //Tiffany
		case 3:
			return String.format("%1$s is %2$s than a %3$s %4$s", name, randomWordService.randomAdjBader(), randomWordService.randomAdjBad(), randomWordService.randomAnimal()); //Tiffany
		case 4:
			return String.format("%1$s cant %2$s", name, randomWordService.randomVerb());		
		case 5:
			return String.format("%1$s should learn how to %2$s", name, randomWordService.randomVerb());
		case 6:
			return String.format("hey %1$s my %2$s can teach you how to %3$s", name, randomWordService.randomAnimal(), randomWordService.randomVerb());
		default:
			return name + " likes unicorns";
			
		}
	}
	
	/**
	 * returns a static compliment
	 * 
	 * @param name
	 * @return
	 */
	private static synchronized String staticCompliment(String name) {
		Random random = new Random();
		int randNum = random.nextInt(9);
		switch(randNum) {
		case 0:
			return name + " is a sweetheart."; //Tiffany
		case 1:
			return name + " deserves everything nice."; //Tiffany
		case 2:
			return name + " is amazing."; //Tiffany
		case 3:
			return name + " is the best at video games."; //Tiffany
		case 4:
			return name + " is an adorable cinnamon roll."; //Tiffany
		case 5:
			return name + " is love. " + name + " is life. <3"; //Tiffany
		case 6:
			return name + " is an angel from heaven."; //Tiffany
		case 7:
			return "I have a crush on " + name ; //Tiffany
		case 8:
			return "I love you, " + name + "!!!"; //Tiffany
		default:
			return name + " likes unicorns";
			
		}
	}
	
}


