package com.danny.bot.client.listeners;

import com.danny.bot.handler.ContextMessageHandler;
import com.danny.bot.handler.MessageHandler;
import com.danny.bot.handler.StatusChangeHandler;
import com.danny.bot.handler.TypingEventHandler;
import com.danny.bot.handler.UserVoiceChannelJoinHandler;
import com.danny.bot.handler.factory.MessageHandlerFactory;
import com.danny.bot.service.ContextService;
import com.danny.bot.service.RandomWordService;
import com.danny.bot.service.RateLimitingService;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.StatusChangeEvent;
import sx.blah.discord.handle.impl.events.TypingEvent;
import sx.blah.discord.handle.impl.events.UserVoiceChannelJoinEvent;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.Status;

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
		RandomWordService.getInstance();
	}
	
	@EventSubscriber
	public void onStatusChangeEvent(StatusChangeEvent event) {
		StatusChangeHandler statueChangeHandler = new StatusChangeHandler();
		statueChangeHandler.handleStatusChangeEvent(event.getNewStatus());
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
		if (isValidCommand(handler, message) && canMakeRequest(message)) {
			handler.handleMessage(message);
		}

	}
	
	@EventSubscriber
	public void onUserVoiceChannelJoinEvent(UserVoiceChannelJoinEvent event) {
		UserVoiceChannelJoinHandler handler = new UserVoiceChannelJoinHandler();
		handler.handleUserVoiceChannelJoinEvent(event.getChannel(), event.getUser());
		
	}
	
	@EventSubscriber
	public void onTypingEvent(TypingEvent event) {
		TypingEventHandler handler = new TypingEventHandler();
		handler.handleTypingEvent(event.getChannel(), event.getUser());
	}
	
	private boolean isValidCommand(MessageHandler handler, IMessage message) {
		if (handler instanceof ContextMessageHandler)  {
			return ContextService.getInstance().getContext(message.getContent()) != null;
		} else {
			return true;
		}
	}
	
	private boolean canMakeRequest(IMessage message) {
		return RateLimitingService.getInstance().canMakeRequest(message.getAuthor().getID());
	}

}
