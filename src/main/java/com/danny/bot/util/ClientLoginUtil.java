package com.danny.bot.util;

import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

/**
 * Utility class for Client Login
 * 
 * @author Danny
 *
 */
public final class ClientLoginUtil {

	/**
	 * Creates a Client and logs in
	 * 
	 * @param token
	 * @param login
	 * @return
	 */
	public static IDiscordClient createClient(String token, boolean login) { 
	    ClientBuilder clientBuilder = new ClientBuilder(); 
	    clientBuilder.withToken(token);
	    try {
	        if (login) {
	            return clientBuilder.login(); 
	        } else {
	            return clientBuilder.build();
	        }
	    } catch (DiscordException e) { 
	        e.printStackTrace();
	        return null;
	    }
	  }
}
