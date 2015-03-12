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

	public BlackjackGame(Dealer dealer, ArrayList<Player> players, Rules rules) {
		this.dealer = dealer;
		this.players = players;
		this.rules = rules;
	}

	public void play(int times) {
		int gamesPlayed = 0;
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
					if(player.play(dealer.getHand()) == ActionEnum.HIT) {
						this.dealer.deal(player);
						//System.out.println(player.getName()+": gets a card");
					} else {
						//System.out.println(player.getName()+": stays");
						donePlaying.add(player);
					}
				}

			} while (players.size() > donePlaying.size());
			int x = 0;
			do {
				if(dealer.play(players) == ActionEnum.HIT) {
					dealer.getCard();
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
			System.out.println("Dealer: " + this.dealer.toString());
			System.out.println("Dealer: " + getValue(this.dealer.getHand()));
			for (Player player : players) {
				System.out.println(player.getName() + " : " + getValue(player.getHand()));
			}
			finishRound();
			gamesPlayed++;

		} while (times > gamesPlayed);

	}

	private void finishRound() {
		this.checkWinner(this.dealer, this.players);
		this.dealer.retrieveCards(this.players);
		System.out.println("Score: ");
		for (Player player : this.players) {
			System.out.println("Name: " + player.getName() + "Score: " + "Wins: " + player.getWins());
		}
		System.out.println("Dealer: " + this.dealer.getWins());
	}

	private void checkWinner(Dealer dealer, ArrayList<Player> players) {
		for (Player player : players) {
			if((getValue(dealer.getHand()) < 22) && getValue(dealer.getHand()) >= getValue(player.getHand())) {
				dealer.win();
				player.loss();
			} else {
				if(getValue(player.getHand()) > 21) {
					dealer.win();
					player.loss();
				} else {
					player.win();
				}
			}
		}
	}

	public int getValue(ArrayList<Card> hand) {

		int aces = 0;
		int valueOutput = hand.stream().mapToInt(c -> c.getValue()).sum();
		for (Card c : hand) {
			if(c.getFace() == CardFace.ACE) {
				aces++;
			}
		}
		if(aces == 0) {
			return valueOutput;
		} else {
			if(valueOutput <= 11) {
				valueOutput += 9;
			} else {

			}
			return valueOutput;
		}
	}
}
