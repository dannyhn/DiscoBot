package com.danny.bot.handler;

import com.danny.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.IMessage;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
public class InfoMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = getInfo();
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String getInfo() {
		String msg = "A Discord Bot Created and Designed by Danny N. in Collaboration with Tiffany T.\n";
		return msg;
	}



	
}
