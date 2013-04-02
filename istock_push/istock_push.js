/**
	name: istock_push
	author: yangshengcheng
	date: 2013-3-31
**/

function istock_push(istockHost,istockPort,register,recHost,recPort,msg,event)
{
	var fs = require('fs');
	var net=require('net');

	/*try to register for stock quote update news*/
	var stat = 'fail';
	var client = net.connect(istockPort,istockHost);
	client.setEncoding('utf-8');
	client.on('error',function(){
		stat='fail';
	});

	client.on('connect',function()
	{
		client.write(register+'#'+recHost+'#'+ recPort+'\n\r');
	});

	client.on('data',function(data)
	{
		data = data.replace('\n','');
		if(data == 're_success')
		{
			stat = 'success';
		}
	});
	client.on('close', function() {
		console.log('register status '+ stat);
	});


	/* now start to receive istock's update quote */
	var socket_server = net.createServer(function(sock)
	{
		sock.setEncoding('utf-8');
		sock.on('data',function(data){
			global.msg = data;
			event.emit('stock');
			//console.log(data);
	});

	sock.on('close',function(){
		//console.log('istock close for this transfer!');
	});
	}).listen(recPort,recHost);

	return function(req,res,next)
	{		
		next();
	};
	

}

module.exports=istock_push;