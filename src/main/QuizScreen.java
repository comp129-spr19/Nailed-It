package main;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class QuizScreen extends Stage {

	private DifficultyScreenLayout diffScreenLayout;
	private Scene difficultyScreen;
	
	public QuizScreen() {
		super();
		this.setTitle("Quiz");
		
		// declare new difficulty screen and its layout
		diffScreenLayout = new DifficultyScreenLayout();
		difficultyScreen = new Scene(diffScreenLayout,600,600);
				
		// add style to this new sheet
		difficultyScreen.getStylesheets().add("cssSheets/diffScreenStyle.css");
		
		Scene scene = difficultyScreen;
		this.setScene(scene);
		this.show();
	}

}
