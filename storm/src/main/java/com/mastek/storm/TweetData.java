package com.mastek.storm;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TweetData {
	

	@JsonProperty("text")
	private String tweet;
	
	@JsonProperty("id")
	private Long tweetId;
	
	@JsonProperty("source")
	private String source;
	
	
	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("in_reply_to_status_id")
	private String inRelyToStatusId;
	
	@JsonProperty("in_reply_to_user_id")
	private String inReplyToUserId;
	
	@JsonProperty("in_reply_to_screen_name")
	private String inReplyToUserName;
	
	@JsonProperty("geo")
	private String geo;
	
	@JsonProperty("coordinates")
	private Double[][][] coordinates;
	
	@JsonProperty("retweet_count")
	private Integer retweetCount;
	
	@JsonProperty("favorite_count")
	private Integer favoriteCount;

	@JsonProperty("retweeted")
	private boolean retweeted;
	
	@JsonProperty("favorited")
	private boolean favorited;
	
	@JsonProperty("filter_level")
	private String filterLevel;
	
	@JsonProperty("lang")
	private String lang;
	
	
	@JsonProperty("user")
	private User user;
	
	
	@JsonProperty("place")
	private Place Place;

	
	@JsonProperty("entities")
	private Entities entities;
	
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	public Long getTweetId() {
		return tweetId;
	}
	public void setTweetId(Long tweetId) {
		this.tweetId = tweetId;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getInRelyToStatusId() {
		return inRelyToStatusId;
	}
	public void setInRelyToStatusId(String inRelyToStatusId) {
		this.inRelyToStatusId = inRelyToStatusId;
	}
	public String getInReplyToUserId() {
		return inReplyToUserId;
	}
	public void setInReplyToUserId(String inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Place getPlace() {
		return Place;
	}
	public void setPlace(Place place) {
		Place = place;
	}
	public Entities getEntities() {
		return entities;
	}
	public void setEntities(Entities entities) {
		this.entities = entities;
	}
	public String getInReplyToUserName() {
		return inReplyToUserName;
	}
	public void setInReplyToUserName(String inReplyToUserName) {
		this.inReplyToUserName = inReplyToUserName;
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public Double[][][] getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Double[][][] coordinates) {
		this.coordinates = coordinates;
	}
	public Integer getRetweetCount() {
		return retweetCount;
	}
	public void setRetweetCount(Integer retweetCount) {
		this.retweetCount = retweetCount;
	}
	public Integer getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(Integer favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public boolean isRetweeted() {
		return retweeted;
	}
	public void setRetweeted(boolean retweeted) {
		this.retweeted = retweeted;
	}
	public boolean isFavorited() {
		return favorited;
	}
	public void setFavorited(boolean favorited) {
		this.favorited = favorited;
	}
	public String getFilterLevel() {
		return filterLevel;
	}
	public void setFilterLevel(String filterLevel) {
		this.filterLevel = filterLevel;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

}
