import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class UserDashBoard {
    DataBaseConnectivity db = new DataBaseConnectivity();
    Scanner scanner = new Scanner(System.in);
    int userId;

    public UserDashBoard(int userId) {
        this.userId = userId;
    }

    public void displayUserOptions() {
        System.out.println("Select option:\n1) Book Ticket\n2) View Bookings\n3) Cancel Booking\n4) Go back");
        int option = Integer.parseInt(scanner.nextLine());
        switch (option){
            case 1:
                createBooking();
                break;
            case 2:
                displayBookingByUserId();
                break;
            case 3:
                cancelBooking();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option. Please try again later.");
        }
        displayUserOptions();
    }

    private void cancelBooking() {
        displayBookingByUserId();
        System.out.println();
        System.out.println("Enter the booking id to cancel");
        int bookingId = Integer.parseInt(scanner.nextLine());
        boolean result = db.cancelBookingByBookingId(bookingId);
        if(result){
            System.out.println("Booking canceled successfully");
            displayBookingByUserId();
        }
        else{
            System.out.println("Something went wrong. Please try again later");
        }
    }

    private void displayBookingByUserId() {
        List<Properties> bookingList = db.getBookingByUserId(userId);
        String[] headers = {"Booking Id", "Bus Number", "Source", "Destination", "Seats booked", "Date"};
        System.out.println("+----------------".repeat(headers.length) + "+");
        System.out.print("|");
        for (String header: headers) {
            System.out.printf("%15s |", header);
        }
        System.out.println();
        System.out.println("+----------------".repeat(headers.length) + "+");
        for (Properties booking: bookingList) {
            System.out.printf("|%15d |%15d |%15s |%15s |%15d |%15s |\n", booking.get("bookingId"), booking.get("busNumber"), booking.get("source"), booking.get("destination"), booking.get("bookedSeat"), booking.get("date"));
        }
        System.out.println("+----------------".repeat(headers.length) + "+");
    }

    private void createBooking() {
        String source, destination, date;
        System.out.println("Enter the source location:");
        source = scanner.nextLine();
        System.out.println("Enter the destination location:");
        destination = scanner.nextLine();
        System.out.println("Enter the date of journey:");
        date = scanner.nextLine();
        displayAvailableBus(source, destination, date);
        System.out.println("Enter the bus number:");
        int busId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter number of seats:");
        int noOfSeat = Integer.parseInt(scanner.nextLine());
        boolean result = db.createBooking(busId, this.userId, source, destination, date, noOfSeat);
        if(result){
            System.out.println("Booking Completed");
            displayBookingByUserId();
        }
        else{
            System.out.println("Something went wrong. please try again later");
        }
    }

    private void displayAvailableBus(String source, String destination, String date) {
        List<Properties> busList = db.getBusListByValue(source, destination, date);
        String[] headers = {"Bus Number", "Bus Name", "Source", "Destination", "Capacity", "Date"};
        System.out.println("+----------------".repeat(headers.length) + "+");
        System.out.print("|");
        for (String header: headers) {
            System.out.printf("%15s |", header);
        }
        System.out.println();
        System.out.println("+----------------".repeat(headers.length) + "+");
        for (Properties bus: busList) {
            System.out.printf("|%15d |%15s |%15s |%15s |%15d |%15s |\n", bus.get("busId"), bus.get("busName"), bus.get("source"), bus.get("destination"), bus.get("maxCapacity"), bus.get("date"));
        }
        System.out.println("+----------------".repeat(headers.length) + "+");
    }
}
