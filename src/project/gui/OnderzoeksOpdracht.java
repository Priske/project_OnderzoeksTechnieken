package project.gui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import project.domain.BlackjackGame;
import project.domain.DealerPlayStyle;
import project.domain.MimicDealerPlaystyle;
import project.domain.Rules;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		ArrayList<Player> player = new ArrayList<>();
                player.add(new Player("Ben", new MimicDealerPlaystyle()));
                player.add(new Player("Michiel", new MimicDealerPlaystyle()));
		BlackjackGame bjg = new BlackjackGame(new Dealer(1, new DealerPlayStyle()), player, new Rules());
		bjg.play(10);
		System.exit(0);
	}
}
