/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain.strategies;

import java.util.ArrayList;
import project.domain.ActionEnum;
import project.domain.Card;

/**

 @author Ben
 */
public interface PlayStyle {

	public ActionEnum play(ArrayList<Card> playerHand, ArrayList<Card> dealerHand);
}
