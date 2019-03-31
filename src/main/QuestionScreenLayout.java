package main;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.application.*;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.stage.*;

public class QuestionScreenLayout extends HBox {

	public QuestionScreenLayout(String question, String ansrA, String ansrB, String ansrC, String ansrD, EventHandler<ActionEvent> action) {
		super();
		
		Text questionText = new Text(question);
		
		ToggleGroup answers = new ToggleGroup();
		
		RadioButton answerA = new RadioButton("A: " + ansrA);
		answerA.setId("A");
		answerA.setToggleGroup(answers);
		answerA.setOnAction(action);
		
		RadioButton answerB = new RadioButton("B: " + ansrB);
		answerB.setId("B");
		answerB.setToggleGroup(answers);
		answerB.setOnAction(action);
		
		RadioButton answerC = new RadioButton("C: " + ansrC);
		answerC.setId("C");
		answerC.setToggleGroup(answers);
		answerC.setOnAction(action);
		
		RadioButton answerD = new RadioButton("D: " + ansrD);
		answerD.setId("D");
		answerD.setToggleGroup(answers);
		answerD.setOnAction(action);
		
		Button next = new Button("Next");
		next.setId("next");
		next.setOnAction(action);
		
		this.getChildren().addAll(questionText, answerA, answerB, answerC, answerD, next);
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
		
	}

}
