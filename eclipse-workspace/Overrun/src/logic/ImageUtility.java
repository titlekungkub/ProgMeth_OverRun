// This class is image class
// It load image for draw in this game.
package logic;

import javafx.scene.image.Image;

public class ImageUtility {

	public static Image imagePlayer, imageMonster, imageCoin, background, totoro, player, imageKabigon, endingbg,
			gamebg, dog, imagePlayerImmune, heart, deadbg, stand, story, loader,endtwo,imageDragon;
	static {
		loadResource();
	}

	public static void loadResource() {
		imagePlayer = new Image(ClassLoader.getSystemResource("image/player.gif").toString());
		imageMonster = new Image(ClassLoader.getSystemResource("image/monster.gif").toString());
		gamebg = new Image(ClassLoader.getSystemResource("image/gamebg.png").toString());
		imageCoin = new Image(ClassLoader.getSystemResource("image/coin.gif").toString());
		background = new Image(ClassLoader.getSystemResource("image/pixel.gif").toString());
		totoro = new Image(ClassLoader.getSystemResource("image/totoro.gif").toString());
		player = new Image(ClassLoader.getSystemResource("image/player2.gif").toString());
		imageKabigon = new Image(ClassLoader.getSystemResource("image/kabigon.gif").toString());
		endingbg = new Image(ClassLoader.getSystemResource("image/ending.png").toString());
		dog = new Image(ClassLoader.getSystemResource("image/dog.gif").toString());
		imagePlayerImmune = new Image(ClassLoader.getSystemResource("image/immune.gif").toString());
		heart = new Image(ClassLoader.getSystemResource("image/heart.jpg").toString());
		stand = new Image(ClassLoader.getSystemResource("image/stand.gif").toString());
		deadbg = new Image(ClassLoader.getSystemResource("image/dead.png").toString());
		story = new Image(ClassLoader.getSystemResource("image/story.png").toString());
		loader = new Image(ClassLoader.getSystemResource("image/loader.png").toString());
		endtwo = new Image(ClassLoader.getSystemResource("image/ending2.png").toString());
		imageDragon = new Image(ClassLoader.getSystemResource("image/dragon.gif").toString());
	}
}
