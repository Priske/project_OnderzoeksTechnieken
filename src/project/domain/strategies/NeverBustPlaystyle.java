/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.strategies;

import project.domain.Action;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class NeverBustPlaystyle implements PlayStyle {

	@Override
	public Action play(Player player, Dealer dealer) {
		if(player.getValue() > 10) {
			return Action.STAY;
		}
		return Action.HIT;
	}
}
