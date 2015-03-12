package project.gui;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.stage.Stage;
import project.domain.BlackjackGame;
import project.domain.DealerPlayStyle;
import project.domain.MimicDealerPlaystyle;
import project.domain.Rules;
import project.domain.ThorpsPlayStyle;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		ArrayList<Player> player = new ArrayList<>();
                player.add(new Player("Ben", new ThorpsPlayStyle()));
                player.add(new Player("Michiel", new ThorpsPlayStyle()));
                player.add(new Player("Siel", new ThorpsPlayStyle()));
                player.add(new Player("Maxim", new ThorpsPlayStyle()));
		BlackjackGame bjg = new BlackjackGame(new Dealer(8, new DealerPlayStyle()), player, new Rules());
		bjg.play(10);
		System.exit(0);
	}
}
