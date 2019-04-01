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
public class FinalScreenLayout extends Stage implements EventHandler<ActionEvent> {
	
	private MainStage main;
	
	FinalScreenLayout(MainStage main) {
		super();
		this.main = main;
		
//		finalStage = stage;
//		finalStage.
//		Label results = new Label("Aren't you late for your interview?");
//		this.setTitle("Quiz complete!");
//		
//		Scene finalScene = new Scene(qScreenLayout, FINAL_SCREEN_HEIGHT, FINAL_SCREEN_WIDTH);
//		
//		Scene scene = difficultyScreen;
//		stage.setScene(scene);
//		stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "yes":
			case "no":
				main.switchToDifficulty();
				break;
			default:
				System.out.println("What did you do >:(");
				break;
			}
		}		
	}
}
