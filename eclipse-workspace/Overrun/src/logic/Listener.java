// This class is get input form play.
package logic;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class Listener {
	private Scene scene;
	public Listener(Scene scene){
		this.scene=scene;
	}

	public void addListener(){	
		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyPressed : " + event.getCode().toString());
				if (!InputUtility.getKeyPressed(event.getCode()))
					InputUtility.setKeyTriggered(event.getCode(), true);
				InputUtility.setKeyPressed(event.getCode(), true);
			}
		});

		this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				System.out.println("KeyReleased : " + event.getCode().toString());
				InputUtility.setKeyPressed(event.getCode(), false);
			}
		});
	}
}
