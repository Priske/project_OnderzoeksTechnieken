/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**

 @author Ben
 */
public class BlackjackGame {

	private Dealer dealer;
	private Player[] players;
	private Rules rules;

	public BlackjackGame(Dealer dealer, Player[] players, Rules rules) {
		this.dealer = dealer;
		this.players = players;
		this.rules = rules;
	}

	public void play() {
		this.dealer.deal(this.players);
		System.out.println("TestDealDone");
		for (Player player : this.players) {
			System.out.println(player.getName() + ": " + player.toString());
		}
		System.out.println(this.dealer.toString());
		for (Player player : this.players) {
			player.play();
		}
		for (Player player : this.players) {
			System.out.println(player.getName() + ": " + player.toString());
		}
		if(this.dealer.play(this.players[1].getHand()) == ActionEnum.Hit) {
			this.dealer.deal(this.dealer);
		}
		System.out.println(this.dealer.toString());
	}
}
