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
//            case 3:
//                updateBus();
//                break;
            default:
                System.out.println("Logout successfully.");
                return;
        }
        displayAdminOptions();

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
            System.out.println();
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
