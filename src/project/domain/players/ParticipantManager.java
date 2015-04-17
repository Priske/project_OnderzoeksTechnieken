package project.domain.players;

import java.util.List;
import java.util.Optional;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.exceptions.PlayerNotFoundException;
import project.domain.strategy.dealer.DealerPlayStyle;
import project.domain.strategy.player.PlayerPlayStyle;

public class ParticipantManager {

	private final Dealer dealer;
	private final ObservableList<Player> players = FXCollections.observableArrayList();
	private final SimpleIntegerProperty numberOfPlayers = new SimpleIntegerProperty();

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

	public SimpleIntegerProperty numberPlayersProperty() {
		return this.numberOfPlayers;
	}

	public int getNumberPlayers() {
		return this.numberOfPlayers.get();
	}

	public void setNumberPlayers(int numberPlayers) {
		if(numberPlayers < 1) {
			throw new IllegalArgumentException("Number of players cannot be smaller then 1.");
		}
		this.numberOfPlayers.set(numberPlayers);
	}

	public void setDealerStrategy(DealerPlayStyle strategy) {
		this.dealer.setStrategy(strategy);
	}

	public int getNumberDecks() {
		return this.dealer.getNumberDecks();
	}

	public void setNumberDecks(int numberDecks) {
		this.dealer.setNumberDecks(numberDecks);
	}

	public ObservableList<Player> getPlayers() {
		return this.players;
	}

	public void setPlayers(List<Player> players) {
		if(players == null) {
			throw new IllegalArgumentException("Player list cannot be null.");
		}
		this.players.clear();
		this.players.addAll(players);
	}

	public void setPlayerStrategy(int id, PlayerPlayStyle strategy) {
		Optional<Player> opt = this.players.stream().filter(p -> p.equalsId(id)).findFirst();
		opt.orElseThrow(() -> new PlayerNotFoundException("Player for id '" + id + "' not found."));
		opt.get().setStrategy(strategy);
	}
}
