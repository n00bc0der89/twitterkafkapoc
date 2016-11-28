var Kafka = require('kafka-node');

var HighLevelConsumerKafka = Kafka.HighLevelConsumer;
var OffsetKafka = Kafka.Offset;
var ClientKafka = Kafka.Client;
var clientKafka = new ClientKafka('localhost:2181', 'kafkaConsumer_'+process.pid);
var payloads = [ { topic: 'kafkasink2' }];

var options = {
	groupId: 'consumer-node-group',
	autoCommit: true,
	autoCommitMsgCount: 100,
	autoCommitIntervalMs: 5000,
	fetchMaxWaitMs: 100,
	fetchMinBytes: 1,
	fetchMaxBytes: 1024 * 10,
	fromOffset: true,
	fromBeginning: false
};

//TODO : Implement Low Level Kafka Client
var consumerKafka = new HighLevelConsumerKafka(clientKafka, payloads, options);
var offsetKafka = new OffsetKafka(clientKafka);

consumerKafka.on('message', function(message) {
	console.log(message.value);
});

consumerKafka.on('error', function(messerrage) {
	console.log(err);
});