package com.company;

import java.util.Scanner;

public class GameFunctions {

    public static class globalVariables {
        /***
         * Class listing all the global variables of the functions of the game
         */
        // Declaration of variables
        public static String winnerName;
        public static String player1Name, player2Name;
        public static String signPlayer1 = "1";
        public static String signPlayer2 = "2";
        public static int[] player1Coordinates = {4, 5};
        public static int[] player2Coordinates = {5, 5};
        public static String[][] grid = {
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        };
    }

    public static void getPlayer1Name() {
        /***
         * Take player 1 's name
         * Keep player 1 's name in global variable player1Name
         */
        while (true) {
            System.out.print("Player 1, enter your name : ");
            Scanner player1Input = new Scanner(System.in);
            String player1Name = player1Input.nextLine();

            if (player1Name.length() >= 2 && player1Name.length() <= 10) {
                globalVariables.player1Name = player1Name;
                break;
            } else {
                System.out.println("You cannot enter less than 2 characters and more than 10 characters");
            }
        }
    }

    public static void getPlayer2Name() {
        /***
         * Take player 2 's name
         * Keep player 2 's name in global variable player2Name
         */
        while (true) {
            System.out.print("Player 2, enter your name : ");
            Scanner player2Input = new Scanner(System.in);
            String player2Name = player2Input.nextLine();

            if (player2Name.length() >= 2 && player2Name.length() <= 10) {
                globalVariables.player2Name = player2Name;
                break;
            }  else {
                System.out.println("You cannot enter less than 2 characters and more than 10 characters");
            }
        }
    }

    public static void initGame() {
        /***
         * Initialisation of the map and of the player's position on the map
         */
        globalVariables.player1Coordinates = new int[]{4, 5};
        globalVariables.player2Coordinates = new int[]{5, 5};
        globalVariables.grid = new String[][]{
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "},
        };

        // Init positions of players
        int rowP1 = globalVariables.player1Coordinates[0];
        int colP1 = globalVariables.player1Coordinates[1];
        int rowP2 = globalVariables.player2Coordinates[0];
        int colP2 = globalVariables.player2Coordinates[1];

        globalVariables.grid[rowP1][colP1] = globalVariables.signPlayer1;
        globalVariables.grid[rowP2][colP2] = globalVariables.signPlayer2;
    }

    public static void printGrid() {
        /***
         * Show the map
         */
        // Draw grid
        int rowCounter = 0;
        System.out.println("\n    0   1   2   3   4   5   6   7   8   9  10");
        System.out.println("  |---|---|---|---|---|---|---|---|---|---|---|");

        for (int row = 0; row < globalVariables.grid.length; row++) {
            for (int col = 0; col < globalVariables.grid[row].length; col++) {
                if (col == 0) {
                    System.out.print(rowCounter + " |");
                    rowCounter++;
                }

                System.out.print(" " + globalVariables.grid[row][col] + " |");
            }
            System.out.println();
            System.out.println("  |---|---|---|---|---|---|---|---|---|---|---| ");
        }
        System.out.println();
    }

    public static void getPlayer1Movement() {
        /**
         * take shifting of player 1 (left, right, up, down)
         * Updating the coordinates of player 1 on the map
         * reset the square player 1 has been
         */
        while (true) {
            // Get player movement
            System.out.println(globalVariables.player1Name + ", it's your turn, you can move ! (z, q, s, d) : ");
            Scanner movementInput = new Scanner(System.in);
            char movement = movementInput.next().charAt(0);

            if (movement == 'z') {

                // Change player coordinates
                globalVariables.player1Coordinates[0] -= 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[0] += 1;
                    getPlayer1Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[0] += 1;
                    getPlayer1Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player1Coordinates[0]][globalVariables.player1Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[0] += 1;
                    getPlayer1Movement();
                    break;
                }

                else {
                    int rowP1 = globalVariables.player1Coordinates[0];
                    int colP1 = globalVariables.player1Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP1][colP1] = globalVariables.signPlayer1;

                    // Clear compartment
                    globalVariables.grid[rowP1 + 1][colP1] = " ";

                    break;
                }
            }
            else if (movement == 's') {

                // Change player coordinates
                globalVariables.player1Coordinates[0] += 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[0] -= 1;
                    getPlayer1Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[0] -= 1;
                    getPlayer1Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player1Coordinates[0]][globalVariables.player1Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[0] -= 1;
                    getPlayer1Movement();
                    break;
                }

