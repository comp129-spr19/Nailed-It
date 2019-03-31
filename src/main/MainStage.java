package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainStage extends Application {

	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 600;

	// different scenes throughout application
	private DifficultyScreenLayout diffScreenLayout;
	private Scene difficultyScreen;

	private Stage stage;
	private QuizEventHandler quizAction;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// stage initialization
		stage = primaryStage;
		stage.setTitle("100% incomplete Dorothy's app"); // TODO: rename the window later

		// event handler initialization
		// TODO: insert general event handler
		quizAction = new QuizEventHandler(this);

		// initialize all scenes
		sceneInitializer();

		// boot into default screen
		Scene scene = difficultyScreen; // TODO: change to menu screen
		stage.setScene(scene);
		stage.show();
	}

	// TODO: outsource to another file
	public void nextQuestion() {
		QuestionScreenLayout qScreenLayout = new QuestionScreenLayout("WHAT?", "No", "Yes", "Kinda", "Only on Tuesdays",
				quizAction);
		Scene questionScreen = new Scene(qScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);

		stage.setScene(questionScreen);
	}

	/*
	 * Purpose: Initializes all screens in application Parameters: None Return
	 * Values: None
	 */

	public void sceneInitializer() {
		// declare new menu screen, its layout, and its style

		// declare new difficulty screen, its layout, and its style
		diffScreenLayout = new DifficultyScreenLayout(quizAction);
		difficultyScreen = new Scene(diffScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		difficultyScreen.getStylesheets().add("cssSheets/style.css");

		// declare new quiz screen, its layout, and its style

		// declare new inspiration screen, its layout, and its style

		// declare new resources screen, its layout, and its style
	}
}
