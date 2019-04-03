import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CompletionScreenLayout extends VBox implements EventHandler<ActionEvent> {

	private MainStage main;
	
	CompletionScreenLayout(MainStage main, int numCorrAnswers, int totalQuestions) {
		super();
		this.main = main;


		// set window text
		Text text = new Text("Quiz completed!\n" + numCorrAnswers + "/" + totalQuestions +  
				" questions correct\n" + "Aren't you late for your interview?");


		// create a button to return to difficulty screen
		Button quit = new Button("Take Quiz Again");
		quit.setId("quit");
		quit.setOnAction(this);

		// add text and button to scene
		this.getChildren().addAll(text, quit);

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
				main.switchToDifficulty();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}
	
	
}
