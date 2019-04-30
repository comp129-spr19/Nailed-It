package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import main.Answer;
import main.MainStage;
import main.Question;

public class QuizScreenLayout extends GridPane implements EventHandler<ActionEvent> {
	public static final String FILEPATH = "file:src/main/resources/";
	public static final String IMAGE_FILEPATH = FILEPATH + "images/";
	public static final String EXPLANATIONS_FILEPATH = FILEPATH + "explanations/";
	public static final int MAX_ATTEMPTS = 2;
	public static final double TOP_BAR_RATIO = 1.0 / 10.0;
	public static final double SCROLLBARS_HEIGHT_OFF = 20;
	public static final double LEFT_RATIO = 2.0 / 3.0;
	public static final double RIGHT_RATIO = 1.0 - LEFT_RATIO;
	public static final double BUTTON_GAP_RATIO = 1.0 / 155.0;
	public static final double BUTTON_WIDTH_RATIO = 1.0 / 8.0;

	private MainStage main;
	private Question question;

	private int numAttempts;
	private boolean complete;
	private Answer selectedAnswer;

	private double leftWidth, rightWidth, topHeight, bottomHeight;

	private HBox menu, answers;
	private VBox answerVBox, questionVBox;
	private ScrollPane questionScroller, answerScroller;
	private Button quit, hint, skip, next, answerA, answerB, answerC, answerD, questionNumber, topic, attempted;
	private Text responseText, answersText, questionText, hintText;
	private Image img, exp;
	ImageView image, explanation;

	public QuizScreenLayout(Question question, MainStage main) {
		super();
		this.main = main;
		this.question = question;
		numAttempts = 0;
		complete = false;

		this.setAlignment(Pos.CENTER);

		leftWidth = main.getScreenWidth() * LEFT_RATIO;
		rightWidth = main.getScreenWidth() * RIGHT_RATIO;
		topHeight = main.getScreenHeight() * TOP_BAR_RATIO;
		bottomHeight = main.getScreenHeight() - topHeight - SCROLLBARS_HEIGHT_OFF;

		createMenu();
		setUpHBox(menu, leftWidth, topHeight);
		GridPane.setConstraints(menu, 0, 0);

		createAnswers();
		setUpHBox(answers, rightWidth, topHeight);
		GridPane.setConstraints(answers, 1, 0);

		createQuestionScroller();
		setUpScrollPane(questionScroller, leftWidth, bottomHeight);
		GridPane.setConstraints(questionScroller, 0, 1);

		createAnswerScroller();
		setUpScrollPane(answerScroller, rightWidth, bottomHeight);
		GridPane.setConstraints(answerScroller, 1, 1);

		this.getChildren().addAll(menu, answers, questionScroller, answerScroller);
	}

	private void setUpHBox(HBox box, double width, double height) {
		box.setMaxHeight(height);
		box.setMinHeight(height);
		box.setMaxWidth(width);
		box.setMinWidth(width);
		box.setId("topbar");
	}

	private void setUpScrollPane(ScrollPane pane, double width, double height) {
		pane.setMaxHeight(height);
		pane.setMinHeight(height);
		pane.setMaxWidth(width);
		pane.setMinWidth(width);
	}

	private void createMenu() {
		menu = new HBox();
		menu.setSpacing(main.getScreenWidth() * BUTTON_GAP_RATIO);
		menu.setAlignment(Pos.CENTER);

		questionNumber = new Button("");
		questionNumber.setId("staticbutton");
		questionNumber.setDisable(true);
		menu.getChildren().add(questionNumber);

		hint = new Button("Hint");
		hint.setId("hint");
		hint.setOnAction(this);
		if (question.getHint().equals("None")) {
			hint.setDisable(true);
		}

		menu.getChildren().add(hint);

		skip = new Button("Skip");
		skip.setId("skip");
		skip.setOnAction(this);
		menu.getChildren().add(skip);

		next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);
		next.setDisable(true);
		menu.getChildren().add(next);

		if (!question.getTopic().equals("None")) {
			topic = new Button("#" + question.getTopic());
			topic.setId("staticbutton");
			topic.setDisable(true);
			topic.setMaxWidth(BUTTON_WIDTH_RATIO * main.getScreenWidth());
			menu.getChildren().add(topic);
		}

		attempted = new Button("Tries: 0/" + MAX_ATTEMPTS);
		attempted.setId("staticbutton");
		attempted.setDisable(true);
		menu.getChildren().add(attempted);

