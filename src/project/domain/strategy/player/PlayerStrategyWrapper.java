package project.domain.strategy.player;

import project.domain.strategy.PlayerPlayStyle;

public abstract class PlayerStrategyWrapper implements PlayerPlayStyle {

	private final String name;

	public PlayerStrategyWrapper(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
