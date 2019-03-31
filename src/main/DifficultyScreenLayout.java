package main;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

// Window = Stage
// Content in Window = Scene
public class DifficultyScreenLayout extends HBox implements EventHandler<ActionEvent>{

	MainStage main;
	
	DifficultyScreenLayout(MainStage main) {
		super();
		this.main = main;
		
		Button easy = new Button("EASY");
		easy.setId("easy");
		easy.setOnAction(this);
		
		Button medium = new Button("MEDIUM");
		medium.setId("medium");
		medium.setOnAction(this);
		
		Button hard = new Button("HARD");
		hard.setId("hard");
		hard.setOnAction(this);
		
		Button next = new Button("CONTINUE");
		next.setId("next");
		next.setOnAction(this);
		
		//Button quit = new Button("Return to Menu");
		//quit.setId("quit");
		//quit.setOnAction(this);
		
		this.getChildren().addAll(easy, medium, hard, next);
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "easy":
				System.out.println("easy chosen");
				break;
			case "medium":
				System.out.println("medium chosen");
				break;
			case "hard":
				System.out.println("hard chosen");
				break;
			case "next":
				main.nextQuestion();
				break;
			//case "quit":
				//mainScreen.switchToHome();
				//break;
			default:
				System.out.println("Hey the QuizEventHandler has no idea what the heck you just did");
			}
		}
	}

}
