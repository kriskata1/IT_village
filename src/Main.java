import java.io.*;
import java.util.Random;

public class Main {

    public static void mainCaller() throws IOException {
        RegistrationAndLogin.main(null);
    }

    public static int currentPositionOnBoard(int positionOnBoard, int randomDiceNum) {
        int count = 1;
        for (int i = 1; i <= randomDiceNum; i++) {
            positionOnBoard = positionOnBoard + count;
            if (positionOnBoard > 12) {
                positionOnBoard = 1;
            }
        }

        return positionOnBoard;
    }

    public static char letterFromGameBoard(char[] gameBoard, int positionOnBoard, int moves, int randomDiceNum) {
        if (moves == 0) {
            return (gameBoard[positionOnBoard - 1]);
        } else {
            if (positionOnBoard == 0) {
                return (gameBoard[currentPositionOnBoard(positionOnBoard, randomDiceNum)]);
            } else {
                return (gameBoard[currentPositionOnBoard(positionOnBoard, randomDiceNum) - 1]);
            }
        }
    }

    public static void namesOfBoardPlaces(char[] gameBoard, int positionOnBoard, int moves, int randomDiceNum) {
        switch (letterFromGameBoard(gameBoard, positionOnBoard, moves, randomDiceNum)) {
            case 'P':
                System.out.println("Wi-Fi pub");
                break;
            case 'I':
                System.out.println("Wi-Fi motel");
                break;
            case 'F':
                System.out.println("Freelance Project");
                break;
            case 'S':
                System.out.println("Storm");
                break;
            case 'V':
                System.out.println("Super PHP");
                break;
            case 'N':
                System.out.println("VSC");
                break;
        }
    }

    public static void descriptionOfBoardPlaces(char[] gameBoard, int positionOnBoard, int moves, int randomDiceNum){
        switch (letterFromGameBoard(gameBoard, positionOnBoard, moves, randomDiceNum)) {
            case 'P':
                System.out.println("You have to buy a Cloud cocktail");
                break;
            case 'I':
                System.out.println("If you have enough money you have to buy it");
                System.out.println("If not - you have to pay your stay");
                break;
            case 'F':
                System.out.println("You receive a payment");
                break;
            case 'S':
                System.out.println("The Wi-Fi in the village dies, you become depressed and skip two rows.");
                break;
            case 'V':
                System.out.println("Your money multiplies by 10");
                break;
            case 'N':
                System.out.println("If you step on this you win the game");
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        //mainCaller();
        int moves = 0;
        String filePath = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\peopleCount.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Random random = new Random();
        char[] gameBoard = {'P', 'I', 'F', 'S', 'F', 'V', 'I', 'F', 'F', 'I', 'N', 'P'};
        int people = Integer.parseInt(reader.readLine());
        int[] peopleMoney = new int[people];
        for (int i = 0; i < peopleMoney.length; i++) {
            peopleMoney[i] = 50;
        }
        while (true) {
            for (int i = 1; i <= people; i++) {
                int positionOnBoard = random.nextInt(gameBoard.length) + 1;
                int randomDiceNum = random.nextInt(6) + 1;
                System.out.println("Position: " + positionOnBoard);
                System.out.println("Dice number: " + randomDiceNum);
                System.out.println(letterFromGameBoard(gameBoard, positionOnBoard, moves, randomDiceNum));
                System.out.println(currentPositionOnBoard(positionOnBoard, randomDiceNum));
                namesOfBoardPlaces(gameBoard,positionOnBoard,moves,randomDiceNum);
                descriptionOfBoardPlaces(gameBoard,positionOnBoard,moves,randomDiceNum);
                System.out.println(peopleMoney[i-1]);
                System.out.println();
                if (letterFromGameBoard(gameBoard, positionOnBoard, moves, randomDiceNum) == 'N') {
                    System.out.println("Player#" + i + " won the game");
                    break;
                }
            }
            moves++;

        }
    }
}