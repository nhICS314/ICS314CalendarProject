package ics314;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Timestamp;
import java.util.stream.Stream;

public class Event implements Comparable<Event> {
	private String classification, comment, timezone, id, start, end, timeStamp, summary,
			geograph, fileName;



	public Event() {
		this.classification = "CLASS:PUBLIC\r\n";
		this.comment = "";
		this.timezone = "";
		// May want to use different code than random to avoid possible usage of the same id
		String rand = "" + Math.random();
		this.id = "UID:" + rand.substring(0, 9) + "-AF23B2@example.com\r\n";
		this.timeStamp = "DTSTAMP:19970610T172345Z\r\n";
		this.start = "DTSTART:19970714T170000Z\r\n";
		this.end = "DTEND:19970715T040000Z\r\n";
		this.summary = "SUMMARY:Beach Day\r\n";
		this.geograph = "";
		this.fileName = "sample.ics";
	}

	// constructor
	public Event(String classification, String comment, 
			String tzid, String prodid,
			String uid, 
			String start, String end, 
			String timeStamp, String summary, 
			String geo, String fileName) {
		this.classification = classification;
		this.comment = comment;
		this.timezone = tzid;
		this.id = uid;
		this.start = start;
		this.end = end;
		this.timeStamp = timeStamp;
		this.summary = summary;
		this.geograph = geo;
		this.fileName = fileName;
	}

	public Event(File eventFile) throws IOException{
	
		this.fileName = eventFile.getName();
		FileReader rd = new FileReader(eventFile.getAbsoluteFile());
		BufferedReader rdr = new BufferedReader(rd);
		//Stream lines = rdr.lines();
		String line = rdr.readLine();
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
			
			
			
			
			
			line = rdr.readLine();
		}
	}
	
	// accessors

	public String getClassification() {
		return classification;
	}

	public String getComment() {
		return comment;
	}

	public String gettzid() {
		return timezone;
	}

	public String getUid() {
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

	public String getGeo() {
		return geograph;
	}

	// mutators

	public void setcla(String cla) {
		classification = cla;
	}

	public void setcomnt(String comnt) {
		comment = comnt;
	}

	public void settzid(String tzid) {
		timezone = tzid;
	}

	public void setuid(String uid) {
		id = uid;
	}

	public void setstrt(String strt) {
		start = strt;
	}

	public void setnd(String nd) {
		end = nd;
	}

	public void setstmp(String stmp) {
		timeStamp = stmp;
	}

	public void setsm(String sm) {
		summary = sm;
	}

	public void setgeo(String geo) {
		geograph = geo;
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
		return "BEGIN:VEVENT\r\n" + this.getClassification() + this.getUid()
				+ this.getTimeStamp() + this.getStart() + this.getEnd()
				+ this.getSummary() + this.getGeo() + this.getComment() + "END:VEVENT\r\n";
	}

	@Override
	public int compareTo(Event o) {
		return this.getStart().compareTo(o.getStart());
	}



}
