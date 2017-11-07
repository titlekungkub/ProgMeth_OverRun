// This is a entity class. It is parent class.
// It is interface class of IRenderable class.
package model;

public abstract class Entity implements IRenderable {
	protected int x, y, z;
	protected boolean isDestroy;

	public Entity(int x, int y) {
		this.x = x;
		this.y = y;
		z = 1;
	}

	@Override
	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isDestroy() {
		return isDestroy;
	}

	public void setDestroy(boolean isDestroy) {
		this.isDestroy = isDestroy;
	}
}
