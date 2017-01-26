package com.danny.bot.handler;

import com.danny.bot.service.RandomWordService;
import com.danny.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Quotes
 * 
 * @author Danny
 *
 */
public class QuoteMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		IChannel currentChannel = message.getChannel();
		String quote = RandomWordService.getInstance().randomQuote();
		MessageUtil.sendMessage(currentChannel, quote, message, true);

	}

}
