package Layouts;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import main.Answer;
import main.MainStage;

public class QuizScreenLayout extends VBox implements EventHandler<ActionEvent> {

	MainStage main;
	
	private Text questionText, responseText;
	private Button answerA, answerB, answerC, answerD;
	private Button next, skip, quit;
	private Answer correctAnswer, selectedAnswer;
	
	public QuizScreenLayout(String question, String ansrA, String ansrB, String ansrC, String ansrD, Answer answer, MainStage main) {
		super();
		this.main = main;
		
		correctAnswer = answer;
		
		questionText = new Text(question);

		answerA = new Button("A: " + ansrA);
		answerA.setId("A");
		answerA.setOnAction(this);

		answerB = new Button("B: " + ansrB);
		answerB.setId("B");
		answerB.setOnAction(this);

		answerC = new Button("C: " + ansrC);
		answerC.setId("C");
		answerC.setOnAction(this);

		answerD = new Button("D: " + ansrD);
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

		this.getChildren().addAll(questionText, answerA, answerB, answerC, answerD, responseText, next, skip, quit);

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
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}
	
	/*
	 * Displays response text based on answer,
	 * then disables/enables appropriate buttons
	 * 
	 * */
	private void handleSelection() {
		setResponse();
		next.setDisable(false);
		disableAnswers();
	}
	
	/*
	 * Disables the full set of answer buttons
	 * */	
	private void disableAnswers() {
		answerA.setDisable(true);
		answerB.setDisable(true);
		answerC.setDisable(true);
		answerD.setDisable(true);
	}
	
	/*
	 * Validates answer, then displays to user using responseText
	 * */
	private void setResponse() {
		String response = new String("Incorrect.");
		if (selectedAnswer == correctAnswer) {
			response = "Correct.";
		}
		responseText.setText(response);
	}

	/*
	 * Increments correct answer in MainStage if needed
	 * */
	private void mainStageCommunication() {
		if (selectedAnswer == correctAnswer) {
			main.incrCorrAnswers();
		}
	}
}
