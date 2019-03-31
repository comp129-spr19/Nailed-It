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
	private Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("100% incomplete Dorothy's app");
		
		// declare new difficulty screen and its layout		
		DifficultyScreenLayout diffScreenLayout = new DifficultyScreenLayout(this);
		Scene difficultyScreen = new Scene(diffScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		// add style to this new sheet
		difficultyScreen.getStylesheets().add("cssSheets/diffScreenStyle.css");
		
		scene = difficultyScreen;
		stage.setScene(scene);
		stage.show();
	}


	public void nextQuestion() {
		QuestionScreenLayout qScreenLayout = new QuestionScreenLayout("WHAT?", "No", "Yes", "Kinda", "Only on Tuesdays", this);
		scene.setRoot(qScreenLayout);
	}
}
