/**
 * Websocket bridge to IoT communication technologies.
 * 
 * @author Papp Istvan
 * 
 * Initial javascript api and implementation.
 * 
 */

$(
	function () {
		
	    var socket = atmosphere;
	    var transport = 'websocket';
	    var clientSocket;
	    
	    var endpoint = '2/127.0.0.1/5555/framestream';
	    
	    var request = { url: 'ws://127.0.0.1:8080/ws/' + endpoint,
	            contentType : "text/plain",
	            logLevel : 'debug',
	            transport : transport ,
	            trackMessageLength : true,
	            reconnectInterval : 5000 };
	    
	    request.onOpen = function(response) {
	        transport = response.transport;
	        // Carry the UUID. This is required if you want to call subscribe(request) again.
	        request.uuid = response.request.uuid;
	        console.log("Connection opened: " + request.uuid);
	    };

	    request.onMessage = function (response) {

	        var message = JSON.parse(response.responseBody);
	        
	        console.log(message);
	    };
	    
	    request.onClose = function(response) {
	    	console.log("Connection closed: " + response.responseBody);
	    }
	    
	    clientSocket = socket.subscribe(request);
	    
});