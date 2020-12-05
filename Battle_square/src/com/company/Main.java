package com.company;
import java.util.Scanner;

public class Main {

    public static void gameLoop() {
        /**
         * Function that makes the game run in a loop as long as the game is not lost by one of the two players
         */
        GameFunctions.getPlayer1Name();
        GameFunctions.getPlayer2Name();
        GameFunctions.initGame();
        GameFunctions.printGrid();

        // Game loop
        while (true) {
            GameFunctions.getPlayer1Movement();
            GameFunctions.printGrid();

            GameFunctions.playerBreakCompartment();
            GameFunctions.printGrid();

            if(GameFunctions.isLose()) {
                break;
            }

            GameFunctions.getPlayer2Movement();
            GameFunctions.printGrid();

            GameFunctions.playerBreakCompartment();
            GameFunctions.printGrid();

            if(GameFunctions.isLose()) {
                break;
            }
        }
        GameFunctions.gameResult();
    }

    public static void showRules() {
        /**
         * Display the rules of the game then gives the possibility to the user to return to the menu
         */
        System.out.println("\nRules : \n");
        System.out.println("1) The Goal of this game is to be the last one !");
        System.out.println("2) Each turn, players can move on a square and break once");
        System.out.println("3) We can't excess the limit of the map");
        System.out.println("4) The player cannot occupy a destroyed square or a square already occupied");
        System.out.println("6) If the player is blocked the game ended and his opponent win");
        System.out.println("\n-- You can go back to the menu by pressing 'm' --");

        while (true) {
            Scanner exitMenuInput = new Scanner(System.in);
            char exitMenu = exitMenuInput.next().charAt(0);

            if (exitMenu == 'm') {
                display_menu();
                menu();
                break;
            }
        }
    }

    public static void display_menu() {
        /**
         * Function displays all menu suggestions
         */
        System.out.println("welcome to Battle-Square !\n");
        System.out.println ( "1) Play\n2) Rules\n3) Leave" ); //suggestions
        System.out.print ( "Selection : "); //user selection
    }

    public static void menu() {
        /**
         * User choice taken from menu
         */
        while (true) {
            Scanner menuInput = new Scanner(System.in);
            String letter = menuInput.nextLine();

            // Play game
            if (letter.equals("1")) {
                System.out.println("Let's Play !");
                gameLoop();
            }
            // Rules
            else if (letter.equals("2")) {
                System.out.println("He Know The Rules (Macron 2020)");
                showRules();
            }
            // Quit game
            else if (letter.equals("3")) {
                System.out.println("Bye Bye");
                System.exit(0);
            }
            // Bonus
            else if (letter.equals("easter egg")) {
                System.out.println("You don't like this game ? Let's play Tic Tac Toe !");
                TicTacToeGame.TicTacToeGetInputs();
            }
            else {
                System.err.println("Can't Do That");
            }
        }
    }

    public static void main(String[] args) {
        // Menu
        display_menu();
        menu();
    }
}
