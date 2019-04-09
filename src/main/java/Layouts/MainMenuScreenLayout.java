package Layouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import main.MainStage;

public class MainMenuScreenLayout extends VBox  implements EventHandler<ActionEvent>{

	private Button quiz;
	private Button editor;
	private MainStage main;
	
	@Override
	public void handle(ActionEvent e) {
		if(e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch(clicked.getId()) {
			case "quiz":
			main.startQuiz();
				break;
			case "editor":
			main.startEditor();
				break;
				default:
					//main.statQuiz();
			}
		}
	}
	
	public MainMenuScreenLayout(MainStage main) {
		super();
		this.main = main;
		
		quiz = new Button("Quiz");
		quiz.setId("quiz");
		quiz.setOnAction(this);
		editor = new Button("Editor");
		editor.setId("editor");
		editor.setOnAction(this);
		

		this.getChildren().addAll(quiz, editor);
		
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
	}
	
	
	
}