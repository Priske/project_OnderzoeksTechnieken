package project.domain.players;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ParticipantManager {

	private Dealer dealer;
	private final ObservableList<Player> players = FXCollections.observableArrayList();

	public ParticipantManager() {
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

	public void setDealer(Dealer dealer) {
		if(dealer == null) {
			throw new IllegalArgumentException();
		}
		this.dealer = dealer;
	}

	public ObservableList<Player> getPlayers() {
		return this.players;
	}
}
