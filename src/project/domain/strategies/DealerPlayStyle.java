package project.domain.strategies;

import java.util.ArrayList;
import java.util.List;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class DealerPlayStyle {

	public Action play(Dealer dealer, List<Player> players) {
		List<Player> filteredList = new ArrayList<>();
		players.stream().filter(player -> player.getValue() <= 21).forEach(player -> filteredList.add(player));
		if(filteredList.size() == 1) {
			Player player = filteredList.get(0);
			if(player.getValue() > dealer.getValue()) {
				return Action.HIT;
			} else {
				return Action.STAY;
			}
		} else if(dealer.getValue() <= 16) {
			return Action.HIT;
		} else {
			return Action.STAY;
		}
	}
}
