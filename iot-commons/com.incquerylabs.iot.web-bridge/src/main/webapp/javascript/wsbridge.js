/**
 * Websocket bridge to IoT communication technologies.
 * 
 * @author Istvan Papp
 * 
 * Initial Javascript API and implementation.
 * 
 */
String.prototype.format = function() {
    var formatted = this;
    for (var arg in arguments) {
        formatted = formatted.replace("{" + arg + "}", arguments[arg]);
    }
    return formatted;
};

function WSConnection(type, host, port, topic) {
	
	this.type = type;
	this.host = host;
	this.port = port;
	this.topic = topic;

	this.socket = atmosphere;
	this.transport = 'websocket';
	
	this.request = { 
			url: "ws://127.0.0.1:8080/ws/{0}/{1}/{2}/{3}".format(this.type,this.host,this.port,this.topic),
	        contentType : "application/json",
	        logLevel : 'debug',
	        transport : this.transport ,
	        trackMessageLength : true,
	        reconnectInterval : 5000,
	        wsconnection: this
	     	};
	    
	WSConnection.prototype.connect = function() {
		this.clientSocket = this.socket.subscribe(this.request);		
	}

	this.request.onOpen = function(response) {
		console.log("Connection opened: " + response.request.uuid);
    };
	
    WSConnection.prototype.onMessageArrived = function(msg) {};
    
	this.request.onMessage = function(response) {
		if(this.wsconnection.onMessageArrived && typeof this.wsconnection.onMessageArrived == "function") {
			var msg = JSON.parse(response.responseBody);
			this.wsconnection.onMessageArrived(msg);
		}
	}
	
    this.request.onClose = function(response) {
	    console.log("Connection closed: " + response.responseBody);
	};
	
	this.request.onClientTimeout = function(response) {
        setTimeout(function (){
           this.connect();
        }, this.request.reconnectInterval);
    };
	
	WSConnection.prototype.publish = function(jsonMessage) {
		var strMessage = JSON.stringify(jsonMessage);
		console.log("Publishing message: " + strMessage);
		this.clientSocket.push(strMessage);
	}
}
