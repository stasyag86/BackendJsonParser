import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import server.SimpleHttpServer;
import storage.EventData;
import threads.EventParserTask;

public class Main {
	
	private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
	private static final String file_path = "C:\\Users\\Stas\\Desktop\\BigPanda\\generator-windows-amd64.exe";
	private static final int httpServerPort = 3000;
	private static final String urlPrefix = "/getallevents";

	public static void main(String[] args) {
		SimpleHttpServer httpServer = new SimpleHttpServer(httpServerPort,urlPrefix);
		startGenerator();
		try {
			httpServer.startHttpListener();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Http error", e);
		}
		
		
	}
	
	
	public static void startGenerator(){
		 ExecutorService pool = Executors.newSingleThreadExecutor();
		
		 ProcessBuilder processBuilder = new ProcessBuilder();
		 processBuilder.command(file_path);
		 try {
	            Process process = processBuilder.start();
	            EventParserTask task = new EventParserTask(process.getInputStream());
	            Future<EventData> future = pool.submit(task);

		 	} catch (Exception e) {
		 		LOGGER.log(Level.SEVERE, "Error", e);
	        } finally {
	            pool.shutdown();
	        }
	}

}
