package com.danny.bot.handler;

import com.danny.bot.util.MessageUtil;
import com.danny.bot.util.YelpUtil;

import sx.blah.discord.handle.obj.IMessage;

/**
 * @author Danny
 *
 */
public class YelpMessageHandler implements MessageHandler{

	@Override
	public void handleMessage(IMessage message) {
		String zipcode = message.getContent().replaceAll(".yelp", "");
		zipcode = zipcode.trim();
		String restaurant = YelpUtil.getYelpInfoFromZipCode(zipcode);
		MessageUtil.sendMessage(message.getChannel(), restaurant, message, false);
	}

}
