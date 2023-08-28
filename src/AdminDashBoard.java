import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AdminDashBoard {
    DataBaseConnectivity db = new DataBaseConnectivity();
    AdminDashBoard(){

    }
    Scanner scanner = new Scanner(System.in);
    public void displayAdminOptions() {
        System.out.println("Select option:-\n1) Display Bus List\n2) Add a new bus\n3) Update a existing bus\n4) logout\n\n");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                displayBusList();
                break;
            case 2:
                addNewBus();
                break;
            case 3:
                updateBus();
                break;
            default:
                System.out.println("Logout successfully.\n\n");
                return;
        }
        displayAdminOptions();

    }

    private void updateBus() {
        displayBusList();
        System.out.println();
        System.out.println("Enter the Bus number to edit: ");
        int busNumber = Integer.parseInt(scanner.nextLine());
        Properties bus;
        bus = db.getBusDetailsByBusNumber(busNumber);
        System.out.println("Select Option:\n1) Edit Travels name\n2) Edit the source location\n3) Edit the Destination location\n4) Edit Maximum capacity\n5) Edit Journey Date");
        int option = Integer.parseInt(scanner.nextLine());
        String fieldModified, modifiedValue;
        switch (option){
            case 1:
                System.out.println("Enter the new name (" + bus.get("busName") + ")");
                modifiedValue = scanner.nextLine();
                fieldModified = "busName";
                break;
            case 2:
                System.out.println("Enter the new source location (" + bus.get("source") + ")");
                modifiedValue = scanner.nextLine();
                fieldModified = "source";
                break;
            case 3:
                System.out.println("Enter the new destination location (" + bus.get("destination") + ")");
                modifiedValue = scanner.nextLine();
                fieldModified = "destination";
                break;
            case 4:
                System.out.println("Enter the new capacity (" + bus.get("maxCapacity") + ")");
                modifiedValue = scanner.nextLine();
                fieldModified = "maxCapacity";
                break;
            case 5:
                System.out.println("Enter the modified date of journey (" + bus.get("date") + ")");
                modifiedValue = scanner.nextLine();
                fieldModified = "date";
                break;
            default:
                System.out.println("Invalid Option. Please try again.");
                return;
        }
        boolean result = db.updateBusDetails(busNumber, fieldModified, modifiedValue);
        if(result){
            System.out.println("Updated Successfully");
            displayBusList();
        }else{
            System.out.println("Something went wrong. Please try again.");
        }
    }

    private void addNewBus() {
        String busName, source, destination, date;
        int capacity;
        System.out.println("Enter the Travels details: \n");
        System.out.println("Enter the Bus Name: ");
        busName = scanner.nextLine();
        System.out.println("Enter the Source: ");
        source = scanner.nextLine();
        System.out.println("Enter the Destination: ");
        destination = scanner.nextLine();
        System.out.println("Total number of seats: ");
        capacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Onward Journey Date (yyyy-MM-dd): ");
        date = scanner.nextLine();
        Properties Bus = new Properties();
        Bus.put("busName", busName);
        Bus.put("source", source);
        Bus.put("destination", destination);
        Bus.put("maxCapacity", capacity);
        Bus.put("date", date);
        boolean result = db.addBus(Bus);
        if(result){
            System.out.println(busName + "'s details were add successfully\n\n");
            displayBusList();
        }
        else {
            System.out.println("Something went wrong. please try again later...\n");
        }
    }

    private void displayBusList() {
      List <Properties> busList = db.getBusList();
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
