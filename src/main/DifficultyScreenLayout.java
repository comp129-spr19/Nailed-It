package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

// Window = Stage
// Content in Window = Scene
public class DifficultyScreenLayout extends HBox implements EventHandler<ActionEvent> {

	MainStage main;
	ToggleButton easy, medium, hard;
	Button next;

	DifficultyScreenLayout(MainStage main) {
		super();
		this.main = main;

		easy = new ToggleButton("EASY");
		easy.setId("easy");
		easy.setOnAction(this);

		medium = new ToggleButton("MEDIUM");
		medium.setId("medium");
		medium.setOnAction(this);

		hard = new ToggleButton("HARD");
		hard.setId("hard");
		hard.setOnAction(this);

		next = new Button("CONTINUE");
		next.setId("next");
		next.setDisable(true);
		next.setOnAction(this);

		this.getChildren().addAll(easy, medium, hard, next);

		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);

	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "next":
				main.genQuestions(getDifficultySet());
				break;
			default:
				System.out.println("Hey what did you just do it's not in the EventHandler");
			}
		} else if (e.getSource() instanceof ToggleButton) {
			if (canEnableContButton()) {
				next.setDisable(false);
			} else {
				next.setDisable(true);
			}
		}
	}

	public boolean[] getDifficultySet() {
		boolean[] difficultySet = { easy.isSelected(), medium.isSelected(), hard.isSelected() };
		return difficultySet;
	}

	/*
	 * Function: Determines whether a difficulty button it selected 
	 * Parameters: None
	 * Return Value: Returns true if any of the difficulty buttons are toggled
	 * Returns false if none of the difficulty buttons are toggled
	 */
	public boolean canEnableContButton() {
		boolean[] difficultySet = getDifficultySet();
		for (int i = 0; i < difficultySet.length; i++) {
			if (difficultySet[i]) {
				return true;
			}
		}
		return false;
	}

}
