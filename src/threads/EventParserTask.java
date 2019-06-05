package threads;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import storage.EventData;
import storage.EventStorage;


public class EventParserTask implements Callable<EventData>{
	
	private static final Logger LOGGER = Logger.getLogger(EventParserTask.class.getName());
	private InputStream inputStream;

    public EventParserTask(InputStream inputStream) {
        this.inputStream = inputStream;
    }

	@Override
	public EventData call() throws Exception {
		EventStorage eventStorage = EventStorage.getInstance();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
        	System.out.println(line);
        	try {
        		Gson g = new Gson();
            	EventData eventData = g.fromJson(line, EventData.class);
            	eventStorage.updateEventCounter(eventData);
            	//System.out.println(eventData.getEvent_type());
            	//System.out.println(eventData.getData());
			} catch (JsonParseException  e) {
				 LOGGER.info("corrupted JSON: " + line);
			}
        	
        }
		
		return null;
	}

}
