package project.gui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import project.domain.*;
import project.domain.players.Dealer;
import project.domain.players.Player;
import project.domain.strategies.DealerPlayStyle;
import project.domain.strategies.MimicDealerPlaystyle;
import project.domain.strategies.ThorpsPlayStyle;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		ArrayList<Player> players = new ArrayList<>();
		players.add(new Player("Ben", new MimicDealerPlaystyle()));
		players.add(new Player("Michiel", new MimicDealerPlaystyle()));
		players.add(new Player("Siel", new ThorpsPlayStyle()));
		players.add(new Player("Maxim", new ThorpsPlayStyle()));
//		Player p = new Player("TestPlayer", null, Arrays.asList(new Card(CardSuit.CLUBS, CardFace.ACE), new Card(CardSuit.DIAMONDS, CardFace.ACE), new Card(CardSuit.HEARTS, CardFace.ACE), new Card(CardSuit.CLUBS, CardFace.FOUR)));
//		System.out.println(p.getValue());
		BlackjackGame game = new BlackjackGame();
		game.setDealer(new Dealer(game.getIntegerProperty("rules.number_decks"), new DealerPlayStyle()));
		game.setPlayers(players);
		game.play(game.getIntegerProperty("rules.number_games_played"));
		System.exit(0);
	}
}
