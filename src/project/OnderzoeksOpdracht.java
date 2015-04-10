package project;

import be.mrtus.common.gui.UnCollapsibleAccordionListener;
import java.util.Properties;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import project.domain.*;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private final SimpleBooleanProperty animatedUI = new SimpleBooleanProperty();
	private final BlackjackGame game;

	public OnderzoeksOpdracht() {
		this.game = new BlackjackGame(this.getDefaultProperties());
		this.animatedUI.set(this.game.getBoolProperty("gui.animated"));
		this.animatedUI.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			this.game.setProperty("gui.animated", newValue);
		});
	}

	@Override
	public void start(Stage stage) {
		Application.setUserAgentStylesheet(this.game.getProperty("gui.style"));
		stage.setScene(this.buildScene());
		stage.setTitle("BlackJack - Analystics");
		stage.show();
	}

	private Scene buildScene() {
		BorderPane borderPane = new BorderPane();
		{
			Accordion accordion = new Accordion();
			{
				accordion.expandedPaneProperty().addListener(new UnCollapsibleAccordionListener());
			}
			borderPane.setLeft(accordion);
			TabPane tabPane = new TabPane();
			{
				tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
			}
			borderPane.setCenter(tabPane);
		}
		return new Scene(borderPane, 800, 600);
	}

	private Properties getDefaultProperties() {
		Properties props = new Properties();
		props.setProperty("gui.style", Application.STYLESHEET_MODENA);
		props.setProperty("gui.animated", "true");
		return props;
	}
}
