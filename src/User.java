import java.util.Scanner;

public class User {
    Scanner scanner = new Scanner(System.in);
    DataBaseConnectivity db = new DataBaseConnectivity();
    
    public int login() {
        String email, password;
        System.out.println("Enter your email id:");
        email = scanner.nextLine();
        System.out.println("Enter your password");
        password = scanner.nextLine();
        int userId = db.getUserId(email, password);
        return userId;
    }

    public void signUp() {
        String name, email, password, confPassword, contactNumber;
        System.out.println("Enter the user name");
        name = scanner.nextLine();
        System.out.println("Enter the email id (which will be useful for login next time)");
        email = scanner.nextLine();
        do{
            System.out.println("Enter the password (case sensitive)");
            password = scanner.nextLine();
            System.out.println("Confirm password");
            confPassword = scanner.nextLine();
            if(!password.equals(confPassword)){
                System.out.println("Confirm password doesn't match with password. Please try again.");
            }
        }while (!password.equals(confPassword));
        System.out.println("Enter the contact number (eg: +91 9876543210)");
        contactNumber = scanner.nextLine();
        db.addNewUser(name, email, password, contactNumber);
    }
}
