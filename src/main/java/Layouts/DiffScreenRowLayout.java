package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import main.MainStage;

public class DiffScreenRowLayout extends HBox implements EventHandler<ActionEvent> {

	MainStage main;

	private ToggleButton easy, medium, hard;
	private Label category;
	private Button next;
	private String categoryName;

	public DiffScreenRowLayout(String categoryName) {
		super();
		this.categoryName = categoryName;
		this.setSpacing(15);

		category = new Label(categoryName);
		category.setId("category");
		category.setMinWidth(200);

		easy = new ToggleButton("Easy");
		easy.setId("easy");
		easy.setOnAction(this);

		medium = new ToggleButton("Medium");
		medium.setId("medium");
		medium.setOnAction(this);

		hard = new ToggleButton("Hard");
		hard.setId("hard");
		hard.setOnAction(this);

		this.getChildren().addAll(category, easy, medium, hard);

		this.setAlignment(Pos.CENTER);
		// this.setSpacing(50);

	}

	/*
	 * Adjust application behavior off of user input
	 * 
	 * @param e User input
	 */
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof ToggleButton) {
			ToggleButton clicked = (ToggleButton) e.getSource();
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

	public String getCategory() {
		return categoryName;
	}

}
