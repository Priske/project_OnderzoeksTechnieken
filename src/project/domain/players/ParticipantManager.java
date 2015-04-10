package project.domain.players;

import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.exceptions.PlayerNotFoundException;
import project.domain.strategy.DealerPlayStyle;
import project.domain.strategy.PlayerPlayStyle;

public class ParticipantManager {

	private final Dealer dealer;
	private final ObservableList<Player> players = FXCollections.observableArrayList();

	public ParticipantManager() {
		this.dealer = new Dealer();
	}

	public void addPlayer(Player player) {
		if(player == null) {
			throw new IllegalArgumentException();
		}
		this.players.add(player);
	}

	public Dealer getDealer() {
		return this.dealer;
	}

	public void setDealerStrategy(DealerPlayStyle strategy) {
		this.dealer.setStrategy(strategy);
	}

	public ObservableList<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<Player> players) {
		this.players.clear();
		this.players.addAll(players);
	}

	public void setPlayerStrategy(int id, PlayerPlayStyle strategy) {
		Optional<Player> opt = this.players.stream().filter(p -> p.equalsId(id)).findFirst();
		opt.orElseThrow(() -> new PlayerNotFoundException("Player for id '" + id + "' not found."));
		opt.get().setStrategy(strategy);
	}
}
