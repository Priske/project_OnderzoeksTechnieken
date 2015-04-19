package project.domain.strategy.player;

import java.io.Serializable;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

public abstract class PlayerPlayStyle implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String name;

	public PlayerPlayStyle(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public abstract Action play(Player player, Dealer dealer);

	@Override
	public String toString() {
		return this.name;
	}
}
