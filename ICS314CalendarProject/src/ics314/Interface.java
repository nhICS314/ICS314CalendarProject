package ics314;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.io.File;

public class Interface {
	
	public String name;
	Scanner scan = new Scanner(System.in);

	public ArrayList<File> askForDirectoryOfIcsFiles(){
		
		boolean noFilesFound = false;
		String inputDirectory = "";
		while (inputDirectory.equals("") || noFilesFound) {
			System.out.print("Update ICS files with Geo Comments. Type the full directory path to do so, or NO to quit.  Then press 'ENTER':  ");			
			inputDirectory = scan.nextLine();
			
			if(inputDirectory.equalsIgnoreCase("no")){
				System.exit(0);
			}
		}
		
		File directory = new File(inputDirectory);
		if (!directory.isDirectory()){
			System.out.println("ERROR.  " + directory + " is not a directory");
			System.exit(0);
		} 
		
		File[] allFiles = directory.listFiles();
		ArrayList<File> fileList = new ArrayList<File>();
		for (File file: allFiles){
			if (file.isFile()){
				if (file.getName().endsWith(".ics")){
					fileList.add(file);
				}
			}
		}
		
		return fileList;
		
	}
	
	//Asks if you want to add an additional event
	
	public boolean askForAddAnother() {
		boolean addEvent = false;
		boolean unknownResponse = false;
		String description = "";
		while (description.equals("") || unknownResponse) {
			System.out.print("Did you want to add another event? Type YES or NO then press 'ENTER':  ");			
			description = scan.nextLine();
			if(description.equalsIgnoreCase("YES")){
				addEvent = true;
				unknownResponse = false;
			}else if(description.equalsIgnoreCase("no")){
				addEvent = false;
				unknownResponse = false;
			}else{
				unknownResponse = true;
			}
		}
		return addEvent;
	}	
	
	public boolean askToUpdateDistances() {
		boolean updateDistances = false;
		boolean unknownResponse = false;
		String userInput = "";
		while (userInput.equals("") || unknownResponse) {
			System.out.print("Did you want to update distances in existing ICS files? Type YES or NO. NO will let you create ICS files  ");			
			userInput = scan.nextLine();
			if(userInput.equalsIgnoreCase("YES")){
				updateDistances = true;
				unknownResponse = false;
			}else if(userInput.equalsIgnoreCase("no")){
				updateDistances = false;
				unknownResponse = false;
			}else{
				unknownResponse = true;
			}
		}
		return updateDistances;
	}
	
	// sets Classification to user specification
	public void askForClassification(Event event) {
		
		
		boolean addClassification = false;
		boolean unknownResponse = false;
		String userInput = "";
		while (userInput.equals("") || unknownResponse) {
			System.out.print("Did you want to add CLASSIFICATION to this event? Type YES or NO then press 'ENTER':  ");			
			userInput = scan.nextLine();
			if(userInput.equalsIgnoreCase("YES")){
				addClassification = true;
				unknownResponse = false;
			}else if(userInput.equalsIgnoreCase("no")){
				addClassification = false;
				unknownResponse = false;
			}else{
				unknownResponse = true;
			}
		}
		
		if (!addClassification){
			return; // don't have to add if they don't want to
		}
		
		String classification = "";
		while (classification.equals("")) {
			System.out.println("Choose the Classification level for the event");
			System.out.print("Enter one of the following, PUBLIC, PRIVATE, or CONFIDENTIAL then press 'ENTER':  ");
			classification = scan.nextLine();
			if (classification.equalsIgnoreCase("PUBLIC")) {
				event.setClassification("CLASS:PUBLIC\r\n");
			} else if (classification.equalsIgnoreCase("PRIVATE")) {
				event.setClassification("CLASS:PRIVATE\r\n");
			} else if (classification.equalsIgnoreCase("CONFIDENTIAL")) {
				event.setClassification("CLASS:CONFIDENTIAL\r\n");
			} else {
				classification = "";
				System.out.println("That is not a valid entry.");
			}
		}
	}
	
