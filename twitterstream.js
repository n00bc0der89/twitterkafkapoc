/*
https://code.google.com/p/gmaps-api-issues/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Introduced%20Fixed%20Summary%20Stars%20ApiType%20Internal&groupby=&sort=&id=8581
http://stackoverflow.com/questions/32462853/google-places-api-popular-times
*/

const Twitter 	= require('twitter');
const kafka 		= require('kafka-node'),
Producer 		= kafka.Producer,
kafkaClient 			= new kafka.Client('localhost:2181'),
producer 		= new Producer(kafkaClient);
const mysql      	= require('mysql');
let twitterIdList = "";

const connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'root',
  database : 'grosvenor'
});
connection.connect();

const client = new Twitter({
	consumer_key: 'wlH22cmnaxOEo9bC2GLHIXUJK',
	consumer_secret: 'UjGDqPsdKGu06POFTgS7xTyOYYkLYhx8uPgY4dBOZRvXyVU2uY',
	access_token_key: '1469634289-a5kJCSPwWkEUrdb3L9s9hIOGHykjBEhF3q2WKIJ',
	access_token_secret: 'CDci8r063MbRYx2T6gQyTbWBqvS2vMrv38g8thH9pwfp8'		
});

connection.query('SELECT twitterid FROM twitterlist WHERE twitterid IS NOT null', function(err, rows, fields) {
	if (err){
		throw err;
	}
	for(let i=0;i<rows.length;i++){
		twitterIdList = twitterIdList + rows[i].twitterid + ',';
	};
	twitterIdList = twitterIdList.substr(0,twitterIdList.length-1);
	startstreaming();
});

function startstreaming(){
	client.stream('statuses/filter', {follow: twitterIdList}, function(stream) {
		stream.on('data', function(tweet) {
		//console.log(tweet);
			if(tweet.text){
				/*
				var present = false;
				var present1 = false;
				var present2 = false;
				var sTweet = tweet.text.toString().toLowerCase();
				present = sTweet.includes("grosvenor");
				present1 = sTweet.includes("mayfair");
				present2 = sTweet.includes("belgravia");			
				if(present || present1 || present2){
				*/
					payloads = [{	 topic: 'kafkasink2', messages: JSON.stringify(tweet), partition: 0 }];
					producer.send(payloads, function (err, data) {
						console.log('Pushed Successfully');
					});
				//}
			}
		});
	});
}

/*   
client.stream('statuses/filter', {locations: '18.89,72.77,19.27,72.98'}, function(stream) {
	stream.on('data', function(tweet) {
		console.log(tweet);
	});
	
	stream.on('error', function(error) {
		console.log(error);
	});
});
*/