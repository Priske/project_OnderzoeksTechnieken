package project.gui;

import domein.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Player[] player = {new Player("Ben"), new Player("Michiel")};
		BlackjackGame bjg = new BlackjackGame(new Dealer(1, new DealerPlayStyle()), player, new Rules());
		bjg.play();
	}
}
