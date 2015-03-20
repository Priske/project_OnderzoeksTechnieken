/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.strategies;

import java.util.List;
import java.util.stream.Collectors;
import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class DealerPlayStyle {

	public Action play(Dealer dealer, List<Player> players) {
		List<Player> list = players.stream().filter(p -> p.getValue() <= 21).collect(Collectors.toList());
		if(list.size() == 1) {
			Player player = list.get(0);
			if(player.getValue() > dealer.getValue()) {
				return Action.HIT;
			} else {
				return Action.STAY;
			}
		} else if(dealer.getValue() <= 16) {
			return Action.HIT;
		} else {
			return Action.STAY;
		}
//		List<Player> clean = new ArrayList<>();
//		for (Player player : players) {
//			if(player.getValue() <= 21) {
//				clean.add(player);
//			}
//		}
//		if(clean.size() == 1) {
//			Player player = clean.get(0);
//			if(player.getValue() > dealer.getValue()) {
//				return Action.HIT;
//			} else {
//				return Action.STAY;
//			}
//		} else {
//			if(dealer.getValue() <= 16) {
//				return Action.HIT;
//			} else {
//				return Action.STAY;
//			}
//		}
	}
}
