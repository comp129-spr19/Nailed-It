package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Answer;
import main.MainStage;
import main.Question;

public class QuizScreenLayout extends VBox implements EventHandler<ActionEvent> {

	MainStage main;

	private Text questionText, responseText, hintText;
	private Button answerA, answerB, answerC, answerD;
	private Button next, skip, quit, hint;
	private Answer correctAnswer, selectedAnswer;
	private String hintString;

	public QuizScreenLayout(Question question, MainStage main) {
		super();
		this.main = main;

		correctAnswer = question.getCorrectAnswer();
		hintText = new Text("");

		questionText = new Text(question.getQuestion());

		answerA = new Button("A: " + question.getAnswerA());
		answerA.setId("A");
		answerA.setOnAction(this);

		answerB = new Button("B: " + question.getAnswerB());
		answerB.setId("B");
		answerB.setOnAction(this);

		answerC = new Button("C: " + question.getAnswerC());
		answerC.setId("C");
		answerC.setOnAction(this);

		answerD = new Button("D: " + question.getAnswerD());
		answerD.setId("D");
		answerD.setOnAction(this);

		responseText = new Text("");

		next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);
		next.setDisable(true);

		skip = new Button("Skip");
		skip.setId("skip");
		skip.setOnAction(this);

		quit = new Button("Return to Menu");
		quit.setId("quit");
		quit.setOnAction(this);

		hint = new Button("Hint");
		hint.setId("hint");
		hint.setOnAction(this);
		hintString = question.getHint();

		this.getChildren().addAll(questionText, answerA, answerB, answerC, answerD, responseText, next, skip, quit,
				hintText, hint);

		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

	}

	/*
	 * 
	 * Adjust application behavior off of user input
	 * 
	 * @param e User input
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "A":
				selectedAnswer = Answer.ANSWER_A;
				handleSelection();
				break;
			case "B":
				selectedAnswer = Answer.ANSWER_B;
				handleSelection();
				break;
			case "C":
				selectedAnswer = Answer.ANSWER_C;
				handleSelection();
				break;
			case "D":
				selectedAnswer = Answer.ANSWER_D;
				handleSelection();
				break;
			case "next":
				mainStageCommunication();
				main.nextQuestion();
				break;
			case "skip":
				main.nextQuestion();
				break;
			case "quit":
				main.switchToDifficulty();
				break;
			case "hint":
				showHint();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}

	/*
	 * Displays response text based on answer, then disables/enables appropriate
	 * buttons
	 * 
	 */
	private void handleSelection() {
		setResponse();
		next.setDisable(false);
		disableAnswers();
	}

	/*
	 * Disables the full set of answer buttons
	 */
	private void disableAnswers() {
		answerA.setDisable(true);
		answerB.setDisable(true);
		answerC.setDisable(true);
		answerD.setDisable(true);
	}

	/*
	 * Validates answer, then displays to user using responseText
	 */
	private void setResponse() {
		String response = new String("Incorrect.");
		if (selectedAnswer == correctAnswer) {
			response = "Correct.";
		}
		responseText.setText(response);
	}

	/*
	 * Increments correct answer in MainStage if needed
	 */
	private void mainStageCommunication() {
		if (selectedAnswer == correctAnswer) {
			main.incrCorrAnswers();
		}
	}

	private void showHint() {
		hintText.setText(hintString);
	}

	// this is a test comment
}
