// This is a Obstacle class. It is inheritance of entity.
package model;

import javafx.scene.canvas.GraphicsContext;
import logic.ImageUtility;

public class ObstacleKabigon extends Entity {

	public static int moveSpeed;

	public ObstacleKabigon(int x, int y) {
		super(x, y);
		this.setDestroy(false);
		setZ(2);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.drawImage(ImageUtility.imageKabigon, this.x, this.y, 125, 125);
	}
}