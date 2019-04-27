package main;

import java.io.IOException;
import java.util.ArrayList;

import JSON.JSONOperations;
import Layouts.QuizScreenLayout;
import Scrapper.Scrapper;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;

/*
 * This class will be in charge of generating questions
 * and operations pertaining to questions.
 * 
 * It is a static class.
 * 
 */

public class GenerateQuestionScreens {
	public static ArrayList<QuizScreenLayout> generate(ArrayList<ToggleButton> categoryButtons, MainStage main) throws IOException {

		ArrayList<QuizScreenLayout> questionScreens = new ArrayList<QuizScreenLayout>();

		for (ToggleButton button : categoryButtons) {
		
			if (button.isSelected()) {
				addQuestions(button,questionScreens,main);
			}
		}

		return questionScreens;

	}


	private static void addQuestions(ToggleButton button, ArrayList<QuizScreenLayout> questionScreens,
			MainStage main) {
		ArrayList<Question> questions = JSONOperations.getQuestions(button.getId());
		ArrayList<QuizScreenLayout> screens = convertQuestionToScreens(questions, main);
		questionScreens.addAll(screens);
	}

	private static ArrayList<QuizScreenLayout> convertQuestionToScreens(ArrayList<Question> questions, MainStage main) {
		ArrayList<QuizScreenLayout> screens = new ArrayList<QuizScreenLayout>();

		for (Question q : questions) {
			screens.add(new QuizScreenLayout(q, main));
		}
		return screens;
	}
}
