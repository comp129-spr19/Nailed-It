package Layouts;

import javafx.scene.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import main.*;

public class QuestionEditorLayout extends BorderPane implements EventHandler<ActionEvent> {
	
	MainStage main;
	
	private TextField questionText, hintText, answerA, answerB, answerC, answerD;
	private RadioButton A, B, C, D;
	private Button submit, quit;
	
	public QuestionEditorLayout(Question question, MainStage main) {
		super();
		this.main = main;

		GridPane answerSelection = setMultipleChoiceOptions(question);
		this.setBottom(answerSelection);

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		vbox.setMaxWidth(600);
		
		questionText = new TextField(question.getQuestion());

		hintText = new TextField(question.getHint());
		
		quit = new Button("Close without Saving");
		quit.setId("quit");
		quit.setOnAction(this);
		
		submit = new Button("Save and Close");
		submit.setId("submit");
		submit.setOnAction(this);
		submit.setDisable(true);

		vbox.getChildren().addAll(questionText, hintText, quit, submit);
		vbox.setAlignment(Pos.BOTTOM_CENTER);

		this.setCenter(vbox);
	}
	
	private GridPane setMultipleChoiceOptions(Question question) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(0);
		grid.setHgap(0);

		answerA = new TextField(question.getAnswerA());
		answerA.setOnAction(this);
		answerA.setPrefSize(300, 75);
		GridPane.setConstraints(answerA, 0, 0);

		answerB = new TextField(question.getAnswerB());
		answerB.setPrefSize(300, 75);
		answerB.setOnAction(this);
		GridPane.setConstraints(answerB, 1, 0);

		answerC = new TextField(question.getAnswerC());
		answerC.setPrefSize(300, 75);
		answerC.setOnAction(this);
		GridPane.setConstraints(answerC, 0, 1);

		answerD = new TextField(question.getAnswerD());
		answerD.setPrefSize(300, 75);
		answerD.setOnAction(this);
		GridPane.setConstraints(answerD, 1, 1);

		grid.getChildren().addAll(answerA, answerB, answerC, answerD);

		return grid;
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch(clicked.getId()) {
			case "quit":
				main.startEditor();
				break;
			case "submit":
				//TODO add logic to update JSON file
				main.startEditor();
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}
}
