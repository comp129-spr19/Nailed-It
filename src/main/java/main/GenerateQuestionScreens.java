package main;

import java.io.IOException;
import java.util.ArrayList;

import JSON.JSONOperations;
import Layouts.DiffScreenRowLayout;
import Layouts.QuizScreenLayout;
import Scrapper.Scrapper;

/*
 * This class will be in charge of generating questions
 * and operations pertaining to questions.
 * 
 * It is a static class.
 * 
 */

public class GenerateQuestionScreens {
	public static ArrayList<QuizScreenLayout> generate(ArrayList<DiffScreenRowLayout> rows, MainStage main) throws IOException {

		ArrayList<QuizScreenLayout> questionScreens = new ArrayList<QuizScreenLayout>();

		for (DiffScreenRowLayout row : rows) {
			if (row.checkIfWebRow()) {
				//addWebQuestions(row,questionScreens,main);
			} else {
				
				addQuestions(row, questionScreens, main);
			}
		}

		return questionScreens;

	}
/*
	// add web questions
	private static void addWebQuestions(DiffScreenRowLayout row, ArrayList<QuizScreenLayout> questionScreens,
			MainStage main) throws IOException {
		boolean[] set = row.getDifficultySet();
		
		if (set[0]) {
			ArrayList<Question> questions = Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/graph-shortest-paths-gq/");
			ArrayList<QuizScreenLayout> graphScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(graphScreens);
		}
		
		if (set[1]) {
			ArrayList<Question> questions = Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/analysis-of-algorithms-gq/");
			ArrayList<QuizScreenLayout> algScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(algScreens);
		}
		
		if (set[2]) {
			ArrayList<Question> questions = Scrapper.getQuestions("https://www.geeksforgeeks.org/algorithms-gq/searching-and-sorting-gq/");
			ArrayList<QuizScreenLayout> sortingScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(sortingScreens);
		}
		
	} */

	private static void addQuestions(DiffScreenRowLayout row, ArrayList<QuizScreenLayout> questionScreens,
			MainStage main) {
		ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory());
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
