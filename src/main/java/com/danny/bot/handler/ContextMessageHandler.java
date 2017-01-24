package com.danny.bot.handler;

import com.danny.bot.service.ContextService;
import com.danny.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IMessage;

public class ContextMessageHandler implements MessageHandler{

	@Override
	public void handleMessage(IMessage message) {
		String messageStr = message.getContent().trim();
		ContextService contextService = ContextService.getInstance();
		String messageToSend = contextService.getContext(messageStr);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, false);
	}

}
