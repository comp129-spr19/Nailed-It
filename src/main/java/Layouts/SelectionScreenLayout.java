package Layouts;

import java.io.IOException;
import java.util.ArrayList;

import JSON.JSONOperations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import main.MainStage;

public class SelectionScreenLayout extends GridPane implements EventHandler<ActionEvent> {

	MainStage main;

	private ArrayList<ToggleButton> categoryButtons;
	private Button nextButton, editor;

	private static final double ASPECT_RATIO = 7.0;
	private static final double HALF_ASPECT_RATIO = ASPECT_RATIO * 2.0;
	private static final double THIRD_ASPECT_RATIO = ASPECT_RATIO * 1.5;
	private static final double SPACING_RATIO = 75.0;

	private int customIndex;

	public SelectionScreenLayout(MainStage main) {
		// super();
		this.main = main;
		this.setHgap(main.getScreenHeight() / SPACING_RATIO);
		this.setVgap(main.getScreenHeight() / SPACING_RATIO);
		generateButtons(JSONOperations.returnCategories());
		setButtonsOnGrid();
		this.setAlignment(Pos.CENTER);

	}

	private void generateButtons(ArrayList<String> returnCategories) {
		int index = 0;
		categoryButtons = new ArrayList<ToggleButton>();
		for (String category : returnCategories) {
			ToggleButton button = new ToggleButton(category);
			if (category.equals("Custom")) {
				customIndex = index;
			}
			button.setId("category");
			button.wrapTextProperty().setValue(true);
			button.setTextAlignment(TextAlignment.CENTER);
			button.setMaxSize(main.getScreenWidth() / ASPECT_RATIO, main.getScreenHeight() / ASPECT_RATIO);
			button.setMinSize(main.getScreenWidth() / ASPECT_RATIO, main.getScreenHeight() / ASPECT_RATIO);

			categoryButtons.add(button);
			index++;
		}

		nextButton = new Button("Begin Quiz");
		nextButton.setId("nextButton");
		nextButton.setOnAction(this);
		nextButton.setMaxSize(main.getScreenWidth() / ASPECT_RATIO, main.getScreenHeight() / HALF_ASPECT_RATIO);
		nextButton.setMinSize(main.getScreenWidth() / ASPECT_RATIO, main.getScreenHeight() / HALF_ASPECT_RATIO);

		editor = new Button("Editor");
		editor.setId("editor");
		editor.wrapTextProperty().setValue(true);
		editor.setOnAction(this);
		editor.setMaxSize(main.getScreenWidth() / THIRD_ASPECT_RATIO, main.getScreenHeight() / HALF_ASPECT_RATIO);
		editor.setMinSize(main.getScreenWidth() / THIRD_ASPECT_RATIO, main.getScreenHeight() / HALF_ASPECT_RATIO);
	}

	private void setButtonsOnGrid() {
		int row = 0;
		int col = 0;

		for (ToggleButton button : categoryButtons) {
			if (!button.getText().equals("Custom")) {
				this.add(button, col, row);
				if (col < 2) {
					col++;
				} else {
					row++;
					col = 0;
				}

			}
		}

		this.add(categoryButtons.get(customIndex), 1, 2);
		this.add(nextButton, 1, 5);
		this.add(editor, 1, 6);

	}

	/*
	 * Adjust application behavior off of user input
	 * 
	 * @param e User input
	 */

	@Override
	public void handle(ActionEvent e) {
		if (categoryButtons != null && e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();

			if (clicked.getId().equals("nextButton")) {
				try {
					main.genQuestions(categoryButtons);
					// System.out.println("WELL HERE I AM");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("HAHAHAHA");
				}
			} else if (clicked.getId().equals("editor")) {
				main.startEditor();
			}

		}
	}

}
