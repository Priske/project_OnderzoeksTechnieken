/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.strategies;

import project.domain.ActionEnum;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class MimicDealerPlaystyle implements PlayStyle {

	@Override
	public ActionEnum play(Player player, Dealer dealer) {
		int playerValue = player.getValue();
		if(playerValue < 17) {
			return ActionEnum.HIT;
		} else {
			return ActionEnum.STAY;
		}
	}
}
