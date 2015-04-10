package project.domain.strategy;

import java.util.List;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

public interface DealerPlayStyle {

	Action play(Dealer dealer, List<Player> players);

	public String getName();

}
