import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Main {
    public static void registration(String username, String password) {
        String whereWrite = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\data.csv";

        try {
            FileWriter fw = new FileWriter(whereWrite, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);


            pw.println(username + "," + password);
            System.out.println("Successfully registered!");
            pw.flush();
            pw.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static boolean isLoginTaken(String loginUsername, String loginPassword) throws FileNotFoundException{
//
//                ArrayList<String> data = new ArrayList<>();
//                File csvFile = new File("tempData.csv");
//                PrintWriter out = new PrintWriter(csvFile);
//
//                boolean check = false;
//
//                data.add(loginUsername);
//                data.add(loginPassword);
//
//                for (String testData : data)
//                {
//                    out.print(testData + " ");
//                }
//                for (int j = 0; j < data.size() / 2; j++) {
//                    for (int i = 0; i < 2; i++) {
//                        if (loginUsername.equalsIgnoreCase(data.get(i)) && loginPassword.equalsIgnoreCase(data.get(i+1))){
//                            check = true;
//                            break;
//                        }
//                    }
//                }
//                out.close();
//                return check;
//            }

    public static String isLoginSuccessful(String loginUsername, String loginPassword){
        String file = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\data.csv";
        BufferedReader reader = null;
        String line = "";
        boolean check = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                for (int j = 0; j < row.length / 2; j++) {
                    for (int i = 0; i < 2; i++) {
                        if (loginUsername.equalsIgnoreCase(row[i]) && loginPassword.equalsIgnoreCase(row[i+1])){
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if (check == true){
            System.out.println();
            return ("Successfully logged in!");
        } else {
            System.out.println();
            return ("Wrong username/password");
        }
    }

    public static boolean isUsernameTaken(String username){
        String file = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\data.csv";
        BufferedReader reader = null;
        String line = "";
        boolean check = false;
        try {
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null) {

                String[] row = line.split(",");
                for (int j = 0; j < row.length / 2; j++) {
                    for (int i = 0; i < 2; i++) {
                        if (username.equalsIgnoreCase(row[j])){
                            check = true;
                            break;
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return check;
    }

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.println("How many people are you?");
        String people = sc.nextLine();

        for (int i = 1; i <= Integer.parseInt(people); i++) {

            System.out.println("\nPlayer#" + i);
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("\nPick a choice: ");
            String choice = sc.nextLine();


            if (choice.equals("1")){

                while (true){
                    System.out.println("\nRegister: ");
                    System.out.println("\nEnter a username: ");
                    String username = sc.nextLine();
                    System.out.println("Enter a password: ");
                    String password = sc.nextLine();
                    if ((isUsernameTaken(username))){
                        System.out.println("The username is already taken!");
                    } else {
                        registration(username,password);
                        break;
                    }
                }



                while (true){
                    System.out.println("\nLogin: ");
                    System.out.println("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.println("Enter a password: ");
                    String loginPassword = sc.nextLine();
                    System.out.println(isLoginSuccessful(loginUsername,loginPassword));
                    if (isLoginSuccessful(loginUsername,loginPassword).equalsIgnoreCase("Successfully logged in!")) {
                        if (i == Integer.parseInt(people)){
                            System.out.println("Game starts.");
                            break;
                        }else {
                            break;
                        }

                    }

                }

            } else if (choice.equals("2")) {
                while (true){
                    System.out.println("\nLogin: ");
                    System.out.println("\nEnter a username: ");
                    String loginUsername = sc.nextLine();
                    System.out.println("Enter a password: ");
                    String loginPassword = sc.nextLine();
//                    while (true){
//
//                        if (isLoginTaken(loginUsername,loginPassword)){
//                            System.out.println("This account is already logged!");
//                        } else {
//                            break;
//                        }
//
//                    }

                    System.out.println(isLoginSuccessful(loginUsername,loginPassword)) ;
                    if (isLoginSuccessful(loginUsername,loginPassword).equalsIgnoreCase("Successfully logged in!")){
                        if (i == Integer.parseInt(people)){
                            System.out.println("Game starts.");
                            break;
                        }else {
                            break;
                        }
                    }
                }
            } else {
                System.out.println("Not a choice.");
            }
        }

    }
}