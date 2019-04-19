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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Constants;
import main.MainStage;

public class DifficultyScreenLayout extends VBox implements EventHandler<ActionEvent> {

	MainStage main;

	private ArrayList<DiffScreenRowLayout> rows;
	private Button next;
	private Text invalidSelectionText;

	public DifficultyScreenLayout(MainStage main) {
		super();
		this.main = main;

		generateRows();

		next = new Button("Start Quiz");
		next.setId("next");
		// next.setDisable(true);
		next.setOnAction(this);
		next.setAlignment(Pos.BOTTOM_CENTER);

		invalidSelectionText = new Text("");

		this.getChildren().addAll(next, invalidSelectionText);

		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);

	}

	// populate array list and rows for this screen.
	public void generateRows() {
		JSONObject file = JSONOperations.createJSONObject(Constants.FILENAME);
		Iterator<String> iterator = file.keys();
		rows = new ArrayList<DiffScreenRowLayout>();
		while (iterator.hasNext()) {
			DiffScreenRowLayout row = new DiffScreenRowLayout(iterator.next());
			rows.add(row);
			this.getChildren().add(row);
		}
		
		DiffScreenWebRow geeksForGeeksRow = new DiffScreenWebRow("GEEKSFORGEEKS");
		rows.add(geeksForGeeksRow);
		this.getChildren().add(geeksForGeeksRow);
	}

	/*
	 * Adjust application behavior off of user input
	 * 
	 * @param e User input
	 */

	@Override
	public void handle(ActionEvent e) {
		System.out.println(e.getSource().toString());
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "next":

				// create QuizHandler Object
				if (isDifficultySelected()) {
					try {
						main.genQuestions(rows);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					displayNoSelectionMsg();
				}
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

	/*
	 * Determines whether a difficulty button is selected
	 * 
	 * @return true if any of the difficulty buttons are toggled, false if none of
	 * the difficulty buttons are toggled
	 */
	public boolean isDifficultySelected() {
		for (DiffScreenRowLayout row : rows) {
			boolean[] difficultySet = row.getDifficultySet();
			// start at index 1, since indexes 1-3 represent difficulties
			/*
			 * for (int i = 1; i < difficultySet.length; i++) { if (difficultySet[i]) {
			 * return true; } }
			 */
			if (checkDiffSet(difficultySet)) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkDiffSet(boolean[] difficultySet) {
		for (int i = 0; i < difficultySet.length; i++) {
			if (difficultySet[i]) {
				return true;
			}

		}
		return false;
	}

	public void displayNoSelectionMsg() {
		// Text txt = new Text();
		invalidSelectionText.setText("No difficulty selected, cannot proceed");
		// this.getChildren().add(txt);
	}

}
