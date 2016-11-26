/*
https://code.google.com/p/gmaps-api-issues/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Introduced%20Fixed%20Summary%20Stars%20ApiType%20Internal&groupby=&sort=&id=8581
http://stackoverflow.com/questions/32462853/google-places-api-popular-times
*/

const Twitter = require('twitter');
const config = require('./config');
const _ = require('underscore'); 
const mysql      = require('mysql');
const members = config.members;
const maxMembers =2000;
const businessHandles = [];

const client = new Twitter({
	consumer_key: 'wlH22cmnaxOEo9bC2GLHIXUJK',
	consumer_secret: 'UjGDqPsdKGu06POFTgS7xTyOYYkLYhx8uPgY4dBOZRvXyVU2uY',
	access_token_key: '1469634289-a5kJCSPwWkEUrdb3L9s9hIOGHykjBEhF3q2WKIJ',
	access_token_secret: 'CDci8r063MbRYx2T6gQyTbWBqvS2vMrv38g8thH9pwfp8'		
});

const connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'root',
  database : 'grosvenor'
});
connection.connect();

function deduplicate(){
	let uniqueBusinessHandles = _.uniq(businessHandles);
	for(let k=0;k<uniqueBusinessHandles.length;k++){
		connection.query('INSERT INTO twitterlist (screen_name) values("'+uniqueBusinessHandles[k]+'")', function(err, rows, fields) {
			if (err) throw err;
		});
	}
}

for(let i=0;i<members.length;i++){
	client.get('lists/show', {slug:members[i].slug,owner_screen_name:members[i].owner_screen_name} ,function(error, tweets, response) {
        if(error){
			console.log(error);
		}
		client.get('lists/members', {count:maxMembers,list_id:JSON.parse(response.body).id,slug:members[i].slug,owner_screen_name:members[i].owner_screen_name} ,function(error, tweets, result) {
			if(error){
				console.log(error);
			}
			let res = JSON.parse(result.body);
			for(let j=0;j<res.users.length;j++){
				businessHandles.push(res.users[j].screen_name);
			};
		});				
	});
}

setTimeout(deduplicate, 60000);