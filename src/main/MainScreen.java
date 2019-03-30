package main;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainScreen extends Application implements EventHandler<ActionEvent> {
	
	Button startButton;
	private DifficultyScreenLayout diffScreenLayout;
	private Scene difficultyScreen;
	
	// represents the entire screen
	private Stage stage;
	
	public static void main(String[] args) {
		//System.out.println("HELLO");
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("100% Complete Dorothy's App");
		
		// declare new difficulty screen and its layout
		diffScreenLayout = new DifficultyScreenLayout();
		difficultyScreen = new Scene(diffScreenLayout,500,500);
		
		
		startButton = new Button("Start Quiz");
		// lambda function that changes scene on button click
		startButton.setOnAction(e -> stage.setScene(difficultyScreen));
		
		
		
		
		StackPane layout = new StackPane();
		layout.getChildren().add(startButton);
		
		Scene scene = new Scene(layout, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void handle (ActionEvent event) {
		System.out.println("YOU PRESSED A BUTTON");
	}
}
