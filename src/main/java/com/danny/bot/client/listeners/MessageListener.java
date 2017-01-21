package com.danny.bot.client.listeners;

import java.util.List;
import java.util.Optional;

import com.danny.bot.client.constants.ClientConstants;

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
	
	  @EventSubscriber
	  public void onReadyEvent(ReadyEvent event) { 
		System.out.println("Ready Received");
		System.out.println("Ready: " + event.toString());
	  }

	  @EventSubscriber
	  public void onMessageReceivedEvent(MessageReceivedEvent event) throws Exception {
	    System.out.println("Message Received: " + event.getMessage());
	    IMessage message = event.getMessage();
	    IGuild currentGuild = message.getGuild();
	    IChannel currentChannel = message.getChannel();
	    List<IUser> usersMentioned = message.getMentions();
	    //message.reply(nameOfAuthor + " likes cows");
	    
	    Optional<String> optionalName;
	    String name;
	    for (IUser user : usersMentioned) {
	    	optionalName = user.getNicknameForGuild(currentGuild);
	    	if (optionalName.isPresent()) {
	    		name = optionalName.get();
	    	} else {
	    		name = user.getName();
	    	}//TODO add delay for spammers
	    	if (!name.equalsIgnoreCase(ClientConstants.BOTNAME)) {
	    		currentChannel.sendMessage(name + " likes cows");
	    	}
	    }
	    
	  }
}
