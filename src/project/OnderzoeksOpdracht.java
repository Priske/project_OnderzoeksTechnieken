package project;

import be.mrtus.common.gui.StyleChoiceBox;
import be.mrtus.common.gui.UnCollapsibleAccordionListener;
import java.util.Properties;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

	private Accordion buildAccordion() {
		Accordion accordion = new Accordion();
		{
			accordion.expandedPaneProperty().addListener(new UnCollapsibleAccordionListener());
			TitledPane pane = this.buildGameSettingsTitledPane();
			accordion.setExpandedPane(pane);
			accordion.getPanes().add(pane);
			accordion.getPanes().add(this.buildSettingsTitledPane());
		}
		return accordion;
	}

	private TitledPane buildGameSettingsTitledPane() {
		TitledPane pane = new TitledPane();
		{
			pane.setText("Game Settings");
			pane.animatedProperty().bind(this.animatedUI);
			BorderPane borderPane = new BorderPane();
			{
				VBox vBox = new VBox();
				{

				}
				borderPane.setCenter(vBox);
			}
			pane.setContent(borderPane);
		}
		return pane;
	}

	private Scene buildScene() {
		BorderPane borderPane = new BorderPane();
		{
			borderPane.setLeft(this.buildAccordion());
			TabPane tabPane = new TabPane();
			{
				tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
			}
			borderPane.setCenter(tabPane);
		}
		return new Scene(borderPane, 800, 600);
	}

	private TitledPane buildSettingsTitledPane() {
		TitledPane pane = new TitledPane();
		{
			pane.setText("Settings");
			pane.animatedProperty().bind(this.animatedUI);
			BorderPane borderPane = new BorderPane();
			{
				VBox vBox = new VBox();
				{
					vBox.setFillWidth(true);
					vBox.setSpacing(10);
					CheckBox animatedCheckBox = new CheckBox("Animated UI");
					{
						animatedCheckBox.setPrefWidth(150);
						animatedCheckBox.selectedProperty().set(this.animatedUI.get());
						animatedCheckBox.selectedProperty().bindBidirectional(this.animatedUI);
						vBox.getChildren().add(animatedCheckBox);
					}
					StyleChoiceBox choiceBox = new StyleChoiceBox();
					{
						String style = this.game.getProperty("gui.style");
						if(style == null || style.isEmpty()) {
							style = Application.STYLESHEET_MODENA;
							this.game.setProperty("gui.style", style);
						}
						choiceBox.getSelectionModel().select(style);
						choiceBox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
							if(oldValue == null || !oldValue.equalsIgnoreCase(newValue)) {
								this.game.setProperty("gui.style", newValue);
							}
						});
						choiceBox.setPrefWidth(120);
						HBox hBox = new HBox();
						{
							hBox.setSpacing(5);
							hBox.getChildren().add(new Label("Style:"));
							hBox.getChildren().add(choiceBox);
						}
						vBox.getChildren().add(hBox);
					}
				}
				borderPane.setCenter(vBox);
			}
			pane.setContent(borderPane);
		}
		return pane;
	}

	private Properties getDefaultProperties() {
		Properties props = new Properties();
		props.setProperty("gui.style", Application.STYLESHEET_MODENA);
		props.setProperty("gui.animated", "true");
		return props;
	}
}
