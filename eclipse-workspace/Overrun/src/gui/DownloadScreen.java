// This is Class of Story and HowToPlay Scene.
// It will be called suddenly when player are enter to play the game.
package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.ImageUtility;
import main.Main;

public class DownloadScreen extends StackPane {
	private Canvas canvas;
	public static AnimationTimer animadownload;

	// This is only show game's story and how to play the game.
	public DownloadScreen() {
		super();
		this.canvas = new Canvas(720, 480);
		this.getChildren().add(canvas);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		animadownload = new AnimationTimer() {
			Long start = 0l;
			Long count = 0l;

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (start == 0l)
					start = now;
				if (count == 0l)
					count = now;
				long diff = now - start;
				long diff2 = now - count;
				if (diff2 <= 4000000000l) {
					gc.clearRect(0, 0, 720, 450);
					gc.drawImage(ImageUtility.story, 0, 0, 720, 480);
				} else {
					if (diff >= 100000000l) {
						gc.clearRect(0, 0, 720, 450);
						Font theFont = Font.font("Agency FB", FontWeight.LIGHT, 30);
						gc.setFont(theFont);
						gc.drawImage(ImageUtility.loader, 0, 0, 720, 480);
						if (now % 4 == 0) {
							gc.fillText("Loading", 570, 430);
						} else if (now % 4 == 1) {
							gc.fillText("Loading.", 570, 430);
						} else if (now % 4 == 2) {
							gc.fillText("Loading..", 570, 430);
						} else {
							gc.fillText("Loading...", 570, 430);
						}

						if (diff2 >= 8000000000l) {
							System.out.println("swap");
							if (Main.instance.isDownloadSceneShown()) {
								Main.instance.toDownloadScene();
							}
							Main.instance.toGameScene();
							this.stop();
							count = 0l;
						}
						start = 0l;
					}
				}
			}
		};

	}
}
