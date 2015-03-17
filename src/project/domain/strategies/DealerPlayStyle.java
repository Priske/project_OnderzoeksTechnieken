/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.strategies;

import java.util.ArrayList;
import project.domain.ActionEnum;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class DealerPlayStyle {

	public ActionEnum play(Dealer dealer, ArrayList<Player> players) {
		ArrayList<Player> clean = new ArrayList<>();
		for (Player player : players) {
			if(player.getValue() > 21) {

			} else {
				clean.add(player);
			}
		}
		if(clean.size() == 1) {
			Player player = clean.get(0);
			if(player.getValue() > dealer.getValue()) {
				return ActionEnum.HIT;
			} else {
				return ActionEnum.STAY;
			}
		} else {
			if(dealer.getValue() <= 16) {
				return ActionEnum.HIT;
			} else {
				return ActionEnum.STAY;
			}
		}
	}
}
