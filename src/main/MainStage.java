package main;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.control.*;
import javafx.stage.*;

public class MainStage extends Application {

	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 600;

	// different scenes throughout application
	private DifficultyScreenLayout diffScreenLayout;
	private Scene difficultyScreen;

	private Stage stage;
	private Scene scene;
	
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
		QuestionScreenLayout qScreenLayout = new QuestionScreenLayout("WHAT?", "No", "Yes", "Kinda", "Only on Tuesdays", this);
		scene.setRoot(qScreenLayout);
	}
	
	public void switchToDifficulty() {
		// declare new difficulty screen, its layout, and its style		
		DifficultyScreenLayout diffScreenLayout = new DifficultyScreenLayout(this);
		scene.setRoot(diffScreenLayout);
	}
}
