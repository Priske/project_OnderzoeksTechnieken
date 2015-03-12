/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.players;

import java.util.ArrayList;
import java.util.stream.Collectors;
import project.domain.ActionEnum;
import project.domain.Card;
import project.domain.PlayStyle;

/**

 @author Ben
 */
public class Player {

	private final ArrayList<Card> hand = new ArrayList<>();
	private final String name;
        private PlayStyle ps;
        private int wins = 0;
        private int losses = 0;
        private int ties = 0;

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getTies() {
        return ties;
    }

    public void setTies(int ties) {
        this.ties = ties;
    }
        

	public Player(String name, PlayStyle ps) {
		this.name = name;
                this.ps=ps;
	}
        public ActionEnum play(ArrayList<Card> playerHand) {
		return this.ps.play(this.hand, playerHand);
	}
	/*public ActionEnum play() {
		int value = 0;
		value = this.hand.stream().mapToInt(c -> c.getValue()).sum();
		System.out.println(value);
                return 
	}*/

	public void getCard(Card card) {
		this.hand.add(card);
	}

	public ArrayList<Card> getHand() {
		return this.hand;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		String output = this.hand.stream().map(card -> card.toString()).collect(Collectors.joining(", "));
		return output;
	}
}
