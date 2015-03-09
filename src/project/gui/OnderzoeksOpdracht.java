package project.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import project.domain.BlackjackGame;
import project.domain.DealerPlayStyle;
import project.domain.Rules;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Player[] player = {new Player("Ben"), new Player("Michiel")};
		BlackjackGame bjg = new BlackjackGame(new Dealer(1, new DealerPlayStyle()), player, new Rules());
		bjg.play();
		System.exit(0);
	}
}
