// This is logic class of game.
package logic;

import model.*;

public class GameManager {

	private int level = 0;
	private static PlayerChibi player;
	private int countNextMonster, countNextKabigon, countNextDragon = 0;
	private int generatCoin = 0;
	private int wait = 0;

	public void newPlayer() {
		player = new PlayerChibi(50, 350);
		addEntity(player);
		this.wait = 0;
	}

	private void addEntity(IRenderable entity) {
		RenderableHolder.getInstance().add(entity);
	}

	// check if player jump.check is player strike monster?.check is player
	// collect coin?.
	public void updatePlayer() {
		player.checkJump();
		checkStrikeMonster();
		checkCollectCoin();
	}

	// generate coin if there are obstacle come out.
	// generate obstacle
	// there are 4 waves
	// wave 1 : 10 monster obstacle with can avoid by a jump.
	// wave 2 : 15 monster obstacle with sometimes must avoid by double jump.
	// wave 3 : 25 monster obstacle and kabigon obstacle.
	// wave 4 : 50 monster obstacle and kabigon obstacle and dragon obstacle.
	public void updateObstacle() {
		moveObstacle();
		outOfScene();
		removeDestroyEntity();
		if (!player.isGameOveryStyle() && this.wait == 0) {
			if (generatCoin == 50) {
				Coin coin = new Coin(740, 175);
				addEntity(coin);
			} else if (generatCoin == 100) {
				Coin coin = new Coin(740, 225);
				addEntity(coin);
				generatCoin = 0;
			}
			generatCoin++;
		}
		if (level < 10) {
			int x = (int) Math.floor(Math.random() * 1000);
			if (x < 50 && countNextMonster > 70) {
				ObstacleMonster dog = new ObstacleMonster(740, 375);
				addEntity(dog);
				level++;
				countNextMonster = 0;
			} else {
				countNextMonster++;
			}
		}
		if (level >= 10 && level < 25) {
			int x = (int) Math.floor(Math.random() * 1000);
			if (x < 10 && countNextMonster > 15) {
				ObstacleMonster dog = new ObstacleMonster(740, 375);
				addEntity(dog);
				level++;
				countNextMonster = 0;
			} else {
				countNextMonster++;
			}
		}

		if (level >= 25 && level < 50) {
			int x = (int) Math.floor(Math.random() * 1000);
			if (x < 10 && countNextMonster > 15 && countNextKabigon > 70) {
				ObstacleMonster dog = new ObstacleMonster(740, 375);
				addEntity(dog);
				level++;
				countNextMonster = 0;
			} else if (10 < x && x < 20 && countNextMonster > 15 && countNextKabigon > 70) {
				ObstacleKabigon kabigon = new ObstacleKabigon(740, 325);
				addEntity(kabigon);
				level++;
				countNextKabigon = 0;
			} else {
				countNextKabigon++;
				countNextMonster++;
			}
		}
		if (level >= 50 && level < 100) {
			int x = (int) Math.floor(Math.random() * 1000);
			if (x < 25 && x >= 15 && countNextDragon > 100) {
				ObstacleDragon dragon = new ObstacleDragon(740, 50);
				addEntity(dragon);
				level++;
				countNextDragon = 0;
			}
			if (x < 10 && countNextMonster > 15 && countNextKabigon > 70) {
				ObstacleMonster dog = new ObstacleMonster(740, 375);
				addEntity(dog);
				level++;
				countNextMonster = 0;
			} else if (10 < x && x < 20 && countNextMonster > 15 && countNextKabigon > 70) {
				ObstacleKabigon kabigon = new ObstacleKabigon(740, 325);
				addEntity(kabigon);
				level++;
				countNextKabigon = 0;
			} else {
				countNextKabigon++;
				countNextMonster++;
				countNextDragon++;
			}
		}
		if (level >= 400) {
			player.setGameOveryStyle(true);
			this.wait++;
			if (this.wait > 400) {
				this.wait = 0;
			}
		}
	}

	public int getWait() {
		return wait;
	}

	private void removeDestroyEntity() {
		for (int i = RenderableHolder.getInstance().getEntities().size() - 1; i >= 0; i--) {
			if (RenderableHolder.getInstance().getEntities().get(i).isDestroy()) {
				if (!(((Entity) RenderableHolder.getInstance().getEntities().get(i)) instanceof PlayerChibi)) {
					RenderableHolder.getInstance().getEntities().remove(i);
				}
			}
		}
	}

