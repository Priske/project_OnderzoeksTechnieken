package project.domain.strategy.dealer;

import java.util.List;
import java.util.stream.Collectors;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class DealerPlayStyleDefault extends DealerPlayStyle {

	public DealerPlayStyleDefault() {
		super("Default");
	}

	@Override
	public Action play(Dealer dealer, List<Player> players) {
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		if(players == null) {
			throw new IllegalArgumentException("Player list cannot be null.");
		}
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
