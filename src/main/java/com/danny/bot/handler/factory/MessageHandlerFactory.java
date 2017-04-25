package com.danny.bot.handler.factory;

import com.danny.bot.handler.ComplimentMessageHandler;
import com.danny.bot.handler.ContextMessageHandler;
import com.danny.bot.handler.HelpMessageHandler;
import com.danny.bot.handler.InfoMessageHandler;
import com.danny.bot.handler.InsultMessageHandler;
import com.danny.bot.handler.MessageHandler;
import com.danny.bot.handler.PlayerMessageHandler;
import com.danny.bot.handler.ProfanityMessageHandler;
import com.danny.bot.handler.QuoteMessageHandler;
import com.danny.bot.handler.ReadyMessageHandler;
import com.danny.bot.handler.RollMessageHandler;
import com.danny.bot.handler.StevenMessageHandler;
import com.danny.bot.handler.YelpMessageHandler;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Factory that determines which MessageHandler to use
 * 
 * @author Tiffany
 *
 */
public class MessageHandlerFactory {
	/**
	 * @param message
	 * @return
	 */
	public MessageHandler getMessageHandler(IMessage message) {
		if (message.getContent().startsWith(".info")) {
			return new InfoMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".steven")) {
			return new StevenMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".yelp")) {
			return new YelpMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".c")) {
			return new ComplimentMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".quote")) {
			return new QuoteMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".help")) {
			return new HelpMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".i")) {
			return new InsultMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".ready")) {
			return new ReadyMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".roll")) {
			return new RollMessageHandler();
		} else if (message.getContent().toLowerCase().startsWith(".playerinfo")) {
			return new PlayerMessageHandler();
		} else if (isProfanity(message)) {
			return new ProfanityMessageHandler();
		} else {
			return new ContextMessageHandler();
		}

	}
	
	private boolean isProfanity(IMessage message) {
		return message.getContent().toLowerCase().contains("fag");
	}
}
