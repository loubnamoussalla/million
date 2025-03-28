import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

         Menue menue ;
        User user;
    System.out.println("Welcome to who wins the million Game!");
    System.out.println("Please enter your name here: ");
    String name = scanner.nextLine();

    System.out.println("Choose 1 for Easy mode or 2 for Hard mode ");
    int mode = scanner.nextInt();
    if(mode == 1)
        user = new User(name, true);

    else
        user = new User(name, false);

    menue = new Menue(user);
    menue.launchScreen();
//
//        q.load_questions();
//
//    Lifeline lifeline = new Lifeline();


    }



}