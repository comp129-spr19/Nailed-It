package main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CompletionScreenLayout extends VBox implements EventHandler<ActionEvent> {

	private MainStage main;

	CompletionScreenLayout(MainStage main) {
		super();
		this.main = main;

		Text questionText = new Text("Quiz Completed.");

		Button quit = new Button("Take Quiz Again");
		quit.setId("quit");
		quit.setOnAction(this);

		this.getChildren().addAll(questionText, quit);

		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);

		// finalStage = stage;
		// finalStage.
		// Label results = new Label("Aren't you late for your interview?");
		// this.setTitle("Quiz complete!");
		//
		// Scene finalScene = new Scene(qScreenLayout, FINAL_SCREEN_HEIGHT,
		// FINAL_SCREEN_WIDTH);
		//
		// Scene scene = difficultyScreen;
		// stage.setScene(scene);
		// stage.show();
	}

	@Override
	public void handle(ActionEvent e) {
		// if (e.getSource() instanceof Button) {
		// Button clicked = (Button) e.getSource();
		// switch (clicked.getId()) {
		// case "yes":
		// case "no":
		// main.switchToDifficulty();
		// break;
		// default:
		// System.out.println("What did you do >:(");
		// break;
		// }
		// }

		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "quit":
				main.switchToDifficulty();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}
}
