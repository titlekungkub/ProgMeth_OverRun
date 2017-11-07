// This class is audio class
// It load sound for play in this game.
package logic;

import javafx.scene.media.AudioClip;

public class AudioUtility {

	private static AudioClip sound_collect, sound_jump, configsound, gamesound, endingsound, entersound, menusound,
			deadsound, hitsound, bfendingsound;
	static {
		loadResource();
	}

	public static void loadResource() {
		sound_collect = new AudioClip(ClassLoader.getSystemResource("sound/collect.wav").toString());
		sound_jump = new AudioClip(ClassLoader.getSystemResource("sound/jump.wav").toString());
		configsound = new AudioClip(ClassLoader.getSystemResource("sound/configsound.mp3").toString());
		gamesound = new AudioClip(ClassLoader.getSystemResource("sound/gamesound.mp3").toString());
		endingsound = new AudioClip(ClassLoader.getSystemResource("sound/ending.mp3").toString());
		entersound = new AudioClip(ClassLoader.getSystemResource("sound/press.wav").toString());
		menusound = new AudioClip(ClassLoader.getSystemResource("sound/MenuSound.wav").toString());
		hitsound = new AudioClip(ClassLoader.getSystemResource("sound/hit.wav").toString());
		deadsound = new AudioClip(ClassLoader.getSystemResource("sound/deadsound.wav").toString());
		bfendingsound = new AudioClip(ClassLoader.getSystemResource("sound/bfendingsound.mp3").toString());
	}

	public static void playSound(String identifier) {
		if (identifier.equals("collect")) {
			sound_collect.setVolume(0.2);
			sound_collect.play();
		}
		if (identifier.equals("jump")) {
			sound_jump.setVolume(0.5);
			sound_jump.play();
		}
		if (identifier.equals("enter")) {
			entersound.setVolume(0.5);
			entersound.play();
		}
		if (identifier.equals("configsound")) {
			configsound.setVolume(0.6);
			configsound.play();
		}
		if (identifier.equals("gamesound")) {
			gamesound.setVolume(0.3);
			gamesound.play();
		}
		if (identifier.equals("endingsound")) {
			endingsound.setVolume(0.4);
			endingsound.play();
		}
		if (identifier.equals("menusound")) {
			menusound.setVolume(0.3);
			menusound.play();
		}
		if (identifier.equals("deadsound")) {
			deadsound.setVolume(0.5);
			deadsound.play();
		}
		if (identifier.equals("hitsound")) {
			hitsound.setVolume(0.5);
			hitsound.play();
		}
		if (identifier.equals("bfendingsound")) {
			bfendingsound.setVolume(0.5);
			bfendingsound.play();
		}
	}

	public static void stopSound(String identifier) {
		if (identifier.equals("collect")) {
			sound_collect.stop();
		}
		if (identifier.equals("jump")) {
			sound_jump.stop();
		}
		if (identifier.equals("configsound")) {
			configsound.stop();
		}
		if (identifier.equals("gamesound")) {
			gamesound.stop();

		}
		if (identifier.equals("endingsound")) {
			endingsound.stop();

		}
		if (identifier.equals("bfendingsound")) {
			bfendingsound.stop();

		}
	}
}
