package com.danny.bot.handler.factory;

import com.danny.bot.handler.InsultMessageHandler;
import com.danny.bot.handler.MessageHandler;
import com.danny.bot.handler.StevenMessageHandler;

import sx.blah.discord.handle.obj.IMessage;

/**
 * @author Tiffany
 *
 */
public class MessageHandlerFactory {
	/**
	 * @param message
	 * @return
	 */
	public MessageHandler getMessageHandler(IMessage message) {
		if (message.getContent().startsWith(".i")) {
			return new InsultMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".steven")) {
			return new StevenMessageHandler();
		} else {
			return null;
		}

	}
}
