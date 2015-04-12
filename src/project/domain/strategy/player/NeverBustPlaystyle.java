package project.domain.strategy.player;

import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class NeverBustPlaystyle extends PlayerStrategyWrapper {

	public NeverBustPlaystyle() {
		super("Never bust");
	}

	@Override
	public Action play(Player player, Dealer dealer) {
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		if(player == null) {
			throw new IllegalArgumentException("Player cannot be null.");
		}
		if(player.getScore() > 10) {
			return Action.STAY;
		}
		return Action.HIT;
	}
}
