import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        User user;
        System.out.println("Welcome to Who Wants to Be a Millionaire Game!");
        System.out.println("Please enter your name here: ");
        String name = scanner.nextLine();

        System.out.println("Choose 1 for Easy mode or 2 for Hard mode ");
        int mode = scanner.nextInt();
        if (mode == 1)
            user = new User(name, true);
        else
            user = new User(name, false);

        Menue menue = new Menue(); // Create Menue object without passing User
        menue.launchScreen(); // Launch the menu screen
    }
}
