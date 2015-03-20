package project.domain.strategies;

import java.util.List;
import project.domain.Action;
import project.domain.Card;
import project.domain.CardFace;
import project.domain.players.Dealer;
import project.domain.players.Player;

/**

 @author Ben
 */
public class ThorpsPlayStyle implements PlayStyle {

	@Override
	public Action play(Player player, Dealer dealer) {
		if(player.countAces() == 1 && player.getValue() < 16) {
			return this.acePlay(dealer.showTopCard(), this.getNonAceCard(player.getHand()));
		} else if(player.countAces() == 2) {
			if(player.getValue() < 17) {
				return Action.HIT;
			}
			return Action.STAY;
		} else {
			switch (dealer.showTopCard().getFace()) {
				case ACE:
					if(player.getValue() < 17) {
						return Action.HIT;
					}
					return Action.STAY;
				case DEUCE:
//					if(player.getValue() < 13) {
//						return Action.HIT;
//					}
//					return Action.STAY;
				case THREE:
					if(player.getValue() < 13) {
						return Action.HIT;
					}
					return Action.STAY;
				case FOUR:
				case FIVE:
				case SIX:
					if(player.getValue() < 12) {
						return Action.HIT;
					}
					return Action.STAY;
				case SEVEN:
				case EIGHT:
				case NINE:
				case TEN:
				case JACK:
				case QUEEN:
				case KING:
					if(player.getValue() < 17) {
						return Action.HIT;
					}
					return Action.STAY;
				default:
					return null;
			}
		}
	}

	private Action acePlay(Card dealerCard, Card playerCard) {
		switch (playerCard.getFace()) {
			case DEUCE:
			case THREE:
			case FOUR:
			case FIVE:
			case SIX:
				return Action.HIT;
			case SEVEN:
				if(dealerCard.getValue() < 9) {
					return Action.STAY;
				}
				return Action.HIT;
			case EIGHT:
			case NINE:
				return Action.STAY;
			default:
				return null;
		}
	}

	private Card getNonAceCard(List<Card> hand) {
//		return hand.stream().filter(c -> c.getFace() != CardFace.ACE).findFirst().orElseThrow(new NoNonAceCardFoundException());
		return hand.stream().filter(c -> c.getFace() != CardFace.ACE).findFirst().orElse(null);
	}
}
