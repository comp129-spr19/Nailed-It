import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class DifficultyScreenLayout extends HBox implements EventHandler<ActionEvent> {

	MainStage main;

	ToggleButton easy, medium, hard;
	Button next;

	DifficultyScreenLayout(MainStage main) {
		super();
		this.main = main;

		easy = new ToggleButton("EASY");
		easy.setId("easy");
		easy.setOnAction(this);

		medium = new ToggleButton("MEDIUM");
		medium.setId("medium");
		medium.setOnAction(this);

		hard = new ToggleButton("HARD");
		hard.setId("hard");
		hard.setOnAction(this);

		next = new Button("CONTINUE");
		next.setId("next");
		next.setDisable(true);
		next.setOnAction(this);

		this.getChildren().addAll(easy, medium, hard, next);

		this.setAlignment(Pos.CENTER);
		this.setSpacing(50);

	}

	/*
	 * Adjust application behavior off of user input
	 * 
	 * @param e User input
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "next":
				main.genQuestions(getDifficultySet());
				// create QuizHandler Object
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		} else if (e.getSource() instanceof ToggleButton) {
			if (isDifficultySelected()) {
				next.setDisable(false);
			} else {
				next.setDisable(true);
			}
		}
	}

	// TODO: Determine if this method is usable
	/*
	 * Initializes difficulty button settings
	 * 
	 * @param button The button object to be initialized
	 * 
	 * @param buttonLabel The front facing button label seen by the user
	 * 
	 * @param buttonID The ID used by the application to identify the button
	 */
	public void createDifficultyOptionButton(ToggleButton button, String buttonLabel, String buttonID) {
		button = new ToggleButton(buttonLabel);
		button.setId(buttonID);
		button.setOnAction(this);
	}

	/*
	 * Returns an array of each difficulty button's selection status
	 * 
	 * @return An array of booleans flagging each difficulty button's selection
	 * status
	 */
	public boolean[] getDifficultySet() {
		return new boolean[] { easy.isSelected(), medium.isSelected(), hard.isSelected() };
	}

	/*
	 * Determines whether a difficulty button is selected
	 * 
	 * @return true if any of the difficulty buttons are toggled, false if none of
	 * the difficulty buttons are toggled
	 */
	public boolean isDifficultySelected() {
		boolean[] difficultySet = getDifficultySet();
		for (int i = 0; i < difficultySet.length; i++) {
			if (difficultySet[i]) {
				return true;
			}
		}
		return false;
	}

}
