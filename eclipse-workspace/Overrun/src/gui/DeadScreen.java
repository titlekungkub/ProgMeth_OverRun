// This is Class of Dead Scene.I mean game over by player is die.
// It will be called suddenly when player are out of hearth.
package gui;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioUtility;
import logic.GameManager;
import logic.ImageUtility;
import main.Main;

public class DeadScreen extends StackPane {
	private Canvas canvas;
	private GraphicsContext gc;
	private Thread dead;
	private boolean onDeadScene;
	private boolean Choose;
	private GameScreen gamescreen;

	// Construction method deal with Input.
	public DeadScreen(GameScreen gamescreen) {
		super();
		this.canvas = new Canvas(720, 480);
		this.getChildren().add(canvas);
		gc = this.canvas.getGraphicsContext2D();
		this.gamescreen = gamescreen;
		onDeadScene = true;
		canvas.setFocusTraversable(true);
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					if (!Choose) {
						AudioUtility.playSound("enter");
						if (Main.instance.isDeadSceneShown()) {
							Main.instance.toDeadScene();
						}
						Main.instance.toDownloadScene();
						onDeadScene = false;
						DownloadScreen.animadownload.start();
						System.out.println("PRESS ENTER");
					} else {
						AudioUtility.playSound("enter");
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirm Exit");
						alert.setHeaderText(null);
						alert.setContentText("Give up ?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK) {
							System.exit(0);
						}
					}

				}
				if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
					AudioUtility.playSound("menusound");
					Choose = !Choose;
				}
			}
		});
	}

	// This method show GUI of game.
	public void startDeadScene() {
		onDeadScene = true;
		Choose = false;
		dead = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (onDeadScene) {
						Thread.sleep(10);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								gc.clearRect(0, 0, 720, 480);
								gc.setFill(Color.WHITE);
								gc.drawImage(ImageUtility.deadbg, 0, 0, 720, 480);
								gamescreen.getgame();
								gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 50));
								gc.fillText("YOUR SCORE : " + GameManager.getPlayerScore(), 200, 100);
								if (!Choose) {
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 50));
									gc.fillText("Play again", 500, 250);
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 30));
									gc.fillText("Exit", 550, 300);
								} else {
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 30));
									gc.fillText("Play again", 500, 250);
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 50));
									gc.fillText("Exit", 550, 300);
								}
							}
						});
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		dead.start();
	}
}