package com.company;
import java.util.Scanner;

public class TicTacToeGame {

    public static class TicTacToeVariables {
        /**
         * Function listing all the global variables of the game of Tic Tac Toe
         */
        public static String playerTeam = "X";
        public static String playerNb = "Player 2";
        public static int round = 0;
        public static String C1L1 = " ";
        public static String C1L2 = " ";
        public static String C1L3 = " ";
        public static String C2L1 = " ";
        public static String C2L2 = " ";
        public static String C2L3 = " ";
        public static String C3L1 = " ";
        public static String C3L2 = " ";
        public static String C3L3 = " ";
    }

    static public void TicTacToeGetInputs() {
        /**
         * Take the inputs of the players
         */
        //Get user team and player number
        if(TicTacToeVariables.playerTeam.equals("X")) {
            TicTacToeVariables.playerTeam = "O";
        } else {
            TicTacToeVariables.playerTeam = "X";
        }
        if(TicTacToeVariables.playerNb.equals("Player 1")) {
            TicTacToeVariables.playerNb = "Player 2";
        } else {
            TicTacToeVariables.playerNb = "Player 1";
        }


        while (true) {

            //Get player inputs
            System.out.println("\n" + TicTacToeVariables.playerNb + ", you are playing '" + TicTacToeVariables.playerTeam + "' where do you want to tick ?");

            System.out.print("Column : ");
            Scanner userColumnInput = new Scanner(System.in);
            char userColumnChar = userColumnInput.next().charAt(0);
            int userColumn = Character.getNumericValue(userColumnChar);

            System.out.print("Row : ");
            Scanner userLineInput = new Scanner(System.in);
            char userLineChar = userLineInput.next().charAt(0);
            int userLine = Character.getNumericValue(userLineChar);


            // Check if user inputs are correct
            if (Math.abs(userColumn) < 4 && Math.abs(userLine) < 4 && userColumn != 0 && userLine != 0) {
                if (TicTacToeVariables.round == 0) {
                    MakeStroke(userColumn, userLine);
                } else {
                    //Check if compartment is already taken
                    if (userColumn == 1 && userLine == 1 && TicTacToeVariables.C1L1 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 1 && userLine == 2 && TicTacToeVariables.C1L2 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 1 && userLine == 3 && TicTacToeVariables.C1L3 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 2 && userLine == 1 && TicTacToeVariables.C2L1 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 2 && userLine == 2 && TicTacToeVariables.C2L2 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 2 && userLine == 3 && TicTacToeVariables.C2L3 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 3 && userLine == 1 && TicTacToeVariables.C3L1 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 3 && userLine == 2 && TicTacToeVariables.C3L2 != " ") {
                        System.out.println("The box is already full !");
                    } else if (userColumn == 3 && userLine == 3 && TicTacToeVariables.C3L3 != " ") {
                        System.out.println("The box is already full !");
                    } else {
                        MakeStroke(userColumn, userLine);
                    }
                }
            }
            else {
                System.err.println("Please enter a valid figure");
            }
        }
    }

    static public void MakeStroke(int userColumn, int userLine) {
        /**
         * Function filling the selected square by the user and displays the map
         */
        //Fill case
        if(userColumn == 1 && userLine == 1) {
            TicTacToeVariables.C1L1 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 1 && userLine == 2) {
            TicTacToeVariables.C1L2 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 1 && userLine == 3) {
            TicTacToeVariables.C1L3 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 2 && userLine == 1) {
            TicTacToeVariables.C2L1 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 2 && userLine == 2) {
            TicTacToeVariables.C2L2 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 2 && userLine == 3) {
            TicTacToeVariables.C2L3 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 3 && userLine == 1) {
            TicTacToeVariables.C3L1 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 3 && userLine == 2) {
            TicTacToeVariables.C3L2 = TicTacToeVariables.playerTeam;
        } else if(userColumn == 3 && userLine == 3) {
            TicTacToeVariables.C3L3 = TicTacToeVariables.playerTeam;
        }

        //Print grid
        System.out.println(" --- --- --- ");
        System.out.println("| " + TicTacToeVariables.C1L1 + " | " + TicTacToeVariables.C2L1 + " | " + TicTacToeVariables.C3L1 + " |");
        System.out.println(" --- --- --- ");
        System.out.println("| " + TicTacToeVariables.C1L2 + " | " + TicTacToeVariables.C2L2 + " | " + TicTacToeVariables.C3L2 + " |");
        System.out.println(" --- --- --- ");
        System.out.println("| " + TicTacToeVariables.C1L3 + " | " + TicTacToeVariables.C2L3 + " | " + TicTacToeVariables.C3L3 + " |");
        System.out.println(" --- --- --- ");

        //Check if game is win
        isWin();
    }

