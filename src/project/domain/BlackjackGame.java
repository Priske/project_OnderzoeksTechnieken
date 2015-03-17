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
			ArrayList<Player> donePlaying = new ArrayList<>();
			this.dealer.deal(this.players);
			//System.out.println("TestDealDone");
			for (Player player : this.players) {
				System.out.println(player.getName() + ": " + player.toString());
			}
			System.out.println("Dealer: " + this.dealer.toString());
			do {
				for (Player player : this.players) {
					if(player.play(this.dealer) == Action.HIT) {
						this.dealer.deal(player);
						//System.out.println(player.getName()+": gets a card");
					} else {
						//System.out.println(player.getName()+": stays");
						donePlaying.add(player);
					}
				}

			} while (this.players.size() > donePlaying.size());
			int x = 0;
			do {
				if(this.dealer.play(this.players) == Action.HIT) {
					this.dealer.getCard();
					// System.out.println("Dealer gets a card");
				} else {
					x = 1;
					// System.out.println("Dealer Stays");
				}

			} while (x == 0);
			for (Player player : this.players) {
				System.out.println(player.getName() + ": " + player.toString());
			}
			/*
			 if(this.dealer.play(this.players[1].getHand()) == ActionEnum.HIT) {
			 this.dealer.deal(this.dealer);
			 }
			 */
			System.out.println("Dealer: " + this.dealer);
			System.out.println("Dealer: " + this.dealer.getValue());
			for (Player player : this.players) {
				System.out.println(player.getName() + " : " + player.getValue());
			}
			finishRound();
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
			System.out.println("Name: " + player.getName() + "\n\t" + "Wins: " + player.getWins());
		}
		System.out.println("Dealer: " + this.dealer.getWins());
	}

	private void checkWinner(Dealer dealer, ArrayList<Player> players) {
		int dealerValue = dealer.getValue();
		players.forEach(player -> {
			int playerValue = player.getValue();
			if(dealerValue < 22 && dealerValue >= playerValue) {
				dealer.won();
			} else {
				if(playerValue > 21) {
					dealer.won();
				} else {
					player.won();
				}
			}
		});
	}
}
