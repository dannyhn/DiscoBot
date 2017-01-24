package com.danny.bot.util;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IMessage;

public final class MessageUtil {

	public static void sendMessage(IChannel channel, String message, IMessage original, boolean delete) {
		if (message == null) {
			return;
		}
		try {
			channel.sendMessage(message);
			if (delete) {
				original.delete();
			}
		} catch (Exception e) {
			System.out.println("Error Sending Message: " + original.getContent()  + " " + e.getMessage());
		}

	}
	
}
