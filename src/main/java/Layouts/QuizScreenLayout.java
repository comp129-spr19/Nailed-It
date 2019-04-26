package Layouts;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Answer;
import main.MainStage;
import main.Question;

public class QuizScreenLayout extends BorderPane implements EventHandler<ActionEvent> {
	public static final int MAX_ATTEMPTS = 2;
	public static final String IMAGE_FILEPATH = "file:src/main/resources/images/";

	MainStage main;

	private Text questionText, responseText, hintText,headerOne,headerTwo,questionNumber,totalQuestions;
	private Button answerA, answerB, answerC, answerD;
	private Button next, skip, quit, hint;
	private Answer correctAnswer, selectedAnswer;
	private String hintString;
	private int numAttempts;
	private boolean complete;
	private HBox header;
	private ImageView picture;

	public QuizScreenLayout(Question question, MainStage main) {
		super();
		this.main = main;
		numAttempts = 0;
		complete = false;

		header = new HBox();
		headerOne = new Text("Question ");
		headerTwo = new Text(" out of ");
		questionNumber = new Text("");
		totalQuestions = new Text("");
		header.getChildren().addAll(headerOne,questionNumber,headerTwo,totalQuestions);
		this.setTop(header);
		
		
		
		
		GridPane answerSelection = setMultipleChoiceOptions(question);
		this.setBottom(answerSelection);

		/*************/
		HBox hbox = new HBox();
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(8);

		quit = new Button("Quit");
		quit.setId("quit");
		quit.setOnAction(this);

		skip = new Button("Skip");
		skip.setId("skip");
		skip.setOnAction(this);

		next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);
		next.setDisable(true);

		if ("NO HINTS WITH GEEKSFORGEEKS".equals(question.getHint())) {
			hint = null; // because hint is null, do not add to hbox
			hbox.getChildren().addAll(quit, skip, next);
		} else {
			hint = new Button("Hint");
			hint.setId("hint");
			hint.setOnAction(this);
			hintString = question.getHint();
			hbox.getChildren().addAll(quit, hint, skip, next);
		}

		hbox.setAlignment(Pos.BOTTOM_CENTER);

		/*************/

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		vbox.setMaxWidth(600);
		
		if (question.getImage() != "") {
			Image image = new Image(IMAGE_FILEPATH + question.getImage());
			picture = new ImageView(image);
			picture.setFitHeight(100);
			picture.setFitWidth(100);
			picture.setVisible(true);
		}

		Label questionText = new Label(question.getQuestion());
		questionText.setWrapText(true);
		questionText.setContentDisplay(ContentDisplay.CENTER);

		hintText = new Text("");
		correctAnswer = question.getCorrectAnswer();
		responseText = new Text("");

		vbox.setAlignment(Pos.BOTTOM_CENTER);

		vbox.getChildren().addAll(picture, questionText, hintText, responseText, hbox);

		this.setCenter(vbox);
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
				answerA.setDisable(true);
				handleSelection();
				break;
			case "B":
				selectedAnswer = Answer.ANSWER_B;
				answerB.setDisable(true);
				handleSelection();
				break;
			case "C":
				selectedAnswer = Answer.ANSWER_C;
				answerC.setDisable(true);
				handleSelection();
				break;
			case "D":
				selectedAnswer = Answer.ANSWER_D;
				answerD.setDisable(true);
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
	 * 
	 */
	private GridPane setMultipleChoiceOptions(Question question) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(0);
		grid.setHgap(0);

		answerA = new Button("A: " + question.getAnswerA());
		answerA.setId("A");
		answerA.setOnAction(this);
		answerA.setPrefSize(300, 75);
		GridPane.setConstraints(answerA, 0, 0);

		answerB = new Button("B: " + question.getAnswerB());
		answerB.setId("B");
		answerB.setPrefSize(300, 75);
		answerB.setOnAction(this);
		GridPane.setConstraints(answerB, 1, 0);

		answerC = new Button("C: " + question.getAnswerC());
		answerC.setId("C");
		answerC.setPrefSize(300, 75);
		answerC.setOnAction(this);
		GridPane.setConstraints(answerC, 0, 1);

		answerD = new Button("D: " + question.getAnswerD());
		answerD.setId("D");
		answerD.setPrefSize(300, 75);
		answerD.setOnAction(this);
		GridPane.setConstraints(answerD, 1, 1);

		grid.getChildren().addAll(answerA, answerB, answerC, answerD);

		return grid;
	}

	/*
	 * Displays response text based on answer, then disables/enables appropriate
	 * buttons
	 * 
	 */
	private void handleSelection() {
		numAttempts++;
		complete = (selectedAnswer == correctAnswer) || (numAttempts >= MAX_ATTEMPTS);
		setResponse();
		if (complete) {
			next.setDisable(false);
			skip.setDisable(true);
			disableAnswers();
		}
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
		if (complete) {
			response += " Answer was: " + correctAnswer.toString();
		}
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

	/*
	 * Sets the hintText variable
	 */
	private void showHint() {
		hintText.setText(hintString);
	}
	
	public void setQuestionCounterText(int qNumber, int totalQuestions) {
		this.questionNumber.setText(""+qNumber);
		this.totalQuestions.setText(""+totalQuestions);
	}
}
