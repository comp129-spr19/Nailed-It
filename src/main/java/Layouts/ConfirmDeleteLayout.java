package Layouts;

import JSON.JSONEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.MainStage;
import main.Question;

public class ConfirmDeleteLayout extends VBox implements EventHandler<ActionEvent> {

	private MainStage main;
	private Question question;
	private String category;
	private Text questionText, confirmText;
	private Button yes, no;

	public ConfirmDeleteLayout(String category, Question question, MainStage main) {
		this.main = main;
		this.question = question;
		this.category = category;

		questionText = new Text("Delete: " + question.getQuestion());
		questionText.setWrappingWidth(main.getScreenWidth());

		confirmText = new Text("Are you sure?");

		HBox hbox = new HBox();

		yes = new Button("Yes.");
		yes.setId("yes");
		yes.setOnAction(this);

		no = new Button("No.");
		no.setId("no");
		no.setOnAction(this);

		hbox.getChildren().addAll(yes, no);
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);

		this.getChildren().addAll(questionText, confirmText, hbox);
		this.setAlignment(Pos.CENTER);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			switch (clicked.getId()) {
			case "yes":
				JSONEditor.deleteQuestion(category, question.getName());
				main.startEditor();
				break;
			case "no":
				main.switchToQuestionEditor(category, question);
				break;
			default:
				System.out.println("Error: what the heck did you just click");
			}
		}
	}

}
