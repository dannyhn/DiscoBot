package com.danny.bot;

import com.danny.bot.client.constants.ClientConstants;
import com.danny.bot.client.listeners.MessageListener;
import com.danny.bot.util.ClientLoginUtil;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

/**
 * Main class
 * 
 * @author Danny
 *
 */
public class Bot {

	/**
	 * Run method
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		IDiscordClient client = ClientLoginUtil.createClient(ClientConstants.TOKEN, true);
		EventDispatcher dispatcher = client.getDispatcher(); 
		dispatcher.registerListener(new MessageListener());

	}

}
