//This is InterruptedException, which I create.
package logic;

import main.Main;

public class EndgameException extends InterruptedException {

	private static final long serialVersionUID = 1L;
	private int errorType;

	// create new EndgameException with 2 type.
	// If errorType is 0. It mean game over by player is not die and no monster
	// come out.
	// and scene will changes to BfendingScene. and toggle the boolean variable
	// isGameSceneShown to false if it true.
	// If errorType is 1. It mean game over by player is die.
	// and scene will changes to DeadScene. and toggle the boolean variable
	// isGameSceneShown to false if it true.
	public EndgameException(int errorType) {
		this.errorType = errorType;
		if (this.errorType == 0) {
			System.out.println("GOOD");
			if (Main.instance.isGameSceneShown()) {
				Main.instance.toGameScene();
			}
			System.out.println("GOOD");
			AudioUtility.playSound("bfendingsound");
			Main.instance.toBfendingScene();
			AudioUtility.stopSound("gamesound");
		} else {
			System.out.println("BAD");
			if (Main.instance.isGameSceneShown()) {
				Main.instance.toGameScene();
			}
			System.out.println("BAD");
			AudioUtility.playSound("deadsound");
			System.out.println("BAD");
			Main.instance.toDeadScene();
			System.out.println("BAD");
			AudioUtility.stopSound("gamesound");
		}
	}
}
