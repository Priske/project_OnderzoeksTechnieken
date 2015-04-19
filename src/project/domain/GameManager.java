package project.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ObservableValue;
import project.domain.card.Card;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class GameManager {

	private final SimpleLongProperty batchTime = new SimpleLongProperty();
	private final BlackjackGame controller;
	private final Dealer dealer;
	private final SimpleIntegerProperty gamesPlayed = new SimpleIntegerProperty();
	private final SimpleIntegerProperty gamesToPlay = new SimpleIntegerProperty();
	private final List<Player> players;
	private boolean dataWasLoaded = false;

	public GameManager(BlackjackGame controller) {
		this.controller = controller;
		this.dealer = this.controller.getDealer();
		this.players = this.controller.getPlayers();
		this.gamesToPlay.set(this.controller.getIntegerProperty("rules.number_games_to_play"));
		this.gamesToPlay.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			this.controller.setProperty("rules.number_games_to_play", (int)newValue);
		});
		this.gamesPlayed.addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
			if((int)newValue == 100) {
				this.shuffleGame();
			}
		});
	}

	public ReadOnlyLongProperty batchTimeProperty() {
		return this.batchTime;
	}

	public void countCard(Card card) {
		this.players.forEach(p -> p.countCard(card));
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

	public void saveDataFile(File file) {
		if(file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(new DataFile(this.dealer, this.players));
		} catch (IOException ex) {
			Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				oos.close();
			} catch (IOException ex) {
				Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void loadDataFile(File file) {
		if(file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			this.setData((DataFile)ois.readObject());
		} catch (IOException | ClassNotFoundException ex) {
			Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			try {
				ois.close();
			} catch (IOException ex) {
				Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		this.dataWasLoaded = true;
	}

	public void play() {
		if(this.dataWasLoaded) {
			this.dataWasLoaded = false;
			this.controller.reloadPlayers();
		}
		long start = System.currentTimeMillis();
		this.resetGame();
		int i = 0;
		do {
			this.gamesPlayed.set(this.gamesPlayed.get() + 1);
			this.playRound();
			if(i % 10 == 0) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException ex) {
					Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
				}

			}
			i++;
		} while (this.gamesToPlay.get() > this.gamesPlayed.get());
		this.batchTime.set(System.currentTimeMillis() - start);
	}

	private void checkWinner() {
		int dealerValue = this.dealer.getScore();
		this.players.forEach(player -> {
			int playerValue = player.getScore();
			if(playerValue > 21 || playerValue <= dealerValue && dealerValue <= 21) {
				if(playerValue == 21 && dealerValue == 21) {
					player.draw();
					this.dealer.draw();
					return;
				}
				if(dealerValue == 21 && this.dealer.getHandSize() == 2) {
					this.dealer.blackJack();
				} else {
					this.dealer.won();
				}
				if(playerValue > 21) {
					player.burned();
				} else {
					player.loss();
				}
			} else {
				if(playerValue == 21 && player.getHandSize() == 2) {
					player.blackJack();
				} else {
					player.won();
				}
				if(dealerValue > 21) {
					this.dealer.burned();
				} else {
					this.dealer.loss();
				}
			}
		});
	}

	private void finishGameRound() {
		this.checkWinner();
		this.dealer.collectCards(this.players);
	}

	private void setData(DataFile dataFile) {
		this.players.clear();
		this.players.addAll(dataFile.getPlayers());
		this.dealer.set(dataFile.getDealer());
	}

	private void initiateGameRound() {
		this.players.forEach(p -> p.makeBet());
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
		this.playPlayers();
		this.playDealer();
		this.finishGameRound();
	}

	private void resetGame() {
		this.gamesPlayed.set(0);
		this.dealer.reset();
		this.players.stream().forEach(p -> p.reset());
	}

	private void resetPlayers() {
		this.players.forEach(p -> p.resetCardCounter());
	}

	private void shuffleGame() {
		this.dealer.shuffleCards();
		this.resetPlayers();
	}
}
