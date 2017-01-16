/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ledclient;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author adam
 */
public class LedClient extends Application {

    private static LedClient instance;

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        MainWindow c= new MainWindow(primaryStage);
        
        primaryStage.setTitle("Main Controller ");
        c.load();
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                killAll();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
        //tetrad.Tetrad.main2(args);
    }

    public void spawnNewWindow() {
        
    }

    public void killAll() {
        try {
            Platform.exit();
        } catch (Exception ex) {
            throw new UnsupportedOperationException();
        }
    }

}
