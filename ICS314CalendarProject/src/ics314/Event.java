package ics314;

public class Event {
	private	String classification, comment, timezone, pro, id, start, end, stamp, sum, geograph, version;

	public Event(){
		classification = "CLASS:PUBLIC\r\n";
		comment = "";
		timezone = "";
		version = "VERSION:2.0\r\n";
		pro = "PRODID://ICS Team Project/Calendar/v1.0//EN\r\n";
		id = "UID:19970610T172345Z-AF23B2@example.com\r\n";
		stamp = "DTSTAMP:19970610T172345Z\r\n";
		start = "DTSTART:19970714T170000Z\r\n";
		end = "DTEND:19970715T040000Z\r\n";
		sum = "SUMMARY:Beach Day\r\n";
		geograph = "";
	}

	//constructor
	public Event(String cla, String comnt, String tzid, String prodid, String uid, String strt, String nd, String stmp, String sm, String geo){
		classification = cla;
		comment = comnt;
		timezone = tzid;
		pro = prodid;
		id = uid;
		start = strt;
		end = nd;
		stamp = stmp;
		sum = sm;
		geograph = geo;
	}

	//accessors

	public String getcla() {
		return classification;
	}
	public String getcomnt() {
		return comment;
	}
	public String gettzid() {
		return timezone;
	}
	public String getprodid() {
		return pro;
	}
	public String getuid() {
		return id;
	}
	public String getstrt() {
		return start;
	}
	public String getnd() {
		return end;
	}
	public String getstmp() {
		return stamp;
	}
	public String getsm() {
		return sum;
	}
	public String getgeo() {
		return geograph;
	}
	public String getVersion(){
		return version;
	}

	//mutators

	public void setcla(String cla){
		classification = cla;
	}
	public void setcomnt(String comnt){
		comment = comnt;
	}
	public void settzid(String tzid){
		timezone = tzid;
	}
	public void setprodid(String prodid){
		pro = prodid;
	}
	public void setuid(String uid){
		id = uid;
	}
	public void setstrt(String strt){
		start = strt;
	}
	public void setnd(String nd){
		end = nd;
	}
	public void setstmp(String stmp){
		stamp = stmp;
	}
	public void setsm(String sm){
		sum = sm;
	}
	public void setgeo(String geo){
		geograph = geo;
	}
	public void setVersion(String vers){
		version = vers;
	}
	
}
