package com.danny.bot.handler;

import com.danny.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.IVoiceChannel;

/**
 * @author Danny
 *
 */
public class UserVoiceChannelJoinHandler {

	public void handleUserVoiceChannelJoinEvent(IVoiceChannel channel, IUser user) {
		String userName = UserUtil.getName(user, channel.getGuild());
		System.out.println("User " + userName + " has joined channel " + channel.getName());
	}
	
}
