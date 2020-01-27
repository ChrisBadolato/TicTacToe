/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author Chris Badolato
 */
public class BoardController implements Initializable {

    @FXML
    private Button Butt7;
    @FXML
    private Button Butt6;
    @FXML
    private Button Butt8;
    @FXML
    private Button Butt3;
    @FXML
    private Button Butt4;
    @FXML
    private Button Butt5;
    @FXML
    private Button Butt0;
    @FXML
    private Button Butt1;
    @FXML
    private Button Butt2;
    @FXML
    private Label p1Label;
    @FXML
    private Label p2Label;
    @FXML
    private Button startButton;
    
    private PlayerSet playerSet;
    private boolean gameStarted;
    private int[] gameBoard = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int p1Wins = 0;
    private int p2Wins = 0;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.gameStarted = false;
    }
    
    public void setLabels(String p1Text, String p2Text){
        p1Label.setText(p1Text);
        p2Label.setText(p2Text);
        
        playerSet = new PlayerSet(p1Text, p2Text);
    }
    
    @FXML
    public void Turn(MouseEvent e){
        if (!gameStarted){
            return;
        }
        
        Button clickedButton = (Button)e.getSource();
        String buttonId = clickedButton.getId();
        
        // Convert button ID to index into 1d array
        int boardIndex = retrieveBoardIndex(buttonId);
        
        // make sure spot is empty
        if(gameBoard[boardIndex]!=0){
            return;         
        }
        
        // occupy spot (fill spot with player turn int and put symbol)
        if(playerSet.currentPlayer == 1){
            //change button text
            clickedButton.setText("O");
            //update gameboard arry at boardIndex
            gameBoard[boardIndex] = 1;
            setCurrentPlayer(2);
            
        }
        else if(playerSet.currentPlayer == 2){
        //change button text
            clickedButton.setText("X");
            gameBoard[boardIndex] = 2;
            setCurrentPlayer(1);
            
        //update gameboard arry at boardIndex       
        }
        
        // Check if the player won
        int playerWon = gameWon();
        if (playerWon != 0){
            displayWinnerDialog(playerWon);
            resetBoard();
        }
        else if(fullBoardCheck() == true){
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Winner Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Tie no winner!");       
            alert.showAndWait();
            resetBoard();
            
        }   
        
    }
    
    public void resetBoard(){
        for(int i = 0; i< gameBoard.length; i++){
            gameBoard[i] = 0;
        }
        Butt0.setText(" ");
        Butt1.setText(" ");
        Butt2.setText(" ");
        Butt3.setText(" ");
        Butt4.setText(" ");
        Butt5.setText(" ");
        Butt6.setText(" ");
        Butt7.setText(" ");
        Butt8.setText(" ");
    }
    
    public boolean fullBoardCheck(){
        for(int i = 0; i < gameBoard.length; i++){
            if(gameBoard[i] == 0){
                return false;
            }
        }
        return true;
    } 
    
    public void displayWinnerDialog(int player){
        if (player == 1){
            p1Wins++;
        }
        else if (player == 2){
            p2Wins++;
        }
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Winner Dialog");
        alert.setHeaderText(null);
        alert.setContentText(p1Label.getText() + ": " + p1Wins + " " + p2Label.getText() + ": " + p2Wins);       
        alert.showAndWait();
        
    }
    
    
    public int gameWon(){
        for (int i=1; i<=2; i++){
            boolean horizontals = checkHorizontals(i);
            if (horizontals){
                //player i won        
                return i;          
            }
            
            boolean verticals = checkVerticals(i);
            if (verticals) {
                //player i won            
                return i;              
            }
            
            boolean diagonals = checkDiagonals(i);
            if (diagonals) {
                //player i won            
                return i;
            }        
        }
        
        return 0;
    }
    
    public boolean checkHorizontals(int player){
        // Check top row horizontally
        if (gameBoard[0] == player && gameBoard[1] == player && gameBoard[2] == player){
            return true;
        }
        
        if (gameBoard[3] == player && gameBoard[4] == player && gameBoard[5] == player){
            return true;
        }
        
        if (gameBoard[6] == player && gameBoard[7] == player && gameBoard[8] == player) {
            return true;
        }
        
        return false;
    }
    
    public boolean checkVerticals(int player){
        if(gameBoard[0] == player && gameBoard[3] == player && gameBoard[6] == player){
            return true;
        }
        if(gameBoard[1] == player && gameBoard[4] == player && gameBoard[7] == player){
            return true;
        }
        if(gameBoard[2] == player && gameBoard[5] == player && gameBoard[8] == player){
           return true; 
        }
        return false;
    }
    
    public boolean checkDiagonals(int player){
        if(gameBoard[0] == player && gameBoard[4] == player && gameBoard[8] == player){
            return true;
        }
        if(gameBoard[6] == player && gameBoard[4] == player && gameBoard[2] == player){
            return true;
        }
        return false;
    }
    
    public int retrieveBoardIndex(String buttonId){
        // Get the last character of string
        String idValue = buttonId.substring(buttonId.length()-1);
        int intIdValue = Integer.parseInt(idValue);
        
        return intIdValue;
        // Parse the character as an integer and return
    }
    
    public void setCurrentPlayer(int player){
        playerSet.currentPlayer = player;
        
        if (player == 1){
            p1Label.setTextFill(Color.RED);
            p2Label.setTextFill(Color.BLACK);
        }
        else if (player == 2){
            p2Label.setTextFill(Color.RED);
            p1Label.setTextFill(Color.BLACK);
        }
    }
    
    @FXML
    public void startClicked(MouseEvent e){
        // Disable the Start Button
        startButton.setDisable(true);
        
        // Enable the game board to be clicked (Set a flag that we check when buttons are clicked)
        gameStarted = true;
        
        // Determine which player goes first
        int startingPlayer = (Math.random() <= 0.5) ? 1 : 2;
        setCurrentPlayer(startingPlayer);
    }
}