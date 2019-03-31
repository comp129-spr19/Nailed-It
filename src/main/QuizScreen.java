package main;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class QuizScreen extends Stage {
	
	public static final int SCREEN_HEIGHT = 600;
	public static final int SCREEN_WIDTH = 600;

	private DifficultyScreenLayout diffScreenLayout;
	private Scene difficultyScreen;
	private QuestionScreenLayout qScreenLayout;
	private Scene questionScreen;
	private QuizEventHandler quizAction;
	
	public QuizScreen(MainScreen mainApp) {
		super();
		this.setTitle("Quiz");
		
		quizAction = new QuizEventHandler(this, mainApp);
		
		// declare new difficulty screen and its layout		
		diffScreenLayout = new DifficultyScreenLayout(quizAction);
		difficultyScreen = new Scene(diffScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		// add style to this new sheet
		difficultyScreen.getStylesheets().add("cssSheets/diffScreenStyle.css");
		
		Scene scene = difficultyScreen;
		this.setScene(scene);
		this.show();
	}

	public void nextQuestion() {
		qScreenLayout = new QuestionScreenLayout("WHAT?", "No", "Yes", "Kinda", "Only on Tuesdays", quizAction);
		questionScreen = new Scene(qScreenLayout, SCREEN_HEIGHT, SCREEN_WIDTH);
		
		this.setScene(questionScreen);
		this.show();
	}
}
