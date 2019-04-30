package Layouts;

import JSON.JSONEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.Answer;
import main.AnswerConverter;
import main.MainStage;
import main.Question;

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

		delete = new Button("Delete Question");
		delete.setId("delete");
		delete.setOnAction(this);

		vbox.getChildren().addAll(questionText, hintText, submit, errorMessage, quit, delete, correctAnswerBox);
		vbox.setAlignment(Pos.BOTTOM_CENTER);

		this.setCenter(vbox);
	}

	private HBox setCorrectAnswer(Question question) {
		answerLabel = new Text("Correct Answer: ");
		answerLabel.setId("correctAnswerLabel");

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

		switch (question.getCorrectAnswer()) {
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
			// default considered- if there is no correct answer, none should be selected
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

		HBox answerAHBox = new HBox();
		HBox answerBHBox = new HBox();
		HBox answerCHBox = new HBox();
		HBox answerDHBox = new HBox();

		Label answerALabel = new Label("A. ");
		Label answerBLabel = new Label(" B. ");
		Label answerCLabel = new Label("C. ");
		Label answerDLabel = new Label(" D. ");

		answerA = new TextField(question.getAnswerA());
		answerA.setPrefSize(300, 75);
		answerAHBox.getChildren().addAll(answerALabel, answerA);
		answerALabel.setId("solutionFieldLabel");
		GridPane.setConstraints(answerAHBox, 0, 0);

		answerB = new TextField(question.getAnswerB());
		answerB.setPrefSize(300, 75);
		answerBHBox.getChildren().addAll(answerBLabel, answerB);
		answerBLabel.setId("solutionFieldLabel");
		GridPane.setConstraints(answerBHBox, 1, 0);

		answerC = new TextField(question.getAnswerC());
		answerC.setPrefSize(300, 75);
		answerCHBox.getChildren().addAll(answerCLabel, answerC);
		answerCLabel.setId("solutionFieldLabel");
		GridPane.setConstraints(answerCHBox, 0, 1);

		answerD = new TextField(question.getAnswerD());
		answerD.setPrefSize(300, 75);
		answerDHBox.getChildren().addAll(answerDLabel, answerD);
		answerDLabel.setId("solutionFieldLabel");
		GridPane.setConstraints(answerDHBox, 1, 1);

		grid.getChildren().addAll(answerAHBox, answerBHBox, answerCHBox, answerDHBox);

		return grid;
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "quit":
				main.startEditor();
				break;
			case "submit":
				if (textFieldEmpty()) {
					errorMessage.setText("Cannot save if a field is empty.");
				} else if (noAnswerSelected()) {
					errorMessage.setText("Cannot save if no correct answer is selected.");
				} else {
					boolean didSubmit = submitQuestion();
					if (didSubmit) {
						main.startEditor();
					} else {
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
		TextField[] fields = { questionText, hintText, answerA, answerB, answerC, answerD };
		boolean emptyFound = false;
		for (TextField f : fields) {
			if (f.getText() == null || f.getText().trim().isEmpty()) {
				emptyFound = true;
				break;
			}
		}
		return emptyFound;
	}

	private boolean noAnswerSelected() {
		RadioButton[] buttons = { A, B, C, D };
		boolean selectedFound = false;
		for (RadioButton b : buttons) {
			if (b.isSelected()) {
				selectedFound = true;
				break;
			}
		}
		return !selectedFound;
	}

	private Answer findCorrectAnswer() {
		RadioButton[] buttons = { A, B, C, D };
		String answer = "";
		for (RadioButton b : buttons) {
			if (b.isSelected()) {
				answer = b.getId();
				break;
			}
		}
		return AnswerConverter.stringToAnswer(answer);
	}

	private boolean submitQuestion() {
		Question update = new Question(question.getName(), question.getTopic(), questionText.getText(),
				answerA.getText(), answerB.getText(), answerC.getText(), answerD.getText(), hintText.getText(),
				findCorrectAnswer());
		if (this.isNewQuestion) {
			return JSONEditor.addQuestion(category, update);
		}
		return JSONEditor.updateQuestion(category, update);
	}
}