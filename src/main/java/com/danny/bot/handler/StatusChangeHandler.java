package com.danny.bot.handler;

import com.danny.bot.util.MessageUtil;

import sx.blah.discord.handle.obj.Status;
import sx.blah.discord.handle.obj.Status.StatusType;

/**
 * @author Danny
 *
 */
public class StatusChangeHandler {

	public void handleStatusChangeEvent(Status status) {
		if (status.getType().equals(StatusType.GAME)) {
			System.out.println("Someone is playing: " + status.getStatusMessage());
			// need to do persistent storage of default channel
		}
	}
	
}
