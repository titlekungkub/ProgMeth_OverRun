// This scene is game screen 
package gui;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.AudioUtility;
import logic.EndgameException;
import logic.GameManager;
import logic.ImageUtility;
import model.GameBackground;
import model.IRenderable;
import model.RenderableHolder;

public class GameScreen extends StackPane {
	private Canvas canvas;
	private GameBackground gb;
	private GameManager game;
	private int t = 15;
	public static int screen_width, screen_height;
	private GraphicsContext gc;
	private Thread player, obstacle;

	public GameScreen(GameManager game) {
		super();
		this.canvas = new Canvas(720, 480);
		this.getChildren().add(canvas);
		this.gb = new GameBackground();
		this.game = game;
		this.gc = this.canvas.getGraphicsContext2D();
	}

	public GameManager getgame() {
		return this.game;
	}

	public void paintComponents() {
		gc.clearRect(0, 0, 720, 480);
		gb.draw(gc);
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (!entity.isDestroy()) {
				entity.draw(gc);
			}
		}
		drawScore(gc);
		drawHP(gc);
	}

	public void drawScore(GraphicsContext gc) {
		gc.setLineWidth(2);
		gc.setFill(Color.BLACK);
		Font theFont = Font.font("Agency FB", FontWeight.LIGHT, 50);
		gc.setFont(theFont);
		gc.fillText(GameManager.getPlayerScore(), 340, 50);

	}

	public void drawHP(GraphicsContext gc) {
		if (game.getPlayer().HP == 2) {
			gc.drawImage(ImageUtility.heart, 600, 10, 50, 50);
			gc.drawImage(ImageUtility.heart, 650, 10, 50, 50);
		}
		if (game.getPlayer().HP == 1) {
			gc.drawImage(ImageUtility.heart, 600, 10, 50, 50);
		}

	}

	// reset all of property.
	// clear all thighs form this game before.
	// create new game when it is called.
	public void startgame() {
		game.newPlayer();
		paintComponents();
		game.getPlayer().HP = 2;
		game.getPlayer().SCORE = 0;
		game.setLevel(0);
		;
		game.getPlayer().setDestroy(false);
		AudioUtility.playSound("gamesound");
		player = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!game.getPlayer().isDestroy()) {
						Thread.sleep(t);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								if (game.getPlayer().isGameOveryStyle()
										&& RenderableHolder.instance.getEntities().size() == 1
										&& game.getWait() >= 400) {
									game.getPlayer().setDestroy(true);
								} else if (RenderableHolder.instance.getEntities().size() >= 1) {
									game.updatePlayer();
									gb.updateBackground();
									paintComponents();
								}
								if (game.getPlayer().isDestroy()) {
									player.interrupt();
									obstacle.interrupt();
									while (RenderableHolder.instance.getEntities().size() > 0) {
										game.DestroyEntity();
									}
									try {
										if (game.getPlayer().isGameOveryStyle())
											throw new EndgameException(0);
										else
											throw new EndgameException(1);
									} catch (EndgameException end) {
									}
								}
							}
						});
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		obstacle = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (!game.getPlayer().isDestroy()) {
						Thread.sleep(t);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								game.updateObstacle();
							}
						});
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		});
		obstacle.start();
		player.start();
	}
}