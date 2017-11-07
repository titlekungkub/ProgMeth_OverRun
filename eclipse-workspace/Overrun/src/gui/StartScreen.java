// This is Class of start game Scene.It will be called when you run this game for first time.
package gui;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioUtility;
import logic.ImageUtility;
import main.Main;

public class StartScreen extends StackPane {
	private Canvas canvas;
	private GraphicsContext gc;
	private boolean onStartScene;
	private Thread start;

	// Construction method deal with Input.
	public StartScreen() {
		super();
		this.canvas = new Canvas(720, 480);
		this.getChildren().add(canvas);
		gc = this.canvas.getGraphicsContext2D();
		onStartScene = true;
		canvas.setFocusTraversable(true);
		this.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				// TODO Auto-generated method stub
				if (event.getCode() == KeyCode.ENTER) {
					AudioUtility.playSound("enter");
					Main.instance.toDownloadScene();
					onStartScene = false;
					DownloadScreen.animadownload.start();
					System.out.println("PRESS ENTER");
				}
			}
		});
	}

	// This method only show picture and blink text.
	public void startConfigScene() {
		AudioUtility.playSound("configsound");
		start = new Thread(new Runnable() {
			int count = 0;

			@Override
			public void run() {
				try {
					while (onStartScene) {
						Thread.sleep(10);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (count > 15) {
									gc.clearRect(0, 0, 720, 480);
									gc.setFill(Color.BLACK);
									gc.drawImage(ImageUtility.background, 0, 0, 720, 480);
									gc.drawImage(ImageUtility.totoro, 550, 322, 100, 130);
									gc.drawImage(ImageUtility.player, 100, 360, 100, 100);
									Font theFont = Font.font("Agency FB", FontWeight.LIGHT, 40);
									gc.setFont(theFont);
									gc.fillText("PRESS ENTER TO START", 215, 370);
									count++;
								} else {
									gc.clearRect(0, 0, 720, 480);
									gc.setFill(Color.BLACK);
									gc.drawImage(ImageUtility.background, 0, 0, 720, 480);
									gc.drawImage(ImageUtility.totoro, 550, 322, 100, 130);
									gc.drawImage(ImageUtility.player, 100, 360, 100, 100);
									count++;
								}
								if (count > 30)
									count = 0;
							}
						});
					}
					if (!onStartScene) {
						AudioUtility.stopSound("configsound");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		start.start();
	}
}
