package ics314;

public class Calendar {
	private String name, prodid, version;
	Event[] events;// List of all events in calendar

	public Calendar() {
		this.name = "My Calendar";
		this.version = "VERSION:2.0\r\n";
		this.prodid = "PRODID://ICS Team Project/Calendar/v1.0//EN\r\n";
		this.events = new Event[1000];
	}

	// constructor
	public Calendar(String name, Event[] events) {
		this.name = name;
		this.version = "VERSION:2.0\r\n";
		this.prodid = "PRODID://ICS Team Project/Calendar/v1.0//EN\r\n";
		this.events = events;
	}

	// accessors
	public String getName() {
		return name;
	}

	public String getProdid() {
		return prodid;
	}

	public String getVersion() {
		return version;
	}

	public Event[] getEvents() {
		return events;
	}

	// mutators
	public void setName(String name) {
		this.name = name;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
