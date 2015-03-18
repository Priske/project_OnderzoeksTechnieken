package project.domain;

import java.util.ArrayList;
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
		do {
			System.out.println("*** Game " + (this.gamesPlayed + 1) + " started ***");
			ArrayList<Player> donePlaying = new ArrayList<>();
			this.dealer.deal(this.players);
			System.out.println("Dealer top card:\n\t" + this.dealer.showTopCard());
			do {
				this.players.stream().forEach(player -> {
					if(player.play(this.dealer) == Action.HIT) {
						this.dealer.deal(player);
//						System.out.println(player.getName() + ": gets a card");
					} else {
						donePlaying.add(player);
//						System.out.println(player.getName() + ": stays");
					}
				});

			} while (this.players.size() > donePlaying.size());
			int x = 0;
			do {
				if(this.dealer.play(this.players) == Action.HIT) {
					this.dealer.takeCard();
					// System.out.println("Dealer gets a card");
				} else {
					x = 1;
					// System.out.println("Dealer Stays");
				}

			} while (x == 0);
			this.printPlayerHands();
			this.finishRound();
			this.printGameScore();
			System.out.println("*** Game " + (this.gamesPlayed + 1) + " ended ***\n\n\n");
			this.gamesPlayed++;
		} while (times > this.gamesPlayed);
	}

	private void finishRound() {
		this.checkWinner(this.dealer, this.players);
		this.dealer.clearHand();
		this.dealer.resetDeck();
		this.players.forEach(p -> p.clearHand());
	}

	private void checkWinner(Dealer dealer, ArrayList<Player> players) {
		Player winningPlayer = null;
		int highestValue = 0;
		for (Player player : players) {
			int playerValue = player.getValue();
			if(playerValue > highestValue && playerValue <= 21) {
				highestValue = playerValue;
				winningPlayer = player;
			}
		}
		int dealerValue = dealer.getValue();
		if(dealerValue > highestValue && dealerValue <= 21) {
			dealer.won();
		} else {
			winningPlayer.won();
		}
	}

	private void printGameScore() {
		System.out.println("Game score: ");
		this.players.stream().forEach(player -> System.out.println("\t" + player.getName() + ":\n\t\t" + "Wins: " + player.getWins()));
		System.out.println("\tDealer: \n\t\tWins: " + this.dealer.getWins());
	}

	private void printPlayerHands() {
		System.out.println("Hands:");
		this.players.stream().forEach(player -> System.out.println("\t" + player.getName() + ":\n\t\t" + player.toString() + "\n\t\tValue: " + player.getValue()));
		System.out.println("\tDealer:\n\t\t" + this.dealer + "\n\t\tValue: " + this.dealer.getValue());
	}
}
