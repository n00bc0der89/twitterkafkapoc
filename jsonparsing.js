const sanitizeHtml = require('sanitize-html');
const config = require('./config');
const constants = config.middlewareconstants;
const jsonParsing = {};
(function(jsonParsing){

  jsonParsing.getParsedString = function(inputString){
	var idList = ["id_str","created_at","text","source","in_reply_to_status_id_str","in_reply_to_user_id_str","in_reply_to_screen_name","user.id_str","user.name","user.screen_name","user.location","user.description","user.url","user.protected","user.verified","user.followers_count","user.following","user.statuses_count","user.friends_count","user.listed_count","user.favourites_count","user.created_at","user.geo_enabled","user.lang","user.profile_image_url","user.default_profile","user.default_profile_image","geo.coordinates[0]","coordinates.coordinates[0]","retweet_count","favorite_count","retweeted","favorited","filter_level","lang","place.place_type","place.name","place.full_name","place.country_code","place.country","place.bounding_box.coordinates[0][0]","entities.hashtags[0].text","entities.user_mentions[0].screen_name","entities.urls[0].url"];
	var input = sanitizeHtml(inputString, {
		allowedTags: [],
		allowedAttributes: []
	});

	input = input.replace(/&amp;/gi,'&').trim();
	input = input.replace(/&lt;/gi,'<').trim();
	input = input.replace(/p&gt;/gi,'>').trim();
	input = input.replace(/\|/g,'#').trim();
	input = input.replace(/&quot;/gi,'"').trim();
	input = input.replace(/\t/g,'').trim();
	input = input.replace(/\\n/g,' ').trim();	
	input = input.replace(/\r/g,'').trim();

	var parsedJSON = JSON.parse(input);
	var output="";
	var val;
	for(var i=0;i<idList.length;i++){
		val = 'parsedJSON.'+idList[i];
		try{
			if(val == "parsedJSON.created_at" || val == "parsedJSON.user.created_at")
			{
				var date = new Date(Date.parse(eval(val))).toISOString().replace(/T/, ' ').replace(/\..+/, '');
				evalVal =date ;
			}
			else{			
				evalVal = eval(val);
			}
		}catch(ex){
			evalVal = '';
		}
		if(evalVal == undefined || evalVal == null) {
			output = output + 'null' + constants.fieldDelimter;
		}else{
			output = output + evalVal + constants.fieldDelimter;
		} 
	}
	output = output.substr(0,output.length-1);
	return output;	
  };

}(jsonParsing));

module.exports = jsonParsing;
