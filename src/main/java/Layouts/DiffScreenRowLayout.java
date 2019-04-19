package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import main.MainStage;

public class DiffScreenRowLayout extends HBox implements EventHandler<ActionEvent> {

	MainStage main;

	protected ToggleButton easy, medium, hard;
	private Label category;
	private String categoryName;
	private DifficultyScreenLayout parentPane;
	
	// flag that tells us if this is a user or web row
	protected boolean isWebRow;
	
	public DiffScreenRowLayout(String categoryName, DifficultyScreenLayout parentPane) {
		super();
		this.categoryName = categoryName;
		this.parentPane = parentPane;
		this.setSpacing(15);

		category = new Label(categoryName);
		category.setId("category");
		category.setMinWidth(200);

		easy = new ToggleButton("Easy");
		easy.setId("easy");
		easy.setOnAction(this.parentPane);

		medium = new ToggleButton("Medium");
		medium.setId("medium");
		medium.setOnAction(this.parentPane);

		hard = new ToggleButton("Hard");
		hard.setId("hard");
		hard.setOnAction(this.parentPane);

		this.getChildren().addAll(category, easy, medium, hard);

		this.setAlignment(Pos.CENTER);
		// this.setSpacing(50);
		
		isWebRow = false;

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
	
	public boolean checkIfWebRow() {
		return isWebRow;
	}

}
