package project.domain.strategies;

import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class NeverBustPlaystyle implements PlayStyle {

	@Override
	public Action play(Player player, Dealer dealer) {
		if(player.getScore() > 10) {
			return Action.STAY;
		}
		return Action.HIT;
	}
}
