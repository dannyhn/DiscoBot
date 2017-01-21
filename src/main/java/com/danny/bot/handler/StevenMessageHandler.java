package com.danny.bot.handler;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.DiscordException;
import sx.blah.discord.util.MissingPermissionsException;
import sx.blah.discord.util.RateLimitException;


/**
 * @author Tiffany
 *
 */
public class StevenMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		IChannel currentChannel = message.getChannel();

		String messageToSend = "Good Clothes. Visit: http://www.milleniumbrand.com/";

		try {
			currentChannel.sendMessage(messageToSend);
		} catch (MissingPermissionsException | RateLimitException | DiscordException e) {
			e.printStackTrace();
		}
			
			
		// "Good Clothes. Visit: http://www.milleniumbrand.com/";
	}

}
