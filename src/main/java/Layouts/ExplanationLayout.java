package Layouts;

import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import main.*;

public class ExplanationLayout extends VBox implements EventHandler<ActionEvent> {
	public static final String EXPLANATIONS_FILEPATH = "file:src/main/resources/explanations/";
	
	private MainStage main;
	private ImageView image;
	private Button next;
	
	public ExplanationLayout(MainStage main, String filename) {
		this.main = main;
		
		next = new Button("Next");
		next.setId("next");
		next.setOnAction(this);
		
		Image img = new Image(EXPLANATIONS_FILEPATH + filename);
		image = new ImageView(img);
		image.setVisible(true);
		
		this.getChildren().addAll(next, image);
		this.setSpacing(30);
		this.setAlignment(Pos.CENTER);
	}

	@Override
	public void handle(ActionEvent e) {
		if (e.getSource() instanceof Button) {
			Button clicked = (Button) e.getSource();
			if (clicked.getId().equals("next")) {
				main.nextQuestion();
			}
		}
	}


}
