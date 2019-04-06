package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import main.MainStage;

public class DiffScreenRowLayout extends HBox implements EventHandler<ActionEvent> {

	MainStage main;

	private ToggleButton easy, medium, hard, category;
	private Button next;
	private String categoryName;
	
	public DiffScreenRowLayout(String categoryName) {
		super();
		this.categoryName = categoryName;
		
		category = new ToggleButton(categoryName);
		category.setId("category");
		category.setOnAction(this);
		
		easy = new ToggleButton("EASY");
		easy.setId("easy");
		easy.setOnAction(this);
		easy.setDisable(true);

		medium = new ToggleButton("MEDIUM");
		medium.setId("medium");
		medium.setOnAction(this);
		medium.setDisable(true);

		hard = new ToggleButton("HARD");
		hard.setId("hard");
		hard.setOnAction(this);
		hard.setDisable(true);
	

		this.getChildren().addAll(category,easy, medium, hard);

		this.setAlignment(Pos.CENTER);
		//this.setSpacing(50);

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
			if (clicked.getId().equals("category")) {
				if (clicked.isSelected()) {
				enableDiffButtons();
				} else {
					disableDiffButtons();
				}
			}

		}
	}
	
	// enable difficulty buttons
	public void enableDiffButtons() {
		easy.setDisable(false);
		medium.setDisable(false);
		hard.setDisable(false);
	}
	
	// disable difficulty buttons and deselct them
	public void disableDiffButtons() {
		easy.setSelected(false);
		easy.setDisable(true);
		
		medium.setSelected(false);
		medium.setDisable(true);
		
		hard.setSelected(false);
		hard.setDisable(true);
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
		return new boolean[] { category.isSelected(),easy.isSelected(), medium.isSelected(), hard.isSelected() };
	}

	public String getCategory() {
		return categoryName;
	}


}
