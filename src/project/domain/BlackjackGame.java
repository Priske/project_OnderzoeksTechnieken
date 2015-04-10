package project.domain;

import be.mrtus.common.domain.SettingsManager;
import be.mrtus.common.domain.SettingsManagerDefault;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.stream.IntStream;
import project.domain.players.Dealer;
import project.domain.players.Participant;
import project.domain.players.Player;
import project.domain.strategies.DealerPlayStyle;
import project.domain.strategies.MimicDealerPlaystyle;
import project.domain.strategies.ThorpsPlayStyle;

public class BlackjackGame implements SettingsManager {

	private Dealer dealer;
	private int gamesPlayed;
	private final List<Player> players = new ArrayList<>();
	private final SettingsManager settingsMgr;

	public BlackjackGame() {
		this.settingsMgr = new SettingsManagerDefault("BlackJackGame", this.getDefaultProperties());
		this.restoreDefault();

		this.setDealer(this.createDealer());
		this.setPlayers(this.createPlayers());
//		this.play(this.game.getIntegerProperty("rules.number_games_played"));
		this.play(10);
	}

	@Override
	public void addDefaultProperties(Properties properties) {
		this.settingsMgr.addDefaultProperties(properties);
	}

	@Override
	public boolean getBoolProperty(String key) {
		return this.settingsMgr.getBoolProperty(key);
	}

	public void setDealer(Dealer dealer) {
		this.dealer = dealer;
	}

	@Override
	public int getIntegerProperty(String key) {
		return this.settingsMgr.getIntegerProperty(key);
	}

	public void setPlayers(List<Player> players) {
		this.players.clear();
		this.players.addAll(players);
	}

	@Override
	public String getProperty(String key) {
		return this.settingsMgr.getProperty(key);
	}

	public void play(int times) {
		long start = System.currentTimeMillis();
		do {
			this.gamesPlayed++;
			this.initiateGameRound();
			List<Player> tempPlayers = new ArrayList<>(this.players);
			do {
				Iterator<Player> iter = tempPlayers.iterator();
				while (iter.hasNext()) {
					Player player = iter.next();
					if(player.play(this.dealer) != Action.HIT) {
						iter.remove();
					}
				}
			} while (!tempPlayers.isEmpty());
			while (true) {
				if(this.dealer.play(this.players) != Action.HIT) {
					break;
				}
			}
			this.finishGameRound();
		} while (times > this.gamesPlayed);
		this.printGameSummary();
		System.out.println("Game batch took: " + (System.currentTimeMillis() - start) + "ms");
	}

	@Override
	public void restoreDefault() {
		this.settingsMgr.restoreDefault();
	}

	@Override
	public void setProperty(String key, boolean value) {
		this.settingsMgr.setProperty(key, key);
	}

	@Override
	public void setProperty(String key, int value) {
		this.settingsMgr.setProperty(key, value);
	}

	@Override
	public void setProperty(String key, String value) {
		this.settingsMgr.setProperty(key, value);
	}

	private void checkWinner(Dealer dealer, List<Player> players) {
		int dealerValue = dealer.getScore();
		players.stream().forEach(player -> {
			int playerValue = player.getScore();
			if(playerValue > 21 || playerValue <= dealerValue && dealerValue <= 21) {
				if(playerValue > 21) {
					player.burned();
				}
				dealer.won();
			} else {
				if(dealerValue > 21) {
					dealer.burned();
				}
				player.won();
			}
		});
	}

	private Dealer createDealer() {
		return new Dealer(this.getIntegerProperty("rules.number_decks"), new DealerPlayStyle());
	}

	private List<Player> createPlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player("Ben", new MimicDealerPlaystyle()));
		players.add(new Player("Michiel", new MimicDealerPlaystyle()));
		players.add(new Player("Siel", new ThorpsPlayStyle()));
		players.add(new Player("Maxim", new ThorpsPlayStyle()));
		return players;
	}

	private void finishGameRound() {
//		this.printPlayerHands();
		this.checkWinner(this.dealer, this.players);
		this.dealer.collectCards(this.players);
//		this.printGameSummary();
//		System.out.println("*** Game " + (this.gamesPlayed) + " ended ***\n\n\n");
	}

	private Properties getDefaultProperties() {
		Properties props = new Properties();
		props.setProperty("rules.number_decks", "8");
		props.setProperty("rules.number_players", "4");
		props.setProperty("rules.number_cards_played_before_shuffle", "0");
		props.setProperty("rules.number_games_played", "1000");
		return props;
	}

	private double getWinPercentage(double value) {
		return (value / this.gamesPlayed) * 100;
	}

	private void initiateGameRound() {
//		System.out.println("*** Game " + (this.gamesPlayed + 1) + " started ***");
		IntStream.range(0, 2).forEach(i -> {
			this.players.forEach(p -> p.addCard(this.dealer.deal()));
			this.dealer.takeCard();
		});
//		System.out.println("Dealer top card:\n\t" + this.dealer.showTopCard());
	}

	private void printGameSummary() {
		System.out.println("Game Summary: ");
		System.out.println("\tGames played: " + this.gamesPlayed);
		this.players.stream().forEach(player -> this.printPlayerScore(player));
		System.out.println("\t" + this.dealer.getName() + ": \n\t\tWins: " + this.dealer.getWins() + "/" + (this.gamesPlayed * 4) + " -> " + ((double)this.dealer.getWins() / (this.gamesPlayed * 4)) * 100 + "%");
	}

	private void printParticipantHand(Participant p) {
		System.out.println("\t" + p.getName() + ":\n\t\t" + p.toString() + "\n\t\tValue: " + p.getScore());
	}

	private void printPlayerHands() {
		System.out.println("Hands:");
		this.players.stream().forEach(player -> this.printParticipantHand(player));
		this.printParticipantHand(this.dealer);
	}

	private void printPlayerScore(Player p) {
		System.out.println("\t" + p.getName() + ":\n\t\t" + "Wins: " + p.getWins() + " -> " + this.getWinPercentage(p.getWins()) + "%");
	}
}
