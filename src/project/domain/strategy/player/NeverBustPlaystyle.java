package project.domain.strategy.player;

import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;
import project.domain.strategy.PlayerPlayStyle;

public class NeverBustPlaystyle implements PlayerPlayStyle {

	@Override
	public String getName() {
		return "Never bust";
	}

	@Override
	public Action play(Player player, Dealer dealer) {
		if(player.getScore() > 10) {
			return Action.STAY;
		}
		return Action.HIT;
	}
}
