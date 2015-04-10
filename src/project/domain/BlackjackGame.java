package project.domain;

import be.mrtus.common.domain.SettingsManager;
import be.mrtus.common.domain.SettingsManagerDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.collections.ObservableList;
import project.domain.players.Dealer;
import project.domain.players.Participant;
import project.domain.players.ParticipantManager;
import project.domain.players.Player;
import project.domain.strategy.dealer.DealerPlayStyleDefault;
import project.domain.strategy.player.MimicDealerPlaystyle;
import project.domain.strategy.player.ThorpsPlayStyle;

public class BlackjackGame implements SettingsManager {

	private final SettingsManager settingsMgr;
	private final ParticipantManager participantMgr;
	private final GameManager gameMgr;

	public BlackjackGame() {
		this.settingsMgr = new SettingsManagerDefault("BlackJackGame", this.getDefaultProperties());
//		this.restoreDefault();

		this.participantMgr = new ParticipantManager();
		this.setPlayers(this.createPlayers());

		this.gameMgr = new GameManager(this);

//		this.play();
	}

	public BlackjackGame(Properties props) {
		this();
		this.addDefaultProperties(props);
	}

	@Override
	public void addDefaultProperties(Properties properties) {
		this.settingsMgr.addDefaultProperties(properties);
	}

	@Override
	public boolean getBoolProperty(String key) {
		return this.settingsMgr.getBoolProperty(key);
	}

	public Dealer getDealer() {
		return this.participantMgr.getDealer();
	}

	@Override
	public int getIntegerProperty(String key) {
		return this.settingsMgr.getIntegerProperty(key);
	}

	public ObservableList<Player> getPlayers() {
		return this.participantMgr.getPlayers();
	}

	public void setPlayers(List<Player> players) {
		this.participantMgr.setPlayers(players);
	}

	@Override
	public String getProperty(String key) {
		return this.settingsMgr.getProperty(key);
	}

	public void play() {
		long start = System.currentTimeMillis();
		this.gameMgr.play();
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

	private Dealer createDealer() {
		return new Dealer(this.getIntegerProperty("rules.number_decks"), new DealerPlayStyleDefault());
	}

	private List<Player> createPlayers() {
		List<Player> list = new ArrayList<>();
		list.add(new Player("Ben", new MimicDealerPlaystyle()));
		list.add(new Player("Michiel", new MimicDealerPlaystyle()));
		list.add(new Player("Siel", new ThorpsPlayStyle()));
		list.add(new Player("Maxim", new ThorpsPlayStyle()));
		return list;
	}

	private Properties getDefaultProperties() {
		Properties props = new Properties();
		props.setProperty("rules.number_decks", "8");
		props.setProperty("rules.number_players", "4");
		props.setProperty("rules.number_cards_played_before_shuffle", "0");
		props.setProperty("rules.number_games_to_play", "1000");
		return props;
	}

	private double getWinPercentage(double value, double gamesPlayed) {
		return (value / gamesPlayed) * 100;
	}

	private void printGameSummary() {
		double gamesPlayed = this.gameMgr.getGamesPlayed();
		System.out.println("Game Summary: ");
		System.out.println("\tGames played: " + gamesPlayed);
		this.participantMgr.getPlayers().stream().forEach(player -> this.printPlayerScore(player));
		Dealer dealer = this.participantMgr.getDealer();
		System.out.println("\t" + dealer.getName() + ": \n\t\tWins: " + dealer.getWins() + "/" + (gamesPlayed * 4) + " -> " + (dealer.getWins() / (gamesPlayed * 4)) * 100 + "%");
	}

	private void printParticipantHand(Participant p) {
		System.out.println("\t" + p.getName() + ":\n\t\t" + p.toString() + "\n\t\tValue: " + p.getScore());
	}

	private void printPlayerHands() {
		System.out.println("Hands:");
		this.participantMgr.getPlayers().stream().forEach(player -> this.printParticipantHand(player));
		this.printParticipantHand(this.participantMgr.getDealer());
	}

	private void printPlayerScore(Player p) {
		System.out.println("\t" + p.getName() + ":\n\t\t" + "Wins: " + p.getWins() + " -> " + this.getWinPercentage(p.getWins(), this.gameMgr.getGamesPlayed()) + "%");
	}
}
