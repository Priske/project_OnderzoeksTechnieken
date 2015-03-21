package project.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;
import project.domain.players.Dealer;
import project.domain.players.Participant;
import project.domain.players.Player;

public class BlackjackGame {

	private final Dealer dealer;
	private int gamesPlayed;
	private final List<Player> players;
	private final Rules rules;

	public BlackjackGame(Dealer dealer, List<Player> players, Rules rules) {
		this.dealer = dealer;
		this.players = players;
		this.rules = rules;
	}

	public void play(int times) {
		long start = System.currentTimeMillis();
		do {
			this.gamesPlayed++;
//			System.out.println("*** Game " + (this.gamesPlayed + 1) + " started ***");
			this.initiateGameRound();
//			System.out.println("Dealer top card:\n\t" + this.dealer.showTopCard());
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
//			ArrayList<Player> donePlaying = new ArrayList<>();
//			do {
//				this.players.stream().forEach(player -> {
//					if(player.play(this.dealer) != Action.HIT) {
//						donePlaying.add(player);
//						player.done();
////						System.out.println(player.getName() + ": stays");
//					} else {
////						System.out.println(player.getName() + ": gets a card");
//					}
//				});
//			} while (this.players.size() > donePlaying.size());
//			if(this.players.stream().filter(p -> !p.isDone()).count() > 0) {
//				System.out.println("PLAYERS ARE NOT DONE PLAYING YET, YOU EVIL BASTARD");
//			}
			do {
				if(this.dealer.play(this.players) != Action.HIT) {
					break;
					// System.out.println("Dealer Stays");
				} else {
					// System.out.println("Dealer gets a card");
				}
			} while (true);
//			this.printPlayerHands();
			this.finishGameRound();
//			this.printGameScore();
//			System.out.println("*** Game " + (this.gamesPlayed) + " ended ***\n\n\n");
		} while (times > this.gamesPlayed);
		this.printGameSummary();
		System.out.println("Game batch took: " + (System.currentTimeMillis() - start) + "ms");
	}

	private void checkWinner(Dealer dealer, List<Player> players) {
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

	private void finishGameRound() {
		this.checkWinner(this.dealer, this.players);
		this.dealer.collectCards(this.players);
	}

	private double getWinPercentage(double value) {
		return (value / this.gamesPlayed) * 100;
	}

	private void initiateGameRound() {
		IntStream.range(0, 2).forEach(i -> {
			this.players.forEach(p -> p.addCard(this.dealer.deal()));
			this.dealer.takeCard();
		});
	}

	private void printGameSummary() {
		System.out.println("Game Summary: ");
		System.out.println("\tGames played: " + this.gamesPlayed);
		this.players.stream().forEach(player -> this.printPlayerScore(player));
		System.out.println("\t" + this.dealer.getName() + ": \n\t\tWins: " + this.dealer.getWins() + "/" + (this.gamesPlayed * 4) + " -> " + ((double)this.dealer.getWins() / (this.gamesPlayed * 4)) * 100 + "%");
	}

	private void printParticipantHand(Participant p) {
		System.out.println("\t" + p.getName() + ":\n\t\t" + p.toString() + "\n\t\tValue: " + p.getValue());
	}

	private void printPlayerHands() {
		System.out.println("Hands:");
		this.players.stream().forEach(player -> this.printParticipantHand(player));
		this.printParticipantHand(this.dealer);
	}

	private void printPlayerScore(Player p) {
		System.out.println("\t" + p.getName() + ":\n\t\t" + "Wins: " + p.getWins() + " -> " + this.getWinPercentage(p.getWins()) + "%");
	}
}
