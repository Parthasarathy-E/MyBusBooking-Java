/*
Application Title:Bus Ticket Reservation System
Author name:Parthasarathy E
create on:12/10/2022
last Modified Date and time:13/10/2022
reviewed by:Anushya
reviewed Date:12.10.2022

*/

import java.util.Scanner;
import java.util.ArrayList;

public class BusDemo {
	static Scanner scanner= new Scanner(System.in);
	public static void welcomeMessage(){
		System.out.println("Welcome to My Bus Booking Service.\nPlease select your user type:-\n1) User\n2) Admin\n3) Exit\n\n");
		int userType = scanner.nextInt();
		scanner.nextLine();
		switch (userType) {
			case 1:
				showUserOptions();
				break;
			case 2:
				Admin admin = new Admin();
				int adminId = admin.login();
				if(adminId != -1 )
					openAdminDashboard();
				else
					welcomeMessage();
				break;
			default:
				return;
		}
		welcomeMessage();
	}

	private static void showUserOptions() {
		System.out.println("Select option:\n1) Login\n2) Create account\n3) Cancel and go back");
		int option = Integer.parseInt(scanner.nextLine());
		switch (option){
			case 1:
				userLogin();
				break;
			case 2:
				userSignUp();
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid option. Please try again");
				showUserOptions();
		}
	}

	private static void userSignUp() {
		User user = new User();
		user.signUp();
		userLogin();
	}

	private static void userLogin() {
		User user = new User();
		int userId = user.login();
		if(userId != -1){
			openUserDashboard();
		}else{
			System.out.println("Invalid Credentials. Please try again");
			showUserOptions();
		}
	}

	private static void openUserDashboard() {
	}

	private static void openAdminDashboard() {
		AdminDashBoard adminDashBoard = new AdminDashBoard();
		adminDashBoard.displayAdminOptions();
		welcomeMessage();
	}

	public static void main(String[] args) {
		welcomeMessage();
//		ArrayList<Bus> buses = new ArrayList<Bus>();
//		ArrayList<Booking> bookings = new ArrayList<Booking>();
//		buses.add(new Bus(1, true, 1));
//		buses.add(new Bus(2, false, 3));
//		buses.add(new Bus(3, true, 2));
//		buses.add(new Bus(4, true, 4));
//		buses.add(new Bus(5, false, 2));
//		buses.add(new Bus(6, true, 4));
//		buses.add(new Bus(7, true, 5));
//		buses.add(new Bus(8, false, 4));
//
//		int userOpt = 1;
//		Scanner Scanner = new Scanner(System.in);
//		for (Bus bus : buses) {
//			bus.displayBusInfo();
//		}
//		while (userOpt == 1) {
//			try {
//				System.out.println("Enter 1 to book and 2 to exit");
//				userOpt = Integer.parseInt(Scanner.nextLine());
//				if (userOpt == 1) {
//					Booking booking = new Booking();
//					if (booking.isAvailable(bookings, buses) && booking.flag) {
//						bookings.add(booking);
//						System.out.println("Your booking is confirmed");
//						System.out.println("Thank you for Booking");
//					} else {
//						// if(booking.Bus_avail)
//						// {
//						System.out.println("Sorry .Bus is full. Try another date");
//						// }
//					}
//				} else if (userOpt > 2) {
//					System.out.println("Enter the given input");
//				}
//			} catch (NumberFormatException exception) {
//				System.out.println("Enetr a valid Input");
//			}
	}
}
