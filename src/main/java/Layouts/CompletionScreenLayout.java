package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.MainStage;

public class CompletionScreenLayout extends VBox implements EventHandler<ActionEvent> {

	private MainStage main;

	public CompletionScreenLayout(MainStage main, int numCorrAnswers, int totalQuestions) {
		super();
		this.main = main;

		// set window text
		Text completionMessage = new Text("Quiz completed!");
		completionMessage.setId("completionMessage");
		Text scoreMessage = new Text(numCorrAnswers + "/" + totalQuestions + " questions correct");
		scoreMessage.setId("scoreMessage");
		Text congratulatoryMessage = new Text("Aren't you late for your interview?");
		congratulatoryMessage.setId("congratulatoryMessage");

		double score = (double) numCorrAnswers / (double) totalQuestions;

		if (score >= .9)
			congratulatoryMessage.setText("You passed with an A! Gnaaaarlyyyyy dude!");
		else if (score >= .8)
			congratulatoryMessage.setText("You passed with a B! B's are da bestest!");
		else if (score >= .7)
			congratulatoryMessage.setText("You passed with a C! Must have been not that E-C.");
		else if (score >= .6)
			congratulatoryMessage.setText("You passed with a D! D's get degrees, but they don't always get jobs.");
		else
			congratulatoryMessage.setText("You scored in F territory. Big oof. Study up young Padawan.");

		// create a button to return to difficulty screen
		Button quit = new Button("Take Another Quiz");
		quit.setId("quit");
		quit.setOnAction(this);

		// add text and button to scene
		this.getChildren().addAll(completionMessage, scoreMessage, congratulatoryMessage, quit);

		// format layout
		this.setAlignment(Pos.CENTER);
		this.setSpacing(10);
	}

	@Override
	public void handle(ActionEvent e) {

		// check that a button was clicked
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "quit":
				main.switchToSelection();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}

}
