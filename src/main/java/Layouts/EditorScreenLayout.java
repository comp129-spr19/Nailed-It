package Layouts;

import java.util.ArrayList;

import JSON.JSONOperations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.MainStage;
import main.Question;

public class EditorScreenLayout extends VBox implements EventHandler<ActionEvent> {
	private MainStage main;
	
	private HBox top;
	private ComboBox category, difficulty;
	private Button search;
	
	private ArrayList<Question> questions;
	private VBox questionList;
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button button = (Button) e.getSource();
			if (button.getId().equals("search")) {
				if (!(difficulty.getSelectionModel().isEmpty()) &&
					!(category.getSelectionModel().isEmpty())) {
				listQuestions();
				}
			}
		}
		
	}

	public EditorScreenLayout(MainStage main) {
		super();
		this.main = main;
		
		top = new HBox();
		difficulty = new ComboBox();
		category = JSONOperations.returnCategoryList();
		difficulty = new ComboBox<String>();
		difficulty.getItems().addAll("easy", "medium", "hard");
		
		search = new Button("Search");
		search.setId("search");
		search.setOnAction(this);
		
		questionList = new VBox();
		
		
		top.getChildren().addAll(category, difficulty, search);
		this.getChildren().addAll(top,questionList);
	}
	
	private void listQuestions() {
		String categoryStr = (String) category.getValue();
		String diffStr = (String) difficulty.getValue();
		questionList.getChildren().clear();
		 questions = JSONOperations.getQuestions(categoryStr, diffStr);
		for (int i = 0; i < questions.size();i++) {
			questionList.getChildren().add(createQuestionBox(i,questions.get(i)));
		}
		
		
	}

	private HBox createQuestionBox(int id, Question question) {
			HBox box = new HBox();
			
			CheckBox check = new CheckBox((id+1) +"." + question.getQuestion());
			check.setId(id + "checkBox");
			Button edit = new Button("Edit");
			edit.setId(id+"edit");
			
			box.getChildren().addAll(check,edit);
			return box;
		
	}
}
