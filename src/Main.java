import java.io.*;
import java.util.*;

public class Main {

    public static void mainCaller() throws IOException {
        RegistrationAndLogin.main(null);
    }

    public static int currentPositionOnBoard(int moves, int[] startingPositionOnBoard, int a, int[] positionOnBoard) {
        if (moves != 0){
            return positionOnBoard[a];
        } else {
            return startingPositionOnBoard[a];
        }
    }

    public static char letterFromGameBoard(char[] gameBoard, int moves, int[] startingPositionOnBoard, int a, int[] positionOnBoard) {
        return (gameBoard[currentPositionOnBoard(moves, startingPositionOnBoard, a, positionOnBoard) - 1]);
    }

    public static void namesOfBoardPlaces(char[] gameBoard, int moves, int[] startingPositionOnBoard, int a, int[] positionOnBoard) {
        switch (letterFromGameBoard(gameBoard, moves, startingPositionOnBoard, a, positionOnBoard)) {
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

    public static void descriptionAndMoneyOfBoardPlaces(char[] gameBoard, int moves, int[] startingPositionOnBoard, int a, int[] positionOnBoard, int[] peopleMoney, boolean boughtOrNot) {
        switch (letterFromGameBoard(gameBoard, moves, startingPositionOnBoard, a, positionOnBoard)) {
            case 'P':
                System.out.println("You have to buy a Cloud cocktail");
                peopleMoney[a] = peopleMoney[a] - 5;
                break;
            case 'I':
                System.out.println("If you have enough money you have to buy it");
                System.out.println("If not - you have to pay your stay");
                if ((startingPositionOnBoard[a] == 2 || positionOnBoard[a] == 2) && boughtOrNot || (startingPositionOnBoard[a] == 7 || positionOnBoard[a] == 7) && boughtOrNot || (startingPositionOnBoard[a] == 10 || positionOnBoard[a] == 10) && boughtOrNot){
                    peopleMoney[a] = peopleMoney[a] - 20;
                }
                if (peopleMoney[a] >= 100){
                    peopleMoney[a] = peopleMoney[a] - 100;
                    boughtOrNot = true;
                    System.out.println("You bought this motel");
                } else {
                    peopleMoney[a] = peopleMoney[a] - 10;
                }
                break;
            case 'F':
                System.out.println("You receive a payment");
                peopleMoney[a] = peopleMoney[a] + 20;
                break;
            case 'S':
                System.out.println("The Wi-Fi in the village dies, you become depressed and skip two rows.");

                break;
            case 'V':
                System.out.println("Your money multiplies by 10");
                peopleMoney[a] = peopleMoney[a] * 10;
                break;
            case 'N':
                System.out.println("If you step on this you win the game");
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //mainCaller();
        int moves = 0;
        boolean boughtOrNot = false;
        String filePath = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\peopleCount.csv";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Random random = new Random();
        char[] gameBoard = {'P', 'I', 'F', 'S', 'F', 'V', 'I', 'F', 'F', 'I', 'N', 'P'};
        int people = Integer.parseInt(reader.readLine());
        int[] peopleMoney = new int[people];
        for (int i = 0; i < peopleMoney.length; i++) {
            peopleMoney[i] = 50;
        }
        int[] startingPositionOnBoard = new int[people];
        int[] positionOnBoard = new int[people];
        char[] previousLetters = new char[people];
        System.out.println("Press ENTER to roll the dice.");
        final int[] b = {0};

        while (moves <= 9) {
            String rollAgain = sc.nextLine();
            String wait = "Rolling the dice";
            System.out.print(wait);
            new Timer().scheduleAtFixedRate(new TimerTask() {
                int times = 0;
                @Override
                public void run() {
                    times++;
                    System.out.print(".");
                    if (times == 4) {
                        cancel();
                    }

                }
            }, 1000, 1000);

            int finalMoves = moves;
            new Timer().scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {
                    int a = 0;
                    for (int i = 1; i <= people; i++) {
                        startingPositionOnBoard[i-1] = random.nextInt(gameBoard.length) + 1;
                        int randomDiceNum = random.nextInt(6) + 1;
                        if (finalMoves != 0){
                            int count = 1;
                            for (int j = 1; j <= randomDiceNum; j++) {
                                positionOnBoard[a] = positionOnBoard[a] + count;
                                if (positionOnBoard[a] > 12) {
                                    positionOnBoard[a] = 1;
                                }
                            }
                        } else {
                            positionOnBoard[a] = startingPositionOnBoard[a];
                        }

                        if (b[0] == 0){
                            previousLetters[i-1] = letterFromGameBoard(gameBoard,finalMoves,startingPositionOnBoard,a,positionOnBoard);
                        }

                        if ((previousLetters[i-1] == 'S') && (b[0] <= 2)){
                            System.out.println("Player#" + i + " is missing a move.");
                            b[0]++;
                            continue;
                        }

                        System.out.println();
                        System.out.println();
                        System.out.println("Player#" + i);
                        if (finalMoves != 0){
                            System.out.println("Position: " + currentPositionOnBoard(finalMoves, startingPositionOnBoard, a, positionOnBoard));
                            System.out.println("Dice number: " + randomDiceNum);
                        }
                        else {
                            System.out.println("Starting position: " + currentPositionOnBoard(finalMoves, startingPositionOnBoard, a, positionOnBoard));
                        }
                        System.out.println("Letter from board: " + letterFromGameBoard(gameBoard, finalMoves, startingPositionOnBoard, a, positionOnBoard));
                        namesOfBoardPlaces(gameBoard, finalMoves, startingPositionOnBoard, a, positionOnBoard);
                        descriptionAndMoneyOfBoardPlaces(gameBoard, finalMoves, startingPositionOnBoard, a, positionOnBoard, peopleMoney, boughtOrNot);
                        System.out.println("Money: " + peopleMoney[i - 1]);
                        System.out.println();
                        if (letterFromGameBoard(gameBoard, finalMoves, startingPositionOnBoard, a, positionOnBoard) == 'N') {
                            System.out.println("Player#" + i + " won the game");
                            break;
                        }

                        if (b[0] == 2){
                            b[0] = 0;
                        }
                        a++;
                    }

                    cancel();
                }
            }, 4500, 100);
            moves++;
        }
    }
}