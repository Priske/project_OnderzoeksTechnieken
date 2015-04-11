package project;

import be.mrtus.common.gui.UnCollapsibleAccordionListener;
import be.mrtus.common.gui.control.NumericField;
import be.mrtus.common.gui.control.StyleChoiceBox;
import be.mrtus.common.gui.util.ButtonUtil;
import java.util.Properties;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.domain.*;
import project.domain.players.Player;

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

	public Tab createPlayerTab() {
		Tab tab = new Tab("Player config");
		{
			TableView<Player> table = new TableView();
			{
				TableColumn id = new TableColumn("Id");
				{
					id.setCellValueFactory(new PropertyValueFactory("id"));
				}
				table.getColumns().add(id);
				TableColumn name = new TableColumn("Name");
				{
					name.setPrefWidth(75);
					name.setCellValueFactory(new PropertyValueFactory("name"));
				}
				table.getColumns().add(name);
				TableColumn wins = new TableColumn("Wins");
				{
					wins.setPrefWidth(75);
					wins.setCellValueFactory(new PropertyValueFactory("wins"));
				}
				table.getColumns().add(wins);
				TableColumn burns = new TableColumn("Burns");
				{
					burns.setPrefWidth(75);
					burns.setCellValueFactory(new PropertyValueFactory("burned"));
				}
				table.getColumns().add(burns);
				TableColumn strategy = new TableColumn("Strategy");
				{
					strategy.setPrefWidth(75);
					strategy.setCellFactory(ComboBoxTableCell.forTableColumn(game.getPlayerStrategies()));
				}
				table.getColumns().add(strategy);
				table.setItems(this.game.getPlayers());
			}
			tab.setContent(table);
		}
		return tab;
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
					vBox.setFillWidth(true);
					vBox.setSpacing(10);
					HBox hBoxNumberGamesToPlay = new HBox();
					{
						hBoxNumberGamesToPlay.setFillHeight(true);
						hBoxNumberGamesToPlay.setSpacing(10);
						hBoxNumberGamesToPlay.getChildren().add(new Label("Games to play"));
						NumericField numericField = new NumericField();
						{
							numericField.setAlignment(Pos.CENTER_RIGHT);
							numericField.setPrefWidth(50);
							numericField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
								if(newValue.matches("[0-9]+")) {
									this.game.setNumberGamesToPlay(Integer.parseInt(newValue));
								}
							});
							numericField.setText((int)this.game.getGamesToPlay() + "");
						}
						hBoxNumberGamesToPlay.getChildren().add(numericField);
					}
					vBox.getChildren().add(hBoxNumberGamesToPlay);
					HBox hBoxNumberOfDecks = new HBox();
					{
						hBoxNumberOfDecks.setFillHeight(true);
						hBoxNumberOfDecks.setSpacing(10);
						hBoxNumberOfDecks.getChildren().add(new Label("Number decks"));
						NumericField numericField = new NumericField();
						{
							numericField.setAlignment(Pos.CENTER_RIGHT);
							numericField.setPrefWidth(50);
							numericField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
								if(newValue.matches("[0-9]+")) {
									this.game.setNumberDecks(Integer.parseInt(newValue));
								}
							});
							numericField.setText(this.game.getNumberDecks() + "");
						}
						hBoxNumberOfDecks.getChildren().add(numericField);
					}
					vBox.getChildren().add(hBoxNumberOfDecks);
					HBox hBoxNumberPlayers = new HBox();
					{
						hBoxNumberPlayers.setFillHeight(true);
						hBoxNumberPlayers.setSpacing(10);
						hBoxNumberPlayers.getChildren().add(new Label("Number players"));
						NumericField numericField = new NumericField();
						{
							numericField.setAlignment(Pos.CENTER_RIGHT);
							numericField.setPrefWidth(50);
							numericField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
								if(newValue.matches("[0-9]+")) {
									this.game.setNumberPlayers(Integer.parseInt(newValue));
								}
							});
							numericField.setText(this.game.getNumberPlayers() + "");
						}
						hBoxNumberPlayers.getChildren().add(numericField);
					}
					vBox.getChildren().add(hBoxNumberPlayers);
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
				tabPane.getTabs().add(this.createPlayerTab());
			}
			borderPane.setCenter(tabPane);
			borderPane.setBottom(ButtonUtil.createButton("Play", (ActionEvent event) -> new Thread(() -> this.game.play()).start()));
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
