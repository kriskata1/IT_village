import java.io.*;
import java.util.*;

public class Main {

    public static void mainCaller() throws IOException {
        RegistrationAndLogin.main(null);
    }

    public static int currentPositionOnBoard(int moves, int[] startingPositionOnBoard, int id, int[] positionOnBoard) {
        if (moves != 0) {
            return positionOnBoard[id];
        } else {
            return startingPositionOnBoard[id];
        }
    }

    public static char letterFromGameBoard(char[] gameBoard, int moves, int[] startingPositionOnBoard, int id, int[] positionOnBoard) {
        return (gameBoard[currentPositionOnBoard(moves, startingPositionOnBoard, id, positionOnBoard) - 1]);
    }

    public static void namesOfBoardPlaces(char[] gameBoard, int moves, int[] startingPositionOnBoard, int id, int[] positionOnBoard) {
        switch (letterFromGameBoard(gameBoard, moves, startingPositionOnBoard, id, positionOnBoard)) {
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

    public static void descriptionOfBoardPlaces(char[] gameBoard, int finalMoves, int[] startingPositionOnBoard, int id, int[] positionOnBoard, int[] peopleMoney, boolean[] boughtOrNot, int[] idsOfBuyers, int[] missingMoves, int i) {
        switch (letterFromGameBoard(gameBoard, finalMoves, startingPositionOnBoard, id, positionOnBoard)) {
            case 'P':
                System.out.println(("You have to buy a Cloud cocktail [-5]"));
                peopleMoney[id] = peopleMoney[id] - 5;
                break;
            case 'I':
                System.out.println("If you have enough money(100) you have to buy it [-100]");
                System.out.println("If not - you have to pay your stay [-10]");
                ifStepOnMotel(peopleMoney, id, positionOnBoard, boughtOrNot, idsOfBuyers, missingMoves);
                isMotelBought(peopleMoney, id, positionOnBoard, boughtOrNot, idsOfBuyers);
                break;
            case 'F':
                System.out.println("You receive a payment [+20]");
                peopleMoney[id] = peopleMoney[id] + 20;
                break;
            case 'S':
                System.out.println("The Wi-Fi in the village dies, you become depressed and skip two rows. [-2 rows]");
                break;
            case 'V':
                System.out.println("Your money multiplies by 10 [*10]");
                peopleMoney[id] = peopleMoney[id] * 10;
                break;
            case 'N':
                System.out.println("If you step on this you win the game");

                if (finalMoves != 0 && finalMoves != 1 && finalMoves != 2) {
                    System.out.println("Player#" + i + " has won the game from " + (finalMoves + 1) + " moves.");
                    System.exit(0);

                } else {
                    System.out.println("Sorry, you can't win from the first, second or third time.");
                }

                break;
        }
    }

    public static void isMotelBought(int[] peopleMoney, int id, int[] positionOnBoard, boolean[] boughtOrNot, int[] idsOfBuyers) {

        if ((peopleMoney[id] >= 100 && positionOnBoard[id] == 2) && !boughtOrNot[0]) {

            peopleMoney[id] = peopleMoney[id] - 100;                    //if the first motel is not bought, buy it and save the id of the player who bought it
            boughtOrNot[0] = true;
            System.out.println("You bought the first motel");
            idsOfBuyers[0] = id;

        } else if ((peopleMoney[id] >= 100 && positionOnBoard[id] == 7) && !boughtOrNot[1]) {

            peopleMoney[id] = peopleMoney[id] - 100;            //if the second motel is not bought, buy it and save the id of the player who bought it
            boughtOrNot[1] = true;
            System.out.println("You bought the second motel");
            idsOfBuyers[1] = id;

        } else if ((peopleMoney[id] >= 100 && positionOnBoard[id] == 10) && !boughtOrNot[2]) {

            peopleMoney[id] = peopleMoney[id] - 100;            //if the third motel is not bought, buy it and save the id of the player who bought it
            boughtOrNot[2] = true;
            System.out.println("You bought the third motel");
            idsOfBuyers[2] = id;

        } else if ((peopleMoney[id] < 100 && positionOnBoard[id] == 2 && !boughtOrNot[0]) || (peopleMoney[id] < 100 && positionOnBoard[id] == 7 && !boughtOrNot[1]) || (peopleMoney[id] < 100 && positionOnBoard[id] == 10 && !boughtOrNot[2])) {
            peopleMoney[id] = peopleMoney[id] - 10;

        }
    }

    public static void ifStepOnMotel(int[] peopleMoney, int id, int[] positionOnBoard, boolean[] boughtOrNot, int[] idsOfBuyers, int[] missingMoves) {

        if (positionOnBoard[id] == 2 && boughtOrNot[0]) {

            if (idsOfBuyers[0] != id) {         //if the current player's id is not equal to the buyer of the first motel give money to the owner and get money from the current player
                System.out.println("You stepped on Player#" + (idsOfBuyers[0] + 1) + "'s motel");

                if (missingMoves[idsOfBuyers[0]] == 0) {

                    System.out.println("You gave 20 coins to the owner.");
                    peopleMoney[idsOfBuyers[0]] = peopleMoney[idsOfBuyers[0]] + 20;     //if the owner of the motel is not missing moves give him his money else not
                    peopleMoney[id] = peopleMoney[id] - 20;

                } else {
                    System.out.println("Player#" + (idsOfBuyers[0] + 1) + " is missing a move and he won't receive 20 coins.");
                }

            }
        }

        if (positionOnBoard[id] == 7 && boughtOrNot[1]) {

            if (idsOfBuyers[1] != id) {         //if the current player's id is not equal to the buyer of the second motel give money to the owner and get money from the current player
                System.out.println("You stepped on Player#" + (idsOfBuyers[1] + 1) + "'s motel");

                if (missingMoves[idsOfBuyers[1]] == 0) {

                    System.out.println("You gave 20 coins to the owner.");
                    peopleMoney[idsOfBuyers[1]] = peopleMoney[idsOfBuyers[1]] + 20;         //if the owner of the motel is not missing moves give him his money else not
                    peopleMoney[id] = peopleMoney[id] - 20;

                } else {
                    System.out.println("Player#" + (idsOfBuyers[1] + 1) + " is missing a move and he won't receive 20 coins.");
                }

            }
        }

        if (positionOnBoard[id] == 10 && boughtOrNot[2]) {

            if (idsOfBuyers[2] != id) {         //if the current player's id is not equal to the buyer of the third motel give money to the owner and get money from the current player
                System.out.println("You stepped on Player#" + (idsOfBuyers[2] + 1) + "'s motel");

                if (missingMoves[idsOfBuyers[2]] == 0) {

                    System.out.println("You gave 20 coins to the owner.");
                    peopleMoney[idsOfBuyers[2]] = peopleMoney[idsOfBuyers[2]] + 20;         //if the owner of the motel is not missing moves give him his money else not
                    peopleMoney[id] = peopleMoney[id] - 20;

                } else {
                    System.out.println("Player#" + (idsOfBuyers[2] + 1) + " is missing a move and he won't receive 20 coins.");
                }

            }
        }
    }

    public static void changePosIfNotMissMove(int finalMoves, int[] startingPositionOnBoard, int id, int[] positionOnBoard, int randomDiceNum) {

        if (finalMoves != 0) {
            int count = 1;
            System.out.println("Previous position: " + currentPositionOnBoard(finalMoves, startingPositionOnBoard, id, positionOnBoard));

            for (int j = 1; j <= randomDiceNum; j++) {
                positionOnBoard[id] = positionOnBoard[id] + count;

                if (positionOnBoard[id] > 12) {
                    positionOnBoard[id] = 1;

                }
            }

            System.out.println("Dice number: " + randomDiceNum);
            System.out.println("New position: " + currentPositionOnBoard(finalMoves, startingPositionOnBoard, id, positionOnBoard));

        } else {

            positionOnBoard[id] = startingPositionOnBoard[id];
            System.out.println("Starting position: " + currentPositionOnBoard(finalMoves, startingPositionOnBoard, id, positionOnBoard));

        }
    }

    public static void ifMissingMove(char[] previousLetters, int i, int[] missingMoves, int finalMoves, int[] startingPositionOnBoard, int id, int[] positionOnBoard) {

        if ((previousLetters[i - 1] == 'S') && (missingMoves[i - 1] < 2)) {     //if a player received 'S' from a previous move, and he is missing less than 2 moves

            if (finalMoves != 0 && missingMoves[i - 1] != 0) {
                System.out.println("Position: " + currentPositionOnBoard(finalMoves, startingPositionOnBoard, id, positionOnBoard));    //displays position even when missing moves

            }

            System.out.println();
            System.out.println("Player#" + i + " is missing a move.");
            System.out.println();

            missingMoves[i - 1]++;

            if (missingMoves[i - 1] == 2) {
                missingMoves[i - 1] = 0;        //when he misses the second move it resets his missed moves

            }
        }
    }

    public static void ifRunOutOfMoney(int i, int people, boolean[] runOutOfMoney, String check) {

        if (i == people) {
            int countIf1 = 0;

            for (int j = 0; j < runOutOfMoney.length; j++) {
                if (runOutOfMoney[j]) {
                    check = check + "1";

                } else {
                    check = check + "0";

                }
            }
            if (!(check.contains("0"))) {
                System.out.println();
                System.out.println("Everyone ran out of money. Game ends.");
                System.exit(0);

            }
            for (int j = 0; j < check.length(); j++) {

                if (check.charAt(j) == '1') {
                    countIf1++;

                    if (countIf1 == (people - 1)) {
                        System.out.println();
                        System.out.println("Player#" + (check.indexOf("0") + 1) + " has won the game");
                        System.exit(0);

                    }
                }
            }
        }
    }

    public static void areMotelsBought(int[] idsOfBuyers, int i, boolean[] boughtOrNot, int[] peopleMoney) {

        if ((boughtOrNot[0] && (idsOfBuyers[0] == i - 1)) && (boughtOrNot[1] && (idsOfBuyers[1] == i - 1)) && (boughtOrNot[2] && (idsOfBuyers[2] == i - 1))) {
            System.out.println();
            System.out.println("Player#" + i + " bought all motels. He has " + peopleMoney[i - 1] + " money.");
            System.exit(0);

        }
    }

    public static void isOutOfMoves(int moves, int randomMoves, int people, Scanner sc, int[] peopleMoney) {
        if (moves == randomMoves) {

            for (int i = 1; i <= people; i++) {

                System.out.println();
                System.out.println("Player#" + i + " ran out of moves. Player#" + i + " has got " + peopleMoney[i - 1] + " money!");
            }

            System.exit(0);

        } else {
            String rollAgain = sc.nextLine();
            String rollDice = "Rolling the dice";
            System.out.print(rollDice);

        }
    }

    public static void loadingAnimation() {
        new Timer().scheduleAtFixedRate(new TimerTask() {       //a timer that runs a function every second until the dots become four(simulating dice rolling)
            int times = 0;

            @Override
            public void run() {
                times++;
                System.out.print(".");

                if (times == 4) {
                    cancel();
                }

            }
        }, 500, 1000);
    }

    public static void ranOutOfMoneyMessage(int[] peopleMoney, int i, boolean[] runOutOfMoney, int people) {
        if (peopleMoney[i - 1] <= 0) {
            System.out.println("Player#" + i + " ran out of money.");
            runOutOfMoney[i - 1] = true;

            if (people == 1){
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        mainCaller();   //calling the registration and login class before the beginning of the game

        int moves = 0;
        String filePath = "C:\\Users\\Крис\\Desktop\\web\\Back end\\Final project\\IT_village\\peopleCount.csv";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        Random random = new Random();
        char[] gameBoard = {'P', 'I', 'F', 'S', 'F', 'V', 'I', 'F', 'F', 'I', 'N', 'P'};
        int people = Integer.parseInt(reader.readLine());   //get the amount of people playing from the csv file
        boolean[] boughtOrNot = new boolean[3];
        int[] peopleMoney = new int[people];

        for (int i = 0; i < peopleMoney.length; i++) {      //gives every player 50 money
            peopleMoney[i] = 50;

        }
        int randomMoves = 6 + random.nextInt(28 - 6 + 1);
        System.out.println();
        System.out.println("Player/players get " + randomMoves + " moves for the game.");
        int[] startingPositionOnBoard = new int[people];
        int[] positionOnBoard = new int[people];
        char[] previousLetters = new char[people];
        boolean[] runOutOfMoney = new boolean[people];
        System.out.println();
        System.out.println("Press ENTER to roll the dice.");
        int[] missingMoves = new int[people];
        int[] idsOfBuyers = new int[3];

        while (moves <= randomMoves) {
            isOutOfMoves(moves, randomMoves, people, sc, peopleMoney);

            loadingAnimation();
            int finalMoves = moves;
            new Timer().scheduleAtFixedRate(new TimerTask() {       //a timer that runs the function after 4.5 seconds(right after the dice finished rolling)

                @Override
                public void run() {
                    int id = 0;

                    for (int i = 1; i <= people; i++) {     //loops through every player's current stats
                        String check = "";

                        if (peopleMoney[i - 1] <= 0) {
                            System.out.println();
                            System.out.println("Player#" + i + " ran out of money.");
                            runOutOfMoney[i - 1] = true;

                            if (people == 1){
                                System.exit(0);
                            }
                            ifRunOutOfMoney(i, people, runOutOfMoney, check);
                            id++;
                            continue;

                        }

                        areMotelsBought(idsOfBuyers, i, boughtOrNot, peopleMoney);

                        startingPositionOnBoard[i - 1] = random.nextInt(gameBoard.length) + 1;
                        int randomDiceNum = random.nextInt(6) + 1;

                        System.out.println();
                        System.out.println();
                        System.out.println("Player#" + i);
                        System.out.println("Money before payments: " + peopleMoney[i - 1]);

                        if (missingMoves[i - 1] == 0) {     //if a player is not missing a move change and display his position and dice number

                            changePosIfNotMissMove(finalMoves, startingPositionOnBoard, id, positionOnBoard, randomDiceNum);

                            previousLetters[i - 1] = letterFromGameBoard(gameBoard, finalMoves, startingPositionOnBoard, id, positionOnBoard);

                        }
                        ifMissingMove(previousLetters, i, missingMoves, finalMoves, startingPositionOnBoard, id, positionOnBoard);

                        System.out.println("Letter from board: " + letterFromGameBoard(gameBoard, finalMoves, startingPositionOnBoard, id, positionOnBoard));
                        namesOfBoardPlaces(gameBoard, finalMoves, startingPositionOnBoard, id, positionOnBoard);
                        descriptionOfBoardPlaces(gameBoard, finalMoves, startingPositionOnBoard, id, positionOnBoard, peopleMoney, boughtOrNot, idsOfBuyers, missingMoves, i);
                        System.out.println("Money after payments: " + peopleMoney[i - 1]);
                        System.out.println();

                        ranOutOfMoneyMessage(peopleMoney,i, runOutOfMoney, people);
                        ifRunOutOfMoney(i, people, runOutOfMoney, check);

                        id++;
                    }
                    cancel();
                }
            }, 4500, 100);

            moves++;
        }
    }
}