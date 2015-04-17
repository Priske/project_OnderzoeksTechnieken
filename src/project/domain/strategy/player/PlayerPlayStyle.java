package project.domain.strategy.player;

import project.domain.Action;
import project.domain.Bet;
import project.domain.players.Dealer;
import project.domain.players.Player;

public abstract class PlayerPlayStyle {

	private final String name;

	public PlayerPlayStyle(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public abstract Action play(Player player, Dealer dealer, Bet bet);

	@Override
	public String toString() {
		return this.name;
	}
}
