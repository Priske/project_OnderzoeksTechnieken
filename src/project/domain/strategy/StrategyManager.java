package project.domain.strategy;

import project.domain.strategy.dealer.DealerPlayStyleDefault;
import project.domain.strategy.player.NeverBustPlaystyle;
import project.domain.strategy.player.MimicDealerPlaystyle;
import project.domain.strategy.player.ThorpsPlayStyle;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.domain.exceptions.StrategyNotFoundException;

public class StrategyManager {

	private final ObservableList<DealerPlayStyle> dealerStrategies = FXCollections.observableArrayList();
	private final ObservableList<PlayerPlayStyle> playerStrategies = FXCollections.observableArrayList();

	public StrategyManager() {
		this.addDealerStrategy(new DealerPlayStyleDefault());
		this.addPlayerStrategy(new MimicDealerPlaystyle());
		this.addPlayerStrategy(new NeverBustPlaystyle());
		this.addPlayerStrategy(new ThorpsPlayStyle());
	}

	public void addDealerStrategy(DealerPlayStyle strategy) {
		this.dealerStrategies.add(strategy);
	}

	public void addPlayerStrategy(PlayerPlayStyle strategy) {
		this.playerStrategies.add(strategy);
	}

	public ObservableList<DealerPlayStyle> getDealerStrategies() {
		return this.dealerStrategies;
	}

	public DealerPlayStyle getDealerStrategy(String name) {
		Optional<DealerPlayStyle> opt = this.dealerStrategies.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst();
		opt.orElseThrow(() -> new StrategyNotFoundException("Strategy '" + name + "' could not be found."));
		return opt.get();
	}

	public ObservableList<PlayerPlayStyle> getPlayerStrategies() {
		return this.playerStrategies;
	}

	public PlayerPlayStyle getPlayerStrategy(String name) {
		Optional<PlayerPlayStyle> opt = this.playerStrategies.stream().filter(s -> s.getName().equalsIgnoreCase(name)).findFirst();
		opt.orElseThrow(() -> new StrategyNotFoundException("Strategy '" + name + "' could not be found."));
		return opt.get();
	}
}
