package ics314;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Event implements Comparable<Event> {
	private String classification, 
				   comment, 
				   timezone, 
				   id, 
				   start, 
				   end, 
				   timeStamp, 
				   summary,
				   geograph, 
				   fileName;

	public Event() {
		this.classification = "";
		this.comment = "";
		this.timezone = "";
		// avoid possible usage of the same id
		String rand = "" + Math.random();
		String randString = rand.substring(0, 9);
		this.id = "UID:" + randString + "-AF23B2@example.com\r\n";
		this.timeStamp = "";
		this.start = "";
		this.end = "";
		this.summary = "";
		this.geograph = "";
	
	}

	// constructor
	public Event(String classification, String comment, 
			String timezone,
			String id, 
			String start, String end, 
			String timeStamp, String summary, 
			String geograph, String fileName) {
		//sets all fields to default by calling default constructor
		this();
		this.classification = classification;
		this.comment = comment;
		this.timezone = timezone;
		this.id = id;
		this.start = start;
		this.end = end;
		this.timeStamp = timeStamp;
		this.summary = summary;
		this.geograph = geograph;
		this.fileName = fileName;
	}

	public Event(File eventFile) throws IOException{
		this();	//sets all fields to default by calling default constructor
		this.fileName = eventFile.getAbsolutePath();
		FileReader fileReader = new FileReader(eventFile.getAbsoluteFile());
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = bufferedReader.readLine();
		while (line != null){
			
			if (line.startsWith("CLASS")){
				this.classification = line + "\r\n";
			}
			if (line.startsWith("GEO:")){
				this.geograph = line + "\r\n";
			}
			if (line.startsWith("SUMMARY:")){
				this.summary = line + "\r\n";
			}
			if (line.startsWith("DTSTART;TZID")){
				this.start = line + "\r\n";
			}
			if (line.startsWith("DTEND;TZID")){
				this.end = line + "\r\n";
			}
			if (line.startsWith("DTSTAMP")){
				this.timeStamp = line + "\r\n";
			}
			if (line.startsWith("UID:")){
				this.id = line + "\r\n";
			}

			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	// accessors

	public String getClassification() {
		return classification;
	}

	public String getComment() {
		return comment;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getId() {
		return id;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public String getSummary() {
		return summary;
	}

	public String getGeograph() {
		return geograph;
	}

	// mutators

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setGeograph(String geograph) {
		this.geograph = geograph;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileNameWithExtension() {
		if (this.fileName.endsWith(".ics")){
			return this.fileName;
		} else {
			return this.fileName + ".ics";
		}
	}
	
	public String getString(){
		return "BEGIN:VEVENT\r\n" 
				+ this.getClassification() 
				+ this.getId()
				+ this.getTimeStamp() 
				+ this.getStart() 
				+ this.getEnd()
				+ this.getSummary() 
				+ this.getGeograph() 
				+ this.getComment() 
				+ "END:VEVENT\r\n";
	}

	@Override
	public int compareTo(Event compareEvent) {
		//finds where date/time starts and converts to int
		int tIndex1 = this.start.lastIndexOf('T');
		String timeOnly1 = this.start.substring(tIndex1+1).trim();
		int tIndex2 = compareEvent.start.lastIndexOf('T');
		String timeOnly2 = this.start.substring(tIndex2+1).trim();
		int time1 = Integer.parseInt(timeOnly1);
		int time2 = Integer.parseInt(timeOnly2);
		//recalculates time start with time zone taken into account
		if (this.start.contains("Honolulu")){
			// do nothing we calculate from Honolulu
		}else if(this.start.contains("Los_Angeles")){
			time1 = time1 - 30000;
		}
		else if (this.start.contains("Chicago")){
			time1 = time1 - 50000;
		} else if(this.start.contains("New_York")){
			time1 = time1 - 60000;
		}else if(this.start.contains("Halifax")){
			time1 = time1 - 70000;
		}else if(this.start.contains("Denver")){
			time1 = time1 - 40000;
		}else if(this.start.contains("Anchorage")){
			time1 = time1 - 20000;
		}
		
		if (compareEvent.start.contains("Honolulu")){
			// do nothing we calculate from Honolulu
		}else if(this.start.contains("Los_Angeles")){
			time2 = time2 - 30000;
		}
		else if (compareEvent.start.contains("Chicago")){
			time2 = time2 - 50000;
		} else if(this.start.contains("New_York")){
			time2 = time2 - 60000;
		}else if(compareEvent.start.contains("Halifax")){
			time2 = time2 - 70000;
		}else if(compareEvent.start.contains("Denver")){
			time2 = time2 - 40000;
		}else if(compareEvent.start.contains("Anchorage")){
			time2 = time2 - 20000;
		}
		
		return Integer.compare(time1, time2);
	}



}
