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
	private ComboBox category;
	private Button search, addQuestion,returnToMainMenu;
	
	private ArrayList<Question> questions;
	private VBox questionList;
	
	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button button = (Button) e.getSource();
			if (button.getId().equals("search")) {
				if (!(category.getSelectionModel().isEmpty())) {
					listQuestions();
				}
			}
			else if (button.getId().contains("edit")) {
				String ID = button.getId();
				ID = ID.substring(0, ID.length() - 4);
				int toEdit = Integer.parseInt(ID);
				String categoryStr = (String) category.getValue();
				main.switchToQuestionEditor(categoryStr, questions.get(toEdit));
			} else if (button.getId().equals("addQuestion")) {
				//if (category.getValue() == null)
				//System.out.println("CREED");
				main.switchToNewQuestionEditor((String)category.getValue());
			} else if (button.getId().equals("returnToMainMenu")) {
				main.switchToMainMenu();
			}
		}
		
	}

	public EditorScreenLayout(MainStage main) {
		super();
		this.main = main;
		
		top = new HBox();
		category = JSONOperations.returnCategoryList();
		
		search = new Button("Search");
		search.setId("search");
		search.setOnAction(this);
		
		returnToMainMenu = new Button("Main Menu");
		returnToMainMenu.setId("returnToMainMenu");
		returnToMainMenu.setOnAction(this);
		
		
		questionList = new VBox();
		
		//addQuestion = new Button("Add Question");
		
		
		top.getChildren().addAll(category, search);
		this.getChildren().addAll(top,questionList,returnToMainMenu);
		this.setSpacing(50);
	}
	
	private void listQuestions() {
		String categoryStr = (String) category.getValue();
		questionList.getChildren().clear();
		 questions = JSONOperations.getQuestions(categoryStr);
		for (int i = 0; i < questions.size();i++) {
			questionList.getChildren().add(createQuestionBox(i,questions.get(i)));
		}
		
		addQuestion = new Button("Add Question");
		addQuestion.setId("addQuestion");
		addQuestion.setOnAction(this);
		questionList.getChildren().add(addQuestion);
		
		
	}

	private HBox createQuestionBox(int id, Question question) {
			HBox box = new HBox();
			
			CheckBox check = new CheckBox((id+1) + "." + question.getQuestion());
			check.setId(id + "checkBox");
			Button edit = new Button("Edit");
			edit.setId(id+"edit");
			edit.setOnAction(this);
			
			box.getChildren().addAll(check,edit);
			return box;
		
	}
}
