package com.danny.bot.client.listeners;

import com.danny.bot.handler.MessageHandler;
import com.danny.bot.handler.factory.MessageHandlerFactory;
import com.danny.bot.util.RandomWordUtil;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IMessage;

/**
 * @author Danny
 *
 */
public class MessageListener {

	/**
	 * Triggers on bot startup
	 * 
	 * @param event
	 */
	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		RandomWordUtil.init();
	}

	/**
	 * Triggers on message received event
	 * 
	 * @param event
	 * @throws Exception
	 */
	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) {
		IMessage message = event.getMessage();
		MessageHandlerFactory handlerFactory = new MessageHandlerFactory();
		MessageHandler handler = handlerFactory.getMessageHandler(message);
		if (handler != null) {
			handler.handleMessage(message);
		}

	}

}
