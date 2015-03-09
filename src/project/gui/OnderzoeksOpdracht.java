/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.gui;

import domein.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**

 @author Ben
 */
public class OnderzoeksOpdracht extends Application {

	@Override
	public void start(Stage primaryStage) {
		Player[] player = {new Player("Ben"), new Player("Michiel")};
		BlackjackGame bjg = new BlackjackGame(new Dealer(1, new DealerPlayStyle()), player, new Rules());
		bjg.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
