package QuizLayouts;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import main.Answer;
import main.MainStage;
import main.Question;

public class QuizScreenLayout extends GridPane implements EventHandler<ActionEvent> {
	public static final int MAX_ATTEMPTS = 2;
	public static final String FILEPATH = "file:src/main/resources/";
	public static final String IMAGE_FILEPATH = FILEPATH + "images/";
	public static final String EXPLANATIONS_FILEPATH = FILEPATH + "explanations/";
	
	MainStage main;
	Question question;
	
	int numAttempts;
	boolean complete;
	
	HBox menu;
	Button quit, hint, skip, next, explanation;
	HBox header;
	Text divisor, questionNumber, totalQuestions;
	
	HBox answers;
	Button answerA, answerB, answerC, answerD;
	
	ScrollPane questionScroller;
	VBox questionVBox;
	Image img;
	ImageView image;
	Text questionText, hintText;
	
	ScrollPane answerText;
	
	public QuizScreenLayout(Question question, MainStage main) {
		super();
		this.main = main;
		this.question = question;
		numAttempts = 0;
		complete = false;
		
		createMenu();
		createAnswers();
		createQuestionText();
		createAnswerText();
		
		
	}
	
	private void createMenu() {
		menu = new HBox();
		menu.setPadding(new Insets(10));
		menu.setSpacing(8);
		menu.setAlignment(Pos.BOTTOM_CENTER);
		
		quit = new Button("Quit");
		quit.setId("quit");
		quit.setOnAction(this);
		menu.getChildren().add(quit);

		if (question.getHint() != "None") {
			hint = new Button("Hint");
			hint.setId("hint");
			hint.setOnAction(this);
			menu.getChildren().add(hint);
		}
		
		skip = new Button("Skip");
		skip.setId("skip");
		skip.setOnAction(this);
		menu.getChildren().add(skip);

		next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);
		next.setDisable(true);
		menu.getChildren().add(next);
		
		if (question.getExplanation() != "") {
			explanation = new Button("Explanation");
			explanation.setId("explain");
			explanation.setOnAction(this);
			explanation.setDisable(true);
			menu.getChildren().add(explanation);
		}
		
		header = new HBox();
		divisor = new Text("/");
		questionNumber = new Text("");
		totalQuestions = new Text("");
		header.getChildren().addAll(questionNumber,divisor,totalQuestions);
		menu.getChildren().add(header);
	}

	private void createAnswers() {
		answers = new HBox();
		
		answerA = new Button("A");
		answerA.setId("A");
		answerA.setOnAction(this);
		
		answerB = new Button("B");
		answerB.setId("B");
		answerB.setOnAction(this);
		
		answerC = new Button("C");
		answerC.setId("C");
		answerC.setOnAction(this);
		
		answerD = new Button("D");
		answerD.setId("D");
		answerD.setOnAction(this);
		
		answers.getChildren().addAll(answerA, answerB, answerC, answerD);
	}

	private void createQuestionText() {
		questionScroller = new ScrollPane();
		questionVBox = new VBox();

		hintText = new Text("");
		questionVBox.getChildren().add(hintText);
		
		if (question.getImage() != "") {
			img = new Image(IMAGE_FILEPATH + question.getImage());
			image = new ImageView(img);
			questionVBox.getChildren().add(image);
		}

		questionText = new Text(question.getQuestion());
		questionVBox.getChildren().add(questionText);
		
		questionScroller.setContent(questionVBox);
	}

	private void createAnswerText() {
		
	}


	@Override
	public void handle(ActionEvent e) {
		/*if (e.getSource() instanceof Button) {
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
			case "explain":
				main.switchToExplanation(explanationString);
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}*/
	}
	
	
	/*private void handleSelection() {
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
	 *
	private void disableAnswers() {
		answerA.setDisable(true);
		answerB.setDisable(true);
		answerC.setDisable(true);
		answerD.setDisable(true);
	}

	/*
	 * Validates answer, then displays to user using responseText
	 *
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
	 *
	private void mainStageCommunication() {
		if (selectedAnswer == correctAnswer) {
			main.incrCorrAnswers();
		}
	}

	/*
	 * Sets the hintText variable
	 *
	private void showHint() {
		hintText.setText(hintString);
	}
	
	public void setQuestionCounterText(int qNumber, int totalQuestions) {
		this.questionNumber.setText(""+qNumber);
		this.totalQuestions.setText(""+totalQuestions);
	}*/
}
