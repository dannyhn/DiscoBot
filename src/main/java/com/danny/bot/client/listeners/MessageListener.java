package com.danny.bot.client.listeners;

import java.util.List;

import com.danny.bot.client.constants.ClientConstants;
import com.danny.bot.util.InsultUtil;
import com.danny.bot.util.UserUtil;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Danny
 *
 */
public class MessageListener {

	/**
	 * Triggers on bot startup
	 * 
	 * @param event
	 */
	@EventSubscriber
	public void onReadyEvent(ReadyEvent event) {
		System.out.println("Ready Received");
		System.out.println("Ready: " + event.toString());
	}

	/**
	 * Triggers on message received event
	 * 
	 * @param event
	 * @throws Exception
	 */
	@EventSubscriber
	public void onMessageReceivedEvent(MessageReceivedEvent event) throws Exception {
		System.out.println("Message Received: " + event.getMessage());
		IMessage message = event.getMessage();
		if (message.getContent().startsWith(".i")) {
			sendsInsult(message);
		}

	}

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
}
