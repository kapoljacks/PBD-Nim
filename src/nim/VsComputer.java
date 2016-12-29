/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import java.util.Random;
import java.util.Scanner;

public class VsComputer {
   
    public void onePlayerGame() {
        
        Scanner input = new Scanner(System.in);
        boolean wonGame = false;
        
       
        // get player names  
        System.out.print("Player 1, enter your name: ");
        String p1 = input.next();
        System.out.println("Player 2 is HAL!");
        String p2 = "HAL";
        System.out.println("");
        
        // start new game, create piles object with specified amounts for each pile, player 1 always starts
        Piles piles = new Piles(3, 4, 5);
        piles.drawPiles(piles.sortPilesReturnRows());
        String currentPlayer = p1;
        
        // main game loop, alternating player turns
        while (true) {         
            // if it is human turn, do this, ELSE do ComputerTurn
            if (currentPlayer.equals(p1)) {
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
            } else {
                // COMPUTER TURN
                System.out.println("");
                Random rng = new Random();
                int readPileA, readPileB, readPileC;
                readPileA = piles.getaPile();
                readPileB = piles.getbPile();
                readPileC = piles.getcPile();
                String selectedPile = pickPile(readPileA, readPileB, readPileC);
                int counters = 0;
                switch (selectedPile) {
                    case "A": int max = piles.getaPile();
                    counters = rng.nextInt(max) + 1;
                    break;

                    case "B": max = piles.getbPile();
                    counters = rng.nextInt(max) + 1;
                    break;

                    case "C": max = piles.getcPile();
                    counters = rng.nextInt(max) + 1;
                    break;
                }
                // actually call pile and remove counters
                piles.removeFromPile(selectedPile, counters);
                
                // tell human what computer did and redraw piles        
                System.out.println("Computer's turn...");
                System.out.printf("HAL selects pile %s and removes %d counters.\n", selectedPile, counters);
                System.out.println("");
                piles.drawPiles(piles.sortPilesReturnRows());
                //check if game has been won
                wonGame = piles.dignityChecker();
                if (wonGame) { break; }
                wonGame = piles.isGameOver();
                if (wonGame) { break; }
                
                // switch back to human
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

    public String pickPile(int readPileA, int readPileB, int readPileC) {
        String selectedPile = null;
        Random rng = new Random();
        if (readPileA > 0 && readPileB > 0 && readPileC > 0) {
            int random = rng.nextInt(3);
            if (random == 0) {
                selectedPile = "A";
            } else if (random == 1) {
                selectedPile = "B";
            } else {
                selectedPile = "C";
            }
        } else if (readPileA == 0 && readPileB > 0 && readPileC > 0) {
            int random = rng.nextInt(2);
            if (random == 0) {
                selectedPile = "B";
            } else {
                selectedPile = "C";
            }
        } else if (readPileA > 0 && readPileB == 0 && readPileC > 0) {
            int random = rng.nextInt(2);
            if (random == 0) {
                selectedPile = "A";
            } else {
                selectedPile = "C";
            }
        } else if (readPileA > 0 && readPileB > 0 && readPileC == 0) {
            int random = rng.nextInt(2);
            if (random == 0) {
                selectedPile = "A";
            } else {
                selectedPile = "B";
            }
        } else if (readPileA == 0 && readPileB == 0 && readPileC > 0) {
            selectedPile = "C";
        } else if (readPileA > 0 && readPileB == 0 && readPileC == 0) {
            selectedPile = "A";
        } else if (readPileA == 0 && readPileB > 0 && readPileC == 0) {
            selectedPile = "B";
        }
        return selectedPile;
    }
    
}
