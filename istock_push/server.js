/**
	name: istock_push
	author: yangshengcheng
	date: 2013-3-31
**/

var connect = require('connect'),
    http = require('http'),
    wsio = require('socket.io'),
	fs = require('fs');


var EventEmitter = require('events').EventEmitter;
var event = new EventEmitter();

global.msg = '';

var istockHost = '172.16.11.102';
var istockPort = '9008';
var register = 'gfzq';
var middlewareHost = '172.16.11.110';
var middlewarePort = '9001';
var webPort = '8888';


// the istock middleware 
var istock_push = require('./istock_push');

var app = connect().use(istock_push(istockHost,istockPort,register,middlewareHost,middlewarePort,msg,event)).use(function(req, res){
	res.writeHead(200, { 'Content-type': 'text/html' });
	res.end(fs.readFileSync('./original.htm'));
  });

var server =http.createServer(app);

server.listen(webPort, function()
{
    console.log('Server listening at http://localhost:8888/');
});

// Attach the socket.io server
io = wsio.listen(server);
// Define a message handler
io.sockets.on('connection', function(socket) 
{
	event.on('stock',function()
	{
		socket.send(global.msg);
	});
});

