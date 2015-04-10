package project.gui;

import javafx.application.Application;
import javafx.stage.Stage;
import project.domain.*;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private BlackjackGame game;

	public OnderzoeksOpdracht() {
		this.game = new BlackjackGame();
	}

	@Override
	public void start(Stage stage) {

	}
}
