package com.mastek.storm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TweetParseHelper {
	public static final String delimeter="|";
	public static String createObjectRecord(TweetData obj) throws ParseException {
		StringBuilder  record=new StringBuilder("");
		
		if(obj!=null){
			User objUser=obj.getUser();
			Place objPlace=obj.getPlace();
			Entities objEntities=obj.getEntities();
			record.append(obj.getTweetId().toString());record.append(delimeter);
			record.append(parseTwitterUTC(obj.getCreatedAt()));record.append(delimeter);
			record.append(obj.getTweet());record.append(delimeter);
			record.append(obj.getSource());record.append(delimeter);			
			record.append(obj.getInRelyToStatusId());record.append(delimeter);
			record.append(obj.getInReplyToUserId());record.append(delimeter);
			record.append(obj.getInReplyToUserName());record.append(delimeter);
			if(objUser !=null){
				record.append(objUser.getUserId());record.append(delimeter);
				record.append(objUser.getUserFullName());record.append(delimeter);
				record.append(objUser.getUserScreenName());record.append(delimeter);
				record.append(objUser.getUserLocation());record.append(delimeter);
				record.append(objUser.getUserDescription());record.append(delimeter);
				record.append(objUser.getUserURL());record.append(delimeter);
				record.append(objUser.isUserProtected());record.append(delimeter);
				record.append(objUser.isUserVerified());record.append(delimeter);
				record.append(objUser.getUserFollowerCount());record.append(delimeter);
				record.append(objUser.getUserFollowingCount());record.append(delimeter);
				record.append(objUser.getUserStatusCount());record.append(delimeter);
				record.append(objUser.getUserFriendsCount());record.append(delimeter);
				record.append(objUser.getUserListedCount());record.append(delimeter);
				record.append(objUser.getUserFavouriteCount());record.append(delimeter);
				record.append(parseTwitterUTC(objUser.getUserCreatedAt()));record.append(delimeter);
				record.append(objUser.isUserGeoEnabled());record.append(delimeter);
				record.append(objUser.getUserLanguage());record.append(delimeter);
				record.append(objUser.getUserProfileImageURL());record.append(delimeter);
				record.append(objUser.isUserDefaultProfile());record.append(delimeter);
				record.append(objUser.isUserDefaultProfileImage());record.append(delimeter);
			}
			record.append(obj.getGeo());record.append(delimeter);
			record.append(Arrays.deepToString(obj.getCoordinates()));record.append(delimeter);
			record.append(obj.getRetweetCount());record.append(delimeter);
			record.append(obj.getFavoriteCount());record.append(delimeter);
			record.append(obj.isRetweeted());record.append(delimeter);
			record.append(obj.isFavorited());record.append(delimeter);
			record.append(obj.getFilterLevel());record.append(delimeter);
			record.append(obj.getLang());record.append(delimeter);
			if(objPlace !=null){
	
				record.append(objPlace.getPlaceType());record.append(delimeter);
				record.append(objPlace.getPlaceName());record.append(delimeter);
				record.append(objPlace.getPlaceFullName());record.append(delimeter);
				record.append(objPlace.getPlaceCountryCode());record.append(delimeter);
				record.append(objPlace.getPlaceCountry());record.append(delimeter);
				BoundingBox objBoundingBox=objPlace.getBoundingBox();
				if(objBoundingBox !=null){
					if(objBoundingBox.getCoordinates()!=null){
					record.append(Arrays.deepToString(objBoundingBox.getCoordinates()));record.append(delimeter);
					}
					else{
					record.append(objBoundingBox.getCoordinates());record.append(delimeter);
					}
						
				}
				
			}
			if(objEntities !=null){
				record.append(Arrays.toString(objEntities.getHashtags()));record.append(delimeter);
				//TODO:Add user mentions here
				record.append(Arrays.toString(objEntities.getUrls()));
			}
		}
		return record.toString();
	}
	
	public static String parseTwitterUTC(String date) throws ParseException {
		String out="";
		if(date !=null && !("".equals(date))){
           String twitterFormat="EEE MMM dd HH:mm:ss ZZZZZ yyyy";
           // Important note. Only ENGLISH Locale works.
            SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
            sf.setLenient(true);
            Date dt=sf.parse(date);
            SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
            out= sdf.format(dt);
		}
		return out;
     }

	
}
