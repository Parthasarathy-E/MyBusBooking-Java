/*
Application Title:Bus Ticket Reservation System
Author name:Parthasarathy E
create on:12/10/2022
last Modified Date and time:13/10/2022
reviewed by:Anushya
reviewed Date:12.10.2022

*/

import java.util.Scanner;

public class BusDemo {
	static Scanner scanner= new Scanner(System.in);
	public static void welcomeMessage(){
		System.out.println("Welcome to My Bus Booking Service.\nPlease select your user type:-\n1) User\n2) Admin\n3) Exit");
		int userType = Integer.parseInt(scanner.nextLine());
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
			openUserDashboard(userId);
		}else{
			System.out.println("Invalid Credentials. Please try again");
			showUserOptions();
		}
	}

	private static void openUserDashboard(int userId) {
		UserDashBoard userDashBoard = new UserDashBoard(userId);
		userDashBoard.displayUserOptions();
		welcomeMessage();
	}

	private static void openAdminDashboard() {
		AdminDashBoard adminDashBoard = new AdminDashBoard();
		adminDashBoard.displayAdminOptions();
		welcomeMessage();
	}

	public static void main(String[] args) {
		welcomeMessage();
	}
}
