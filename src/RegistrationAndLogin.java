import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;

public class RegistrationAndLogin {
    public static void isRegistrationSuccessful(String username, String password) throws IOException {

        String filePath = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\data.csv";

        FileWriter fw = new FileWriter(filePath, true);
        BufferedWriter bw = new BufferedWriter(fw);                 //writes entered values to the csv file
        PrintWriter pw = new PrintWriter(bw);

        pw.println(username + "," + password);
        System.out.println();
        System.out.println("Successfully registered!");
        System.out.println();
        pw.flush();
        pw.close();
    }

    public static boolean isLoginSuccessful(String loginUsername, String loginPassword) throws IOException {

        String filePath = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\data.csv";

        BufferedReader reader;
        String line;
        boolean check = false;
        reader = new BufferedReader(new FileReader(filePath));

        while ((line = reader.readLine()) != null) {            //loops through the csv file and if username and password match
            String[] row = line.split(",");

            if (loginUsername.equalsIgnoreCase(row[0]) && loginPassword.equalsIgnoreCase(row[1])) {
                check = true;
                break;

            }
        }

        return check;
    }

    public static String loginMessage(String loginUsername, String loginPassword) throws IOException {

        if (isLoginSuccessful(loginUsername, loginPassword)) {
            return ("Successfully logged in!");

        } else {
            return ("Wrong username/password");

        }
    }

    public static boolean isUsernameTaken(String username) throws IOException {

        String file = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\data.csv";

        BufferedReader reader;
        String line;
        boolean check = false;
        reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            String[] row = line.split(",");

            for (int j = 0; j < row.length / 2; j++) {      //loops through(column) every username in the csv file and checks if the entered value is in any of the usernames in there

                for (int i = 0; i < 2; i++) {

                    if (username.equalsIgnoreCase(row[j])) {
                        check = true;
                        break;

                    }
                }
            }
        }

        return check;
    }

    public static String isAccountLoggedAlready(String[] tempUsernames, String[] tempPasswords, int index) {

        boolean checkUser = false;
        boolean checkPass = false;

        if (index > 0) {

            for (int i = 1; i < index + 1; i++) {       //loops through and compares all the already logged usernames and passwords with the currently entered ones

                if (tempUsernames[index].equalsIgnoreCase(tempUsernames[index - i])) {
                    checkUser = true;

                }

                if (tempPasswords[index].equalsIgnoreCase(tempPasswords[index - i])) {
                    checkPass = true;

                }
            }
        }

        index++;

        if (checkUser && checkPass) {
            return "There is already an account logged with the same credentials.";

        } else {
            return "";

        }
    }

    public static void loggedAlreadyMessage(String loginUsername, String loginPassword, String[] tempUsernames, String[] tempPasswords, int index) throws IOException {

        if (loginMessage(loginUsername, loginPassword).equalsIgnoreCase("Successfully logged in!")) {

            if (isAccountLoggedAlready(tempUsernames, tempPasswords, index).equalsIgnoreCase("There is already an account logged with the same credentials.")) {

                System.out.println();
                System.out.println(isAccountLoggedAlready(tempUsernames, tempPasswords, index));
                System.out.println();

            } else {

                System.out.println();
                System.out.println(loginMessage(loginUsername, loginPassword));
                System.out.println();

            }

        } else {

            System.out.println();
            System.out.println(loginMessage(loginUsername, loginPassword));
            System.out.println();

        }
    }

    public static boolean registrationAndLoginNoMatch(String username, String password, String loginUsername, String loginPassword) {

        if (!(username.equalsIgnoreCase(loginUsername) && password.equalsIgnoreCase(loginPassword))) {

            System.out.println();
            System.out.println("The entered credentials don't match with the registered ones.");
            System.out.println();
            return true;

        } else {
            System.out.println();
            System.out.println("Successfully logged in!");
            System.out.println();
            return false;

        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("WELCOME TO THE IT VILLAGE GAME.");
        System.out.println("LET'S START...");
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("How many people are you?");
        String people = sc.nextLine();
        File csvFile = new File("peopleCount.csv");
        PrintWriter pw = new PrintWriter(csvFile);
        pw.print(people);       //prints the amount of people you entered in the csv file
        pw.close();

        int index = 0;
        String[] tempUsernames = new String[Integer.parseInt(people)];
        String[] tempPasswords = new String[Integer.parseInt(people)];

        for (int i = 1; i <= Integer.parseInt(people); i++) {

            System.out.println("\nPlayer#" + i);
            System.out.println("\n1. Register");    //loops through all the players and asks each to pick a choice
            System.out.println("2. Login");
            System.out.println("\nPick a choice: ");
            String choice = sc.nextLine();
            String username;
            String password;

            if (choice.equals("1")) {

                while (true) {
                    System.out.println("\nRegister: ");
                    System.out.println("\nEnter a username: ");
                    username = sc.nextLine();
                    System.out.println("Enter a password: ");
                    password = sc.nextLine();

                    if ((isUsernameTaken(username))) {

                        System.out.println();
                        System.out.println("The username is already taken!");
                        System.out.println();

                    } else {
                        isRegistrationSuccessful(username, password);
                        break;

                    }
                }

                while (true) {

                    System.out.println("\nLogin: ");
                    System.out.println("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.println("Enter a password: ");
                    String loginPassword = sc.nextLine();

                    tempUsernames[index] = loginUsername;
                    tempPasswords[index] = loginPassword;

                    if (!registrationAndLoginNoMatch(username, password, loginUsername, loginPassword) && loginMessage(loginUsername, loginPassword).equalsIgnoreCase("Successfully logged in!") && isAccountLoggedAlready(tempUsernames, tempPasswords, index).equalsIgnoreCase("")) {
                        index++;

                        if (i == Integer.parseInt(people)) {        //if we are looping through the last person of the list then start the game
                            System.out.println("Game starts.");

                        }
                        break;
                    }
                }


            } else if (choice.equals("2")) {

                while (true) {

                    System.out.println("\nLogin: ");
                    System.out.println("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.println("Enter a password: ");
                    String loginPassword = sc.nextLine();

                    tempUsernames[index] = loginUsername;
                    tempPasswords[index] = loginPassword;

                    loggedAlreadyMessage(loginUsername, loginPassword, tempUsernames, tempPasswords, index);

                    if (loginMessage(loginUsername, loginPassword).equalsIgnoreCase("Successfully logged in!") && isAccountLoggedAlready(tempUsernames, tempPasswords, index).equalsIgnoreCase("")) {
                        index++;

                        if (i == Integer.parseInt(people)) {
                            System.out.println("Game starts.");

                        }
                        break;
                    }
                }

            } else {
                System.out.println();
                System.out.println("Not a choice.");

                while (true) {
                    System.out.println("\nEnter 'continue' to pick a choice again: ");
                    choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("continue")) {
                        i--;
                        break;

                    }

                }
            }
        }
    }
}
