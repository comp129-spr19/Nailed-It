package main;
import java.util.ArrayList;

import Layouts.CompletionScreenLayout;
import Layouts.DifficultyScreenLayout;
import Layouts.QuizScreenLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/*
 * The window that persists throughout the application. 
 * Handles the scene changes according to application requests
 */
public class MainStage extends Application {

	public static final String STYLE_SOURCE = "cssSheets/style.css";
	public static final String APPLICATION_NAME = "Dorothy's Application"; // TODO: rename the window later
	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 600;

	// layouts for different screens throughout application
	private DifficultyScreenLayout diffScreenLayout;
	private CompletionScreenLayout completionScreenLayout;

	private Stage stage;
	private Scene scene;

	// quiz-logic variables
	private ArrayList<QuizScreenLayout> questions;
	private int currentQuestionIndex;
	
	// should be removed when refactoring.
	private int numCorrAnswers;

	/*
	 * Application entry point, launches all functions to begin applications
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/*
	 * Initializes application stage and scene formatting on startup, transfers
	 * control to a screen
	 * 
	 * @param primaryStage The main window that will persist throughout the
	 * application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle(APPLICATION_NAME);

		scene = new Scene(new StackPane(), SCREEN_HEIGHT, SCREEN_WIDTH);
		scene.getStylesheets().add(STYLE_SOURCE);
		stage.setScene(scene);

		switchToDifficulty();
		stage.show();
	}

	// TODO: outsource to another file
	/*
	 * Populates questions with the next question
	 */
	public void nextQuestion() {
		if (currentQuestionIndex < questions.size()) {
			scene.setRoot(questions.get(currentQuestionIndex));
			currentQuestionIndex++;
		} else if (currentQuestionIndex == questions.size()) {
			switchToCompletion();
		}
	}

	/*
	 * Switches scene to difficulty screen
	 */
	public void switchToDifficulty() {
		diffScreenLayout = new DifficultyScreenLayout(this);
		scene.setRoot(diffScreenLayout);
	}

	/*
	 * Switches scene to completion screen
	 */
	public void switchToCompletion() {

		// declare new difficulty screen, its layout, and its style
		CompletionScreenLayout compScreenLayout = new CompletionScreenLayout(this,numCorrAnswers,questions.size());
		scene.setRoot(compScreenLayout);
		

		//scene.setRoot(completionScreenLayout);

	}

	/*
	 * Initializer for the screen layouts in the application
	 */
	/*
	public void initializeScreenLayouts() {
		diffScreenLayout = new DifficultyScreenLayout(this);
		completionScreenLayout = new CompletionScreenLayout(this);
	}  */

	// TODO: outsource to another file
	/*
	 * Generates questions to be used
	 * 
	 * @param diffSet An array of booleans representing the difficulty settings the
	 * user toggles
	 */
	public void genQuestions(boolean[] diffSet) {
		questions = Questions.generate(diffSet, this);
		currentQuestionIndex = 0;
		numCorrAnswers = 0;
		nextQuestion();
	}
	
	public void incrCorrAnswers() {
		numCorrAnswers++;
	}
}
