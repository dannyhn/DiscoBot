package com.danny.bot.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Danny
 *
 */
public final class RandomWordService {
	
	private Map<String, List<String>> wordMap;
	private Random random = new Random();
	
	private final String animal = "animal";
	private final String adjBad = "adjBad";
	private final String adjBader = "adjBader";
	private final String verb = "verb";	
	private final String quote = "quote";
	
	private static RandomWordService randomWordService;
	
	/**
	 * Way to get Singleton instance
	 * 
	 * @return
	 */
	public static synchronized RandomWordService getInstance() {
		if (randomWordService == null) {
			randomWordService = new RandomWordService();
		}
		return randomWordService;
	}
	
	
	/**
	 * returns a random animal
	 * 
	 * @return
	 */
	public String randomAnimal() {
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
	public String randomAdjBad() {
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
	public String randomAdjBader() {
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
	public String randomVerb() {
		if (wordMap.containsKey(verb)) {
			return randomWordFromList(wordMap.get(verb));
		} else {
			return "eat";
		}
	}
	
	/**
	 * returns a random verb
	 * 
	 * @return
	 */
	public String randomQuote() {
		if (wordMap.containsKey(quote)) {
			return randomWordFromList(wordMap.get(quote));
		} else {
			return "Oh my god, girls are the cows of people";
		}
	}
	
	/**
	 * @param lsOfWords
	 * @return
	 */
	private String randomWordFromList(List<String> lsOfWords) {
		int index = random.nextInt(lsOfWords.size());
		return lsOfWords.get(index);
	}
	
	/**
	 * @param is
	 * @return
	 */
	private String convertStreamToString(String fileName, ClassLoader classLoader) {
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		if (inputStream == null) {
			inputStream = classLoader.getResourceAsStream("resources/" + fileName);
		}
		String theString = "";
		try {
			theString = IOUtils.toString(inputStream, "UTF-8");
		} catch (Exception e) {
			System.out.println("Error Converting InputStream");
			e.printStackTrace();
		}
		IOUtils.closeQuietly(inputStream);
		return theString;

	}
	
	private RandomWordService() {
		wordMap = new HashMap<String, List<String>>();
		ObjectMapper mapper = new ObjectMapper();
		ClassLoader classLoader = RandomWordService.class.getClassLoader();
		try {
			String[] animalsArr = mapper.readValue(convertStreamToString("animals.json", classLoader), String[].class);
			wordMap.put(animal, Arrays.asList(animalsArr));
			String[] adjBadArr = mapper.readValue(convertStreamToString("adjBad.json", classLoader), String[].class);
			wordMap.put(adjBad, Arrays.asList(adjBadArr));
			String[] adjBaderArr = mapper.readValue(convertStreamToString("adjBader.json", classLoader), String[].class);
			wordMap.put(adjBader, Arrays.asList(adjBaderArr));
			String[] verbArr = mapper.readValue(convertStreamToString("verbs.json", classLoader), String[].class);
			wordMap.put(verb, Arrays.asList(verbArr));
			String[] quoteArr = mapper.readValue(convertStreamToString("quotes.json", classLoader), String[].class);
			wordMap.put(quote, Arrays.asList(quoteArr));
		} catch (IOException e) {
			System.out.println("Error on RandomWordUtil Init: " + e.getMessage());
		}
	}

}
