/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.domain;

import java.util.ArrayList;

/**

 @author Ben
 */
public interface PlayStyle {

	public ActionEnum play(ArrayList<Card> dealerHand, ArrayList<Card> playerHand);
}
