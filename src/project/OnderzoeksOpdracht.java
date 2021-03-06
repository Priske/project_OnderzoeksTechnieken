package project;

import be.mrtus.common.gui.UnCollapsibleAccordionListener;
import be.mrtus.common.gui.control.NumericField;
import be.mrtus.common.gui.control.StyleChoiceBox;
import be.mrtus.common.gui.util.ButtonUtil;
import java.io.File;
import java.util.Properties;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import project.domain.*;
import project.domain.players.Dealer;
import project.domain.players.Player;

public class OnderzoeksOpdracht extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private final SimpleBooleanProperty animatedUI = new SimpleBooleanProperty();
	private final BlackjackGame game;
	private boolean play = false;

	public OnderzoeksOpdracht() {
		this.game = new BlackjackGame(this.getDefaultProperties());
		this.animatedUI.set(this.game.getBoolProperty("gui.animated"));
		this.animatedUI.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
			this.game.setProperty("gui.animated", newValue);
		});
	}

	public Tab buildDealerTab() {
		Tab tab = new Tab("Dealer config");
		{
			TableView<Dealer> table = new TableView();
			{
				table.setEditable(true);
				TableColumn name = new TableColumn("Name");
				{
					name.setCellValueFactory(new PropertyValueFactory("name"));
				}
				table.getColumns().add(name);
				TableColumn wins = new TableColumn("Wins");
				{
					wins.setPrefWidth(75);
					wins.setCellValueFactory(new PropertyValueFactory("wins"));
				}
				table.getColumns().add(wins);
				TableColumn loss = new TableColumn("Loss");
				{
					loss.setPrefWidth(75);
					loss.setCellValueFactory(new PropertyValueFactory("loss"));
				}
				table.getColumns().add(loss);
				TableColumn burns = new TableColumn("Burns");
				{
					burns.setPrefWidth(75);
					burns.setCellValueFactory(new PropertyValueFactory("burned"));
				}
				table.getColumns().add(burns);
				TableColumn draws = new TableColumn("Draws");
				{
					draws.setPrefWidth(75);
					draws.setCellValueFactory(new PropertyValueFactory("draw"));
				}
				table.getColumns().add(draws);
				TableColumn blackJack = new TableColumn("BlackJack");
				{
					blackJack.setPrefWidth(75);
					blackJack.setCellValueFactory(new PropertyValueFactory("blackJack"));
				}
				table.getColumns().add(blackJack);
				TableColumn strategy = new TableColumn("Strategy");
				{
					strategy.setPrefWidth(150);
					strategy.setCellValueFactory(new PropertyValueFactory("strategy"));
					strategy.setCellFactory(ComboBoxTableCell.forTableColumn(this.game.getDealerStrategies()));
				}
				table.getColumns().add(strategy);
				table.setItems(FXCollections.observableArrayList(this.game.getDealer()));
			}
			tab.setContent(table);
		}
		return tab;
	}

	public Tab buildPlayerTab() {
		Tab tab = new Tab("Player config");
		{
			TableView<Player> table = new TableView();
			{
				table.setEditable(true);
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
				TableColumn loss = new TableColumn("Loss");
				{
					loss.setPrefWidth(75);
					loss.setCellValueFactory(new PropertyValueFactory("loss"));
				}
				table.getColumns().add(loss);
				TableColumn burns = new TableColumn("Burns");
				{
					burns.setPrefWidth(75);
					burns.setCellValueFactory(new PropertyValueFactory("burned"));
				}
				table.getColumns().add(burns);
				TableColumn draws = new TableColumn("Draws");
				{
					draws.setPrefWidth(75);
					draws.setCellValueFactory(new PropertyValueFactory("draw"));
				}
				table.getColumns().add(draws);
				TableColumn blackJack = new TableColumn("BlackJack");
				{
					blackJack.setPrefWidth(75);
					blackJack.setCellValueFactory(new PropertyValueFactory("blackJack"));
				}
				table.getColumns().add(blackJack);
				TableColumn money = new TableColumn("Money");
				{
					money.setPrefWidth(75);
					money.setCellValueFactory(new PropertyValueFactory("money"));
				}
				table.getColumns().add(money);
				TableColumn bet = new TableColumn("Bet");
				{
					bet.setPrefWidth(75);
					bet.setCellValueFactory(new PropertyValueFactory("betValue"));
				}
				table.getColumns().add(bet);
				TableColumn strategy = new TableColumn("Strategy");
				{
					strategy.setPrefWidth(150);
					strategy.setCellValueFactory(new PropertyValueFactory("strategy"));
					strategy.setCellFactory(ComboBoxTableCell.forTableColumn(this.game.getPlayerStrategies()));
				}
				table.getColumns().add(strategy);
				TableColumn cardCounter = new TableColumn("Card counter");
				{
					cardCounter.setPrefWidth(150);
					cardCounter.setCellValueFactory(new PropertyValueFactory("cardCounter"));
					cardCounter.setCellFactory(ComboBoxTableCell.forTableColumn(this.game.getCardCounters()));
				}
				table.getColumns().add(cardCounter);

				TableColumn cardCounterValue = new TableColumn("Card counter value");
				{
					cardCounterValue.setPrefWidth(150);
					cardCounterValue.setCellValueFactory(new PropertyValueFactory("cardCounterValue"));
				}
				table.getColumns().add(cardCounterValue);
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

	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
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

	private Node buildBottomPanel() {
		BorderPane borderPane = new BorderPane();
		{
			borderPane.setPadding(new Insets(10));
			borderPane.setLeft(ButtonUtil.createButton("Play", (ActionEvent event) -> this.play(), 150, 50));
			borderPane.setCenter(this.buildSummaryPanel());
		}
		return borderPane;
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
							numericField.setText(this.game.getGamesToPlay() + "");
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

	private Node buildMenuBar() {
		MenuBar bar = new MenuBar();
		{
			Menu data = new Menu("Data");
			{
				MenuItem loadData = new MenuItem("Load data");
				{
					loadData.setOnAction((ActionEvent event) -> this.loadData());
				}
				data.getItems().add(loadData);
				MenuItem saveData = new MenuItem("Save data");
				{
					saveData.setOnAction((ActionEvent event) -> this.saveData());
				}
				data.getItems().add(saveData);
			}
			bar.getMenus().add(data);
		}
		return bar;
	}

	private Scene buildScene() {
		BorderPane borderPane = new BorderPane();
		{
			borderPane.setTop(this.buildMenuBar());
			borderPane.setLeft(this.buildAccordion());
			TabPane tabPane = new TabPane();
			{
				tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
				tabPane.getTabs().add(this.buildPlayerTab());
				tabPane.getTabs().add(this.buildDealerTab());
			}
			borderPane.setCenter(tabPane);
			borderPane.setBottom(this.buildBottomPanel());
		}
		return new Scene(borderPane, 900, 600);
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

	private Node buildSummaryPanel() {
		HBox hBox = new HBox();
		{
			hBox.setPadding(new Insets(10));
			hBox.setFillHeight(true);
			hBox.setSpacing(20);
//			VBox gamesPlayedVBox = new VBox();
//			{
//				gamesPlayedVBox.getChildren().add(new Label("Simulating game:"));
//				Label gamesPlayedLabel = new Label(this.game.getGamesPlayed() + "/" + this.game.getGamesToPlay());
//				{
//					ChangeListener<Number> listener = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//						Platform.runLater(() -> gamesPlayedLabel.setText(this.game.getGamesPlayed() + "/" + this.game.getGamesToPlay()));
//					};
//					this.game.gamesPlayedProperty().addListener(listener);
//					this.game.gamesToPlayProperty().addListener(listener);
//				}
//				gamesPlayedVBox.getChildren().add(gamesPlayedLabel);
//			}
//			hBox.getChildren().add(gamesPlayedVBox);
			ProgressIndicator indicator = new ProgressIndicator();
			{
				this.game.gamesPlayedProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
					Platform.runLater(() -> indicator.setProgress((int)newValue / (this.game.getGamesToPlay() * 1.0)));
				});
			}
			hBox.getChildren().add(indicator);

			VBox batchTimeVBox = new VBox();
			{
				batchTimeVBox.getChildren().add(new Label("Batch took: "));
				Label timeLabel = new Label();
				{
					this.game.batchTimeProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
						Platform.runLater(() -> timeLabel.setText(newValue + "ms"));
					});
				}
				batchTimeVBox.getChildren().add(timeLabel);
			}
			hBox.getChildren().add(batchTimeVBox);
		}
		return hBox;
	}

	private Properties getDefaultProperties() {
		Properties props = new Properties();
		props.setProperty("gui.style", Application.STYLESHEET_MODENA);
		props.setProperty("gui.animated", "true");
		return props;
	}

	private void loadData() {
		File file = new FileChooser().showOpenDialog(null);
		if(file != null) {
			this.game.loadDataFile(file);
		} else {
			JOptionPane.showMessageDialog(null, "No file selected to load.");
		}
	}

	private void play() {
		if(!this.play) {
			this.play = true;
			new Thread(() -> {
				this.game.play();
				this.play = false;
			}).start();
		}
	}

	private void saveData() {
		File file = new FileChooser().showSaveDialog(null);
		if(file != null) {
			this.game.saveDataFile(file);
		} else {
			JOptionPane.showMessageDialog(null, "No file selected to save.");
		}
	}
}
