package project.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class DataFile implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Dealer dealer;
	private final List<Player> players = new ArrayList<>();

	public DataFile(Dealer dealer, List<Player> players) {
		this.dealer = dealer;
		this.players.addAll(players);
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	public List<Player> getPlayers() {
		return this.players;
	}
}
