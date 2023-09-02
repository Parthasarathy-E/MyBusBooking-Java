
/*
Application Title:Bus Ticket Reservation System
Author name:Parthasarathy E
create on:12/10/2022
last Modified Date and time:13/10/2022
reviewed by:Anushya
reviewed Date:12.10.2022

*/

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DataBaseConnectivity {
	private String user = "root";
	private String password = "Praveen@006"; // "partha"
	private String baseUrl = "jdbc:mysql://localhost:3306/busdatabase";
	Statement st = null;
	DataBaseConnectivity(){
		Connection connection= null;
		try {
			connection = DriverManager.getConnection(baseUrl, user, password);
			st =connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List getBusList(){
		List<Properties> busList = new ArrayList<>();
		String query = "SELECT * FROM bus";
		try {
			ResultSet resultSet = st.executeQuery(query);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (resultSet.next()){
				Properties Bus = new Properties();
				int busId = (int) resultSet.getObject(1);
				String busName = (String) resultSet.getObject(2);
				String source = (String) resultSet.getObject(3);
				String destination = (String) resultSet.getObject(4);
				int maxCapacity = (int) resultSet.getObject(5);
				Object bookedSeat = (Object) resultSet.getObject(6);
				java.util.Date Date = (Date) resultSet.getObject(7);
				String date = sdf.format(Date);
				Bus.put("busId", busId);
				Bus.put("busName", busName);
				Bus.put("source", source);
				Bus.put("destination", destination);
				Bus.put("maxCapacity", maxCapacity);
				Bus.put("bookedSeat", bookedSeat);
				Bus.put("date", date);
				busList.add(Bus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  busList;
	}
	public static void database(String query){
		  try{


		  Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/busdatabase","root","Praveen@006");
		  Statement st=connection.createStatement();
		   st.executeUpdate(query);
		 // statement.execute(s);  
		  connection.close();
		  }
		  catch(SQLException exception) {
		    System.err.println(exception);
		  }
		  
		  }


	public boolean addBus(Properties bus) {
		String query = "insert into bus (busName, source, destination, maxCapacity, bookedSeats, date) values ('" + bus.get("busName") + "', '" + bus.get("source") + "', '" + bus.get("destination") + "', '" + bus.get("maxCapacity") + "', '{}', '" + bus.get("date") + "')";
		try {
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Properties getBusDetailsByBusNumber(int busNumber) {
		String query = "SELECT * FROM bus WHERE `busId` = " + busNumber + " LIMIT 1 ;";
		Properties bus = new Properties();
		try {
			ResultSet resultSet = st.executeQuery(query);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (resultSet.next()){
				int busId = (int) resultSet.getObject(1);
				String busName = (String) resultSet.getObject(2);
				String source = (String) resultSet.getObject(3);
				String destination = (String) resultSet.getObject(4);
				int maxCapacity = (int) resultSet.getObject(5);
				Object bookedSeat = (Object) resultSet.getObject(6);
				java.util.Date Date = (Date) resultSet.getObject(7);
				String date = sdf.format(Date);
				bus.put("busId", busId);
				bus.put("busName", busName);
				bus.put("source", source);
				bus.put("destination", destination);
				bus.put("maxCapacity", maxCapacity);
				bus.put("bookedSeat", bookedSeat);
				bus.put("date", date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bus;
	}

	public boolean updateBusDetails(int busNumber, String fieldModified, String modifiedValue) {
		String query = "UPDATE bus SET `" + fieldModified + "` = '" + modifiedValue + "' where `busId` = " + busNumber +";";
		try {
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public void addNewUser(String name, String email, String password, String contactNumber) {
		boolean result = checkUserAlreadyExist(email);
		if(result){
			String query = "INSERT INTO userdetails (userName, userEmail, password, contactNumber) values ('" + name + "', '" + email + "', '" + password + "', '" + contactNumber + "')";
			try {
				st.executeUpdate(query);
				System.out.println("Account created successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("User already exist with that email id");
			System.out.println("Please try again later with different email id");
		}
	}

	private boolean checkUserAlreadyExist(String email) {
		String query = "SELECT userId FROM userdetails WHERE `userEmail` = \""+ email +"\";";
		int userId = -1;
		try {
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()){
				userId = (int) resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId == -1;
	}

	public int getUserId(String email, String password) {
		int userId = -1;
		String query = "SELECT userId FROM userdetails WHERE `userEmail` = \""+ email +"\" AND `password` = \"" + password +"\" ;";
		try {
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()){
				 userId = (int) resultSet.getObject(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}

	public List<Properties> getBusListByValue(String sourceLoc, String destinationLoc, String selectedDate) {
		List<Properties> busList = new ArrayList<>();
		String query = "SELECT * FROM bus WHERE `source` = \"" + sourceLoc + "\" and `destination` = \"" + destinationLoc + "\" and `date` = \"" + selectedDate + "\" ;";
		try {
			ResultSet resultSet = st.executeQuery(query);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			while (resultSet.next()){
				Properties Bus = new Properties();
				int busId = (int) resultSet.getObject(1);
				String busName = (String) resultSet.getObject(2);
				String source = (String) resultSet.getObject(3);
				String destination = (String) resultSet.getObject(4);
				int maxCapacity = (int) resultSet.getObject(5);
				Object bookedSeat = (Object) resultSet.getObject(6);
				java.util.Date Date = (Date) resultSet.getObject(7);
				String date = sdf.format(Date);
				Bus.put("busId", busId);
				Bus.put("busName", busName);
				Bus.put("source", source);
				Bus.put("destination", destination);
				Bus.put("maxCapacity", maxCapacity);
				Bus.put("bookedSeat", bookedSeat);
				Bus.put("date", date);
				busList.add(Bus);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return  busList;
	}

	public boolean createBooking(int busId, int userId, String source, String destination, String date, int noOfSeat) {
		String query = "insert into booking (userId, source, destination, noOfSeats, busNumber, date) values ('" + userId + "', '" + source + "', '" + destination + "', '" + noOfSeat + "', '" + busId + "', '" + date + "')";
		try {
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Properties> getBookingByUserId(int userId) {
		List<Properties> bookingList = new ArrayList<>();
		String query = "SELECT * FROM booking WHERE userId = '" + userId + "';";
		try {
			ResultSet resultSet = st.executeQuery(query);
			while (resultSet.next()){
				Properties booking = new Properties();
				int bookingId = (int) resultSet.getObject(1);
				String source = (String) resultSet.getObject(3);
				String destination = (String) resultSet.getObject(4);
				String date = (String) resultSet.getObject(5);
				int busNumber = (int) resultSet.getObject(6);
				int bookedSeat = (int) resultSet.getObject(7);
				booking.put("bookingId", bookingId);
				booking.put("source", source);
				booking.put("destination", destination);
				booking.put("busNumber", busNumber);
				booking.put("bookedSeat", bookedSeat);
				booking.put("date", date);
				bookingList.add(booking);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookingList;
	}

	public boolean cancelBookingByBookingId(int bookingId) {
		String query = "DELETE FROM booking WHERE bookingId = '" + bookingId + "';";
		try {
			st.executeUpdate(query);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}