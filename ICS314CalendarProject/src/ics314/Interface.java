package ics314;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class Interface {
	
	public String name;
	Scanner scan = new Scanner(System.in);

	//Asks if you want to add an additional event
	public boolean askForAddAnother() {
		boolean addEvent = false;
		boolean unknownResponse = false;
		String description = "";
		while (description.equals("") || unknownResponse) {
			System.out.print("Did you want to add another event? Type YES or NO then press 'ENTER':  ");			
			description = scan.nextLine();
			if(description.equals("YES")){
				addEvent = true;
			}else{
				addEvent = false;
			}
		}
		return addEvent;
	}	
	
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
	
	public void askForCalendarName(Calendar myCalendar) {
		String tempName = "";
		while (tempName.equals("")) {
			System.out.print("Enter the name of the calendar file then press 'ENTER':  ");
			tempName = scan.nextLine();
		}
		myCalendar.setName(tempName); 
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
			System.out.print("Enter the end time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
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
	
	//if more than 1 ics file to compute Great Circle Distance
	public void greatCircleDist(Event event1, Event event2, Calendar cal) { //throws FileNotFoundException
		String latitude1, longitude1, latitude2, longitude2, fileName = "C:/" + cal.getName() + ".ics";
		/*
		File file = new File(fileName);
		FileReader rd = new FileReader(file.getAbsoluteFile());
		BufferedReader rdr = new BufferedReader(rd);
		FileWriter wt = new FileWriter(file.getAbsoluteFile());
		BufferedWriter wtr = new BufferedWriter(wt);
		*/
		
		String Line = new String();
		String current = new String();
		int eventNum = 1, i = 0;
		double lat1, lon1, lat2, lon2, distance, radianConverter = 3.14159/180, haversinea;
		//obtain strings and save to latitude1, longitude1, latitude2, longitude2
		//get to GEO line
		/*
		while((current = rdr.readLine()) != null) {
			if(current.startsWith("GEO:")) {
				Line = current;
				break;
			}
		}*/
		Line = event1.getgeo();
		//ignore ';' from string
		i = Line.indexOf(';');
		latitude1 = Line.substring(4, i-1); 
		longitude1 = Line.substring(i+1, Line.length()-1);
		eventNum++;
		/*
		while((current = rdr.readLine()) != null) {
			if(current.startsWith("GEO:")) {
				Line = current;
				break;
			}
		}*/
		Line = event2.getgeo();
		//ignore ';' from string
		i = Line.indexOf(';');
		latitude2 = Line.substring(4, i-1);
		longitude2 = Line.substring(i+1, Line.length()-1);
		eventNum++;
		//rdr.close();
		
		//convert String to float
		//try {
			lat1 = Double.parseDouble(latitude1);
		/*} catch (NumberFormatException e) {
			System.out.println("Error with translation!");
		}*/
		lon1 = Double.parseDouble(longitude1);
		lat2 = Double.parseDouble(latitude2);
		lon2 = Double.parseDouble(longitude2);
		lat1 = lat1 * radianConverter;
		lon1 = lon1 * radianConverter;
		lat2 = lat2 * radianConverter;
		lon2 = lon2 * radianConverter;
		
		//compute distance
		haversinea = Math.sin((lat1 - lat2)/2) * Math.sin((lat1 - lat2)/2) + Math.cos(lat1) * 
					 Math.cos(lat2) * Math.sin((lon1 - lon2)/2) * Math.sin((lon1 - lon2)/2);
		//6371000 is the earth's radius
		distance = 6371000 * 2 * Math.atan2(Math.sqrt(haversinea), Math.sqrt(1 - haversinea));
		
		 //set comment; not sure if will set at all or not
		event1.setcomnt("COMMENT:Great Circle Distance from this event to the second: " + distance + "\r\n");
	}
}