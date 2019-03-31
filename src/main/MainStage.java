package main;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainStage extends Application {

	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 600;
	
	private Stage stage;
	private QuizEventHandler quizAction;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("100% incomplete Dorothy's app");
		
		quizAction = new QuizEventHandler(this);
		
		// declare new difficulty screen and its layout		
		DifficultyScreenLayout diffScreenLayout = new DifficultyScreenLayout(quizAction);
		Scene difficultyScreen = new Scene(diffScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		// add style to this new sheet
		difficultyScreen.getStylesheets().add("cssSheets/diffScreenStyle.css");
		
		Scene scene = difficultyScreen;
		stage.setScene(scene);
		stage.show();
	}


	public void nextQuestion() {
		QuestionScreenLayout qScreenLayout = new QuestionScreenLayout("WHAT?", "No", "Yes", "Kinda", "Only on Tuesdays", quizAction);
		Scene questionScreen = new Scene(qScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		stage.setScene(questionScreen);
	}
}
