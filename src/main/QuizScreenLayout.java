package main;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class QuizScreenLayout extends VBox implements EventHandler<ActionEvent> {

	MainStage main;
	
	private RadioButton answerA, answerB, answerC, answerD;
	private Answer correctAnswer;
	private ToggleGroup answers;
	
	public QuizScreenLayout(String question, String ansrA, String ansrB, String ansrC, String ansrD, Answer answer, MainStage main) {
		super();
		this.main = main;
		
		correctAnswer = answer;
		
		Text questionText = new Text(question);

		answers = new ToggleGroup();

		answerA = new RadioButton("A: " + ansrA);
		answerA.setId("A");
		answerA.setToggleGroup(answers);
		answerA.setOnAction(this);

		 answerB = new RadioButton("B: " + ansrB);
		answerB.setId("B");
		answerB.setToggleGroup(answers);
		answerB.setOnAction(this);

		answerC = new RadioButton("C: " + ansrC);
		answerC.setId("C");
		answerC.setToggleGroup(answers);
		answerC.setOnAction(this);

		 answerD = new RadioButton("D: " + ansrD);
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
				checkAnswers();
				main.nextQuestion();
				break;
			case "skip":
				main.nextQuestion();
				break;
			case "quit":
				main.switchToDifficulty();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}
	
	private void checkAnswers() {
		ObservableList<Toggle> answerToggle = answers.getToggles();
		for (int i = 0; i < answers.getToggles().size(); i++) {
			if (answerToggle.get(i).isSelected()) {
				if (validated(i)) {
				main.incrCorrAnswers();
				break;
				}
			}
		}
	}
	
	private boolean validated(int index) {
		Answer selectedAnswer;
		switch(index) {
		case 0:
			selectedAnswer = Answer.ANSWER_A;
			break;
		case 1:
			selectedAnswer = Answer.ANSWER_B;
			break;
		case 2:
			selectedAnswer = Answer.ANSWER_C;
			break;
		case 3:	
			selectedAnswer = Answer.ANSWER_D;
			break;
		default:
			selectedAnswer = null;
		}
		
		return (selectedAnswer == correctAnswer);
	}

}