	//creates geo position for event using latitude and longitude coordinates
	public void askForLocation(Event event) { //does not check for exactly 6 digits after decimal place!
	
		boolean addLocation = false;
		boolean unknownResponse = false;
		String userInput = "";
		while (userInput.equals("") || unknownResponse) {
			System.out.print("Did you want to add location to this event? Type YES or NO then press 'ENTER':  ");			
			userInput = scan.nextLine();
			if(userInput.equalsIgnoreCase("YES")){
				addLocation = true;
				unknownResponse = false;
			}else if(userInput.equalsIgnoreCase("no")){
				addLocation = false;
				unknownResponse = false;
			}else{
				unknownResponse = true;
			}
		}
		
		if (!addLocation){
			return; // don't have to add if they don't want to
		}
		
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
		scan.nextLine();  //need this to clear buffer 
		event.setGeograph("GEO:" + latitude + ";" + longitude + "\r\n");
	}
	
	public void askForDescription(Event event) {
		String description = "";
		while (description.equals("")) {
			System.out.print("Enter the name of the event then press 'ENTER':  ");
			description = scan.nextLine();
			}
		event.setSummary("SUMMARY:" + description + "\r\n");
		name = description;
	}
	
	public void askForFileName(Event event) {
		String tempName = "";
		while (tempName.equals("")) {
			System.out.print("Enter the name of the calendar file then press 'ENTER':  ");
			tempName = scan.nextLine();
		}
		event.setFileName(tempName); 
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
			System.out.print("Which timezone is this taking place-USA only(HST, PST, CST, EST, AST, MST, AKST):  ");
			timezone = scan.next();
			tZID = Interface.findTimeZone(timezone);
		}
		event.setTimezone(tZID);
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
				System.out.println("'" + month + "' is not a valid entry!");
			}
		}
		while (dy == null || dy < 1 || dy > 31) {
			System.out.print("Enter the day of the month for the event using two digits (ex: 01):  ");
			day = scan.next();
			try {
				dy = Integer.parseInt(day);
			} catch (NumberFormatException e) {
				System.out.println("'" + day + "' is not a valid entry!");
			}
		}
		while (yr == null || yr < 1000 || yr > 9999) {
			System.out.print("Enter the year for the event using 4 digits (ex: 2012):  ");
			year = scan.next();
			try {
				yr = Integer.parseInt(year);
			} catch (NumberFormatException e) {
				System.out.println("'" + year + "' is not a valid entry!");
			}
		}
		while (tm == null || tm < 0 || tm > 2400) {
			System.out.print("Enter the start time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
			time = scan.next();
			try {
				tm = Integer.parseInt(time);
			} catch (NumberFormatException e) {
				System.out.println("'" + time + "' is not a valid entry!");
			}
		}
		start = year + month + day + "T" + time + "00";
		event.setStart("DTSTART;TZID" + event.getTimezone() + start + "\r\n");
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
				System.out.println("'" + month + "' is not a valid entry!");
			}
		}
		while (dy == null || dy < 1 || dy > 31) {
			System.out.print("Enter the day of the month for the event using two digits (ex: 01):  ");
			day = scan.next();
			try {
				dy = Integer.parseInt(day);
			} catch (NumberFormatException e) {
				System.out.println("'" + day + "' is not a valid entry!");
			}
		}
		while (yr == null || yr < 1000 || yr > 9999) {
			System.out.print("Enter the year for the event using 4 digits (ex: 2012):  ");
			year = scan.next();
			try {
				yr = Integer.parseInt(year);
			} catch (NumberFormatException numberFormatException) {
				System.out.println("'" + year + "' is not a valid entry!");
			}
		}
		while (tm == null || tm < 0 || tm > 2400) {
			System.out.print("Enter the end time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
			time = scan.next();
			try {
				tm = Integer.parseInt(time);
			} catch (NumberFormatException e) {
				System.out.println("'" + time + "' is not a valid entry!");
			}
		}
		
		scan.nextLine(); // clear the buffer
		end = year + month + day + "T" + time + "00";
		event.setEnd("DTEND;TZID" + event.getTimezone() + end + "\r\n");
	}
	
	public void getDStamp(Event event){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		event.setTimeStamp("DTSTAMP:" + dateFormat.format(date)+ "T" + timeFormat.format(date) + "Z\r\n");
	}
	
