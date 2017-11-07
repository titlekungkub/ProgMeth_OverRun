// This is the main class for play this game.
package main;

import gui.BeforeEndingScreen;
import gui.StartScreen;
import gui.DeadScreen;
import gui.DownloadScreen;
import gui.EndgoodgameScreen;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.GameManager;
import logic.Listener;
import gui.GameScreen;

public class Main extends Application {
	public static Main instance;
	private Stage primaryStage;
	private Scene configScene;
	private Scene gameScene;
	private Scene endgoodgameScene;
	private Scene downloadScene;
	private Scene deadScene;
	private Scene bfendingScene;
	private EndgoodgameScreen endgoodgameScreen;
	private GameScreen gameScreen;
	private StartScreen configScreen;
	private DeadScreen deadScreen;
	private DownloadScreen downloadScreen;
	private BeforeEndingScreen bfendingScreen;
	private GameManager game;
	private Listener listener;
	private boolean isGameSceneShown = false;
	private boolean isDownloadSceneShown = false;
	private boolean isEndgoodgameSceneShown = false;
	private boolean isDeadSceneShown = false;
	private boolean isBfendingSceneShown = false;

	@Override
	public void start(Stage primaryStage) {
		instance = this;
		this.primaryStage = primaryStage;
		primaryStage.setTitle("OVERRUN");
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		this.game = new GameManager();
		this.configScreen = new StartScreen();
		this.configScene = new Scene(configScreen);
		this.gameScreen = new GameScreen(game);
		this.gameScene = new Scene(gameScreen);
		this.downloadScreen = new DownloadScreen();
		this.downloadScene = new Scene(downloadScreen);
		this.listener = new Listener(gameScene);
		this.endgoodgameScreen = new EndgoodgameScreen();
		this.endgoodgameScene = new Scene(endgoodgameScreen);
		this.deadScreen = new DeadScreen(gameScreen);
		this.deadScene = new Scene(deadScreen);
		listener.addListener();
		primaryStage.setScene(configScene);
		configScreen.startConfigScene();
		primaryStage.show();
		primaryStage.sizeToScene();
	}

	public static void main(String[] args) {
		launch(args);
	}

	// toggle boolean isGameSceneShown and if isGameSceneShown is false,change
	// scene to Game Screen.
	public synchronized void toGameScene() {
		if (!this.isGameSceneShown) {
			this.primaryStage.setScene(gameScene);
			System.out.println("To Game Screen");
			gameScreen.startgame();
		}
		this.isGameSceneShown = !this.isGameSceneShown;
	}

	// toggle boolean isDownloadSceneShown and if isDownloadSceneShown is
	// false,change scene to Download Screen.
	public synchronized void toDownloadScene() {
		if (!this.isDownloadSceneShown) {
			this.primaryStage.setScene(downloadScene);
			System.out.println("To Download Screen");
		}
		this.isDownloadSceneShown = !this.isDownloadSceneShown;
	}

	// toggle boolean isEndgoodgameSceneShown and if isEndgoodgameSceneShown is
	// false,change scene to Endgame Screen.
	public synchronized void toEndgoodgameScene() {
		if (!this.isEndgoodgameSceneShown) {
			this.primaryStage.setScene(endgoodgameScene);
			System.out.println("To Endgame Screen");
			endgoodgameScreen.startEndgameScene();
		}
		this.isEndgoodgameSceneShown = !this.isEndgoodgameSceneShown;
	}

	// toggle boolean isDeadSceneShown and if isDeadSceneShown is false,change
	// scene to Dead Screen.
	public synchronized void toDeadScene() {
		if (!this.isDeadSceneShown) {
			this.primaryStage.setScene(deadScene);
			System.out.println("To Dead Screen");
			deadScreen.startDeadScene();
		}
		this.isDeadSceneShown = !this.isDeadSceneShown;
	}

	// toggle boolean isBfendingSceneShown and if isBfendingSceneShown is
	// false,change scene to BeforeEndingScreen.
	public synchronized void toBfendingScene() {
		if (!this.isBfendingSceneShown) {
			System.out.println("To BFending Screen");
			this.bfendingScreen = new BeforeEndingScreen();
			this.bfendingScene = new Scene(bfendingScreen);
			this.primaryStage.setScene(bfendingScene);
		}
		this.isBfendingSceneShown = !this.isBfendingSceneShown;
	}

	public boolean isGameSceneShown() {
		return isGameSceneShown;
	}

	public boolean isDownloadSceneShown() {
		return isDownloadSceneShown;
	}

	public boolean isEndgoodgameSceneShown() {
		return isEndgoodgameSceneShown;
	}

	public boolean isDeadSceneShown() {
		return isDeadSceneShown;
	}

	public boolean isBfendingSceneShown() {
		return isBfendingSceneShown;
	}
}
