package Layouts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONObject;

import JSON.JSONOperations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Constants;
import main.MainStage;

public class SelectionScreenLayout extends GridPane implements EventHandler<ActionEvent> {

	MainStage main;

	private ArrayList<ToggleButton> categoryButtons;
	private Button nextButton;
	
	private static final double BUTTON_HEIGHT = 100;
	private static final double BUTTON_WIDTH = 200;

	private int customIndex;


	public SelectionScreenLayout(MainStage main) {
		//super();
		this.main = main;
		generateButtons(JSONOperations.returnCategories());
		setButtonsOnGrid();
		this.setAlignment(Pos.CENTER);
		
		
	}


	private void generateButtons(ArrayList<String> returnCategories) {
		int index = 0;
		categoryButtons = new ArrayList<ToggleButton>();
		for (String category: returnCategories) {
			ToggleButton button = new ToggleButton(category);
			if (category.equals("Custom")) {
				customIndex = index;
			}
			button.setId(category);
			//button.setOnAction(this);
			button.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
			button.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
			
			
			categoryButtons.add(button);
			index++;
		}
		
		nextButton = new Button("Next");
		nextButton.setId("nextButton");
		nextButton.setOnAction(this);
		nextButton.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		nextButton.setMinSize(BUTTON_WIDTH,BUTTON_HEIGHT);
		
	}
	
	private void setButtonsOnGrid() {
		int row = 0;
		int col = 0;
		
		for (ToggleButton button : categoryButtons) {
			if (!button.getId().equals("Custom")) {
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
					//System.out.println("WELL HERE I AM");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					System.out.println("HAHAHAHA");
				}
			}
			
		}
	}


	


}
