// This is a Obstacle class. It is inheritance of entity.
package model;

import javafx.scene.canvas.GraphicsContext;
import logic.ImageUtility;


public class ObstacleDragon extends Entity{

	public ObstacleDragon(int x, int y) {
		super(x,y);
		this.setDestroy(false);
		setZ(2);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(ImageUtility.imageDragon, this.x, this.y,200,144);
	}
}
