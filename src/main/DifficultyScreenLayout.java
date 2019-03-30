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

	DifficultyScreenLayout() {
		
		super();
		
		Button easy = new Button("EASY");
		easy.setId("easy");
		
		Button medium = new Button("MEDIUM");
		medium.setId("medium");
		
		Button hard = new Button("HARD");
		hard.setId("hard");
		
		this.getChildren().addAll(easy,medium,hard);
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);
		
		
	}

	

}
