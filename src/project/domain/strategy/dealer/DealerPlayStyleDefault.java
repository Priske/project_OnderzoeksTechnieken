package project.domain.strategy.dealer;

import java.util.List;
import java.util.stream.Collectors;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;
import project.domain.strategy.DealerPlayStyle;

public class DealerPlayStyleDefault implements DealerPlayStyle {

	@Override
	public String getName() {
		return "Default";
	}

	@Override
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
