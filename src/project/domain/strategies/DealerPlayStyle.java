package project.domain.strategies;

import java.util.List;
import java.util.stream.Collectors;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class DealerPlayStyle {

	public Action play(Dealer dealer, List<Player> players) {
		List<Player> filteredList = players.stream().filter(player -> player.getScore() <= 21).collect(Collectors.toList());
		if(filteredList.size() == 1) {
			Player player = filteredList.get(0);
			if(player.getScore() > dealer.getScore()) {
				return Action.HIT;
			}
			return Action.STAY;
		} else if(dealer.getScore() <= 16) {
			return Action.HIT;
		}
		return Action.STAY;
	}
}