    public static void isWin() {
        /**
         * Function checking if the game is won by one of the players or not
         */
        //Check if game is win by 'X'
        //Win by vertical line
        if(TicTacToeVariables.C1L1.equals("X") && TicTacToeVariables.C1L2.equals("X") && TicTacToeVariables.C1L3.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C2L1.equals("X") && TicTacToeVariables.C2L2.equals("X") && TicTacToeVariables.C2L3.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C3L1.equals("X") && TicTacToeVariables.C3L2.equals("X") && TicTacToeVariables.C3L3.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        //Win by horizontal line
        else if(TicTacToeVariables.C1L1.equals("X") && TicTacToeVariables.C2L1.equals("X") && TicTacToeVariables.C3L1.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C1L2.equals("X") && TicTacToeVariables.C2L2.equals("X") && TicTacToeVariables.C3L2.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C1L3.equals("X") && TicTacToeVariables.C2L3.equals("X") && TicTacToeVariables.C3L3.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        //Win by inclined line
        else if(TicTacToeVariables.C1L1.equals("X") && TicTacToeVariables.C2L2.equals("X") && TicTacToeVariables.C3L3.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C1L3.equals("X") && TicTacToeVariables.C2L2.equals("X") && TicTacToeVariables.C3L1.equals("X")) {
            System.out.println("You won !");
            System.exit(0);
        }

        //Check if game is win by 'O'
        //Win by vertical line
        if(TicTacToeVariables.C1L1.equals("O") && TicTacToeVariables.C1L2.equals("O") && TicTacToeVariables.C1L3.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C2L1.equals("O") && TicTacToeVariables.C2L2.equals("O") && TicTacToeVariables.C2L3.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C3L1.equals("O") && TicTacToeVariables.C3L2.equals("O") && TicTacToeVariables.C3L3.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        //Win by horizontal line
        else if(TicTacToeVariables.C1L1.equals("O") && TicTacToeVariables.C2L1.equals("O") && TicTacToeVariables.C3L1.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C1L2.equals("O") && TicTacToeVariables.C2L2.equals("O") && TicTacToeVariables.C3L2.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C1L3.equals("O") && TicTacToeVariables.C2L3.equals("O") && TicTacToeVariables.C3L3.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        //Win by inclined line
        else if(TicTacToeVariables.C1L1.equals("O") && TicTacToeVariables.C2L2.equals("O") && TicTacToeVariables.C3L3.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }
        else if(TicTacToeVariables.C1L3.equals("O") && TicTacToeVariables.C2L2.equals("O") && TicTacToeVariables.C3L1.equals("O")) {
            System.out.println("You won !");
            System.exit(0);
        }

        //Game isn't win
        //Check if game is a tie
        else {
            isEquality();
        }
    }

    public static void isEquality() {
        /**
         * Function checking if there is a tie or not
         */
        if(TicTacToeVariables.C1L1 != " " && TicTacToeVariables.C1L2 != " " && TicTacToeVariables.C1L3 != " " && TicTacToeVariables.C2L1 != " " && TicTacToeVariables.C2L2 != " " && TicTacToeVariables.C2L3 != " " && TicTacToeVariables.C3L1 != " " && TicTacToeVariables.C3L2 != " " && TicTacToeVariables.C3L3 != " ") {
            System.out.println("Tie !");
            System.exit(0);
        }

        //Game isn't a tie
        else {
            //Next stroke
            TicTacToeVariables.round++;
            TicTacToeGetInputs();
        }
    }
}
