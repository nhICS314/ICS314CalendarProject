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
		//Scanner scan = new Scanner(System.in);
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
	//	scan.close();
	}
	
	//creates geo position for event using latitude and longitude coordinates
	public void askForLocation(Event event) {
		float latitude = 0;
		float longitude = 0;
		//Scanner scan = new Scanner(System.in);
		while (latitude == 0 || longitude == 0) {
			System.out.print("Enter the latitude up to 6 decimal places for the event then press 'ENTER':  ");
			latitude = scan.nextFloat();
			System.out.print("Enter the longitude up to 6 decimal places for the event then press 'ENTER':  ");
			longitude = scan.nextFloat();
			}
		event.setgeo("GEO:" + latitude + ";" + longitude + "\r\n");
		//scan.close();
	}
	
	public void askForDescription(Event event) {
		String description = "";
		//Scanner scan = new Scanner(System.in);
		while (description.equals("")) {
			System.out.print("Enter the name of the event then press 'ENTER':  ");
			description = scan.nextLine();
			}
		event.setsm("SUMMARY:" + description + "\r\n");
		name = description;
	//	scan.close();
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
		//Scanner scan = new Scanner(System.in);
		while (timezone.equals("")){
			System.out.print("Which timezone is this taking place-USA only(example: HST, PST):  ");
			timezone = scan.next();
		}
		tZID = Interface.findTimeZone(timezone);
		event.settzid(tZID);
		//scan.close();
	}
	
	public void askForStartTime(Event event) {
		String month = "";
		String day = "";
		String year = "";
		String start = "";
		String time = "";
		//Scanner scan = new Scanner(System.in);
		while (month.equals("") || day.equals("")  || year.equals("")  || time.equals("")) {
			System.out.println("Create the start date and time for the event.");
			System.out.print("Enter the month for the event using two digits (ex: 07):  ");
			month = scan.next();
			System.out.print("Enter the day of the month for the event using two digits (ex: 01):  ");
			day = scan.next();
			System.out.print("Enter the year for the event using 4 digits (ex: 2012):  ");
			year = scan.next();
			System.out.print("Enter the start time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
			time = scan.next();
			}
		start = year + month + day + "T" + time + "00";
		event.setstrt("DTSTART;TZID" + event.gettzid() + start + "\r\n");
		//scan.close();
	}
	
	public void askForEndTime(Event event) {
		String month = "";
		String day = "";
		String year = "";
		String end = "";
		String time = "";
		//Scanner scan = new Scanner(System.in);
		while (month.equals("") || day.equals("") || year.equals("") || time.equals("")) {
			System.out.println("Create the end date and time for the event.");
			System.out.print("Enter the month for the event using two digits (ex: 07):  ");
			month = scan.next();
			System.out.print("Enter the day of the month for the event using two digits (ex: 01):  ");
			day = scan.next();
			System.out.print("Enter the year for the event using 4 digits (ex: 2012):  ");
			year = scan.next();
			System.out.print("Enter the end time for the event in military time do NOT use colons (ex: enter 1400 instead of 2pm):  ");
			time = scan.next();
			}
		end = year + month + day + "T" + time + "00";
		event.setnd("DTEND;TZID" + event.gettzid() + end + "\r\n");
		//scan.close();
	}
	
	public void getDStamp(Event event){
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		DateFormat timeFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		event.setstmp("DTSTAMP:" + dateFormat.format(date)+ "T" + timeFormat.format(date) + "Z\r\n");
	}

	//scan.close();
}
