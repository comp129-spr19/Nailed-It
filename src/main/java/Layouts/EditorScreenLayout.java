package Layouts;

import java.util.ArrayList;

import JSON.JSONOperations;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.ColorUtil;
import main.MainStage;
import main.Question;

public class EditorScreenLayout extends VBox implements EventHandler<ActionEvent> {
	private MainStage main;

	public static final double BUTTON_GAP_RATIO = 1.0 / 155.0;
	public static final double TOP_BAR_RATIO = 1.0 / 10.0;

	private HBox top;
	private ComboBox category;
	private Button addQuestion, returnToMainMenu, reloadBackup;
	private ArrayList<Question> questions;
	private VBox questionList;
	private ScrollPane questionScroll;

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button button = (Button) e.getSource();
			if (button.getId().contains("edit")) {
				String ID = button.getId();
				ID = ID.substring(0, ID.length() - 4);
				int toEdit = Integer.parseInt(ID);
				String categoryStr = (String) category.getValue();
				main.switchToQuestionEditor(categoryStr, questions.get(toEdit));
			} else if (button.getId().equals("addQuestion")) {
				main.switchToNewQuestionEditor((String) category.getValue());
			} else if (button.getId().equals("returnToMainMenu")) {
				main.switchToSelection();
			} else if (button.getId().equals("reloadBackup")) {
				main.switchToReloadComfirm();
			}
		} else if (e.getSource() instanceof ComboBox) {
			ComboBox box = (ComboBox) e.getSource();
			if (!box.getSelectionModel().isEmpty()) {
				this.setStyle(ColorUtil.editorColor((String) box.getValue()));
				listQuestions();
				addQuestion.setDisable(false);
			}
		}

	}

	public EditorScreenLayout(MainStage main) {
		super();
		this.main = main;

		top = new HBox();

		setUpHBox(top, main.getScreenHeight() * TOP_BAR_RATIO, main.getScreenWidth() * BUTTON_GAP_RATIO);

		category = JSONOperations.returnCategoryList();
		category.setId("dropdown");
		category.setOnAction(this);

		returnToMainMenu = new Button("Main Menu");
		returnToMainMenu.setId("returnToMainMenu");
		returnToMainMenu.setOnAction(this);

		reloadBackup = new Button("Reset Questions");
		reloadBackup.setId("reloadBackup");
		reloadBackup.setOnAction(this);

		questionList = new VBox();
		questionScroll = new ScrollPane();
		addQuestion = new Button("Add Question");
		addQuestion.setId("addQuestion");
		addQuestion.setOnAction(this);
		addQuestion.setDisable(true);
		questionScroll.setContent(questionList);

		top.getChildren().addAll(category, returnToMainMenu, reloadBackup, addQuestion);
		this.getChildren().addAll(top, questionScroll);
		this.setSpacing(50);
	}

	private void listQuestions() {
		String categoryStr = (String) category.getValue();
		questionList.getChildren().clear();
		questions = JSONOperations.getQuestions(categoryStr);
		for (int i = 0; i < questions.size(); i++) {
			questionList.getChildren().add(createQuestionBox(i, questions.get(i)));
		}
	}

	private HBox createQuestionBox(int id, Question question) {
		HBox box = new HBox();

		Button edit = new Button("Edit");
		edit.setId(id + "edit");
		edit.setOnAction(this);

		ScrollPane scroll = new ScrollPane();
		Text text = new Text(question.getQuestion());
		scroll.setContent(text);

		scroll.setMaxSize(main.getScreenWidth() - 110, 300);
		scroll.setMinSize(main.getScreenWidth() - 110, 300);

		TitledPane title = new TitledPane(id + ".", scroll);
		title.setExpanded(false);

		box.getChildren().addAll(edit, title);
		return box;
	}

	private void setUpHBox(HBox box, double height, double spacing) {
		box.setMaxHeight(height);
		box.setMinHeight(height);
		box.setSpacing(spacing);
		box.setAlignment(Pos.CENTER);
		box.setId("topbar");
	}
}