//	Your system will compute the great circle distance, in statue miles and kilometers, from the location of EventN to EventNplusOne, and record that in the comment field of EventN.
//	Similarly, your system will compute the great circle distance, in statue miles and kilometers, from the location of EventNplusOne to EventNplusTwo, and record that in the comment field of EventNplusOne.
//	The idea is to give the user an idea how far apart their events are (can they walk? Bus? Drive? Fly?)
//	But I said that location was optional for events. What if there is only one event? What do you do with the last event of the day? What are you going to do then? You’ll have to think about it and come up with something reasonable.
	
	//if more than 1 ics file to compute Great Circle Distance
	public void greatCircleDist(Event event1, Event event2) { 
		String latitude1, longitude1, 
			   latitude2, longitude2;
		
		// get the coordinates out of the two events
		
		String geo1 = new String();
		String geo2 = new String();
		int  i = 0;
		double lat1, lon1, lat2, lon2;

	
		// cannot calculate unless both geos are there
		
		geo1 = event1.getGeograph();
		if (geo1 == null || geo1.isEmpty()){
			event1.setComment("COMMENT: Cannot calculate Great Circle Distance from this event to the next because no GEO set on this event \r\n");
			return;
		}
		
		geo2 = event2.getGeograph();
		if (geo2 == null || geo2.isEmpty()){
			event1.setComment("COMMENT: Cannot calculate Great Circle Distance from this event to the next because no GEO set on the next event \r\n");
			return;
		}
		
		//ignore ';' from string
		i = geo1.indexOf(';');
		latitude1 = geo1.substring(4, i-1); 
		longitude1 = geo1.substring(i+1, geo1.length()-1);
		
		//ignore ';' from string
		i = geo2.indexOf(';');
		latitude2 = geo2.substring(4, i-1);
		longitude2 = geo2.substring(i+1, geo2.length()-1);
		
		lat1 = Double.parseDouble(latitude1);
		lon1 = Double.parseDouble(longitude1);
		lat2 = Double.parseDouble(latitude2);
		lon2 = Double.parseDouble(longitude2);
		
		double kilometers = calculateGreatCircleDistance(lat1, lon1, lat2, lon2);
		double miles = convertKilometersToMiles(kilometers);
		event1.setComment("COMMENT:Great Circle Distance from this event to the next: " + miles + " miles or " + kilometers + " in km \r\n");
	}

	public static double calculateGreatCircleDistance(double lat1, double lon1, double lat2, double lon2) {
		double distance, 
		   radianConverter = 3.14159/180, 
		   haversinea;
		lat1 = lat1 * radianConverter;
		lon1 = lon1 * radianConverter;
		lat2 = lat2 * radianConverter;
		lon2 = lon2 * radianConverter;
		
		//compute distance
		haversinea = Math.sin((lat1 - lat2)/2) * Math.sin((lat1 - lat2)/2) + Math.cos(lat1) * 
					 Math.cos(lat2) * Math.sin((lon1 - lon2)/2) * Math.sin((lon1 - lon2)/2);
		//6371 is the earth's radius in km
		distance = 6371 * 2 * Math.atan2(Math.sqrt(haversinea), Math.sqrt(1 - haversinea));
		return distance;
	}

	public double convertKilometersToMiles(double km){
		return km * 0.621371;
	}
	
}