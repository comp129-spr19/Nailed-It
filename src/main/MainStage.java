package main;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

//TODO Explain what MainStage does
public class MainStage extends Application {

	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 600;

	// different scenes throughout application
	private DifficultyScreenLayout diffScreenLayout;
	private Scene difficultyScreen;
	private CompletionScreenLayout completionScreenLayout;
	private Scene completionScreen;

	private Stage stage;
	private Scene scene;
	private ArrayList<QuizScreenLayout> questions;
	private int currentQuestionIndex;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// stage initialization
		stage = primaryStage;
		stage.setTitle("100% incomplete Dorothy's app"); // TODO: rename the window later

		scene = new Scene(new StackPane(), SCREEN_HEIGHT, SCREEN_WIDTH);
		scene.getStylesheets().add("cssSheets/style.css");
		stage.setScene(scene);

		switchToDifficulty();
		stage.show();
	}

	// TODO: outsource to another file
	public void nextQuestion() {
		if (currentQuestionIndex < questions.size()) {
			scene.setRoot(questions.get(currentQuestionIndex));
			currentQuestionIndex++;
		} else if (currentQuestionIndex == questions.size()) {
			switchToDifficulty();
		}
	}

	public void switchToDifficulty() {
		// declare new difficulty screen, its layout, and its style
		DifficultyScreenLayout diffScreenLayout = new DifficultyScreenLayout(this);
		scene.setRoot(diffScreenLayout);
	}

	public void switchToCompletion() {
		// declare new difficulty screen, its layout, and its style
		CompletionScreenLayout compScreenLayout = new CompletionScreenLayout(this);
		scene.setRoot(compScreenLayout);
	}

	// TODO: do we want this to be in main? why can't we move this to question
	// screen layout, or even it's own class?
	// generate questions to be used.
	public void genQuestions(boolean[] diffSet) {
		questions = Questions.generate(diffSet, this);
		currentQuestionIndex = 0;
		nextQuestion();
	}
}
