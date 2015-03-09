/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;

/**

 @author Ben
 */
public interface PlayStyle {

	public ActionEnum play(ArrayList<Card> DealerHand, ArrayList<Card> PlayerHand);
}
