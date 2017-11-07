// This class use for draw and update background of game.
// It is interface class of IRenderable class.
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import logic.ImageUtility;

public class GameBackground implements IRenderable {
	private Image bgImage = null;
	private int currentX = 0;
	private int imageWidth;

	public GameBackground() {
		bgImage = ImageUtility.gamebg;
		if (bgImage != null)
			imageWidth = (int) bgImage.getWidth();
		else
			imageWidth = 0;
	}

	public void updateBackground() {
		currentX++;
		if (currentX >= imageWidth)
			currentX = 0;
	}

	@Override
	public int getZ() {
		return Integer.MIN_VALUE;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (bgImage == null)
			return;
		int currentDrawingX = 0;
		int currentDrawingY = 0;

		while (currentDrawingY < 480) {
			WritableImage croppedImage = new WritableImage(bgImage.getPixelReader(), currentX, 0, imageWidth - currentX,
					(int) bgImage.getHeight());
			gc.drawImage(croppedImage, currentDrawingX, currentDrawingY);
			currentDrawingY += bgImage.getHeight();
		}
		currentDrawingX += imageWidth - currentX;
		currentDrawingY = 0;

		while (currentDrawingX < 720) {
			while (currentDrawingY < 480) {
				gc.drawImage(bgImage, currentDrawingX, currentDrawingY);
				currentDrawingY += bgImage.getHeight();
			}
			currentDrawingX += imageWidth;
			currentDrawingY = 0;
		}
	}

	@Override
	public boolean isDestroy() {
		return false;
	}
}
