package project.domain.strategy.dealer;

import java.util.List;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

public abstract class DealerPlayStyle {

	private final String name;

	public DealerPlayStyle(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public abstract Action play(Dealer dealer, List<Player> players);

}
