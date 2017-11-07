// This is Class of Endgood Scene.I mean game over by player is not die and no monster come out.
// It will be called after BeforeEndgood Scene has been called.
package gui;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioUtility;
import logic.ImageUtility;
import main.Main;

public class EndgoodgameScreen extends StackPane {
	private Canvas canvas;
	private GraphicsContext gc;
	private Thread ending;
	private boolean onEndingScene;
	private boolean Choose;

	// This constructor method deals with input form player.
	public EndgoodgameScreen() {
		super();
		this.canvas = new Canvas(720, 480);
		this.getChildren().add(canvas);
		gc = this.canvas.getGraphicsContext2D();
		onEndingScene = true;
		canvas.setFocusTraversable(true);
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.ENTER) {
					if (!Choose) {
						AudioUtility.playSound("enter");
						if (Main.instance.isEndgoodgameSceneShown()) {
							Main.instance.toEndgoodgameScene();
						}
						Main.instance.toDownloadScene();
						onEndingScene = false;
						DownloadScreen.animadownload.start();
						System.out.println("PRESS ENTER");
					} else {
						AudioUtility.playSound("enter");
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Confirm Exit");
						alert.setHeaderText(null);
						alert.setContentText("Bye Bye");

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

	// This is animation of this Scene
	public void startEndgameScene() {
		onEndingScene = true;
		Choose = false;

		AudioUtility.playSound("endingsound");
		ending = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (onEndingScene) {
						Thread.sleep(10);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								gc.clearRect(0, 0, 720, 480);
								gc.setFill(Color.BLACK);
								gc.drawImage(ImageUtility.endtwo, 0, 0, 720, 480);
								gc.drawImage(ImageUtility.dog, 550, 340, 64, 64);
								gc.drawImage(ImageUtility.stand, 480, 310, 100, 100);
								if (!Choose) {
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 50));
									gc.fillText("Play again", 220, 370);
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 30));
									gc.fillText("Exit", 300, 420);
								} else {
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 30));
									gc.fillText("Play again", 220, 370);
									gc.setFont(Font.font("Agency FB", FontWeight.LIGHT, 50));
									gc.fillText("Exit", 300, 420);
								}
							}
						});
					}
					if (!onEndingScene) {
						AudioUtility.stopSound("endingsound");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		ending.start();
	}
}