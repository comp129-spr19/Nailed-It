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
	private Text errorMessage, answerLabel;
	private RadioButton A, B, C, D;
	private ToggleGroup correctAnswer;
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
		
		HBox correctAnswerBox = setCorrectAnswer(question);
		
		submit = new Button("Save and Close");
		submit.setId("submit");
		submit.setOnAction(this);
		
		errorMessage = new Text("");
		
		quit = new Button("Close without Saving");
		quit.setId("quit");
		quit.setOnAction(this);

		vbox.getChildren().addAll(questionText, hintText, submit, errorMessage, quit, correctAnswerBox);
		vbox.setAlignment(Pos.BOTTOM_CENTER);

		this.setCenter(vbox);
	}
	
	private HBox setCorrectAnswer(Question question) {
		answerLabel = new Text("Correct Answer: ");
		
		correctAnswer = new ToggleGroup();
		
		A = new RadioButton("A");
		A.setToggleGroup(correctAnswer);
		
		B = new RadioButton("B");
		B.setToggleGroup(correctAnswer);
		
		C = new RadioButton("C");
		C.setToggleGroup(correctAnswer);
		
		D = new RadioButton("D");
		D.setToggleGroup(correctAnswer);
		
		switch(question.getCorrectAnswer()) {
		case ANSWER_A:
			A.setSelected(true);
			break;
		case ANSWER_B:
			B.setSelected(true);
			break;
		case ANSWER_C:
			C.setSelected(true);
			break;
		case ANSWER_D:
			D.setSelected(true);
			break;
		default:
			//default considered- if there is no correct answer, none should be selected
		}
		
		HBox correctAnswerBox = new HBox();
		correctAnswerBox.setSpacing(5);
		correctAnswerBox.getChildren().addAll(answerLabel, A, B, C, D);
		correctAnswerBox.setAlignment(Pos.CENTER);
		return correctAnswerBox;
	}
	
	private GridPane setMultipleChoiceOptions(Question question) {
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(0, 0, 0, 0));
		grid.setAlignment(Pos.CENTER);
		grid.setVgap(0);
		grid.setHgap(0);

		answerA = new TextField(question.getAnswerA());
		answerA.setPrefSize(300, 75);
		GridPane.setConstraints(answerA, 0, 0);

		answerB = new TextField(question.getAnswerB());
		answerB.setPrefSize(300, 75);
		GridPane.setConstraints(answerB, 1, 0);

		answerC = new TextField(question.getAnswerC());
		answerC.setPrefSize(300, 75);
		GridPane.setConstraints(answerC, 0, 1);

		answerD = new TextField(question.getAnswerD());
		answerD.setPrefSize(300, 75);
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
				if (textFieldEmpty()) {
					errorMessage.setText("Cannot save if a field is empty.");
				}
				else if (noAnswerSelected()) {
					errorMessage.setText("Cannot save if no correct answer is selected.");
				}
				else {
					submitQuestion();
					main.startEditor();
				}
				break;
			default:
				System.out.println("ERROR: No input case in EventHandler");
			}
		}
	}
	
	private boolean textFieldEmpty() {
		TextField[] fields = {questionText, hintText, answerA, answerB, answerC, answerD};
		boolean emptyFound = false;
		for (TextField f: fields) {
			if (f.getText() == null || f.getText().trim().isEmpty()) {
				emptyFound = true;
				break;
			}
		}
		return emptyFound;
	}
	
	private boolean noAnswerSelected() {
		RadioButton[] buttons = {A, B, C, D};
		boolean selectedFound = false;
		for (RadioButton b: buttons) {
			if (b.isSelected()) {
				selectedFound = true;
				break;
			}
		}
		return !selectedFound;
	}
	
	private void submitQuestion() {
		System.out.println("question not submitted- no implementation");
		//TODO - link to JSONOperations (Mockito)
	}
}
