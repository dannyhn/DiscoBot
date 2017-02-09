package com.danny.bot.util;

import org.apache.commons.lang3.StringUtils;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public final class MessageUtil {

	public static void sendMessage(IChannel channel, String message, IMessage original, boolean delete) {
		if (StringUtils.isEmpty(message)) {
			return;
		}
		try {
			channel.sendMessage(message);
			if (delete) {
				original.delete();
			}
		} catch (Exception e) {
			System.out.println("Error Sending Message: " + message + "\nOriginal: "+ original.getContent()  + " " + e.getMessage());
		}

	}
	
}
