package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Layouts.CompletionScreenLayout;
import Layouts.ConfirmDeleteLayout;
import Layouts.DiffScreenRowLayout;
import Layouts.DifficultyScreenLayout;
import Layouts.EditorScreenLayout;
import Layouts.MainMenuScreenLayout;
import Layouts.QuestionEditorLayout;
import Layouts.QuizScreenLayout;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;

/*
 * The window that persists throughout the application. 
 * Handles the scene changes according to application requests
 */
public class MainStage extends Application {

	public static final String STYLE_SOURCE = "cssSheets/style.css";
	public static final String APPLICATION_NAME = "Nailed It"; // TODO: rename the window later
	public static final double SCALE_FACTOR = 2;

	// layouts for different screens throughout application
	private DifficultyScreenLayout diffScreenLayout;
	private CompletionScreenLayout completionScreenLayout;
	private MainMenuScreenLayout mainMenuScreenLayout;
	private EditorScreenLayout editorScreenLayout;
	private QuestionEditorLayout questionEditorLayout;
	private QuestionEditorLayout newQuestionEditorLayout;
	private ConfirmDeleteLayout confirmDeleteLayout;

	private int screenHeight;
	private int screenWidth;
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

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setMinWidth(primaryScreenBounds.getWidth());
		stage.setMaxWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());
		stage.setMinHeight(primaryScreenBounds.getHeight());
		stage.setMaxHeight(primaryScreenBounds.getHeight());

		scene = new Scene(new StackPane(), getScreenHeight(), getScreenWidth());
		scene.getStylesheets().add(STYLE_SOURCE);
		stage.setScene(scene);
		// switchToDifficulty();
		switchToMainMenu();
		stage.show();
	}

	public void switchToMainMenu() {
		mainMenuScreenLayout = new MainMenuScreenLayout(this);
		scene.setRoot(mainMenuScreenLayout);
	}

	public Scale scaleWindowControls() {
		Scale scale = new Scale();
		scale.setX(SCALE_FACTOR);
		scale.setY(SCALE_FACTOR);
		scale.setPivotX(getScreenHeight() / 2);
		scale.setPivotY(getScreenHeight() / 2);

		return scale;
	}

	// TODO: outsource to another file
	/*
	 * Populates questions with the next question
	 */
	public void nextQuestion() {
		if (currentQuestionIndex < questions.size()) {
			System.out.println("Question num " + currentQuestionIndex);
			System.out.println("TOTAL QUESTIONS " + questions.size());
			questions.get(currentQuestionIndex).setQuestionCounterText(currentQuestionIndex+1, questions.size());
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
		CompletionScreenLayout compScreenLayout = new CompletionScreenLayout(this, numCorrAnswers, questions.size());
		scene.setRoot(compScreenLayout);

		// scene.setRoot(completionScreenLayout);

	}

	// TODO: outsource to another file
	/*
	 * Generates questions to be used
	 * 
	 * @param diffSet An array of booleans representing the difficulty settings the
	 * user toggles
	 */
	public void genQuestions(ArrayList<DiffScreenRowLayout> rows) throws IOException {
		questions = GenerateQuestionScreens.generate(rows, this);
		//Collections.shuffle(questions);
		currentQuestionIndex = 0;
		numCorrAnswers = 0;
		nextQuestion();
	}

	public void incrCorrAnswers() {
		numCorrAnswers++;
	}

	public Stage getStage() {
		return this.stage;
	}

	public Scene getScene() {
		return this.scene;
	}

	public void startQuiz() {
		switchToDifficulty();

	}

	public void startEditor() {
		editorScreenLayout = new EditorScreenLayout(this);
		scene.setRoot(editorScreenLayout);
	}

	public void switchToQuestionEditor(String category, Question question) {
		questionEditorLayout = new QuestionEditorLayout(category, question, this, false);
		scene.setRoot(questionEditorLayout);
	}

	public void switchToNewQuestionEditor(String category) {
		Question blank = new Question("", "", "", "", "", "", "", Answer.ANSWER_A);
		newQuestionEditorLayout = new QuestionEditorLayout(category, blank, this, true);
		scene.setRoot(newQuestionEditorLayout);
	}

	public void switchToDeleteConfirm(String category, Question question) {
		confirmDeleteLayout = new ConfirmDeleteLayout(category, question, this);
		scene.setRoot(confirmDeleteLayout);
	}

	public int getScreenHeight() {
		return this.screenHeight;
	}

	public int getScreenWidth() {
		return this.screenWidth;
	}
}
