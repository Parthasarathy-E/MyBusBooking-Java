/*
Application Title:Bus Ticket Reservation System
Author name:Parthasarathy E
create on:12/10/2022
last Modified Date and time:13/10/2022
reviewed by:Anushya
reviewed Date:12.10.2022

*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner; 

public class Booking extends DataBaseConnectivity{
  String passengerName;
  int busNumber;
  Date date;
  boolean flag=true;//,count=true;
  boolean busAvailable=true;
  Scanner scanner=new Scanner(System.in);
  Booking(){
	  
	  System.out.println("Enter name of the Passenger: ");
	  passengerName =scanner.next();
	  //String query="insert into bus values ("+passengerName+");";
//	  database(query);
	  System.out.println("Enter the Bus no: ");
	  busNumber =scanner.nextInt();
	 // busNo="insert into bus values("+busNumber+");";
	 // database(query1);
	  System.out.println("Enter Date dd-mm-yyyy: ");
	  String dateInput=scanner.next();
	  String query="insert into details values ('"+passengerName+"',"+busNumber+",'"+dateInput+"');";
	  database(query);
	  SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
	  try {
		date =(Date)dateFormat.parse(dateInput); 
	} catch (ParseException exception) {
		// TODO Auto-generated catch block
		exception.printStackTrace();
		flag=false;
	}
  }
  
public boolean isAvailable(ArrayList<Booking> bookings, ArrayList<Bus> buses) {
	   int capacity=0;
	  
	   for(Bus bus:buses) {
		   try {
		 if(bus.getbusNo() == busNumber) {
			 capacity =bus.getCapacity();
		 }
//		 else {
//			 System.out.println("Bus is not Found");
//			 Bus_avail=false;
//			 break;
//		 }
		   }
		 catch(NumberFormatException exceptions) {
			 System.out.println("Invalid Input");
			 break;
		 }
	   }
	   int booked =0;
	   for(Booking  book:bookings) {
		   if(book.busNumber == busNumber && book.date.equals(date)) {   //Is there anybody can already book the same bus number and same date
			booked++;   
		   }
	   }
	   return (booked<capacity)?true:false;
  }


}

