package project.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableValue;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class GameManager {

	private final SimpleLongProperty batchTime = new SimpleLongProperty();
	private final BlackjackGame controller;
	private final Dealer dealer;
	private final SimpleIntegerProperty gamesPlayed = new SimpleIntegerProperty();
	private final SimpleIntegerProperty gamesToPlay = new SimpleIntegerProperty();
	private final List<Player> players;

	public GameManager(BlackjackGame controller) {
		this.controller = controller;
		this.dealer = this.controller.getDealer();
		this.players = this.controller.getPlayers();
		this.gamesToPlay.set(this.controller.getIntegerProperty("rules.number_games_to_play"));
		this.gamesToPlay.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			this.controller.setProperty("rules.number_games_to_play", (int)newValue);
		});
	}

	public ReadOnlyLongProperty batchTimeProperty() {
		return this.batchTime;
	}

	public ReadOnlyIntegerProperty gamesPlayedProperty() {
		return this.gamesPlayed;
	}

	public ReadOnlyIntegerProperty gamesToPlayProperty() {
		return this.gamesToPlay;
	}

	public int getGamesPlayed() {
		return this.gamesPlayed.get();
	}

	public int getGamesToPlay() {
		return this.gamesToPlay.get();
	}

	public void setNumberGamesToPlay(int gamesToPlay) {
		if(gamesToPlay < 0) {
			throw new IllegalArgumentException("Games to play cannot be smaller then 1.");
		}
		this.gamesToPlay.set(gamesToPlay);
	}

	public void play() {
		long start = System.currentTimeMillis();
		this.resetGame();
		do {
			this.gamesPlayed.set(this.gamesPlayed.get() + 1);
			this.playRound();
		} while (this.gamesToPlay.get() > this.gamesPlayed.get());
		this.batchTime.set(System.currentTimeMillis() - start);
	}

	private void checkWinner() {
		int dealerValue = this.dealer.getScore();
		this.players.stream().forEach(player -> {
			int playerValue = player.getScore();
			if(playerValue > 21 || playerValue <= dealerValue && dealerValue <= 21) {
				if(playerValue > 21) {
					player.burned();
				}
				if(playerValue == 21 && dealerValue == 21) {
					player.draw();
					this.dealer.draw();
					return;
				}
				if(dealerValue == 21) {
					this.dealer.blackJack();
				}
				this.dealer.won();
			} else {
				if(dealerValue > 21) {
					this.dealer.burned();
				}
				if(playerValue == 21) {
					player.blackJack();
				}
				player.won();
			}
		});
	}

	private void finishGameRound() {
		this.checkWinner();
		this.dealer.collectCards(this.players);
	}

	private void initiateGameRound() {
		IntStream.range(0, 2).forEach(i -> {
			this.players.forEach(p -> p.addCard(this.dealer.deal()));
			this.dealer.takeCard();
		});
	}

	private void playDealer() {
		while (true) {
			if(this.dealer.play(this.players) != Action.HIT) {
				break;
			}
		}
	}

	private void playPlayers() {
		List<Player> tempPlayers = new ArrayList<>(this.players);
		do {
			Iterator<Player> iter = tempPlayers.iterator();
			while (iter.hasNext()) {
				Player player = iter.next();
				if(player.play(this.dealer) != Action.HIT) {
					iter.remove();
				}
			}
		} while (!tempPlayers.isEmpty());
	}

	private void playRound() {
		this.initiateGameRound();
		this.playDealer();
		this.playPlayers();
		this.finishGameRound();
	}

	private void resetGame() {
		this.gamesPlayed.set(0);
		this.dealer.reset();
		this.players.stream().forEach(p -> p.reset());
	}
}
