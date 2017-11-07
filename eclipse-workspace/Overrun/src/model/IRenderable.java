// This is IRenderable class. all of class with can draw must implement this class.
package model;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
	public int getZ();

	public void draw(GraphicsContext gc);

	public boolean isDestroy();
}
