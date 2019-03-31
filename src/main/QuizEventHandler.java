package main;

import javafx.event.*;
import javafx.scene.control.Button;

public class QuizEventHandler implements EventHandler<ActionEvent> {
	
	private MainStage stage;
	
	public QuizEventHandler(MainStage stage) {
		super();
		this.stage = stage;
	}
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "easy":
				System.out.println("easy chosen");
				break;
			case "medium":
				System.out.println("medium chosen");
				break;
			case "hard":
				System.out.println("hard chosen");
				break;
			case "next":
				stage.nextQuestion();
				break;
			case "quit":
				//mainScreen.switchToHome();
				break;
			default:
				System.out.println("Hey the QuizEventHandler has no idea what the heck you just did");
			}
		}
	}

}
