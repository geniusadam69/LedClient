/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledclient;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author adam
 */
public class MainWindow {
    private Stage mainStage;
    @FXML
    private Circle led1Status;
    @FXML
    private Circle led2Status;
    
    public MainWindow(Stage stage){
        mainStage = stage;
    }
    
    public void load(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        loader.setController(this);
        try {
            Pane root = loader.load();
            Scene scene = new Scene(root);
            mainStage.setScene(scene);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new UnsupportedOperationException(ex);
        }
    }
    
    
    @FXML
    private void led1Button(){
        led1Status.setFill(Color.LIMEGREEN);
    }
    @FXML
    private void led2Button(){
        led1Status.setFill(Color.RED);
    }
}
