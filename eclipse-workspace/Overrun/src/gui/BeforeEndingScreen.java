// This is Class of BeforeEndgood Scene.I mean game over by player is not die and no monster come out.
// It will be called when player are alive when 100 obstacle are passed
package gui;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioUtility;
import logic.ImageUtility;
import main.Main;

public class BeforeEndingScreen extends StackPane {
	private Canvas canvas;
	public static AnimationTimer animBfEnding;

	public BeforeEndingScreen() {
		super();
		this.canvas = new Canvas(720, 480);
		this.getChildren().add(canvas);
		GraphicsContext gc = this.canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, 720, 480);
		gc.setFill(Color.WHITE);
		gc.drawImage(ImageUtility.endingbg, 0, 0, 720, 480);
		newAnimation(gc);
	}

	// This method creates animation.
	// It shows the credit text,which run from right to left
	// After that,It show player run
	// After all of it. It will call to toggle scene to Endgood Scene.
	public static void newAnimation(GraphicsContext gc) {
		animBfEnding = new AnimationTimer() {
			Long start = 0l;
			Long count = 0l;
			Long count2 = 0l;
			int w = 740;
			int xPlayer = -80;

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				if (start == 0l)
					start = now;
				if (count == 0l)
					count = now;
				if (count2 == 0l)
					count2 = now;
				long diff = now - start;
				long diff2 = now - count;
				long diff3 = now - count2;
				if (diff2 <= 35000000000l) {
					if (diff >= 100000000l) {
						gc.clearRect(0, 0, 720, 450);
						gc.drawImage(ImageUtility.endingbg, 0, 0, 720, 480);
						Font theFont = Font.font("Agency FB", FontWeight.LIGHT, 30);
						gc.setFont(theFont);
						gc.fillText("Credit : Siraphat Gruysiriwong 5831078221 , Arnan Onkon 5830613821     "
								+ "BGMs : Maplestory BGMs , Emil Chronicle Online BGMs     "
								+ "Special Thanks : All sound effects , All of our friends which are testers.     "
								+ "Thank You For Playing...", w, 80);
						w -= 10;
						xPlayer += 2;
						start = 0l;
					}
					if (diff3 >= 10000000l) {
						gc.clearRect(0, 310, 720, 170);
						gc.drawImage(new WritableImage(ImageUtility.endingbg.getPixelReader(), 0, 300, 720, 180), 0,
								300);
						gc.drawImage(ImageUtility.imagePlayer, xPlayer, 350);
					}
				} else {
					System.out.println("swap");
					if (Main.instance.isBfendingSceneShown()) {
						Main.instance.toBfendingScene();
					}
					Main.instance.toEndgoodgameScene();
					AudioUtility.stopSound("bfendingsound");
					this.stop();
					count = 0l;
				}
			}
		};
		animBfEnding.start();
	}
}
