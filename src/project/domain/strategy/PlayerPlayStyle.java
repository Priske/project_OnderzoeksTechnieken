package project.domain.strategy;

import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

public interface PlayerPlayStyle {

	public Action play(Player player, Dealer dealer);

	public String getName();
}
