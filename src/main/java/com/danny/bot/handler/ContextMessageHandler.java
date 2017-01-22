package com.danny.bot.handler;

import com.danny.bot.util.ContextUtil;
import com.danny.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IMessage;

public class ContextMessageHandler implements MessageHandler{

	@Override
	public void handleMessage(IMessage message) {
		String messageStr = message.getContent().trim();
		String messageToSend = ContextUtil.getContext(messageStr);
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, false);
	}

}
