import java.util.Scanner;

public class User implements TimeManagement {

    Scanner scanner = new Scanner(System.in);

    protected String username;
    protected String name;
    protected String password;
    protected String userID;
    protected boolean loginStatus;

    @Override
    public void createUser()
    {
        System.out.println("Enter your name: ");
        this.name = scanner.nextLine();

        System.out.println("Create a username: ");
        this.username = scanner.nextLine();

        System.out.println("Create password: ");
        this.password = scanner.nextLine();
    }

    @Override
    public void login() {
    }
}