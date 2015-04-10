package project.domain.strategy.player;

import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;
import project.domain.strategy.PlayerPlayStyle;

public class MimicDealerPlaystyle implements PlayerPlayStyle {

	@Override
	public String getName() {
		return "Mimic dealer";
	}

	@Override
	public Action play(Player player, Dealer dealer) {
		int playerValue = player.getScore();
		if(playerValue < 17) {
			return Action.HIT;
		}
		return Action.STAY;
	}
}
