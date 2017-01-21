package com.danny.bot.util;

import java.util.Optional;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IUser;

/**
 * @author Danny
 *
 */
public final class UserUtil {

	/**
	 * Gets name of User based off Guild
	 * 
	 * @param user
	 * @param guild
	 * @return
	 */
	public static String getName(IUser user, IGuild guild) {

		Optional<String> optionalName;
		String name;
		optionalName = user.getNicknameForGuild(guild);
		if (optionalName.isPresent()) {
			name = optionalName.get();
		} else {
			name = user.getName();
		}
		return name;
	}

}
