package project.domain;

import be.mrtus.common.domain.SettingsManager;
import be.mrtus.common.domain.SettingsManagerDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyLongProperty;
import javafx.collections.ObservableList;
import project.domain.players.Dealer;
import project.domain.players.Participant;
import project.domain.players.ParticipantManager;
import project.domain.players.Player;
import project.domain.strategy.StrategyManager;
import project.domain.strategy.dealer.DealerPlayStyle;
import project.domain.strategy.player.MimicDealerPlaystyle;
import project.domain.strategy.player.PlayerPlayStyle;
import project.domain.strategy.player.ThorpsPlayStyle;

public class BlackjackGame implements SettingsManager {

	private final GameManager gameMgr;
	private final ParticipantManager participantMgr;
	private final SettingsManager settingsMgr;
	private final StrategyManager strategyMgr;

	public BlackjackGame() {
		this.settingsMgr = new SettingsManagerDefault("BlackJackGame", this.getDefaultProperties());
//		this.restoreDefault();

		this.strategyMgr = new StrategyManager();
		this.participantMgr = new ParticipantManager();
		this.setPlayers(this.createPlayers());

		this.gameMgr = new GameManager(this);

		this.loadSettings();
	}

	public BlackjackGame(Properties props) {
		this();
		this.addDefaultProperties(props);
	}

	@Override
	public void addDefaultProperties(Properties properties) {
		this.settingsMgr.addDefaultProperties(properties);
	}

	public ReadOnlyLongProperty batchTimeProperty() {
		return this.gameMgr.batchTimeProperty();
	}

	public ReadOnlyIntegerProperty gamesPlayedProperty() {
		return this.gameMgr.gamesPlayedProperty();
	}

	public ReadOnlyIntegerProperty gamesToPlayProperty() {
		return this.gameMgr.gamesToPlayProperty();
	}

	@Override
	public boolean getBoolProperty(String key) {
		return this.settingsMgr.getBoolProperty(key);
	}

	public Dealer getDealer() {
		return this.participantMgr.getDealer();
	}

	public int getGamesPlayed() {
		return this.gameMgr.getGamesPlayed();
	}

	public int getGamesToPlay() {
		return this.gameMgr.getGamesToPlay();
	}

	@Override
	public int getIntegerProperty(String key) {
		return this.settingsMgr.getIntegerProperty(key);
	}

	public int getNumberDecks() {
		return this.participantMgr.getNumberDecks();
	}

	public void setNumberDecks(int numberDecks) {
		this.participantMgr.setNumberDecks(numberDecks);
		this.setProperty("rules.number_decks", numberDecks);
	}

	public void setNumberGamesToPlay(int gamesToPlay) {
		this.gameMgr.setNumberGamesToPlay(gamesToPlay);
		this.setProperty("rules.number_games_to_play", gamesToPlay);
	}

	public int getNumberPlayers() {
		return this.participantMgr.getNumberPlayers();
	}

	public void setNumberPlayers(int numberPlayers) {
		this.participantMgr.setNumberPlayers(numberPlayers);
		this.setProperty("rules.number_players", numberPlayers);
	}

	public ObservableList<DealerPlayStyle> getDealerStrategies() {
		return this.strategyMgr.getDealerStrategies();
	}

	public ObservableList<PlayerPlayStyle> getPlayerStrategies() {
		return this.strategyMgr.getPlayerStrategies();
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

	public synchronized void play() {
		this.gameMgr.play();
//		this.printGameSummary();
	}

	@Override
	public void restoreDefault() {
		this.settingsMgr.restoreDefault();
	}

	@Override
	public void setProperty(String key, boolean value) {
		this.settingsMgr.setProperty(key, value);
	}

	@Override
	public void setProperty(String key, int value) {
		this.settingsMgr.setProperty(key, value);
	}

	@Override
	public void setProperty(String key, String value) {
		this.settingsMgr.setProperty(key, value);
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

	private void loadSettings() {
		this.participantMgr.setNumberDecks(this.getIntegerProperty("rules.number_decks"));
		this.participantMgr.setNumberPlayers(this.getIntegerProperty("rules.number_players"));
		this.gameMgr.setNumberGamesToPlay(this.getIntegerProperty("rules.number_games_to_play"));
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
