/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author Chris Badolato
 */
class PlayerSet {
    String Player1;
    String Player2;
    int currentPlayer;
    
    public PlayerSet(String p1, String p2){
        this.Player1 = p1;
        this.Player2 = p2;
        this.currentPlayer = 0;
    }
    
    public void set(String p1, String p2){
        this.Player1 = p1;
        this.Player2 = p2;
        
    }
    public String getp1(){
        return this.Player1;  
    }
    
    public String getp2(){
        return this.Player2;
    }
    
    
}

class ArrayCheck {
    
}
