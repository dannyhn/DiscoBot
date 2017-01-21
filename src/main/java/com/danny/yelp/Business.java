package com.danny.yelp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Danny
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Business {
	
	private String phoneNumber = "No Number";
	private String rating = "No Rating";
	private String name = "No Name";
	private String url = "No Url";

	@JsonCreator
	public Business(@JsonProperty("display_phone") String phone,
					@JsonProperty("rating") String rating,
					@JsonProperty("name") String name,
					@JsonProperty("url") String url) {
		if (phone != null)
			this.phoneNumber = phone;
		if (rating != null)
			this.rating = rating;
		if (name != null)
			this.name = name;
		if (url != null)
			this.url = url;
	}
	
	@Override
	public String toString() {
		String result = "Restaurant: " + name + "\n";
		result += "Phone: " + phoneNumber + "\n";
		result += "Rating: " + rating + "\n";
		result += "Url: " + url;
		return result;
	}
}
