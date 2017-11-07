// This is a player class. It is inheritance of entity.
package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import logic.AudioUtility;
import logic.ImageUtility;
import logic.InputUtility;

public class PlayerChibi extends Entity {
	public boolean isJump = false;
	public boolean djump = false;
	public int HP;
	public int SCORE;
	private double highJ, highDJ = 0;
	private double timeJ, timeDJ = 0;
	private int normaly;
	private boolean immune;
	private int countImmune = 0;
	private boolean gameOveryStyle;

	public PlayerChibi(int x, int y) {
		super(x, y);
		this.setDestroy(false);
		setZ(10);
		HP = 2;
		SCORE = 0;
		normaly = getY();
		this.immune = true;
		this.gameOveryStyle = false;
	}

	public boolean isGameOveryStyle() {
		return gameOveryStyle;
	}

	public void setGameOveryStyle(boolean gameOveryStyle) {
		this.gameOveryStyle = gameOveryStyle;
	}

	public void setjump(boolean b) {
		isJump = b;
	}

	public void setdjump(boolean b) {
		djump = b;
	}

	// draw player image.
	// if player is immune. draw image for immune. it will stop draw immune
	// image about 0.25 second before player return to normal mode.
	@Override
	public void draw(GraphicsContext gc) {
		if (!this.immune) {
			gc.drawImage(ImageUtility.imagePlayer, this.x, this.y, 100, 100);
		} else if (this.immune && countImmune < 100) {
			gc.drawImage(ImageUtility.imagePlayerImmune, this.x, this.y, 100, 100);
			countImmune++;
		} else if (this.immune && countImmune >= 100 && countImmune < 125) {
			gc.drawImage(ImageUtility.imagePlayer, this.x, this.y, 100, 100);
			countImmune++;
		} else {
			gc.drawImage(ImageUtility.imagePlayer, this.x, this.y, 100, 100);
			setImmune(false);
			countImmune = 0;
		}
	}

	public void dealHP() {
		if (HP - 1 <= 0) {
			this.HP = 0;
			this.setDestroy(true);
		} else {
			this.HP--;
		}
	}

	public int getHP() {
		return HP;
	}

	public synchronized void increaseScore(int point) {
		this.SCORE += point;
	}

	public int getSCORE() {
		return SCORE;
	}

	public void setImmune(boolean immune) {
		this.immune = immune;
	}

	public boolean isImmune() {
		return this.immune;
	}
	// method for check when player want to jump.
	public void checkJump() {
		if (!isDestroy()) {
			if (InputUtility.getKeyTriggered(KeyCode.SPACE) && isJump && djump) {
				InputUtility.setKeyTriggered(KeyCode.SPACE, false);
			}
			if (!isJump && InputUtility.getKeyTriggered(KeyCode.SPACE)) {
				setjump(true);
				AudioUtility.playSound("jump");
				InputUtility.setKeyTriggered(KeyCode.SPACE, false);
			}
			if (isJump && InputUtility.getKeyTriggered(KeyCode.SPACE) && !djump) {
				djump = true;
				AudioUtility.playSound("jump");
				InputUtility.setKeyTriggered(KeyCode.SPACE, false);
			}
			jump();
		} else {
			InputUtility.clear();
		}
	}
	// method logic for calculate virtual jump
	private void jump() {
		if (!isDestroy()) {
			if (isJump) {
				highJ = 78 * timeJ - 10 * timeJ * timeJ;
				highDJ = 78 * timeDJ - 10 * timeDJ * timeDJ;
				if (!djump) {
					timeJ += 0.15;
				} else {
					timeDJ += 0.15;
				}
				setY(normaly - (int) highJ - (int) highDJ);
				if (normaly - (int) highJ - (int) highDJ > normaly) {
					timeJ = 0;
					timeDJ = 0;
					setY(normaly);
					setjump(false);
					djump = false;
				}
			}
		} else {
			setY(normaly);
		}
	}
}
