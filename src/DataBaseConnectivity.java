
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
	private String password = "Praveen@006";
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
}
