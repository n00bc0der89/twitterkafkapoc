package com.mastek.storm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entities {

	@JsonProperty("hashtags")
	private String hashtags[];
	
	@JsonProperty("urls")
	private String urls[];

	public String[] getHashtags() {
		return hashtags;
	}

	public void setHashtags(String[] hashtags) {
		this.hashtags = hashtags;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}
	
	
}
