/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onderzoeksopdracht;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import domein.*;
import java.util.ArrayList;
import java.util.Arrays;
import domein.Card;

/**
 *
 * @author Ben
 */
public class OnderzoeksOpdracht extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Player[] player = { new Player("Ben"), new Player("Michiel")};
        BlackjackGame bjg = new BlackjackGame(new Dealer(1, new DealerPlayStyle()),player , new Rules());
        bjg.Play();
      
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