		quit = new Button("Quit");
		quit.setId("quit");
		quit.setOnAction(this);
		menu.getChildren().add(quit);
	}

	private void createAnswers() {
		answers = new HBox();
		answers.setSpacing(main.getScreenWidth() * BUTTON_GAP_RATIO);
		answers.setAlignment(Pos.CENTER);

		double buttonWidth = rightWidth / 4 - (main.getScreenWidth() * BUTTON_GAP_RATIO);

		answerA = new Button("A");
		answerA.setId("A");
		answerA.setOnAction(this);

		setUpButton(answerA, buttonWidth);

		answerB = new Button("B");
		answerB.setId("B");
		answerB.setOnAction(this);

		setUpButton(answerB, buttonWidth);

		answerC = new Button("C");
		answerC.setId("C");
		answerC.setOnAction(this);
		setUpButton(answerC, buttonWidth);

		answerD = new Button("D");
		answerD.setId("D");
		answerD.setOnAction(this);
		setUpButton(answerD, buttonWidth);
		answers.getChildren().addAll(answerA, answerB, answerC, answerD);
	}

	private void setUpButton(Button button, double width) {
		button.setMaxWidth(width);
		button.setMinWidth(width);
	}

	private void createQuestionScroller() {
		questionScroller = new ScrollPane();
		questionVBox = new VBox();

		if (!question.getImage().equals("")) {
			img = new Image(IMAGE_FILEPATH + question.getImage());
			image = new ImageView(img);
			questionVBox.getChildren().add(image);
		}

		questionText = new Text(question.getQuestion());
		questionText.setId("question");
		questionText.wrappingWidthProperty().bind(questionScroller.widthProperty());
		questionVBox.getChildren().add(questionText);

		questionScroller.setContent(questionVBox);
	}

	private void createAnswerScroller() {
		answerScroller = new ScrollPane();
		answerVBox = new VBox();

		responseText = new Text("");
		hintText = new Text("");

		String answersString = "A: " + question.getAnswerA() + "\n\n";
		answersString += "B: " + question.getAnswerB() + "\n\n";
		answersString += "C: " + question.getAnswerC() + "\n\n";
		answersString += "D: " + question.getAnswerD() + "\n\n";

		answersText = new Text(answersString);
		answersText.setId("answers");
		answersText.wrappingWidthProperty().bind(answerScroller.widthProperty());

		answerVBox.getChildren().addAll(responseText, hintText, answersText);
		answerScroller.setContent(answerVBox);
	}

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
				main.switchToSelection();
				break;
			case "hint":
				showHint();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}

	private void handleSelection() {
		numAttempts++;
		attempted.setText("Tries: " + numAttempts + "/" + MAX_ATTEMPTS);
		complete = (selectedAnswer.equals(question.getCorrectAnswer())) || (numAttempts >= MAX_ATTEMPTS);
		setResponse();
		if (complete) {
			next.setDisable(false);
			skip.setDisable(true);
			disableAnswers();
			showExplanation();
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
		if (selectedAnswer == question.getCorrectAnswer()) {
			String response = "Correct!";
			responseText.setText(response);
			responseText.setId("correct");
		} else if (selectedAnswer != question.getCorrectAnswer() && complete) {
			String response = "Answer " + selectedAnswer + " is incorrect. Answer was: "
					+ question.getCorrectAnswer().toString();
			responseText.setText(response);
			responseText.setId("incorrect");
		} else if (selectedAnswer != question.getCorrectAnswer()) {
			String response = "Answer " + selectedAnswer + " is incorrect.";
			responseText.setText(response);
			responseText.setId("incorrect");
		} else {
			String response = "\n";
		}
		responseText.setTextAlignment(TextAlignment.CENTER);
	}

	/*
	 * Increments correct answer in MainStage if needed
	 */
	private void mainStageCommunication() {
		if (selectedAnswer == question.getCorrectAnswer()) {
			main.incrCorrAnswers();
		}
	}

	/*
	 * Sets the hintText variable
	 */
	private void showHint() {
		hintText.setText("HINT: " + question.getHint() + "\n");
		hintText.setId("hintText");
	}

	private void showExplanation() {
		if (question.getExplanation().equals("")) {
			return;
		}
		exp = new Image(EXPLANATIONS_FILEPATH + question.getExplanation());
		explanation = new ImageView(exp);
		answerVBox.getChildren().add(explanation);
	}

	public void setQuestionCounterText(int qNumber, int totalQuestions) {
		this.questionNumber.setText("Question " + qNumber + "/" + totalQuestions);
	}
}
