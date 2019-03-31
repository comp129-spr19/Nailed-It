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
	private QuizScreen quizStage;
	private Button startButton;
	
	private Stage homeStage;
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		homeStage = primaryStage;
		stage = homeStage;
		homeStage.setTitle("100% Complete Dorothy's App");
		
		startButton = new Button("Start Quiz");
		// lambda function that changes scene on button click
		startButton.setOnAction(e -> switchToQuiz());
			
		StackPane layout = new StackPane();
		layout.getChildren().add(startButton);
		
		Scene scene = new Scene(layout, 600, 600);
		homeStage.setScene(scene);
		homeStage.show();
	}
	
	public void switchToQuiz() {
		quizStage = new QuizScreen(this);
		stage.close();
		stage = quizStage;
		quizStage.show();
	}
	
	public void switchToHome() {
		stage.close();
		stage = homeStage;
		homeStage.show();
	}
}
