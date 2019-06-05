package storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventStorage {
	
	public static EventStorage instance;
	
	private Map<String, Map<String, Integer>>  eventsCounterMap;

	private EventStorage() {
		eventsCounterMap = new ConcurrentHashMap<String,Map<String, Integer>>();
	}
	
	private boolean isEventTypeExist(EventData eventData){
		if (eventsCounterMap.containsKey(eventData.getEvent_type())){
			return true;
		}
		return false;
	}
	
	public void updateEventCounter(EventData eventData){
		Map<String, Integer> dataCounter;
		if (isEventTypeExist(eventData)){
			dataCounter = eventsCounterMap.get(eventData.getEvent_type());
			if (dataCounter.containsKey(eventData.getData())){
				Integer counter = dataCounter.get(eventData.getData());
				counter++;
				dataCounter.put(eventData.getData(), counter);
			}else{
				dataCounter.put(eventData.getData(), 1);
			}
		}else{
			dataCounter = new ConcurrentHashMap<String, Integer>();
			dataCounter.put(eventData.getData(), 1);
			
		}
		eventsCounterMap.put(eventData.getEvent_type(), dataCounter);
	}
	
	public String getAllRecords(){
		StringBuilder builder = new StringBuilder();
		for (String eventType : eventsCounterMap.keySet()){
			builder.append(eventType + " : ");
			Map<String, Integer> eventDataMap = eventsCounterMap.get(eventType);
			for (String eventDataKey : eventDataMap.keySet()){
				Integer counter = eventDataMap.get(eventDataKey);
				builder.append(eventDataKey).append(" -> ").append(counter).append(" , ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	
	public static EventStorage getInstance(){
		if(instance == null){
	        synchronized (EventStorage.class) {
	            if(instance == null){
	                instance = new EventStorage();
	            }
	        }
	    }
		return instance;
	}

	
	
	
	
}
