package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.MainStage;

public class MainMenuScreenLayout extends VBox  implements EventHandler<ActionEvent>{

	private Button quiz;
	private Button editor;
	private MainStage main;
	
	@Override
	public void handle(ActionEvent event) {

	}
	
	public MainMenuScreenLayout(MainStage main) {
		super();
		this.main = main;
		
		quiz = new Button("Quiz");
		quiz.setId("quiz");
		editor = new Button("Editor");
		editor.setId("editor");

		this.getChildren().addAll(quiz, editor);
	}
	
	
	
}