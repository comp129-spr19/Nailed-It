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

public class ConfirmLayout extends VBox implements EventHandler<ActionEvent> {

	private MainStage main;
	private Question question;
	private String category;
	private Text questionText, confirmText;
	private Button yes, no;
	
	public ConfirmLayout(MainStage main) {
		this(main, "Reload backup questions. This will remove any edits or additions you've made to the questions in this application.");
		this.setId("Reload");
	}

	public ConfirmLayout(String category, Question question, MainStage main) {
		this(main, ("Delete " + question.getQuestion()));
		this.question = question;
		this.category = category;
		this.setId("Delete");
	}
	
	public ConfirmLayout(MainStage main, String textContents) {
		this.main = main;
		
		questionText = new Text(textContents);
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
				if (this.getId().equals("Delete")) {
					JSONEditor.deleteQuestion(category, question.getName());
					main.startEditor();
				} else if (this.getId().equals("Reload")) {
					JSONEditor.reloadBackupFile();
					main.startEditor();
				}
				break;
			case "no":
				if (this.getId().equals("Delete")) {
					main.switchToQuestionEditor(category, question);
				} else if (this.getId().equals("Reload")) {
					main.startEditor();
				}
				break;
			default:
				System.out.println("Error: what the heck did you just click");
			}
		}
	}

}
