package com.mastek.storm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Test {
	
	public static final String delimeter="\n";

	public static void main(String[] args) {
		String filePath="D:\\test\\testjson.txt";
		String test = readFile(filePath);
		
		
		try {
			convertToJSon(test);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void convertToJSon(String test) throws JsonParseException, JsonMappingException, IOException {
		 
		//System.out.println("test is "+test);
		
		TweetData obj = new ObjectMapper().readValue(test, TweetData.class);
		String objectRecord=createObjectRecord(obj);
		
		System.out.println(objectRecord);		
	}

	
	private static String createObjectRecord(TweetData obj) {
		StringBuilder  record=new StringBuilder("");
		
		if(obj!=null){
			User objUser=obj.getUser();
			Place objPlace=obj.getPlace();
			Entities objEntities=obj.getEntities();
			record.append(obj.getTweetId().toString());record.append(delimeter);
			record.append(obj.getCreatedAt());record.append(delimeter);
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
				record.append(objUser.getUserCreatedAt());record.append(delimeter);
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
				record.append(Arrays.toString(objEntities.getUrls()));record.append(delimeter);
			}
		}
		return record.toString();
	}

/*	private static String readOneDimensionalStringArray(String arr[]) {
		StringBuilder out=new StringBuilder("[");
		if(arr !=null){
			for(int i=0; i < arr.length;i++){
				out.append(arr[i]);
			}
		}
		out.append("]");
		return out.toString();
	}*/

	private static String readFile(String filePath){
		BufferedReader br = null;
		String sCurrentLine="";
		String out="";
		try {

			

			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				out=out+sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return out;	
	}
	
}
