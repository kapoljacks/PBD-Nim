/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import java.util.Scanner;

public class Nim {

        
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        boolean wonGame = false;
        
        // prompt for number of players. if two, stay here. if one, jump to VsComputerClass and execute onePlayerGame
        
        System.out.println("Welcome to NIM.");
        System.out.print("1 or 2 players? ");
        int gameType = input.nextInt();
        if (gameType == 1) {
            VsComputer newGame = new VsComputer();
            newGame.onePlayerGame();
        }
        else {
            // get player names  
            System.out.print("Player 1, enter your name: ");
            String p1 = input.next();
            System.out.print("Player 2, enter your name: ");
            String p2 = input.next();
            System.out.println("");

            // start new game, create piles object with specified amounts for each pile, player 1 always starts
            Piles piles = new Piles(3, 4, 5);
            piles.drawPiles(piles.sortPilesReturnRows());
            String currentPlayer = p1;

            // main game loop, alternating player turns
            while (true) {         
                //prompt for pile selection and number to remove
                System.out.println("");
                System.out.print(currentPlayer + ", select a pile: ");

                String selectedPile;
                //do while loop prevents user from choosing an empty pile and breaking the game
                do { 
                    selectedPile = input.next();
                    selectedPile = selectedPile.toUpperCase();
                    if (piles.Evaluate(selectedPile) == 0) {
                        System.out.printf("Nice try, %s. That pile is empty. Try again: ", currentPlayer);
                    }
                } while ( piles.Evaluate(selectedPile) == 0);

                //if move is legal, move on to next prompt
                System.out.print("Remove how many from pile? ");
                int removeThese;

                //do while loop to detect illegal moves, utilizes evaluate method
                do {
                    removeThese = input.nextInt();
                    if (removeThese < 0) {
                        System.out.print("You must choose at least 1. Try again: ");
                    }
                    else if (removeThese > piles.Evaluate(selectedPile)) {
                        System.out.print("That pile doesn't have that many. Try again: ");
                    }
                }
                while (removeThese < 0 || removeThese > piles.Evaluate(selectedPile));

                // if move is legal, remove chosen amount from pile, draw piles again
                piles.removeFromPile(selectedPile, removeThese);
                System.out.println("");
                piles.drawPiles(piles.sortPilesReturnRows());
                ////check if game is has been won, first by dignity, then by other circumstance
                wonGame = piles.dignityChecker();
                if (wonGame) { break; }
                wonGame = piles.isGameOver();
                if (wonGame) { break; }

                //change turns
                if (currentPlayer.equals(p1)) {
                    currentPlayer = p2;
                } else {
                    currentPlayer = p1;
                }           
            }
            // was game won in dignity scenario? handle both that and other way of winning
            boolean dignity = piles.dignityChecker();

            if (dignity) {
                String winner = currentPlayer;
                String loser;

                if   ( currentPlayer.equals(p1) ) { loser = p2; }
                else { loser = p1; }

                System.out.println("");
                System.out.printf("%s, you must take the last counter. %s wins!\n", loser, winner);
            }
            else {
                //assign loser and winner and print game results
                String loser = currentPlayer;
                String winner;

                if   ( currentPlayer.equals(p1) ) { winner = p2; }
                else { winner = p1; }

                System.out.println("");
                System.out.printf("%s took the last counter. %s wins!\n", loser, winner);
            }
        }
    }
    
}
