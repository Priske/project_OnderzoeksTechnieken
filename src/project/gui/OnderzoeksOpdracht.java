package project.gui;

import java.util.ArrayList;
import java.util.List;
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

	private BlackjackGame game;

	@Override
	public void start(Stage stage) {
		this.game = new BlackjackGame();
		this.game.setDealer(this.createDealer());
		this.game.setPlayers(this.createPlayers());
//		this.game.play(this.game.getIntegerProperty("rules.number_games_played"));
		this.game.play(10);
		System.exit(0);
	}

	private Dealer createDealer() {
		return new Dealer(this.game.getIntegerProperty("rules.number_decks"), new DealerPlayStyle());
	}

	private List<Player> createPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player("Ben", new MimicDealerPlaystyle()));
		players.add(new Player("Michiel", new MimicDealerPlaystyle()));
		players.add(new Player("Siel", new ThorpsPlayStyle()));
		players.add(new Player("Maxim", new ThorpsPlayStyle()));
		return players;
	}
}
