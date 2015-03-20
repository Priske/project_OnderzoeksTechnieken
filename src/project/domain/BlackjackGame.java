package project.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class BlackjackGame {

	private final Dealer dealer;
	private final ArrayList<Player> players;
	private final Rules rules;
	private int gamesPlayed;

	public BlackjackGame(Dealer dealer, ArrayList<Player> players, Rules rules) {
		this.dealer = dealer;
		this.players = players;
		this.rules = rules;
	}

	public void play(int times) {
		long start = System.currentTimeMillis();
		do {
			this.gamesPlayed++;
//			System.out.println("*** Game " + (this.gamesPlayed + 1) + " started ***");
			List<Player> tempPlayers = new ArrayList<>(this.players);
			this.dealer.deal(this.players);
//			System.out.println("Dealer top card:\n\t" + this.dealer.showTopCard());
			do {
				Iterator<Player> iter = tempPlayers.iterator();
				while (iter.hasNext()) {
					Player player = iter.next();
					if(player.play(this.dealer) == Action.HIT) {
						this.dealer.deal(player);
					} else {
						iter.remove();
					}
				}
			} while (!tempPlayers.isEmpty());
//			ArrayList<Player> donePlaying = new ArrayList<>();
//			do {
//				this.players.stream().forEach(player -> {
//					if(player.play(this.dealer) == Action.HIT) {
//						this.dealer.deal(player);
////						System.out.println(player.getName() + ": gets a card");
//					} else {
//						donePlaying.add(player);
//						player.done();
////						System.out.println(player.getName() + ": stays");
//					}
//				});
//			} while (this.players.size() > donePlaying.size());
//			if(this.players.stream().filter(p -> !p.isDone()).count() > 0) {
//				System.out.println("MOTHERFUCKING FACKING PLAYERS ARE NOT DONE PLAYING YET, YOU EVIL BASTARD");
//			}
			boolean stop = false;
			do {
				if(this.dealer.play(this.players) == Action.HIT) {
					this.dealer.takeCard();
					// System.out.println("Dealer gets a card");
				} else {
					stop = true;
					// System.out.println("Dealer Stays");
				}
			} while (!stop);
//			this.printPlayerHands();
			this.finishRound();
//			this.printGameScore();
//			System.out.println("*** Game " + (this.gamesPlayed) + " ended ***\n\n\n");
		} while (times > this.gamesPlayed);
		System.out.println("Round took: " + (System.currentTimeMillis() - start) + "ms");
		this.printGameScore();
	}

	private void finishRound() {
		this.checkWinner(this.dealer, this.players);
		this.dealer.collectCards(this.players);
		this.players.forEach(p -> p.emptyHand());
	}

	private void checkWinner(Dealer dealer, ArrayList<Player> players) {
		int dealerValue = dealer.getValue();
		players.stream().forEach(player -> {
			int playerValue = player.getValue();
			if(playerValue > 21 || playerValue <= dealerValue && dealerValue <= 21) {
				if(playerValue > 21) {
					player.burned();
				}
				dealer.won();
			} else {
				if(dealerValue > 21) {
					dealer.burned();
				}
				player.won();
			}
		});
	}

	private double getPercentage(double value) {
		return (value / this.gamesPlayed) * 100;
	}

	private void printGameScore() {
		System.out.println("Game score: ");
		System.out.println("\tGames played: " + this.gamesPlayed);
		this.players.stream().forEach(player -> System.out.println("\t" + player.getName() + ":\n\t\t" + "Wins: " + player.getWins() + " -> " + this.getPercentage(player.getWins()) + "%"));
		System.out.println("\tDealer: \n\t\tWins: " + this.dealer.getWins() + " -> " + this.getPercentage(this.dealer.getWins()) + "%");
	}

	private void printPlayerHands() {
		System.out.println("Hands:");
		this.players.stream().forEach(player -> System.out.println("\t" + player.getName() + ":\n\t\t" + player.toString() + "\n\t\tValue: " + player.getValue()));
		System.out.println("\tDealer:\n\t\t" + this.dealer + "\n\t\tValue: " + this.dealer.getValue());
	}
}
