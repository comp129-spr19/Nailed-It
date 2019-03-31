package main;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainScreen extends Application {
	
	// represents the entire window (stage)
	private Stage stage;
	
	// components to be used by the stage
	private Button startButton;
	private QuizScreen quizStage;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		stage.setTitle("100% Complete Dorothy's App");
		
		startButton = new Button("Start Quiz");
		// lambda function that changes scene on button click
		startButton.setOnAction(e -> startQuiz());
			
		StackPane layout = new StackPane();
		layout.getChildren().add(startButton);
		
		Scene scene = new Scene(layout, 600, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void startQuiz() {
		quizStage = new QuizScreen();
		stage.close();
		stage = quizStage;
	}
}
