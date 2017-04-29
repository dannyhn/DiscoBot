package com.danny.bot.handler;

import java.util.Random;

import com.danny.bot.service.RandomWordService;
import com.danny.bot.util.MessageUtil;
import com.danny.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Danny
 *
 */
public class TypingEventHandler {
	
	private static long time = 1;

	private static final long FIFTEENSECONDS = 15000;
	public void handleTypingEvent(IChannel channel, IUser user) {
		long currentTime = System.currentTimeMillis();
		if (currentTime - time > FIFTEENSECONDS) {
			String insult = typingInsult(UserUtil.getName(user, channel.getGuild()));
			time = currentTime;
			MessageUtil.sendMessage(channel, insult, null, false);
		}
	}
	
	private String typingInsult(String userName) {
		Random random = new Random();
		
		int randInt = random.nextInt(3);
		switch(randInt) {
		case 0:
			return userName + " types slower than a " + RandomWordService.getInstance().randomAnimal();
		case 1:
			return "does " + userName + " know that you can type with two hands";
		case 2:
			return userName + " types so god damn much";
		default:
			return userName + " sucks cows";
		}
	}
	
}
