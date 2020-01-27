/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Chris Badolato
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button cancelButton;
    @FXML
    private Button startButton;
    @FXML
    private TextField p1TextField;
    @FXML
    private TextField p2TextField;
    

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }

    @FXML
    private void cancelButtonClicked(MouseEvent event){ 
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    
    

    @FXML
    private void startButtonClicked(MouseEvent event) throws IOException {
        
        String p1Text = p1TextField.getText();
        String p2Text = p2TextField.getText();
        PlayerSet newPlayers = new PlayerSet(p1Text, p2Text);
        
        // Create FXML Loader class to load Board.fxml resource
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Board.fxml"));
        loader.load();
        
        // Retrieve root of loader to get reference to the Scene and Stage
        Parent boardParent = loader.getRoot();
        Scene boardScene = new Scene(boardParent);
        Stage boardStage = (Stage)((Node) event.getSource()).getScene().getWindow();
        boardStage.setScene(boardScene);
        boardStage.show();
                
        // Retrieve controller to get reference to class used to create controller
        BoardController boardController = loader.getController();
        
        // Set text fields using information entered for player 1 and player 2
        boardController.setLabels(p1Text, p2Text);
        
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
                 
    }                 

    private Object getScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
