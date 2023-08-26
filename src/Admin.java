import java.util.Scanner;

public class Admin {
    Scanner scanner = new Scanner(System.in);
    private String userName = "admin";
    private String password = "1"; // "password";
    private String email = "1"; //"admin@mybus.com";
    private int adminId = 1001;

    public int login(){
        System.out.println("Enter your Email Id : ");
        String email = scanner.nextLine();
        System.out.println("Enter your Password : ");
        String password = scanner.nextLine();
        if(this.email.equals(email) && this.password.equals(password)){
            System.out.println("Welcome back " + this.userName + " (" + this.adminId + ")\n\n" );
            return adminId;
        }
        else{
            System.out.println("Invalid credential or something went wrong\nPlease try again later...\n");
            return -1;
        }
    }
}
