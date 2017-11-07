// This is a coin class. It is inheritance of entity.
package model;

import javafx.scene.canvas.GraphicsContext;
import logic.ImageUtility;

public class Coin extends Entity{
	public Coin(int x, int y) {
		super(x,y);
		this.setDestroy(false);
		setZ(2);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(ImageUtility.imageCoin, this.x, this.y,75,75);
	}
}