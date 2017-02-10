package com.danny.bot.handler;

import java.util.Random;

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
public class RollMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		String messageToSend = readyUp(message.getAuthor(), message.getGuild());
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String readyUp(IUser user, IGuild guild) {
		String msg = "";
		Board board = Board.getInstance();
		if (board.canPlayerRoll(user.getID())) {
			msg += board.doPlayerRoll(user.getID());
		} else {
			msg += UserUtil.getName(user, guild) + " cant Roll";
		}
		

		return msg;
	}



	
}
