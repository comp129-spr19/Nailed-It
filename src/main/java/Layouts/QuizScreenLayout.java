package Layouts;

import java.io.File;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import main.Answer;
import main.MainStage;
import main.Question;

public class QuizScreenLayout extends BorderPane implements EventHandler<ActionEvent> {
	public static final int MAX_ATTEMPTS = 2;
	public static final String IMAGE_FILEPATH = "file:src/main/resources/images/";

	MainStage main;

	private Text questionText, responseText, hintText,divisor,questionNumber,totalQuestions, attemptedString;
	private Button answerA, answerB, answerC, answerD;
	private Button next, skip, quit, hint, explanation;
	private Answer correctAnswer, selectedAnswer;
	private String hintString, explanationString;
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
		divisor = new Text("/");
		questionNumber = new Text("");
		totalQuestions = new Text("");
		attemptedString = new Text("");
		header.getChildren().addAll(questionNumber,divisor,totalQuestions);
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

		hbox.getChildren().add(quit);
		if ("NO HINTS WITH GEEKSFORGEEKS".equals(question.getHint())) {
			hint = null;
			// because hint is null, do not add to hbox
		} else {
			hint = new Button("Hint");
			hint.setId("hint");
			hint.setOnAction(this);
			hintString = question.getHint();
			hbox.getChildren().add(hint);
		}
		hbox.getChildren().addAll(skip, next);
		
		if (question.getExplanation() != "") {
			explanation = new Button("Explanation");
			explanation.setId("explain");
			explanation.setOnAction(this);
			explanation.setDisable(true);
			explanationString = question.getExplanation();
			hbox.getChildren().add(explanation);
		}
		hbox.getChildren().addAll(header, attemptedString);

		hbox.setAlignment(Pos.BOTTOM_CENTER);

		/*************/

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		
		if (question.getImage() != "") {
			Image image = new Image(IMAGE_FILEPATH + question.getImage());
			picture = new ImageView(image);
			picture.setVisible(true);
			vbox.getChildren().add(picture);
		}

		Label questionText = new Label(question.getQuestion());
		questionText.setWrapText(true);
		questionText.setContentDisplay(ContentDisplay.CENTER);

		hintText = new Text("");
		correctAnswer = question.getCorrectAnswer();
		responseText = new Text("");

		vbox.setAlignment(Pos.BOTTOM_CENTER);

		vbox.getChildren().addAll(hbox, new ScrollPane(questionText), hintText, responseText);

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
				main.switchToMainMenu();
				break;
			case "hint":
				showHint();
				break;
			case "explain":
				main.switchToExplanation(explanationString);
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
		double height = 75;
		double width = main.getScreenWidth() / 2;

		answerA = new Button("A: " + question.getAnswerA());
		answerA.setId("A");
		setUpButton(answerA, height, width);
		GridPane.setConstraints(answerA, 0, 0);

		answerB = new Button("B: " + question.getAnswerB());
		answerB.setId("B");
		setUpButton(answerB, height, width);
		GridPane.setConstraints(answerB, 1, 0);

		answerC = new Button("C: " + question.getAnswerC());
		answerC.setId("C");
		setUpButton(answerC, height, width);
		GridPane.setConstraints(answerC, 0, 1);

		answerD = new Button("D: " + question.getAnswerD());
		answerD.setId("D");
		setUpButton(answerD, height, width);
		GridPane.setConstraints(answerD, 1, 1);

		grid.getChildren().addAll(answerA, answerB, answerC, answerD);

		return grid;
	}
	
	private void setUpButton(Button button, double height, double width) {
		button.setMaxHeight(height);
		button.setMinHeight(height);
		button.setMaxWidth(width);
		button.setMinWidth(width);
		button.setOnAction(this);
	}

	/*
	 * Displays response text based on answer, then disables/enables appropriate
	 * buttons
	 * 
	 */
	private void handleSelection() {
		numAttempts++;
		attemptedString.setText("Attempted: " + numAttempts + "/" + MAX_ATTEMPTS);
		complete = (selectedAnswer == correctAnswer) || (numAttempts >= MAX_ATTEMPTS);
		setResponse();
		if (complete) {
			next.setDisable(false);
			explanation.setDisable(false);
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
