package project.domain.strategy.player;

import java.util.List;
import project.domain.Action;
import project.domain.card.Card;
import project.domain.card.CardFace;
import project.domain.exceptions.NonAceCardNotFoundException;
import project.domain.players.Dealer;
import project.domain.players.Player;
import project.domain.strategy.PlayerPlayStyle;

public class ThorpsPlayStyle implements PlayerPlayStyle {

	@Override
	public String getName() {
		return "Thorps";
	}

	@Override
	public Action play(Player player, Dealer dealer) {
		if(dealer == null) {
			throw new IllegalArgumentException("Dealer cannnot be null.");
		}
		if(player == null) {
			throw new IllegalArgumentException("Player cannot be null.");
		}
		if(player.countAces() == 1 && player.getScore() < 16) {
			return this.acePlay(dealer.showTopCard(), this.getNonAceCard(player.getHand()));
		} else if(player.countAces() == 2) {
			if(player.getScore() < 17) {
				return Action.HIT;
			}
			return Action.STAY;
		} else {
			switch (dealer.showTopCard().getFace()) {
				case ACE:
					if(player.getScore() < 17) {
						return Action.HIT;
					}
					return Action.STAY;
				case DEUCE:
				case THREE:
					if(player.getScore() < 13) {
						return Action.HIT;
					}
					return Action.STAY;
				case FOUR:
				case FIVE:
				case SIX:
					if(player.getScore() < 12) {
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
					if(player.getScore() < 17) {
						return Action.HIT;
					}
					return Action.STAY;
				default:
					return null;
			}
		}
	}

	private Action acePlay(Card dealerCard, Card playerCard) {
		if(dealerCard == null) {
			throw new IllegalArgumentException("Dealer card cannot be null.");
		}
		if(playerCard == null) {
			throw new IllegalArgumentException("Player card cannot be null.");
		}
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
		if(hand == null) {
			throw new IllegalArgumentException("Card list cannot be null.");
		}
		return hand.stream().filter(c -> c.getFace() != CardFace.ACE).findFirst().orElseThrow(() -> new NonAceCardNotFoundException());
//		return hand.stream().filter(c -> c.getFace() != CardFace.ACE).findFirst().orElse(null);
	}
}
