package project.domain.statistics;

import java.util.ArrayList;
import java.util.List;

public class StatisticsCollector {

	private final List<Turn> turns = new ArrayList<>();

	public void addTurn(Turn turn) {
		this.turns.add(turn);
	}

	public List<Turn> getTurns() {
		return this.turns;
	}
}
