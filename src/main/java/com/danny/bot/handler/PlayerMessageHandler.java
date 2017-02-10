package com.danny.bot.handler;

import com.danny.bot.util.MessageUtil;
import com.danny.bot.util.UserUtil;
import com.danny.game.machikoro.Board;
import com.danny.game.machikoro.Card;
import com.danny.game.machikoro.Player;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * Message Handler for Help
 * 
 * @author Danny
 *
 */
public class PlayerMessageHandler implements MessageHandler {

	@Override
	public void handleMessage(IMessage message) {
		IGuild guild = message.getGuild();
		String messageToSend = "";
		for (IUser user : message.getMentions()) {
			messageToSend += getInfo(user, guild);
		}
		MessageUtil.sendMessage(message.getChannel(), messageToSend, message, true);

	}
	

	private String getInfo(IUser user, IGuild guild) {
		Player player = Board.getInstance().getPlayer(user.getID());
		if (player == null) {
			return "Could not find Player: " + UserUtil.getName(user, guild);
		}
		String msg = "";
		msg += "Name: " + player.getName() + "\n";
		msg += "Money: " + player.getMoney() + "\n";
		for (Card card : player.getCards()) {
			msg += "Card: " + card.getName() + " x" + card.getQuantity() + "\n";
		}
		return msg;
	}



	
}
