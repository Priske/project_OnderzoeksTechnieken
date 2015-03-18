/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.ArrayList;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class BlackjackGame {

	private Dealer dealer;
	private ArrayList<Player> players;
	private Rules rules;
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
				for (Player player : this.players) {
					if(player.play(this.dealer) == Action.HIT) {
						this.dealer.deal(player);
//						System.out.println(player.getName() + ": gets a card");
					} else {
						donePlaying.add(player);
//						System.out.println(player.getName() + ": stays");
					}
				}

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
			System.out.println("Hands:");
			for (Player player : this.players) {
				System.out.println("\t" + player.getName() + ":\n\t\t" + player.toString() + "\n\t\tValue: " + player.getValue());
			}
			/*
			 if(this.dealer.play(this.players[1].getHand()) == ActionEnum.HIT) {
			 this.dealer.deal(this.dealer);
			 }
			 */
			System.out.println("\tDealer:\n\t\t" + this.dealer + "\n\t\tValue: " + this.dealer.getValue());
			this.finishRound();
			System.out.println("*** Game " + (this.gamesPlayed + 1) + " ended ***\n\n\n");
			this.gamesPlayed++;
		} while (times > this.gamesPlayed);
	}

	private void finishRound() {
		this.checkWinner(this.dealer, this.players);
		this.dealer.clearHand();
		this.dealer.resetDeck();
		this.players.forEach(p -> p.clearHand());
		System.out.println("Score: ");
		for (Player player : this.players) {
			System.out.println("\t" + player.getName() + ":\n\t\t" + "Wins: " + player.getWins());
		}
		System.out.println("\tDealer: \n\t\tWins: " + this.dealer.getWins());
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
			winningPlayer.won();
		} else {
			dealer.won();
		}
	}
}
