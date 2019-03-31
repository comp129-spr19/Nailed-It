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
public class DifficultyScreenLayout extends HBox {

	DifficultyScreenLayout(EventHandler<ActionEvent> action) {
		
		super();
		
		Button easy = new Button("EASY");
		easy.setId("easy");
		easy.setOnAction(action);
		
		Button medium = new Button("MEDIUM");
		medium.setId("medium");
		medium.setOnAction(action);
		
		Button hard = new Button("HARD");
		hard.setId("hard");
		hard.setOnAction(action);
		
		Button next = new Button("CONTINUE");
		next.setId("next");
		next.setOnAction(action);
		
		Button quit = new Button("Return to Menu");
		quit.setId("quit");
		quit.setOnAction(action);
		
		this.getChildren().addAll(easy,medium,hard,next,quit);
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		
	}

}
