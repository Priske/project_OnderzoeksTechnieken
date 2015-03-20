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
		List<Player> filteredList = players.stream().filter(p -> p.getValue() <= 21).collect(Collectors.toList());
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
//		List<Player> clean = new ArrayList<>();
//		for (Player player : players) {
//			if(player.getValue() <= 21) {
//				clean.add(player);
//			}
//		}
//		if(clean.size() == 1) {
//			Player player = clean.get(0);
//			if(player.getValue() > dealer.getValue()) {
//				return Action.HIT;
//			} else {
//				return Action.STAY;
//			}
//		} else {
//			if(dealer.getValue() <= 16) {
//				return Action.HIT;
//			} else {
//				return Action.STAY;
//			}
//		}
	}
}
