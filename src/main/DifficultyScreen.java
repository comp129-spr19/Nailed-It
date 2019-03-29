package main;

import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

// Window = Stage
// Content in Window = Scene
public class DifficultyScreen extends HBox {

	DifficultyScreen() {
		super();
		Button easy = new Button("EASY");
		Button medium = new Button("MEDIUM");
		Button hard = new Button("HARD");
		this.getChildren().addAll(easy,medium,hard);
	}

	

}
