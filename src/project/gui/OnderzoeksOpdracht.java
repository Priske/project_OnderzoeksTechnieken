package project.gui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import project.domain.BlackjackGame;
import project.domain.Rules;
import project.domain.players.Dealer;
import project.domain.players.Player;
import project.domain.strategies.DealerPlayStyle;
import project.domain.strategies.ThorpsPlayStyle;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player("Ben", new ThorpsPlayStyle()));
		players.add(new Player("Michiel", new ThorpsPlayStyle()));
		players.add(new Player("Siel", new ThorpsPlayStyle()));
		players.add(new Player("Maxim", new ThorpsPlayStyle()));
		BlackjackGame game = new BlackjackGame(new Dealer(8, new DealerPlayStyle()), players, new Rules());
		game.play(1000);
		System.exit(0);
	}
}
