package com.danny.bot.handler;

import com.danny.bot.service.YelpService;
import com.danny.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Yelp
 * 
 * @author Danny
 *
 */
public class YelpMessageHandler implements MessageHandler{

	@Override
	public void handleMessage(IMessage message) {
		if (message.getContent().toLowerCase().contains(".yelplist")) {
			handleYelpListMessage(message);
		} else {
			handleYelpMessage(message);
		}
	}
	
	/**
	 * handles .yelp message
	 * 
	 * @param message
	 */
	private void handleYelpMessage(IMessage message) {
		String zipcode = message.getContent().replaceAll(".yelp", "");
		zipcode = zipcode.trim();
		String restaurant = YelpService.getInstance().getYelpInfoFromZipCode(zipcode);
		MessageUtil.sendMessage(message.getChannel(), restaurant, message, false);
	}
	
	/**
	 * handles .yelplist message
	 * 
	 * @param message
	 */
	private void handleYelpListMessage(IMessage message) {
		String zipcode = message.getContent().replaceAll(".yelplist", "");
		zipcode = zipcode.trim();
		String restaurant = YelpService.getInstance().getYelpListInfoFromZipCode(zipcode);
		MessageUtil.sendMessage(message.getChannel(), restaurant, message, false);
	}

}
