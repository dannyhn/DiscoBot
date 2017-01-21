package com.danny.bot.util;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Danny
 *
 */
public final class RandomWordUtil {
	
	private static Map<String, List<String>> wordMap;
	private static Random random = new Random();
	
	private static final String animal = "animal";
	private static final String adjBad = "adjBad";
	private static final String adjBader = "adjBader";
	private static final String verb = "verb";
	
	/**
	 * should init before using util class
	 */
	public static void init() {
		wordMap = new HashMap<String, List<String>>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String[] animalsArr = mapper.readValue(new File("src/main/resources/animals.json"), String[].class);
			wordMap.put(animal, Arrays.asList(animalsArr));
			String[] adjBadArr = mapper.readValue(new File("src/main/resources/adjBad.json"), String[].class);
			wordMap.put(adjBad, Arrays.asList(adjBadArr));
			String[] adjBaderArr = mapper.readValue(new File("src/main/resources/adjBader.json"), String[].class);
			wordMap.put(adjBader, Arrays.asList(adjBaderArr));
			String[] verbArr = mapper.readValue(new File("src/main/resources/verbs.json"), String[].class);
			wordMap.put(verb, Arrays.asList(verbArr));
		} catch (IOException e) {
			System.out.println("Error on RandomWordUtil Init: " + e.getMessage());
		}
	}
	
	/**
	 * returns a random animal
	 * 
	 * @return
	 */
	public static String randomAnimal() {
		if (wordMap.containsKey(animal)) {
			return randomWordFromList(wordMap.get(animal));
		} else {
			return "monkey";
		}
	}	
	
	/**
	 * return a random bad adjective
	 * 
	 * @return
	 */
	public static String randomAdjBad() {
		if (wordMap.containsKey(adjBad)) {
			return randomWordFromList(wordMap.get(adjBad));
		} else {
			return "tired";
		}
	}	
	
	/**
	 * returns a random bad adjective with a degree such as slower rather than slow
	 * 
	 * @return
	 */
	public static String randomAdjBader() {
		if (wordMap.containsKey(adjBader)) {
			return randomWordFromList(wordMap.get(adjBader));
		} else {
			return "poorer";
		}
	}	
	
	/**
	 * returns a random verb
	 * 
	 * @return
	 */
	public static String randomVerb() {
		if (wordMap.containsKey(verb)) {
			return randomWordFromList(wordMap.get(verb));
		} else {
			return "eat";
		}
	}
	
	/**
	 * @param lsOfWords
	 * @return
	 */
	private static String randomWordFromList(List<String> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}

}
