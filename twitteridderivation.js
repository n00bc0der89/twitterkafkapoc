/*
https://code.google.com/p/gmaps-api-issues/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Introduced%20Fixed%20Summary%20Stars%20ApiType%20Internal&groupby=&sort=&id=8581
http://stackoverflow.com/questions/32462853/google-places-api-popular-times
*/

const mysql      = require('mysql');
const Twitter = require('twitter');

const connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'root',
  database : 'grosvenor'
});

const client = new Twitter({
	consumer_key: 'wlH22cmnaxOEo9bC2GLHIXUJK',
	consumer_secret: 'UjGDqPsdKGu06POFTgS7xTyOYYkLYhx8uPgY4dBOZRvXyVU2uY',
	access_token_key: '1469634289-a5kJCSPwWkEUrdb3L9s9hIOGHykjBEhF3q2WKIJ',
	access_token_secret: 'CDci8r063MbRYx2T6gQyTbWBqvS2vMrv38g8thH9pwfp8'		
});

connection.connect();

connection.query('SELECT screen_name FROM twitterlist WHERE twitterid IS NULL LIMIT 200', function(err, rows, fields) {
	if (err){
		throw err;
	}
	for(let i=0;i<rows.length;i++){
		console.log(rows[i].screen_name);
		client.get('users/show', {screen_name:rows[i].screen_name} ,function(error, tweets, response) {
			if(error){
				console.log(error);
			}else{
				connection.query('UPDATE twitterlist SET twitterid = "'+JSON.parse(response.body).id_str+'" WHERE screen_name = "'+rows[i].screen_name+'";', function(err, rows, fields) {
					if (err) throw err;
				});
			}
		});			
	};
});

setTimeout(function(){
connection.end();
process.exit(0);
}, 10000);