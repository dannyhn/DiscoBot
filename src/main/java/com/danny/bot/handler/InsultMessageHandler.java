package com.danny.bot.handler;

import java.util.List;

import com.danny.bot.client.constants.ClientConstants;
import com.danny.bot.util.InsultUtil;
import com.danny.bot.util.UserUtil;

import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Tiffany
 *
 */
public class InsultMessageHandler implements MessageHandler {

	/**
	 * Sends insult based off message
	 * 
	 * @param message
	 * @throws Exception
	 */
	private void sendsInsult(IMessage message) throws Exception {
		IGuild currentGuild = message.getGuild();
		IChannel currentChannel = message.getChannel();
		List<IUser> usersMentioned = message.getMentions();

		String name;
		String messageToSend = "";
		for (IUser user : usersMentioned) {
			name = UserUtil.getName(user, currentGuild);
			// TODO add delay for spammers
			if (!name.equalsIgnoreCase(ClientConstants.BOTNAME)) {
				messageToSend += InsultUtil.getInsult(name) + "\n";
			} else {
				messageToSend += UserUtil.getName(message.getAuthor(), currentGuild) + " sucks cows\n";
			}
		}
		if (!messageToSend.equals("")) {
			currentChannel.sendMessage(messageToSend);
		}
	}

	@Override
	public void handleMessage(IMessage message) {
		try {
			sendsInsult(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
