package Layouts;

import javafx.scene.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import main.*;
import JSON.JSONEditor;

public class QuestionEditorLayout extends BorderPane implements EventHandler<ActionEvent> {
	
	MainStage main;
	private String category;
	private Question question;
	
	private TextField questionText, hintText, answerA, answerB, answerC, answerD;
	private Text errorMessage, answerLabel;
	private RadioButton A, B, C, D;
	private ToggleGroup correctAnswer;
	private Button submit, quit, delete;
	private boolean isNewQuestion;
	
	public QuestionEditorLayout(String category, Question question, MainStage main, Boolean isNewQuestion) {
		super();
		this.main = main;
		this.category = category;
		this.question = question;
		this.isNewQuestion = isNewQuestion;

		VBox answerSelection = setMultipleChoiceOptions(question);
		this.setBottom(answerSelection);

		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10));
		vbox.setSpacing(8);
		vbox.setMaxWidth(600);
		
		questionText = new TextField(question.getQuestion());
		Tooltip q = new Tooltip("Question");
		questionText.setTooltip(q);

		hintText = new TextField(question.getHint());
		Tooltip hint = new Tooltip("Hint");
		hintText.setTooltip(hint);
		
		HBox correctAnswerBox = setCorrectAnswer(question);
		
		submit = new Button("Save and Close");
		submit.setId("submit");
		submit.setOnAction(this);
		
		errorMessage = new Text("");
		
		quit = new Button("Close without Saving");
		quit.setId("quit");
		quit.setOnAction(this);
		
		if (question.getName() != "") {
			delete = new Button("Delete Question");
			delete.setId("delete");
			delete.setOnAction(this);
			vbox.getChildren().add(delete);
		}

		vbox.getChildren().addAll(questionText, hintText, submit, errorMessage, quit, correctAnswerBox);
		vbox.setAlignment(Pos.BOTTOM_CENTER);

		this.setCenter(vbox);}
	
	private HBox setCorrectAnswer(Question question) {
		answerLabel = new Text("Correct Answer: ");
		
		correctAnswer = new ToggleGroup();
		
		A = new RadioButton("A");
		A.setId("A");
		A.setToggleGroup(correctAnswer);
		
		B = new RadioButton("B");
		B.setId("B");
		B.setToggleGroup(correctAnswer);
		
		C = new RadioButton("C");
		C.setId("C");
		C.setToggleGroup(correctAnswer);
		
		D = new RadioButton("D");
		D.setId("D");
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
	
	private VBox setMultipleChoiceOptions(Question question) {
		VBox answers = new VBox();
		answers.setPadding(new Insets(10));
		answers.setSpacing(8);
		
		Label answerALabel = new Label("A. ");
		Label answerBLabel = new Label("B. ");
		Label answerCLabel = new Label("C. ");
		Label answerDLabel = new Label("D. ");
		
		HBox answerAHBox = new HBox();
		HBox answerBHBox = new HBox();
		HBox answerCHBox = new HBox();
		HBox answerDHBox = new HBox();

		answerA = new TextField(question.getAnswerA());
		answerA.setPrefSize(300, 75);
		Tooltip a = new Tooltip("A");
		answerA.setTooltip(a);
		
		answerAHBox.getChildren().addAll(answerALabel, answerA);

		answerB = new TextField(question.getAnswerB());
		answerB.setPrefSize(300, 75);
		Tooltip b = new Tooltip("B");
		answerB.setTooltip(b);
		
		answerBHBox.getChildren().addAll(answerBLabel, answerB);
		
		answerC = new TextField(question.getAnswerC());
		answerC.setPrefSize(300, 75);
		Tooltip c = new Tooltip("C");
		answerC.setTooltip(c);
		
		answerCHBox.getChildren().addAll(answerCLabel, answerC);
		
		answerD = new TextField(question.getAnswerD());
		answerD.setPrefSize(300, 75);
		Tooltip d = new Tooltip("D");
		answerD.setTooltip(d);
		
		answerDHBox.getChildren().addAll(answerDLabel, answerD);

		answers.getChildren().addAll(answerAHBox, answerBHBox, answerCHBox, answerDHBox);
		
		return answers;
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
					boolean didSubmit = submitQuestion();
					if (didSubmit) {
						main.startEditor();
					}
					else {
						errorMessage.setText("Save failed.");
					}
				}
				break;
			case "delete":
				main.switchToDeleteConfirm(category, question);
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
	
	private Answer findCorrectAnswer() {
		RadioButton[] buttons = {A, B, C, D};
		String answer = "";
		for (RadioButton b: buttons) {
			if (b.isSelected()) {
				answer = b.getId();
				break;
			}
		}
		return AnswerConverter.stringToAnswer(answer);
	}
	
	private boolean submitQuestion() {
		Question update = new Question(question.getName(), question.getTopic(), questionText.getText(), answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), hintText.getText(), findCorrectAnswer());
		if (this.isNewQuestion) {
			return JSONEditor.addQuestion(category, update);
		}
		return JSONEditor.updateQuestion(category, update);
	}
}
