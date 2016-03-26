package ics314;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Interface {
	
	public String name;
	Scanner scan = new Scanner(System.in);

	// sets Classification to user specification
	public void askForClassification(Event event) {
		String classification = "";
		while (classification == "") {
			System.out.println("Choose the Classification level for the event");
			System.out.print("Enter one of the following, PUBLIC, PRIVATE, or CONFIDENTIAL then press 'ENTER':  ");
			classification = scan.nextLine();
			if (classification.equalsIgnoreCase("PUBLIC")) {
				event.setcla("CLASS:PUBLIC\r\n");
			} else if (classification.equalsIgnoreCase("PRIVATE")) {
				event.setcla("CLASS:PRIVATE\r\n");
			} else if (classification.equalsIgnoreCase("CONFIDENTIAL")) {
				event.setcla("CLASS:CONFIDENTIAL\r\n");
			} else {
				classification = "";
				System.out.println("That is not a valid entry.");
			}
		}
	}
	
	//creates geo position for event using latitude and longitude coordinates
	public void askForLocation(Event event) { //does not check for exactly 6 digits after decimal place!
		float latitude = 181;
		float longitude = 181;
		while (latitude < -180 || latitude > 180) {
			System.out.print("Enter the latitude up to 6 decimal places for the event then press 'ENTER':  ");
			latitude = scan.nextFloat();
			if(latitude < -180 || latitude > 180) {
				System.out.println("The latitude must be within the range of +/-180 and up to 6 decimals.");
			}
		}
		while (longitude < -180 || longitude > 180) {
			System.out.print("Enter the longitude up to 6 decimal places for the event then press 'ENTER':  ");
			longitude = scan.nextFloat();
			if(longitude < -180 || longitude > 180) {
				System.out.println("The longitude must be within the range of +/-180 and up to 6 decimals.");
			}
		}
		event.setgeo("GEO:" + latitude + ";" + longitude + "\r\n");
	}
	
	public void askForDescription(Event event) {
		String description = "";
		while (description.equals("")) {
			System.out.print("Enter the name of the event then press 'ENTER':  ");
			description = scan.nextLine();
			}
		event.setsm("SUMMARY:" + description + "\r\n");
		name = description;
	}
	
	public static String findTimeZone(String timezone){
		String zoneID = "";
		if (timezone.equalsIgnoreCase("HST")){
			zoneID = "=Pacific/Honolulu:";
		}else if(timezone.equalsIgnoreCase("PST")){
			zoneID = "=America/Los_Angeles:";
		}else if(timezone.equalsIgnoreCase("CST")){
			zoneID = "=America/Chicago:";
		}else if(timezone.equalsIgnoreCase("EST")){
			zoneID = "=America/New_York:";
		}else if(timezone.equalsIgnoreCase("AST")){
			zoneID = "=America/Halifax:";
		}else if(timezone.equalsIgnoreCase("MST")){
			zoneID = "=America/Denver:";
		}else if(timezone.equalsIgnoreCase("AKST")){
			zoneID = "=America/Anchorage:";
		}
		return zoneID;
	}
	
	public void askForTimeZone(Event event){
		String timezone = "";
		String tZID = "";
		while (tZID.equals("")){
			System.out.print("Which timezone is this taking place-USA only(example: HST, PST):  ");
			timezone = scan.next();
			tZID = Interface.findTimeZone(timezone);
		}
		event.settzid(tZID);
	}
	
	public void askForStartTime(Event event) {
		String month = "";
		String day = ""; 
		String year = "";
		String time = "";
		String start = "";
		Integer mnth = null, dy = null, yr = null, tm = null;
		System.out.println("Create the start date and time for the event.");
		while (mnth == null || mnth < 1 || mnth > 12) {
			System.out.print("Enter the month for the event using two digits (ex: 07):  ");
			month = scan.next();
			try {
				mnth = Integer.parseInt(month);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		while (dy == null || dy < 1 || dy > 31) {
			System.out.print("Enter the day of the month for the event using two digits (ex: 01):  ");
			day = scan.next();
			try {
				dy = Integer.parseInt(day);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		while (yr == null || yr < 1000 || yr > 9999) {
			System.out.print("Enter the year for the event using 4 digits (ex: 2012):  ");
			year = scan.next();
			try {
				yr = Integer.parseInt(year);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		while (tm == null || tm < 0 || tm > 2400) {
			System.out.print("Enter the start time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
			time = scan.next();
			try {
				tm = Integer.parseInt(time);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		start = year + month + day + "T" + time + "00";
		event.setstrt("DTSTART;TZID" + event.gettzid() + start + "\r\n");
	}
	
	public void askForEndTime(Event event) {
		String month = "";
		String day = "";
		String year = "";
		String end = "";
		String time = "";
		Integer mnth = null, dy = null, yr = null, tm = null;
		System.out.println("Create the end date and time for the event.");
		while (mnth == null || mnth < 1 || mnth > 12) {
			System.out.print("Enter the month for the event using two digits (ex: 07):  ");
			month = scan.next();
			try {
				mnth = Integer.parseInt(month);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		while (dy == null || dy < 1 || dy > 31) {
			System.out.print("Enter the day of the month for the event using two digits (ex: 01):  ");
			day = scan.next();
			try {
				dy = Integer.parseInt(day);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		while (yr == null || yr < 1000 || yr > 9999) {
			System.out.print("Enter the year for the event using 4 digits (ex: 2012):  ");
			year = scan.next();
			try {
				yr = Integer.parseInt(year);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		while (tm == null || tm < 0 || tm > 2400) {
			System.out.print("Enter the start time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
			time = scan.next();
			try {
				tm = Integer.parseInt(time);
			} catch (NumberFormatException e) {
				System.out.println("This is not a valid entry!");
			}
		}
		end = year + month + day + "T" + time + "00";
		event.setnd("DTEND;TZID" + event.gettzid() + end + "\r\n");
	}
	
	public void getDStamp(Event event){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		event.setstmp("DTSTAMP:" + dateFormat.format(date)+ "T" + timeFormat.format(date) + "Z\r\n");
	}
}