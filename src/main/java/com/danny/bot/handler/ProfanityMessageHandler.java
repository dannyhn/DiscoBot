package com.danny.bot.handler;

import java.util.List;

import com.danny.bot.client.constants.ClientConstants;
import com.danny.bot.util.InsultUtil;
import com.danny.bot.util.MessageUtil;
import com.danny.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Profanity
 * 
 * @author Danny
 *
 */
public class ProfanityMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = getInsultFromMessage(message);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	
	/**
	 * Gets insult based off message
	 * 
	 * @param message
	 * @throws Exception
	 */
	private String getInsultFromMessage(IMessage message) {
		IGuild currentGuild = message.getGuild();
		String messageToSend = InsultUtil.getInsult(UserUtil.getName(message.getAuthor(), currentGuild)) + "\n";

		return messageToSend;
	}



	
}
