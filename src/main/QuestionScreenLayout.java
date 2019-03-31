package main;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.*;

public class QuestionScreenLayout extends HBox implements EventHandler<ActionEvent> {
	
	MainStage main;

	public QuestionScreenLayout(String question, String ansrA, String ansrB, String ansrC, String ansrD, MainStage main) {
		super();
		this.main = main;
		
		Text questionText = new Text(question);
		
		ToggleGroup answers = new ToggleGroup();
		
		RadioButton answerA = new RadioButton("A: " + ansrA);
		answerA.setId("A");
		answerA.setToggleGroup(answers);
		answerA.setOnAction(this);
		
		RadioButton answerB = new RadioButton("B: " + ansrB);
		answerB.setId("B");
		answerB.setToggleGroup(answers);
		answerB.setOnAction(this);
		
		RadioButton answerC = new RadioButton("C: " + ansrC);
		answerC.setId("C");
		answerC.setToggleGroup(answers);
		answerC.setOnAction(this);
		
		RadioButton answerD = new RadioButton("D: " + ansrD);
		answerD.setId("D");
		answerD.setToggleGroup(answers);
		answerD.setOnAction(this);
		
		Button next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);
		
		this.getChildren().addAll(questionText, answerA, answerB, answerC, answerD, next);
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "next":
				main.nextQuestion();
				break;
			case "quit":
				//mainScreen.switchToHome();
				break;
			default:
				System.out.println("Hey the QuizEventHandler has no idea what the heck you just did");
			}
		}
	}

}
