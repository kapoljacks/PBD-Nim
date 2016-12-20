/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nim;

import java.util.Arrays;

/**
 *
 * @author VC-019
 */
public class Piles {
    
    private int aPile;
    private int bPile;
    private int cPile;
    int[] pilesArray;
    
    public Piles (int aPile, int bPile, int cPile) {
        this.aPile = aPile;
        this.bPile = bPile;
        this.cPile = cPile;
    }

    //since rows should be equal to whichever pile has the highest int, this method detects that number and gives us that int to pass into drawPiles method
    public int sortPilesReturnRows(){
        
        this.pilesArray = new int[] {aPile, bPile, cPile};
        
        Arrays.sort(this.pilesArray);
        
        return this.pilesArray[2];
    }
    
    public void removeFromPile(String pile, int removeThese) {
        
        if (pile.equals("A")) {
            this.setaPile(this.getaPile() - removeThese);
        }
        if (pile.equals("B")) {
            this.setbPile(this.getbPile() - removeThese);
        }
        if (pile.equals("C")) {
            this.setcPile(this.getcPile() - removeThese);
        }
    }
    
    public int Evaluate(String selectedPile) {
        if (selectedPile.equals("A")) {
            return this.getaPile();
        }
        if (selectedPile.equals("B")) {
            return this.getbPile();
        }
        if (selectedPile.equals("C")) {
            return this.getcPile();
        }
        return 0;
    }
    
    public void drawPiles() {
        
        for (int rows = 5; rows > 0; rows--) {
               
            if (getaPile() < rows) {
                 System.out.print("  ");
            }
            else if (getaPile() >= rows) {
                 System.out.print("* ");
            }
            if (getbPile() < rows) {
                 System.out.print("  ");
            }
            else if (getbPile() >= rows){
                 System.out.print("* ");
            }
            if (getcPile() < rows) {
                 System.out.print("  ");
            }
            else if (getcPile() >= rows){
                 System.out.print("* ");
            }
            System.out.println("");    
        }
        System.out.println("A B C");
        
    }
    
    public void drawPiles(int rows) {
        
        for (int loopRows = rows; loopRows > 0; loopRows--) {
               
            if (getaPile() < loopRows) {
                 System.out.print("  ");
            }
            else if (getaPile() >= loopRows) {
                 System.out.print("* ");
            }
            if (getbPile() < loopRows) {
                 System.out.print("  ");
            }
            else if (getbPile() >= loopRows){
                 System.out.print("* ");
            }
            if (getcPile() < loopRows) {
                 System.out.print("  ");
            }
            else if (getcPile() >= loopRows){
                 System.out.print("* ");
            }
            System.out.println("");    
        }
        System.out.println("A B C");       
    }
    
    public boolean isGameOver() {
        if ( this.getaPile() == 0 && this.getbPile() == 0 && this.getcPile() == 0 ) {
            return true;
        }
        return false;
    }
    
    public boolean dignityChecker() {
        if ( this.getaPile() +  this.getbPile() + this.getcPile() == 1 ) {
            return true;
        }
        return false;
    }
    
        /**
     * @return the aPile
     */
    public int getaPile() {
        return aPile;
    }

    /**
     * @param aPile the aPile to set
     */
    public void setaPile(int aPile) {
        this.aPile = aPile;
    }

    /**
     * @return the bPile
     */
    public int getbPile() {
        return bPile;
    }

    /**
     * @param bPile the bPile to set
     */
    public void setbPile(int bPile) {
        this.bPile = bPile;
    }

    /**
     * @return the cPile
     */
    public int getcPile() {
        return cPile;
    }

    /**
     * @param cPile the cPile to set
     */
    public void setcPile(int cPile) {
        this.cPile = cPile;
    }

}

