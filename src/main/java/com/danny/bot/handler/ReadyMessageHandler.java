package com.danny.bot.handler;

import com.danny.bot.util.MessageUtil;
import com.danny.bot.util.UserUtil;
import com.danny.game.machikoro.Board;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
public class ReadyMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = readyUp(message.getAuthor(), message.getGuild());
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String readyUp(IUser user, IGuild guild) {
		Board.getInstance().addPlayer(user.getID(), UserUtil.getName(user, guild));
		String msg = UserUtil.getName(user, guild) + " is Ready!";
		return msg;
	}



	
}
