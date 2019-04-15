package main;

import java.util.ArrayList;

import JSON.JSONOperations;
import Layouts.DiffScreenRowLayout;
import Layouts.QuizScreenLayout;

/*
 * This class will be in charge of generating questions
 * and operations pertaining to questions.
 * 
 * It is a static class.
 * 
 */

public class GenerateQuestionScreens {
	public static ArrayList<QuizScreenLayout> generate(ArrayList<DiffScreenRowLayout> rows, MainStage main) {

		ArrayList<QuizScreenLayout> questionScreens = new ArrayList<QuizScreenLayout>();

		for (DiffScreenRowLayout row : rows) {
			addQuestions(row, questionScreens, main);
		}

		return questionScreens;

	}

	private static void addQuestions(DiffScreenRowLayout row, ArrayList<QuizScreenLayout> questionScreens,
			MainStage main) {
		boolean[] set = row.getDifficultySet();
		// add easy questions
		if (set[0]) {
			ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory(), "easy");
			ArrayList<QuizScreenLayout> easyScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(easyScreens);
		}

		// add medium questions
		if (set[1]) {
			ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory(), "medium");
			ArrayList<QuizScreenLayout> medScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(medScreens);
		}
		// add hard questions
		if (set[2]) {
			ArrayList<Question> questions = JSONOperations.getQuestions(row.getCategory(), "hard");
			ArrayList<QuizScreenLayout> hardScreens = convertQuestionToScreens(questions, main);
			questionScreens.addAll(hardScreens);
		}
	}

	private static ArrayList<QuizScreenLayout> convertQuestionToScreens(ArrayList<Question> questions, MainStage main) {
		ArrayList<QuizScreenLayout> screens = new ArrayList<QuizScreenLayout>();

		for (Question q : questions) {
			screens.add(new QuizScreenLayout(q, main));
		}
		return screens;
	}
}