                else {
                    int rowP1 = globalVariables.player1Coordinates[0];
                    int colP1 = globalVariables.player1Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP1][colP1] = globalVariables.signPlayer1;

                    // Clear compartment
                    globalVariables.grid[rowP1 - 1][colP1] = " ";

                    break;
                }
            }
            else if (movement == 'q') {

                // Change player coordinates
                globalVariables.player1Coordinates[1] -= 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[1] += 1;
                    getPlayer1Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[1] += 1;
                    getPlayer1Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player1Coordinates[0]][globalVariables.player1Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[1] += 1;
                    getPlayer1Movement();
                    break;
                }

                else {
                    int rowP1 = globalVariables.player1Coordinates[0];
                    int colP1 = globalVariables.player1Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP1][colP1] = globalVariables.signPlayer1;

                    // Clear compartment
                    globalVariables.grid[rowP1][colP1 + 1] = " ";

                    break;
                }
            }
            else if (movement == 'd') {

                // Change player coordinates
                globalVariables.player1Coordinates[1] += 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[1] -= 1;
                    getPlayer1Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[1] -= 1;
                    getPlayer1Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player1Coordinates[0]][globalVariables.player1Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player1Name);
                    globalVariables.player1Coordinates[1] -= 1;
                    getPlayer1Movement();
                    break;
                }

                else {
                    int rowP1 = globalVariables.player1Coordinates[0];
                    int colP1 = globalVariables.player1Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP1][colP1] = globalVariables.signPlayer1;

                    // Clear compartment
                    globalVariables.grid[rowP1][colP1 - 1] = " ";

                    break;
                }
            }
            else {
                System.out.println("The character is incorrect");
            }
        }
    }

    public static void getPlayer2Movement() {
        /**
         * take shifting of player 2 (left, right, up, down)
         * Updating the coordinates of player 2 on the map
         * reset the square player 2 has been
         */
        while (true) {
            // Get player movement
            System.out.println(globalVariables.player2Name + ", it's your turn, you can move ! (z, q, s, d) : ");
            Scanner movementInput = new Scanner(System.in);
            char movement = movementInput.next().charAt(0);

            if (movement == 'z') {

                // Change player coordinates
                globalVariables.player2Coordinates[0] -= 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[0] += 1;
                    getPlayer2Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[0] += 1;
                    getPlayer2Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player2Coordinates[0]][globalVariables.player2Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[0] += 1;
                    getPlayer2Movement();
                    break;
                }

                else {
                    int rowP2 = globalVariables.player2Coordinates[0];
                    int colP2 = globalVariables.player2Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP2][colP2] = globalVariables.signPlayer2;

                    // Clear compartment
                    globalVariables.grid[rowP2 + 1][colP2] = " ";

                    break;
                }
            }
            else if (movement == 's') {

                // Change player coordinates
                globalVariables.player2Coordinates[0] += 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[0] -= 1;
                    getPlayer2Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[0] -= 1;
                    getPlayer2Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player2Coordinates[0]][globalVariables.player2Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[0] -= 1;
                    getPlayer2Movement();
                    break;
                }

                else {
                    int rowP2 = globalVariables.player2Coordinates[0];
                    int colP2 = globalVariables.player2Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP2][colP2] = globalVariables.signPlayer2;

                    // Clear compartment
                    globalVariables.grid[rowP2 - 1][colP2] = " ";
                    break;
                }
            }
            else if (movement == 'q') {

                // Change player coordinates
                globalVariables.player2Coordinates[1] -= 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[1] += 1;
                    getPlayer2Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[1] += 1;
                    getPlayer2Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player2Coordinates[0]][globalVariables.player2Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[1] += 1;
                    getPlayer2Movement();
                    break;
                }

                else {
                    int rowP2 = globalVariables.player2Coordinates[0];
                    int colP2 = globalVariables.player2Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP2][colP2] = globalVariables.signPlayer2;

                    // Clear compartment
                    globalVariables.grid[rowP2][colP2 + 1] = " ";
                    break;
                }
            }
            else if (movement == 'd') {

                // Change player coordinates
                globalVariables.player2Coordinates[1] += 1;

                // Check if compartment is already taken by opponent
                if (globalVariables.player1Coordinates[0] == globalVariables.player2Coordinates[0] && globalVariables.player1Coordinates[1] == globalVariables.player2Coordinates[1]) {
                    System.out.println("The square is already taken by your opponent " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[1] -= 1;
                    getPlayer2Movement();
                }

                // Check if player want to get out of the grid
                if (!isBorderGrid()) {
                    System.out.println("You can't get off the map " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[1] -= 1;
                    getPlayer2Movement();
                    break;
                }

                // Check if compartment is already destroyed
                if (globalVariables.grid[globalVariables.player2Coordinates[0]][globalVariables.player2Coordinates[1]].equals("#")) {
                    System.out.println("You can't move on a destroyed square " + globalVariables.player2Name);
                    globalVariables.player2Coordinates[1] -= 1;
                    getPlayer2Movement();
                    break;
                }

                else {
                    int rowP2 = globalVariables.player2Coordinates[0];
                    int colP2 = globalVariables.player2Coordinates[1];

                    // Give position of player to grid
                    globalVariables.grid[rowP2][colP2] = globalVariables.signPlayer2;

                    // Clear compartment
                    globalVariables.grid[rowP2][colP2 - 1] = " ";
                    break;
                }
            }
            else {
                System.out.println("The character is incorrect");
            }
        }
    }

    public static boolean isBorderGrid() {
        /***
         * This function returns a boolean indicating whether the player will be outside the map following the movement he is about to do
         *
         * The boolean returned by this function is called in the functions getPlayer1Movement et getPlayer2Movement
         */
        boolean playerMovement;

        // Check if player is at the border of the grid
        if (globalVariables.player1Coordinates[0] == -1 || globalVariables.player2Coordinates[0] == -1 ||
            globalVariables.player1Coordinates[0] == 10 || globalVariables.player2Coordinates[0] == 10 ||
            globalVariables.player1Coordinates[1] == -1 || globalVariables.player2Coordinates[1] == -1 ||
            globalVariables.player1Coordinates[1] == 11 || globalVariables.player2Coordinates[1] == 11)
        {
            playerMovement = false;
        }
        else {
            playerMovement = true;
        }

        return playerMovement;
    }

    public static void playerBreakCompartment() {
        /**
         * Function which can destroy an empty square
         * The chosen empty square is filled with the value String '#'
         */
        while (true) {
            //Get player column and row inputs
            System.out.println("which square do you want to destroy ?");

            System.out.print("Column : ");
            Scanner playerColumnInput = new Scanner(System.in);
            int playerColumn = playerColumnInput.nextInt();

            System.out.print("Row : ");
            Scanner playerRowInput = new Scanner(System.in);
            int playerRow = playerRowInput.nextInt();

            // Check if player inputs are good
            if (Math.abs(playerColumn) < 11 && Math.abs(playerRow) < 10) {

                if (isCompartmentEmpty(playerColumn, playerRow)) {
                    globalVariables.grid[playerRow][playerColumn] = "#";
                    break;
                }
                else {
                    System.out.println("You can't destroy this square \n");
                }
            }
            else {
                System.out.println("Please enter a valid number \n");
            }
        }
    }

    public static boolean isCompartmentEmpty(int playerColumn, int playerRow) {
        /**
         * This function returns a boolean indicating, for a given cell, whether the latter is empty or not
         *
         * The boolean returned by this function is called in the functions playerBreakCompartment
         */
        boolean compartmentEmpty;

        // Check if compartment is occupied
        if (
            globalVariables.player1Coordinates[0] == playerRow && globalVariables.player1Coordinates[1] == playerColumn ||
            globalVariables.player2Coordinates[0] == playerRow && globalVariables.player2Coordinates[1] == playerColumn ||
            globalVariables.grid[playerRow][playerColumn].equals("#"))
        {
            compartmentEmpty = false;
        }
        // Return that compartment is empty
        else {
            compartmentEmpty = true;
        }

        return compartmentEmpty;
    }

    public static boolean isLose() {
        /***
         * This function returns a boolean indicating if the game is lost or not
         *
         * The boolean returned by this function is called in the gameLoop functions of the Main.java file
         */

        // Initialization of variables
        boolean isLose;
        int rowP1 = globalVariables.player1Coordinates[0];
        int colP1 = globalVariables.player1Coordinates[1];
        int rowP2 = globalVariables.player2Coordinates[0];
        int colP2 = globalVariables.player2Coordinates[1];

        // Check if player 1 lose the game
        if (globalVariables.player1Coordinates[0] != 0 && globalVariables.player1Coordinates[0] != 9 &&
                globalVariables.player1Coordinates[1] != 0 && globalVariables.player1Coordinates[1] != 10 &&
                globalVariables.grid[rowP1 + 1][colP1].equals("#") && globalVariables.grid[rowP1 - 1][colP1].equals("#") &&
                globalVariables.grid[rowP1][colP1 + 1].equals("#") && globalVariables.grid[rowP1][colP1 - 1].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player2Name;
        }

        // Check if player 1 lose at border of map
        // Top line
        else if (globalVariables.player1Coordinates[0] == 0 && globalVariables.grid[rowP1][colP1 + 1].equals("#") &&
                 globalVariables.grid[rowP1][colP1 - 1].equals("#") && globalVariables.grid[rowP1 + 1][colP1].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player2Name;
        }
        // Bottom line
        else if (globalVariables.player1Coordinates[0] == 9 && globalVariables.grid[rowP1][colP1 + 1].equals("#") &&
                globalVariables.grid[rowP1][colP1 - 1].equals("#") && globalVariables.grid[rowP1 - 1][colP1].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player2Name;
        }
        // Left line
        else if (globalVariables.player1Coordinates[1] == 0 && globalVariables.grid[rowP1][colP1 + 1].equals("#") &&
                globalVariables.grid[rowP1 + 1][colP1].equals("#") && globalVariables.grid[rowP1 - 1][colP1].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player2Name;
        }
        // Right line
        else if (globalVariables.player1Coordinates[1] == 10 && globalVariables.grid[rowP1][colP1 - 1].equals("#") &&
                globalVariables.grid[rowP1 + 1][colP1].equals("#") && globalVariables.grid[rowP1 - 1][colP1].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player2Name;
        }

        // Check if player 2 lose the game
        else if (
                globalVariables.player2Coordinates[0] != 0 && globalVariables.player2Coordinates[0] != 9 &&
                globalVariables.player2Coordinates[1] != 0 && globalVariables.player2Coordinates[1] != 10 &&
                globalVariables.grid[rowP2 + 1][colP2].equals("#") && globalVariables.grid[rowP2 - 1][colP2].equals("#") &&
                globalVariables.grid[rowP2][colP2 + 1].equals("#") && globalVariables.grid[rowP2][colP2 - 1].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player1Name;
        }
        // Check if player 2 lose at border of map
        // Top line
        else if (globalVariables.player2Coordinates[0] == 0 && globalVariables.grid[rowP2][colP2 + 1].equals("#") &&
                globalVariables.grid[rowP2][colP2 - 1].equals("#") && globalVariables.grid[rowP2 + 1][colP2].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player1Name;
        }
        // Bottom line
        else if (globalVariables.player2Coordinates[0] == 9 && globalVariables.grid[rowP2][colP2 + 1].equals("#") &&
                globalVariables.grid[rowP2][colP2 - 1].equals("#") && globalVariables.grid[rowP2 - 1][colP2].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player1Name;
        }
        // Left line
        else if (globalVariables.player2Coordinates[1] == 0 && globalVariables.grid[rowP2][colP2 + 1].equals("#") &&
                globalVariables.grid[rowP2 + 1][colP2].equals("#") && globalVariables.grid[rowP2 - 1][colP2].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player1Name;
        }
        // Right line
        else if (globalVariables.player2Coordinates[1] == 10 && globalVariables.grid[rowP2][colP2 - 1].equals("#") &&
                globalVariables.grid[rowP2 + 1][colP2].equals("#") && globalVariables.grid[rowP2 - 1][colP2].equals("#"))
        {
            isLose = true;
            globalVariables.winnerName = globalVariables.player1Name;
        }
        else {
            isLose = false;
        }

        return isLose;
    }

    public static void gameResult() {
        /***
         * End of game function indicating the name of the winner and showing the possibility of returning to the menu
         */
        System.out.println("game is ended !");
        System.out.println(globalVariables.winnerName + " won the game !");

        while (true) {
            System.out.println("\n-- tape 'm' to access to the menu --");
            Scanner goToMenuInput = new Scanner(System.in);
            char goToMenu = goToMenuInput.next().charAt(0);

            if (goToMenu == 'm') {
                Main.display_menu();
                Main.menu();
            }
        }
    }
}