	// destroy all of entity
	public void DestroyEntity() {

		for (int i = RenderableHolder.getInstance().getEntities().size() - 1; i >= 0; i--) {
			RenderableHolder.getInstance().getEntities().remove(i);
		}
		player.checkJump();
	}

	private void moveObstacle() {
		for (IRenderable e : RenderableHolder.getInstance().getEntities())
			if (!(e instanceof PlayerChibi) && !(e instanceof ObstacleDragon)) {
				((Entity) e).setX(((Entity) e).getX() - 5);
			} else if (!(e instanceof PlayerChibi) && (e instanceof ObstacleDragon)) {
				((Entity) e).setX(((Entity) e).getX() - 7);
			}
	}

	private void checkStrikeMonster() {
		if (!player.isImmune()) {
			for (IRenderable entity : RenderableHolder.instance.getEntities()) {
				if (!(entity instanceof PlayerChibi) && !(entity instanceof Coin)) {
					Entity e = (Entity) entity;
					if (strikeMonster(e) && !e.isDestroy() && e instanceof ObstacleMonster) {
						player.dealHP();
						player.setImmune(true);
						e.setDestroy(true);
						AudioUtility.playSound("hitsound");
						System.out.println("HIT!!!");
					}
					if (strikeKabigon(e) && !e.isDestroy() && e instanceof ObstacleKabigon) {
						player.dealHP();
						player.setImmune(true);
						e.setDestroy(true);
						AudioUtility.playSound("hitsound");
						System.out.println("HIT!!!");
					}
					if (strikeDragon(e) && !e.isDestroy() && e instanceof ObstacleDragon) {
						player.dealHP();
						player.setImmune(true);
						e.setDestroy(true);
						AudioUtility.playSound("hitsound");
						System.out.println("HIT!!!");
					}
				}
			}
		}
	}

	private void checkCollectCoin() {
		for (IRenderable entity : RenderableHolder.instance.getEntities()) {
			if (entity instanceof Coin) {
				Entity e = (Entity) entity;
				if (collectCoin(e) && !e.isDestroy()) {
					player.increaseScore(10);
					e.setDestroy(true);
					AudioUtility.playSound("collect");
					System.out.println("COLLECT COIN!!!");
				}
			}
		}
	}

	private boolean strikeMonster(Entity e) {
		return (e.getX() + 6 > player.getX() - 35 && e.getX() + 6 < player.getX() + 60)
				&& (e.getY() + 15 > player.getY() - 30 && e.getY() + 15 < player.getY() + 70);
	}

	private boolean strikeKabigon(Entity e) {
		return (e.getX() + 20 > player.getX() - 55 && e.getX() + 20 < player.getX() + 70)
				&& (e.getY() + 20 > player.getY() - 70 && e.getY() + 20 < player.getY() + 60);
	}

	private boolean strikeDragon(Entity e) {
		return (e.getX() + 12 > player.getX() - 122 && e.getX() + 12 < player.getX() + 70)
				&& (e.getY() + 40 > player.getY() - 52 && e.getY() + 40 < player.getY() + 60);
	}

	private boolean collectCoin(Entity e) {
		return (e.getX() + 10 > player.getX() - 40 && e.getX() + 10 < player.getX() + 80)
				&& (e.getY() > player.getY() - 40 && e.getY() + 15 < player.getY() + 90);
	}

	public static int getPlayerHP() {
		return player.HP;
	}

	public static String getPlayerScore() {
		return Integer.toString(player.SCORE);
	}

	public void outOfScene() {
		for (IRenderable entity : RenderableHolder.instance.getEntities()) {
			if (((Entity) entity) instanceof Coin) {
				if (((Entity) entity).getX() + 70 < 0) {
					((Entity) entity).setDestroy(true);
					System.out.println("coin");
				}
			}
			if (((Entity) entity) instanceof ObstacleMonster) {
				if (((Entity) entity).getX() + 70 < 0) {
					((Entity) entity).setDestroy(true);
					player.increaseScore(5);
					System.out.println("Monster");
				}
			}
			if (((Entity) entity) instanceof ObstacleKabigon) {
				if (((Entity) entity).getX() + 120 < 0) {
					((Entity) entity).setDestroy(true);
					player.increaseScore(10);
					System.out.println("Kabigon");
				}
			}
			if (((Entity) entity) instanceof ObstacleDragon) {
				if (((Entity) entity).getX() + 190 < 0) {
					((Entity) entity).setDestroy(true);
					player.increaseScore(10);
					System.out.println("Dragon");
				}
			}
		}
	}

	public PlayerChibi getPlayer() {
		return player;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}