package server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import storage.EventStorage;
import sun.applet.Main;

public class SimpleHttpServer {
	
	private String urlPrefix;;
	private int portNumber;
	
	public SimpleHttpServer(int portNumber, String urlPrefix) {
		this.portNumber = portNumber;
		this.urlPrefix = urlPrefix;
	}

	public void startHttpListener() throws IOException{
		HttpServer server = HttpServer.create(new InetSocketAddress(portNumber), 0);
	    HttpContext context = server.createContext(urlPrefix);
	    context.setHandler(SimpleHttpServer::handleRequest);
	    server.start();
	    
	}
	
	private static void handleRequest(HttpExchange exchange) throws IOException {
		EventStorage instance = EventStorage.getInstance();
		String response = instance.getAllRecords();
		exchange.sendResponseHeaders(200, response.getBytes().length);//response code and length
		OutputStream os = exchange.getResponseBody();
		os.write(response.getBytes());
		os.close();
  }

}
