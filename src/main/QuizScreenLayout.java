package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class QuizScreenLayout extends VBox implements EventHandler<ActionEvent> {

	MainStage main;

	public QuizScreenLayout(String question, String ansrA, String ansrB, String ansrC, String ansrD, MainStage main) {
		super();
		this.main = main;

		Text questionText = new Text(question);

		ToggleGroup answers = new ToggleGroup();

		RadioButton answerA = new RadioButton("A: " + ansrA);
		answerA.setId("A");
		answerA.setToggleGroup(answers);
		answerA.setOnAction(this);

		RadioButton answerB = new RadioButton("B: " + ansrB);
		answerB.setId("B");
		answerB.setToggleGroup(answers);
		answerB.setOnAction(this);

		RadioButton answerC = new RadioButton("C: " + ansrC);
		answerC.setId("C");
		answerC.setToggleGroup(answers);
		answerC.setOnAction(this);

		RadioButton answerD = new RadioButton("D: " + ansrD);
		answerD.setId("D");
		answerD.setToggleGroup(answers);
		answerD.setOnAction(this);

		Button next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);

		Button skip = new Button("Skip");
		skip.setId("skip");
		skip.setOnAction(this);

		Button quit = new Button("Return to Menu");
		quit.setId("quit");
		quit.setOnAction(this);

		this.getChildren().addAll(questionText, answerA, answerB, answerC, answerD, next, skip, quit);

		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

	}

	/*
	 * Adjust application behavior off of user input
	 * 
	 * @param e User input
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "next":
				main.switchToCompletion();
				break;
			case "skip":
				main.switchToCompletion();
				break;
			case "quit":
				main.switchToDifficulty();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}

}
