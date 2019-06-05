package storage;

public class EventData {
	
	private String event_type;
	
	private String data;
	
	public EventData(String event_type, String data) {
		super();
		this.event_type = event_type;
		this.data = data;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
