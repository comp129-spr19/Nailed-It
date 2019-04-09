package Layouts;

import java.util.ArrayList;

import JSON.JSONOperations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.MainStage;

public class EditorScreenLayout extends VBox implements EventHandler<ActionEvent> {
	private MainStage main;
	private HBox top;
	private ComboBox category, difficulty;
	private ArrayList<HBox> questions;
	
	
	@Override
	public void handle(ActionEvent e) {
		
		
	}

	public EditorScreenLayout(MainStage main) {
		super();
		this.main = main;
		
		top = new HBox();
		difficulty = new ComboBox();
		category = JSONOperations.returnCategoryList();
		difficulty = new ComboBox<String>();
		difficulty.getItems().addAll("Easy", "Medium", "Hard");
		top.getChildren().addAll(category, difficulty);
		this.getChildren().addAll(top);
	}
	
	private void questionCreator() {
		
	}
}
