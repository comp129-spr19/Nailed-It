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
	private Scene scene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// stage initialization
		stage = primaryStage;
		stage.setTitle("100% incomplete Dorothy's app"); // TODO: rename the window later
		
		// declare new difficulty screen and its layout		
		DifficultyScreenLayout diffScreenLayout = new DifficultyScreenLayout(this);
		Scene difficultyScreen = new Scene(diffScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		// add style to this new sheet
		difficultyScreen.getStylesheets().add("cssSheets/diffScreenStyle.css");
		
		scene = difficultyScreen;
		stage.setScene(scene);
		stage.show();
	}

	// TODO: outsource to another file
	public void nextQuestion() {
		QuestionScreenLayout qScreenLayout = new QuestionScreenLayout("WHAT?", "No", "Yes", "Kinda", "Only on Tuesdays", this);
		scene.setRoot(qScreenLayout);
	}
}
